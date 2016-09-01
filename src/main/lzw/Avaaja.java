
package lzw;

import tiedosto.Tiedosto;
import tietorakenteet.Sanakirja;
import tietorakenteet.Tavujono;

import java.io.IOException;

/**
 *  Avaa tiedoston, joka on pakattu Pakkaaja-luokalla.
 *
 *  Toimintaperiaate lyhyesti:
 *      Avaajalla on aluksi samanlainen sanakirja kuin pakkaajalla, joka sisältää kaikki yhden pituiset tavujonot.
 *      Avaaja lukee tiedostosta kaksi tavua kerralla, sillä pakkaaja pakkaa tiedon kahden tavun pituisiin koodeihin.
 *      Tavut käännetään koodiksi, ja avaaja hakee sanakirjalta koodia vastaavan tavujonon.
 *      Kun jono on haettu, avaaja lisää sanakirjaan edellisen haetun tavujonon johon on lisätty nykyisen tavujonon ensimmäinen tavu.
 *      Syy tähän on seuraavanlainen: Kun pakkaaja on kirjoittanut koodin, se on löytänyt uuden tavujonon,
 *      lisännyt sen sanakirjaan ja kirjoittanut edellisen jonon koodin.
 *          Uusi jono, jonka pakkaaja löysi on edellinen tavujono + uusi tavu, joka on siis
 *          edellistä seuraavan tavujonon ensimmäinen tavu.
 *          Näin avaaja rakentaa sanakirjan samoin kuin pakkaaja.
 *      Tähän sisältyy yksi poikkeus: joskus avaajalle tulee vastaan koodi jota ei vielä löydy sen omasta sanakirjasta.
 *      Tällainen tapaus syntyy jos ja vain jos pakkaaja on törmännyt heti tavujonoon, jonka se on juuri lisännyt sanakirjaan.
 *      Siis vaikka tavujono puuttuu avaajan sanakirjasta, se on helppo selvittää: puuttuva jono on edellinen tavujono + edellisen tavujonon ensimmäinen tavu.
 */
public class Avaaja {
    private Tiedosto t;
    private Sanakirja sk;
    
    /**
     *  @param tiedosto    Pakattu tiedosto joka halutaan avata.
     *  @param sanakirja   Sanakirja jota käytetään tiedon avaamiseen.
     */
    public Avaaja (Tiedosto tiedosto, Sanakirja sanakirja){
        t = tiedosto;
        sk = sanakirja;
    }
    
    /**
     *  Avaa koodatun tiedoston muodostamalla sanakirjan samalla tavalla kuin koodatessa. Kirjoittaa tavut tiedostoon.
     *
     *  @param  kohde   Polku tiedostoon, jonne avattu tieto kirjoitetaan.
     */
    public void avaa(String kohde) throws IOException{
        try {
            if(t.loppu()) return;
            Tavujono tavut = new Tavujono();
            avaaKooditJonoon(tavut);
            Tiedosto.kirjoita(tavut, kohde);
        } finally {
            t.sulje();
        }
    }
    
    /**
     *  Lukee kaikki koodit tiedostosta, muuttaa ne oikeiksi tavujonoiksi ja lisää ne tavut-jonoon.
     *
     *  @param  tavut   Jono, johon koodatut tavut lisätään.
     */
    private void avaaKooditJonoon(Tavujono tavut) throws IOException{
        int koodi = lueKoodi();
        Tavujono edellinen = sk.hae(koodi);
        Tavujono jono = edellinen.clone();
            jono.tyhjennaJonoon(tavut);
        
        while(!t.loppu()){
            koodi = lueKoodi();
            if(!sk.sisaltaa(koodi)){
                sk.lisaa(edellinen, edellinen.eka());
                jono = sk.hae(koodi);
            }else{
                jono = sk.hae(koodi);
                sk.lisaa(edellinen, jono.eka());
            }
            edellinen = jono.clone();
            jono.tyhjennaJonoon(tavut);
        }
    }
    
    /**
     *  Lukee tiedostosta kaksi tavua ja muuttaa ne koodiksi.
     *
     *  @return Tavuista muodostettu koodi.
     */
    private int lueKoodi() throws IOException{
        int eka = t.lue();
        eka *= 256;
        int toka = t.lue();
        return eka+toka;
    }
}