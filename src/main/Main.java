
package main;

import static vakiot.Vakiot.TIEDOSTOPOLKU;

import java.io.IOException;

import lzw.Pakkaaja;
import lzw.Avaaja;
import tiedosto.Tiedosto;
import tietorakenteet.Sanakirja;

public class Main {

    /**
     *  Pakkaa ja avaa tiedoston ja ajastaa algoritmin.
     */
    public static void main(String[] args){
        String polku = TIEDOSTOPOLKU;
        if(args.length > 0 && !args[0].contains("$")){
            polku = args[0];
        }
        try {
            pakkaa(polku);
            avaa(polku);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     *  Pakkaa polussa olevan tiedoston polkuun polku+".tt".
     */
    public static void pakkaa(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        Sanakirja sk = new Sanakirja();
        Pakkaaja p = new Pakkaaja(t, sk);
        p.pakkaa();
    }
    
    /**
     *  Avaa polussa olevan tiedoston polkuun polku+"t".
     */
    public static void avaa(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku+".tt");
        Sanakirja sk = new Sanakirja();
        Avaaja a = new Avaaja(t, sk);
        a.avaa();
    }
}