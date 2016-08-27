
package avaus;

import tietorakenteet.Tavujono;
import tietorakenteet.Sanakirja;
import tiedosto.Tiedosto;
import tiedosto.Tiedostokasittelija;

import java.io.IOException;


/**
 *  Avaa tiedoston, joka on pakattu Pakkaaja-luokalla.
 */
public class Avaaja {
    private Tiedosto t;
    private Sanakirja sk;
    private byte edellinen;
    
    private Tavujono puskuri;
    
    /**
     *  @params tiedosto    Pakattu tiedosto joka halutaan avata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon avaamiseen.
     */
    public Avaaja (Tiedosto tiedosto, Sanakirja sanakirja){
        t = tiedosto;
        sk = sanakirja;
        puskuri = new Tavujono();
    }
    
    
    /**
     *  Avaa koodatun tiedoston muodostamalla sanakirjan samalla tavalla kuin koodatessa.
     */
    public void avaa() throws IOException{
        Tavujono tavut = new Tavujono();
        lueTavutJonoon(tavut);
        Tiedostokasittelija.kirjoita(tavut.taulukoksi(), t.polku()+"t");
    }
    
    /**
     *  Lukee kaikki koodit tiedostosta ja lisää ne tavujonoon.
     */
    private void lueTavutJonoon(Tavujono tavujono) throws IOException{
        Tavujono jono = new Tavujono();
        jono.lisaa(lue());
        byte seuraava;
        
        while(!t.loppu()){
            seuraava = lue();
            if(sk.sisaltaa(jono, seuraava)){
                jono.lisaa(seuraava);
            }else{
                sk.lisaa(jono, seuraava);
                while(!jono.tyhja()){
                    tavujono.lisaa(jono.poista());
                }
                jono.lisaa(seuraava);
            }
        }
        while(!jono.tyhja()){
            tavujono.lisaa(jono.poista());
        }
    }
    
    private byte lue() throws IOException{
        if(puskuri.tyhja()){
            int eka = t.lue();
            eka *= 256;
            int toka = t.lue();
            int koodi = eka+toka;
            if(!sk.sisaltaa(koodi)){
                puskuri.lisaa(edellinen);
                puskuri.lisaa(edellinen);
            }else{
                puskuri = sk.hae(koodi);
            }
        }
        byte b = puskuri.poista();
        edellinen = b;
        return b;
    }
}