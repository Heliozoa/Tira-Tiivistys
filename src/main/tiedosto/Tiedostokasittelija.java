
package tiedosto;

import tietorakenteet.Tavujono;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tiedostokasittelija {
    
    /**
     *  Kirjoittaa Tavujonossa olevat tavut tiedostoon.
     *
     *  @params jono    Tavujono, jossa olevat tavut kirjoitetaan
     *  @params polku   Tiedostopolku, jonne tavut kirjoitetaan
     */
    public static void kirjoita(Tavujono jono, String polku) throws IOException{
        FileOutputStream fos = new FileOutputStream(polku);
        byte[] tavut = jono.taulukoksi();
        try {
            fos.write(tavut);
        } finally {
            fos.close();
        } 
    }
}