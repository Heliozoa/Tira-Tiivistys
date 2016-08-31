
package main;

import static vakiot.Vakiot.TIEDOSTOPOLKU;
import tietorakenteet.Sanakirja;
import lzw.Pakkaaja;
import lzw.Avaaja;
import tiedosto.Tiedosto;
import java.io.IOException;

public class Main {
    static String polku;
    //Ohjelma suoritetaan näin monta kertaa ja suoritusajoista lasketaan keskiarvo.
    static int suorituskertoja = 10;
    
    /**
     *  Pakkaa ja avaa tiedoston ja ajastaa algoritmin.
     */
    public static void main(String[] args){
        //testi();
        long pakkausAika = 0;
        long avausAika = 0;
        for(int i = 1; i <= suorituskertoja; i++){
            System.out.println("Suorituskerta "+i);
            long aika = System.nanoTime();
            
            if(args.length == 0 || args[0].contains("$")){
                polku = TIEDOSTOPOLKU;
            }else{
                polku = args[0];
            }
            try {
                pakkaus();
                pakkausAika += System.nanoTime() - aika;
                aika = System.nanoTime();
                avaus();
                avausAika += System.nanoTime() - aika;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        pakkausAika /= 1000000 * suorituskertoja;
        avausAika /= 1000000 * suorituskertoja;
        
        System.out.println("Suorituskertoja: "+suorituskertoja+"\n"
                            +"Pakkaus: "+pakkausAika+"ms\n"
                            +"Avaus: "+avausAika+"ms\n"
                            +"Yhteensä: "+(pakkausAika+avausAika)+"ms");
    }
    
    /**
     *  Pakkaa tiedoston.
     */
    public static void pakkaus() throws IOException{
        Tiedosto t = new Tiedosto(polku);
        Sanakirja sk = new Sanakirja();
        Pakkaaja p = new Pakkaaja(t, sk);
        p.pakkaa();
    }
    
    /**
     *  Avaa tiedoston.
     */
    public static void avaus() throws IOException{
        String pakattu = polku+".tt";
        Tiedosto t = new Tiedosto(pakattu);
        Sanakirja sk = new Sanakirja();
        Avaaja a = new Avaaja(t, sk);
        a.avaa();
    }
    
    /**
     *  Testaamista varten.
     */
    public static void testi(){
        int i = -1;
        byte b = (byte) ((i >> 8) & 0xFF);
        byte y = (byte) (i & 0xFF);
        System.out.println(b);
        System.out.println(y);
        System.exit(0);
    }
    
    /**
     *  Tulostaa polussa olevan tiedoston tavu kerrallaan.
     */
    public static void dump(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump();
    }
    
    /**
     *  Tulostaa polussa olevan tiedoston kaksi tavua kerrallaan.
     */
    public static void dump2(String polku) throws IOException{
        Tiedosto t = new Tiedosto(polku);
        t.dump2();
    }
}