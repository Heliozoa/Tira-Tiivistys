
package util;

import java.io.FileOutputStream;

public class Muotoilu {
    
    public static String etunollat(int luku){
        String jono = Integer.toString(luku);
        if(luku < 10) jono = "0"+jono;
        if(luku < 100) jono = "0"+jono;
        return jono;
    }
    
    public static String yhdista(int eka, int toka){
        String vasen = etunollat(eka);
        String oikea = etunollat(toka);
        return vasen+oikea;
    }
    
}