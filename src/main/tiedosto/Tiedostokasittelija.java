
package tiedosto;

import java.io.FileOutputStream;
import java.io.IOException;

public class Tiedostokasittelija {
    
    /**
     *  Kirjoittaa byte-taulukossa olevat tavut.
     */
    public static void kirjoita(byte[] tavut, String polku) throws IOException{
        FileOutputStream fos = new FileOutputStream(polku);
        try {
            fos.write(tavut);
        } finally {
            fos.close();
        } 
    }
}