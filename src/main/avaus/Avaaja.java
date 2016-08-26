
package avaus;

import tietorakenteet.Tavujono;
import avaus.AvausSanakirja;
import tiedosto.Tiedosto;
import tiedosto.Tiedostokasittelija;
import util.Tavukasittelija;

import java.io.IOException;


/**
 *  Avaa tiedoston, joka on pakattu Pakkaaja-luokalla.
 */
public class Avaaja {
    private Tiedosto t;
    private AvausSanakirja s;
    
    private Tavujono puskuri;
    
    /**
     *  @params tiedosto    Pakattu tiedosto joka halutaan avata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon avaamiseen.
     */
    public Avaaja (Tiedosto tiedosto, AvausSanakirja sanakirja){
        t = tiedosto;
        s = sanakirja;
        
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
        String edellinen = lueTavu();
        String jono = "";
        
        while(!t.loppu()){
            jono = lueTavu();
            if(jono.isEmpty()) {
                jono = edellinen.substring(edellinen.length()-3,edellinen.length());
                s.lisaa(edellinen+jono);
                merkkijonoTavujonoon(jono, tavujono);
            }
            if(s.sisaltaaJonon(edellinen+jono)){
                edellinen += jono;
            }else{
                s.lisaa(edellinen+jono);
                merkkijonoTavujonoon(edellinen, tavujono);
                edellinen = jono;
            }
        }
        merkkijonoTavujonoon(edellinen, tavujono);
    }
    
    /**
     *  Pätkii merkkijonon 3-pituisiksi osiksi ja lisää niitä vastaavat tavut tavujonoon.
     */
    private void merkkijonoTavujonoon(String s, Tavujono t){
        for(int i = 0; i < s.length(); i += 3){
            byte b = Tavukasittelija.stringTavuksi(s.substring(i,i+3));
            t.lisaa(b);
        }
    }
    
    /**
     *  Lukee kahden tavun pituisen koodin tiedostosta ja hakee sitä vastaavan tavujonon.
     */
    private String lueTavu() throws IOException{
        String r;
        if(puskuri.tyhja()){
            int eka = t.lue();
            eka *= 256;
            int toka = t.lue();
            if(s.hae(eka+toka) == null){
                return "";
            }
            merkkijonoTavujonoon(s.hae(eka+toka), puskuri);
        }
        r = Tavukasittelija.kaannaString(puskuri.poista());
        return r;
    }
}