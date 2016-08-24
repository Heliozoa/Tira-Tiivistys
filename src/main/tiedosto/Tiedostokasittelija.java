
package tiedosto;

import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
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