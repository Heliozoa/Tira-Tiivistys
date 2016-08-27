
package tiedosto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *  Hoitaa itse tiedoston käsittelyn.
 */
public class Tiedosto {
    private FileInputStream tiedosto;
    private String polku;
    
    private int kohta;
    
    /**
     *  @param polku    Polku, jossa käsiteltävä tiedosto on.
     */
    public Tiedosto(String polku) throws IOException{
        this.polku = polku;
        File t = new File(polku);
        if(!t.isFile() || !t.canRead()){
            throw new FileNotFoundException("Lukukelpoista tiedostoa ei löytynyt kohteesta "+polku);
        }
        tiedosto = new FileInputStream(t);
        
        kohta = 0;
    }
    
    /**
     *  Lukee tiedostosta seuraavan tavun.
     */
    public int lue() throws IOException {
        int tavu = tiedosto.read();
        
        kohta++;
        
        return tavu;
    }
    
    /**
     *  @return Onko tiedosto luettu loppuun.
     */
    public boolean loppu() throws IOException{
        return tiedosto.available() == 0;
    }
    
    /**
     *  Sulkee tiedoston.
     */
    public void sulje() throws IOException{
        tiedosto.close();
    }
    
    /**
     *  Tulostaa tiedoston tavu kerrallaan.
     */
    public void dump() throws IOException {
        while(!loppu()){
            System.out.println(lue());
        }
    }
    
    /**
     *  Tulostaa tiedoston kaksi tavua kerrallaan.
     */
    public void dump2() throws IOException {
        while(!loppu()){
            int eka = lue();
            eka *= 256;
            System.out.println(eka+lue());
        }
    }
    
    public String polku(){
        return polku;
    }
    
    /**
     *  Vain testejä varten
     */
    public int kohta(){
        return kohta;
    }
}