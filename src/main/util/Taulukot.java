
package util;

import java.util.List;

/**
 *  Sisältää toiminnallisuutta taulukoiden käsittelyyn.
 */
public class Taulukot {
    
    /**
     *  Muuttaa Integer-tyyppisen listan int-taulukoksi.
     *
     *  @param lista    Lista, joka halutaan kääntää.
     *  @return         Palauttaa listan pituisen taulukon, joka sisältää sen alkiot oikeassa järjestyksessä.
     */
    public static int[] lukulistaTaulukkoon(List<Integer> lista){
        int[] taulukko = new int[lista.size()];
        
        for(int i = 0; i < taulukko.length; i++){
            taulukko[i] = lista.get(i);
        }
        
        return taulukko;
    }
    
    /**
     *  Kääntää int[]-tyyppisen taulukon byte[]-tyyppiseksi taulukoksi.
     */
    public static byte[] kaannaTavuiksi(int[] taulu){
        byte[] t = new byte[taulu.length];
        for(int i = 0; i < taulu.length; i++){
            t[i] = (byte) taulu[i];
        }
        return t;
    }
}