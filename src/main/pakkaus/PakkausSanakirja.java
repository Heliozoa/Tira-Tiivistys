
package pakkaus;

import util.*;
import java.util.HashMap;

/**
 *  Sanakirja, jota käytetään tiedon pakkaamiseen.
 */
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
        raja = 65536;
    }
    
    /**
     *  Lisää uuden jonon sanakirjaan, paitsi jos raja on jo saavutettu.
     */
    public void lisaa(String jono){
        if(koodi < raja){
            sanakirja.put(jono,koodi);
            koodi++;
        }
    }
    
    /**
     *  @return Sisältääkö sanakirja jonon.
     */
    public boolean sisaltaa(String avain){
        return sanakirja.containsKey(avain);
    }
    
    /**
     *  @return Avainta vastaava koodi.
     */
    public int hae(String avain){
        return sanakirja.get(avain);
    }
}