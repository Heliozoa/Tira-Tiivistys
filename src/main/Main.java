
package main;

import tietorakenteet.Sanakirja;
import pakkaus.Pakkaaja;
import avaus.Avaaja;
import avaus.AvausSanakirja;
import tiedosto.Tiedosto;
import java.io.IOException;

public class Main {
    static String alku;
    
    public static void main(String[] args){
        //testi();
        
        long aika = System.nanoTime();
        
        if(args.length == 0 || args[0].contains("$")){
            alku = "./etc/kalevala";
        }else{
            alku = args[0];
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
        String polku = alku;
        Tiedosto t = new Tiedosto(polku);
        Sanakirja sk = new Sanakirja();
        Pakkaaja p = new Pakkaaja(t, sk);
        p.pakkaa();
    }
    
    /**
     *  Avaa tiedoston.
     */
    public static void avaus() throws IOException{
        String polku = alku+".tt";
        Tiedosto t = new Tiedosto(polku);
        AvausSanakirja s = new AvausSanakirja();
        Avaaja a = new Avaaja(t, s);
        a.avaa();
    }
    
    /**
     *  Testaamista varten.
     */
    public static void testi(){
        Sanakirja sk = new Sanakirja();
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