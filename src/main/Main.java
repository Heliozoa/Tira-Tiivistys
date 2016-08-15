
package main;

import pakkaus.Pakkaaja;
import pakkaus.PakkausSanakirja;
import avaus.Avaaja;
import avaus.AvausSanakirja;
import tiedosto.Tiedosto;

public class Main {
    
    public static void main(String[] args){
        pakkaus();
        avaus();
    }
    
    public static void pakkaus(){
        String polku = "/home/sasami-san/Dev/github/Tira-Tiivistys/testitiedosto";
        Tiedosto t = new Tiedosto(polku);
        PakkausSanakirja s = new PakkausSanakirja();
        Pakkaaja p = new Pakkaaja(t, s);
        p.pakkaa();
    }
    
    public static void avaus(){
        String polku = "/home/sasami-san/Dev/github/Tira-Tiivistys/testitiedosto.tt";
        Tiedosto t = new Tiedosto(polku);
        AvausSanakirja s = new AvausSanakirja();
        Avaaja a = new Avaaja(t, s);
        //t.dump();
        //a.avaa();
    }
}