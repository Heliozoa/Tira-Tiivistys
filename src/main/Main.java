
package main;

import static util.Vakiot.DEBUG;

import pakkaus.Pakkaaja;
import pakkaus.PakkausSanakirja;
import avaus.Avaaja;
import avaus.AvausSanakirja;
import tiedosto.Tiedosto;
import java.io.IOException;

public class Main {
    static String alku;
    
    public static void main(String[] args){
        if(args.length == 0 || args[0].contains("$")){
            alku = "./testitiedosto";
        }else{
            alku = args[0];
        }
        try {
            pakkaus();
            avaus();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void pakkaus() throws IOException{
        String polku = alku;
        Tiedosto t = new Tiedosto(polku);
        PakkausSanakirja s = new PakkausSanakirja();
        Pakkaaja p = new Pakkaaja(t, s);
        p.pakkaa();
    }
    
    public static void avaus() throws IOException{
        String polku = alku+".tt";
        Tiedosto t = new Tiedosto(polku);
        AvausSanakirja s = new AvausSanakirja();
        Avaaja a = new Avaaja(t, s);
        a.avaa();
    }
    
    public static void dump(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump();
    }
    
    public static void dump2(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump2();
    }
}