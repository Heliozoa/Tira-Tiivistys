
package pakkaus;

import tiedosto.Tiedosto;
import tiedosto.Tiedostokasittelija;
import pakkaus.PakkausSanakirja;
import util.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 *  Hoitaa tiedon pakkaamisen. Vastaanottaa Tiedosto- ja PakkausSanakirja-luokkien oliot joiden avulla pakkaus hoidetaan.
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
 */
public class Pakkaaja {
    private Tiedosto t;
    private PakkausSanakirja s;
    
    /**
     *  @params tiedosto    Tiedostosta saadaan tieto joka halutaan pakata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon koodaamiseen.
     */
    public Pakkaaja(Tiedosto tiedosto, PakkausSanakirja sanakirja) {
        t = tiedosto;
        s = sanakirja;
    }
    
    /**
     *  Koodaa Tiedoston t sisällön byte-tyypin taulukkoon, jonka se antaa tiedostolle kirjoitettavaksi muistiin.
     */
    public void pakkaa() throws IOException{
        List<Integer> tavut = new ArrayList<>();
        koodaaListaan(tavut);
        int[] taulu = tavutTauluksi(tavut);
        byte[] tavutaulu = Taulukot.kaannaTavuiksi(taulu);
        Tiedostokasittelija.kirjoita(tavutaulu, t.polku()+".tt");
    }
    
    
    /**
     *  Koodaa Tiedoston t sisällön listaan ja muodostaa samalla sanakirjan.
     *  
     *  @param  tavut   Lista johon tavut lisätään.
     */
    private void koodaaListaan(List<Integer> tavut) throws IOException{
        String edellinen = lue();
        String jono = "";
        
        while(!t.loppu()){
            jono = lue();
            if(s.sisaltaa(edellinen+jono)){
                edellinen += jono;
            } else {
                s.lisaa(edellinen+jono);
                tavut.add(s.hae(edellinen));
                edellinen = jono;
            }
        }
        tavut.add(s.hae(jono));
    }
    
    /**
     *  Muuttaa listan joka sisältää pakatut tavut taulukoksi.
     *
     *  @param  tavut   Lista joka sisältää tiedoston tavut.
     */
    private int[] tavutTauluksi(List<Integer> tavut){
        int[] taulu = new int[tavut.size()*2];
        int n = 0;
        for(Integer i : tavut){
            int k = i.intValue();
            taulu[n+1] = (k & 0xFF);
            taulu[n] = ((k >> 8) & 0xFF);
            n += 2;
        }
        return taulu;
    }
    
    /**
     *  Lukee tavun Tiedostosta t ja muuntaa sen String-tyyppiseksi lisäten siihen samalla etunollat. 
     */
    private String lue() throws IOException{
        return Muotoilu.etunollat(t.lue());
    }
}