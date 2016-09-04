
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
    
    private int bufferi;
    private int bufferiPituus;
    
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
        bufferi = 0;
        bufferiPituus = 0;
    }
    
    /**
     *  Lukee tiedostosta seuraavan tavun.
     *
     *  @return Tiedoston seuraava tavu.
     */
    public int lue() throws IOException {
        return lue(8);
    }
    
    /**
     *  Lukee tiedostosta k bittiä.
     *  Pitää muistissa bufferia, jolloin esim. 5 bittiä luettaessa 'ylimääräiset' 3 bittiä jää sinne muistiin, eikä katoa.
     *
     *  @param  k   Luettavien bittien lukumäärä.
     *  @return Tiedoston seuraavat k bittiä
     */
    public int lue(int k) throws IOException {
        int tulos = 0x0;
        int bufferinPaa = 0x1 << (bufferiPituus - 1);
        for(int i = 0; i < k; i++){
            if(bufferiPituus == 0){
                bufferi = tiedosto.read();
                bufferiPituus = 8;
                bufferinPaa = 0x80;
            }
            int bufferinKorkein = bufferi & bufferinPaa;
            int bufferiBitti = bufferinKorkein >> (bufferiPituus - 1);
            tulos = tulos << 1;
            tulos = tulos | bufferiBitti;
            bufferinPaa = bufferinPaa >> 1;
            bufferiPituus--;
        }
        return tulos;
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
     *  @see    #kirjoita(Tavujono, String)
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