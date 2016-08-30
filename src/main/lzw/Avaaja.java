
package lzw;

import tietorakenteet.Tavujono;
import tietorakenteet.Sanakirja;
import tiedosto.Tiedosto;

import java.io.IOException;


/**
 *  Avaa tiedoston, joka on pakattu Pakkaaja-luokalla.
 */
public class Avaaja {
    private Tiedosto t;
    private Sanakirja sk;
    
    /**
     *  @params tiedosto    Pakattu tiedosto joka halutaan avata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon avaamiseen.
     */
    public Avaaja (Tiedosto tiedosto, Sanakirja sanakirja){
        t = tiedosto;
        sk = sanakirja;
    }
    
    
    /**
     *  Avaa koodatun tiedoston muodostamalla sanakirjan samalla tavalla kuin koodatessa. Kirjoittaa tavut tiedostoon.
     */
    public void avaa() throws IOException{
        if(t.loppu()) return;
        Tavujono tavut = new Tavujono();
        lueTavutJonoon(tavut);
        Tiedosto.kirjoita(tavut, t.polku()+"t");
    }
    
    /**
     *  Lukee kaikki koodit tiedostosta, muuttaa ne oikeiksi tavujonoiksi ja lisää ne tavut-jonoon.
     *
     *  @param  tavut   jono, johon tavut lisätään
     */
    private void lueTavutJonoon(Tavujono tavut) throws IOException{
        Tavujono edellinen;
        int koodi = lueKoodi();
        edellinen = sk.hae(koodi);
        Tavujono jono = edellinen.clone();
        while(!jono.tyhja()){
            tavut.lisaa(jono.poista());
        }
        
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
            while(!jono.tyhja()){
                tavut.lisaa(jono.poista());
            }
        }
    }
    
    /**
     *  Lukee tiedostosta kaksi tavua ja muuttaa ne koodiksi.
     */
    private int lueKoodi() throws IOException{
        int eka = t.lue();
        eka *= 256;
        int toka = t.lue();
        return eka+toka;
    }
}