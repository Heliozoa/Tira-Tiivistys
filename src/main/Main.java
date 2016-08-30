
package main;

import static vakiot.Vakiot.TIEDOSTOPOLKU;
import tietorakenteet.Sanakirja;
import lzw.Pakkaaja;
import lzw.Avaaja;
import tiedosto.Tiedosto;
import java.io.IOException;

public class Main {
    static String polku;
    
    /**
     *  Pakkaa ja avaa tiedoston ja ajastaa algoritmin.
     */
    public static void main(String[] args){
        //testi();
        
        long aika = System.nanoTime();
        
        if(args.length == 0 || args[0].contains("$")){
            polku = TIEDOSTOPOLKU;
        }else{
            polku = args[0];
        }
        try {
            pakkaus();
            System.out.println("Pakkaus: "+ ((System.nanoTime() - aika) / 1000000) +"ms");
            long valiaika = System.nanoTime();
            avaus();
            System.out.println("Avaus: "+ ((System.nanoTime() - valiaika) / 1000000) +"ms");
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Yhteensä: "+ ((System.nanoTime() - aika) / 1000000) +"ms");
    }
    
    /**
     *  Pakkaa tiedoston.
     */
    public static void pakkaus() throws IOException{
        Tiedosto t = new Tiedosto(polku);
        Sanakirja sk = new Sanakirja();
        Pakkaaja p = new Pakkaaja(t, sk);
        p.pakkaa();
    }
    
    /**
     *  Avaa tiedoston.
     */
    public static void avaus() throws IOException{
        String pakattu = polku+".tt";
        Tiedosto t = new Tiedosto(pakattu);
        Sanakirja sk = new Sanakirja();
        Avaaja a = new Avaaja(t, sk);
        a.avaa();
    }
    
    /**
     *  Testaamista varten.
     */
    public static void testi(){
        int i = -1;
        byte b = (byte) ((i >> 8) & 0xFF);
        byte y = (byte) (i & 0xFF);
        System.out.println(b);
        System.out.println(y);
        System.exit(0);
    }
    
    /**
     *  Tulostaa polussa olevan tiedoston tavu kerrallaan.
     */
    public static void dump(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump();
    }
    
    /**
     *  Tulostaa polussa olevan tiedoston kaksi tavua kerrallaan.
     */
    public static void dump2(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump2();
    }
}