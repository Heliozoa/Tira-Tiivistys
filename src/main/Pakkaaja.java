
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
    
    public void lue(){
        while(!t.loppu()){
            int tavu = t.lue();
            System.out.println(tavu);
        }
        
        t.sulje();
    }
}