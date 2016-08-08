


package pakkaus;

import java.io.File;
import java.io.FileInputStream;

/**
 * Tiedosto-luokka hoitaa itse tiedoston käsittelyn.
 * Siltä voi kysyä käsiteltävän tiedoston seuraavan tavun.
 * Poikkeusten käsittely on tarkoitus korjata siistimmäksi myöhemmin.
 */
public class Tiedosto {
    private FileInputStream tiedosto;
    
    /**
     *  @param polku Polku tiedostolle, jota halutaan lukea.
     */
    public Tiedosto(String polku){
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
    
    public int lue(){
        int ret;
        try{
            ret = tiedosto.read();
        } catch (Exception e){
            System.out.println("Virhe: "+e);
            return -1;
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
            
        }
    }
}