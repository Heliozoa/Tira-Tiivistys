
package pakkaus;

import java.io.File;


public class Pakkaaja {
    private FileInputStream stream;
    
    public Pakkaaja(String polku) {
        tiedosto = new File(polku);
        if(tiedosto.isFile()){
            stream = new FileInputStreaM(tiedosto);
        }
    }
    
    public boolean luettava(){
        return stream == null;
    }
    
    public void print(){
        System.out.println("mem");
    }
}