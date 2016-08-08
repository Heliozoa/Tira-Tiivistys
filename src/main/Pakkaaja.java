
package pakkaus;

import pakkaus.Tiedosto;
import pakkaus.Sanakirja;

/**
    Pakkaaja pakkaa tiedoston Tiedosto- ja Sanakirja-luokkien avulla.
    Ei tee vielä mitään.
*/
public class Pakkaaja {
    private Tiedosto t;
    private Sanakirja s;
    
    public Pakkaaja(Tiedosto tiedosto, Sanakirja sanakirja) {
        t = tiedosto;
        s = sanakirja;
    }
    
    /**
     *  pakkaa-metodi muuntaa tiedoston pienempään muotoon.
     *  WIP
     */
    public void pakkaa(){
        int nykyinenJono;
        int seuraava;
        while(!t.loppu()){
            seuraava = t.lue();
            if(s.sisaltaaAvaimen())
        }
    }
    
    public void lue(){
        while(!t.loppu()){
            int tavu = t.lue();
            System.out.println(tavu);
        }
        
        t.sulje();
    }
}