
package pakkaus;

import util.*;
import java.util.HashMap;

public class PakkausSanakirja {
    private HashMap<String,Integer> sanakirja;
    private int koodi;
    private int raja;
    
    public PakkausSanakirja() {
        sanakirja = new HashMap<>();
        for(int i = 0; i <= 255; i++) {
            sanakirja.put(Muotoilu.etunollat(i),i);
        }
        
        koodi = 256;
        raja = 4096;
    }
    
    public void lisaa(String jono){
        if(koodi < raja){
            sanakirja.put(jono,koodi);
            koodi++;
        }
    }
    
    public boolean sisaltaa(String avain){
        return sanakirja.containsKey(avain);
    }
    
    public int hae(String avain){
        return sanakirja.get(avain);
    }
}