
package main;

import pakkaus.Pakkaaja;
import pakkaus.PakkausSanakirja;
import avaus.Avaaja;
import avaus.AvausSanakirja;
import tiedosto.Tiedosto;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args){
        try {
            pakkaus();
            avaus();
            dump("/home/authority/Dev/github/Tira-Tiivistys/minitesti.tt");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void pakkaus() throws IOException{
        String polku = "/home/authority/Dev/github/Tira-Tiivistys/minitesti";
        Tiedosto t = new Tiedosto(polku);
        PakkausSanakirja s = new PakkausSanakirja();
        Pakkaaja p = new Pakkaaja(t, s);
        p.pakkaa();
    }
    
    public static void avaus() throws IOException{
        String polku = "/home/authority/Dev/github/Tira-Tiivistys/minitesti.tt";
        Tiedosto t = new Tiedosto(polku);
        AvausSanakirja s = new AvausSanakirja();
        Avaaja a = new Avaaja(t, s);
        a.avaa();
    }
    
    public static void dump(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump();
    }
}