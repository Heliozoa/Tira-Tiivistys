
package util;

public class Tavukasittelija {
    
    /**
     *  Missä paikkaa on luvun korkein ykkösbitti, ts. kuinka monta bittiä luvun esittämiseen tarvitsee.
     */
    public static int bittikoko(long k){
        int kohta = 0;
        while(k != 0){
            kohta++;
            k = k >> 1;
        }
        return kohta;
    }
}