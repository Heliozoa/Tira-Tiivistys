
package lzw;

import tiedosto.Tiedosto;
import tietorakenteet.Bittijono;
import tietorakenteet.Sanakirja;
import tietorakenteet.Tavujono;
import util.Tavukasittelija;

import java.io.IOException;

/**
 *  Hoitaa tiedon pakkaamisen. Vastaanottaa Tiedosto- ja Sanakirja-luokkien oliot joiden avulla pakkaus hoidetaan.
 *  
 *  Toimintaperiaate lyhyesti:
 *      Algoritmilla on aluksi sanakirja joka sisältää kaikki mahdolliset 256 tavua.
 *      Algoritmi tarkastelee aina tavujonoa kerrallaan, joka on vähintään 2 tavua pitkä.
 *      Jos tavujono löytyy jo sanakirjasta, algoritmi ottaa jonoon mukaan seuraavan tavun.
 *      Jos tavujonoa ei löydy sanakirjasta, se lisätään sanakirjaan ja algoritmi kirjoittaa muistiin sanakirjan koodin,
 *      joka vastaa tavujonoa ilman uusinta tavua.
 *      Koodit tallennetaan kahden tavun pituisina. Tällöin pakattu tiedosto koostuu tavupareista, joista jokainen on koodi jota vastaa jokin tavujono.
 *      Näin lopputuloksena on tiedosto, jossa esim 2 tavua pitkä koodi 897 voi merkitä 30 tavua pitkää tavujonoa.
 *      Avausalgoritmi osaa muodostaa samalla periaatteella sanakirjan lennosta avatessa, siis sanakirjaa ei tarvitse mitenkään
 *      tallentaa muistiin ja tuloksena on pakattu tiedosto jossa on vähemmän tavuja.
 *
 *      HUOM:
 *          Koodi 256 on varattu kertomaan Avaajalle, että koodin pituutta on kasvatettu yhdellä.
 *          Koodi 257 on varattu EOF-koodiksi, johon Avaaja lopettaa.
 */
public class Pakkaaja {
    private Tiedosto t;
    private Sanakirja sk;
    private int koodinPituus;
    
    /**
     *  @param tiedosto    Tiedostosta saadaan tieto joka halutaan pakata.
     *  @param sanakirja   Sanakirja jota käytetään tiedon koodaamiseen.
     */
    public Pakkaaja(Tiedosto tiedosto, Sanakirja sanakirja) {
        t = tiedosto;
        sk = sanakirja;
        koodinPituus = 9;
    }
    
    /**
     *  Koodaa Tiedoston t sisällön byte-tyypin taulukkoon, jonka se antaa tiedostolle kirjoitettavaksi muistiin.
     *
     *  @param  kohde   Polku tiedostoon, jonne pakattu tieto kirjoitetaan.
     */
    public void pakkaa(String kohde) throws IOException{
        try{
            if(t.loppu()) return;
            Tavujono tavut = new Tavujono();
            koodaaBititJonoon(tavut);
            Tiedosto.kirjoita(tavut, kohde);
        } finally {
            t.sulje();
        }
    }
    
    /**
     *  Pakkauslogiikka.
     *  Logiikka tekee samanaikaisesti kahta asiaa:
     *      Pitää yllä sanakirjaa ja seuraa jonojen toistoa tavujonon avulla.
     *      Koodaa sanakirjan avulla bittijonoon pakattua tietoa.
     *
     *      @see Pakkaaja
     *
     *      @param  tavut   Tavujono, johon koodatut tavut lopuksi lisätään.
     */
    private void koodaaBititJonoon(Tavujono tavut) throws IOException{
        Tavujono jono = new Tavujono();
        Bittijono bitit = new Bittijono();
        jono.lisaa(lue());
        byte seuraava;
        
        boolean edellinenLoytyySanakirjasta = false;
        while(!t.loppu()){
            seuraava = lue();
            if(sk.sisaltaa(jono, seuraava)){
                jono.lisaa(seuraava);
                edellinenLoytyySanakirjasta = false;
            } else {
                long koodi = sk.hae(jono);
                
                lisaaBittijonoon(koodi, bitit);
                
                sk.lisaa(jono, seuraava);
                jono.tyhjenna();
                jono.lisaa(seuraava);
                edellinenLoytyySanakirjasta = true;
            }
        }
        
        //Tiedoston lopun käsittely riippuu tilanteesta, johon tiedoston luku yllä loppui.
        //Tilanteesta riippuen alla koodataan loputkin tavut.
        if(edellinenLoytyySanakirjasta){
            long koodi = sk.hae(jono);
            lisaaBittijonoon(koodi, bitit);
        } else {
            byte vika = jono.poistaLopusta();
            Tavujono temp = new Tavujono();
            temp.lisaa(vika);
            
            long koodi = sk.hae(jono);
            lisaaBittijonoon(koodi, bitit);
            
            koodi = sk.hae(temp);
            lisaaBittijonoon(koodi, bitit);
        }
        
        //EOF-merkki
        bitit.lisaa(257, koodinPituus);
        
        while(!bitit.tyhja()){
            tavut.lisaa(bitit.poistaTavu());
        }
    }
    
    /**
     *  Lisää koodin bittijonoon. Tarkistaa samalla, tarvitseeko koodin pituutta lisätä.
     *
     *  @param  koodi   Koodi, jota lisätään.
     *  @param  bitit    Bittijono, johon koodia lisätään.
     */
    private void lisaaBittijonoon(long koodi, Bittijono bitit){
            tarkistaKoodiPituus(koodi, bitit);
            bitit.lisaa(koodi, koodinPituus);
    }
    
    /**
     *  Tarkistaa, riittääkö nykyinen koodin pituus kirjoittamaan koodia.
     *  Jos ei, pituutta kasvatetaan tarpeen mukaan.
     *
     *  @param  koodi   Koodi, jota ollaan lisäämässä
     *  @param  bitit   Bittijono, johon koodia lisätään.
     */
    private void tarkistaKoodiPituus(long koodi, Bittijono bitit){
        int koodinKoko = Tavukasittelija.bittikoko(koodi);
        while(koodinKoko > koodinPituus){
            bitit.lisaa(256, koodinPituus);
            koodinPituus++;
        }
    }
    
    /*
     *  Lukee seuraavan tavun Tiedostosta t.
     *
     *  @return Tiedostosta luettu seuraava tavu.
     */
    private byte lue() throws IOException{
        return (byte) t.lue();
    }
}