
package main;

import pakkaus.Pakkaaja;

public class Main {
    
    public static void main(String[] args){
        String polku = "/home/sasami-san/ggpo-ng.ini";
        boolean pakataan = true;
        String kohde = "";
        
        Pakkaaja p = new Pakkaaja(polku);
        
        if(p.luettava()){
            p.print();
        }
        
    }
}