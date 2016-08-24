
package util;

import java.io.FileOutputStream;

/**
 *  Luokka merkkijonojen käsittelyyn.
 */
public class Muotoilu {
    
    /**
     *  Muuntaa luvun merkkijonoksi, ja lisää etunollat niin, että merkkijono on aina vähintään 3-pituinen.
     */
    public static String etunollat(int luku){
        String jono = Integer.toString(luku);
        if(luku < 10) jono = "0"+jono;
        if(luku < 100) jono = "0"+jono;
        return jono;
    }
    
    /**
     *  Yhdistää kaksi lukua peräkkäin merkkijonoksi. Muuntaa luvut ensin etunolla-muotoon.
     *
     *  @see etunollat(int)
     */
    public static String yhdista(int eka, int toka){
        String vasen = etunollat(eka);
        String oikea = etunollat(toka);
        return vasen+oikea;
    }
    
}