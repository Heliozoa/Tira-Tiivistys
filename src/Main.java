
package main;

import pakkaus.Pakkaaja;
import pakkaus.Tiedosto;

public class Main {
    
    public static void main(String[] args){
        String polku = "/home/authority/Dev/Tira-Tiivistys/testitiedosto";
        Tiedosto t = new Tiedosto(polku);
        Pakkaaja p = new Pakkaaja(t);
        
        p.lue();
    }
}