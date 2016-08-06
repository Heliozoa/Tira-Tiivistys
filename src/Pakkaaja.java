
package pakkaus;

import pakkaus.Tiedosto;
import java.util.HashMap;

public class Pakkaaja {
    private Tiedosto t;
    private HashMap<Integer,Integer> sanakirja;
    
    public Pakkaaja(Tiedosto tiedosto) {
        t = tiedosto;
        sanakirja = new HashMap<>();
    }
    
    public void lue(){
        while(!t.loppu()){
            System.out.println(t.lue());
        }
        
        t.sulje();
    }
}