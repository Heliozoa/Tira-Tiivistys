
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
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(polku), "UTF8"));

        try {
            out.write(new String(tavut, "UTF-8"));
        } finally {
            out.close();
        } 
    }
}