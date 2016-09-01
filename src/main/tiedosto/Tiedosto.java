
package tiedosto;

import static util.Asetukset.sallitaanYlikirjoitus;

import tietorakenteet.Tavujono;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

/**
 *  Hoitaa itse tiedoston käsittelyn.
 */
public class Tiedosto {
    private FileInputStream tiedosto;
    private String polku;
    
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
    }
    
    /**
     *  Lukee tiedostosta seuraavan tavun.
     *
     *  @return Tiedoston seuraava tavu.
     */
    public int lue() throws IOException {
        return tiedosto.read();
    }
    
    /**
     *  Tarkistaa, onko tiedosto luettu loppuun.
     *
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
    
    public String polku(){
        return polku;
    }
    
    /**
     *  Kirjoittaa Tavujonossa olevat tavut tiedostoon.
     *
     *  @param jono    Tavujono, jossa olevat tavut kirjoitetaan.
     *  @param polku   Tiedostopolku, jonne tavut kirjoitetaan.
     */
    public static void kirjoita(Tavujono jono, String polku) throws IOException{
        kirjoita(jono.taulukoksi(), polku);
    }
    
    /**
     *  @see    kirjoita(Tavujono, String)
     *  @param  tavut   Kirjoitettavat tavut.
     *  @param  polku   Polku, jonne tavut kirjoitetaan.
     */
    public static void kirjoita(byte[] tavut, String polku) throws IOException{
        if(new File(polku).exists() && !sallitaanYlikirjoitus){
            throw new FileAlreadyExistsException(polku);
        }
        FileOutputStream fos = new FileOutputStream(polku);
        try {
            fos.write(tavut);
        } finally {
            fos.close();
        } 
    }
}