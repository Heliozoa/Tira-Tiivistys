
package main;

import pakkaus.Pakkaaja;
import pakkaus.Tiedosto;
import pakkaus.Sanakirja;

public class Main {
    
    public static void main(String[] args){
        pakkaus();
    }
    
    public static void pakkaus(){
        String polku = "/home/sasami-san/Dev/git/github/Tira-Tiivistys/testitiedosto";
        Tiedosto t = new Tiedosto(polku);
        Sanakirja s = new Sanakirja(false);
        Pakkaaja p = new Pakkaaja(t, s);
        
        p.lue();
    }
}