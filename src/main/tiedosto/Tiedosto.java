
package tiedosto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 *  Hoitaa itse tiedoston käsittelyn.
 */
public class Tiedosto {
    private FileInputStream tiedosto;
    private String polku;
    
    /**
     *  @param polku    Polku, jossa tiedosto on.
     */
    public Tiedosto(String polku){
        this.polku = polku;
        File t = new File(polku);
        if(!t.isFile() || !t.canRead()){
            throw new IllegalArgumentException("Lukukelpoista tiedostoa ei löytynyt kohteesta "+polku);
        }
        try{
            tiedosto = new FileInputStream(t);
        } catch (Exception e) {
            System.out.println("Virhe: "+e);
        }
    }
    
    /**
     *  Lukee tiedostosta yhden tavun.
     */
    public int lue(){
        int ret;
        try{
            ret = tiedosto.read();
        } catch (Exception e){
            System.out.println("Virhe: "+e);
            ret = -1;
        }
        return ret;
    }
    
    public boolean loppu(){
        try{
            return tiedosto.available() == 0;
        } catch (Exception e) {
            return true;
        }
    }
    
    public void sulje(){
        try{
            tiedosto.close();
        } catch(Exception e){
            System.out.println("Virhe: "+e);
        }
    }
    
    /**
     *  Kirjoittaa byte-taulukossa olevat tavut.
     */
    public void kirjoita(byte[] bytearray) throws Exception{
        String kohde = polku;
        if(!kohde.contains(".tt")){
            kohde += ".tt";
        } else {
            kohde += "t";
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(kohde), "UTF8"));

        try {
            out.write(new String(bytearray, "UTF-8"));
        } catch (Exception e){
            System.out.println("error "+e);
        } finally {
            out.close();
        } 
    }
    
    public void dump() {
        while(!loppu()){
            System.out.println(lue());
        }
    }
}