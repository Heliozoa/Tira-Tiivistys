
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
    
    private Tavujono edellinen;
    private Tavujono puskuri;
    
    /**
     *  @params tiedosto    Pakattu tiedosto joka halutaan avata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon avaamiseen.
     */
    public Avaaja (Tiedosto tiedosto, Sanakirja sanakirja){
        t = tiedosto;
        sk = sanakirja;
        edellinen = new Tavujono();
        puskuri = new Tavujono();
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
                    tavut.lisaa(jono.poista());
                }
                jono.lisaa(seuraava);
            }
        }
        while(!jono.tyhja()){
            byte b = jono.poista();
            tavut.lisaa(b);
        }
    }
    
    /**
     *  Lukee tiedostosta yhden tavun.
     *  Pakatut tiedostot ovat kahden tavun pituisia koodeja, joista jokainen vastaa jotakin tavujonoa.
     *  Jos puskurista löytyy jotain, voidaan suoraan poistaa sieltä yksi tavu.
     *  Muuten luetaan kaksi tavua itse tiedostosta ja yhdistetään ne koodiksi jota vastaava tavujono haetaan sanakirjaksi. Tämä tavujono tallennetaan sitten puskuriin.
     *  Jos koodia vastaavaa tavujonoa ei löydy, tällöin on kyseessä erityistapaus jossa sama tavu toistuu useasti. Tämän takia pidetään muistissa edellistä tavua.
     *
     *  @return Seuraava tavu, joka halutaan kirjoittaa avattuun tiedostoon.
     */
    private byte lue() throws IOException{
        if(puskuri.tyhja()){
            int eka = t.lue();
            eka *= 256;
            int toka = t.lue();
            int koodi = eka+toka;
            if(!sk.sisaltaa(koodi)){
                byte vika = edellinen.poistaLopusta();
                while(!edellinen.tyhja()){
                    puskuri.lisaa(edellinen.poista());
                }
                puskuri.lisaa(vika);
                puskuri.lisaa(vika);
                edellinen = puskuri.clone();
            }else{
                puskuri = sk.hae(koodi);
                edellinen = puskuri.clone();
            }
        }
        byte b = puskuri.poista();
        return b;
    }
}