
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
    
    /**
     *  @param polku    Polku, jossa tiedosto on.
     */
    public Tiedosto(String polku) throws IOException{
        this.polku = polku;
        File t = new File(polku);
        if(!t.isFile() || !t.canRead()){
            throw new FileNotFoundException("Lukukelpoista tiedostoa ei löytynyt kohteesta "+polku);
        }
        tiedosto = new FileInputStream(t);
    }
    
    /**
     *  Lukee tiedostosta yhden tavun.
     */
    public int lue() throws IOException {
        int tavu = tiedosto.read();
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
    
    public String polku(){
        return polku;
    }
}