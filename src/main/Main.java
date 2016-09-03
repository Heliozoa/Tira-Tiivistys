
package main;

import static util.Asetukset.sallitaanYlikirjoitus;
import static util.Asetukset.TIEDOSTOPOLKU;

import lzw.Avaaja;
import lzw.Pakkaaja;
import tiedosto.Tiedosto;
import tietorakenteet.Sanakirja;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args){
        //test();
        if(args.length > 3 || args.length < 2){
            tulostaOhjeet();
            throw new IllegalArgumentException("Väärä määrä parametreja.");
        }
        
        String polku = args[1];
        String kohde;
        
        if(args[0].equals("-p")){
            polku = args[1];
            if(args.length > 2){
                kohde = args[2];
            }else{
                kohde = polku +".pt";
            }
            long aika = System.nanoTime();
            pakkaa(polku, kohde);
            long ajastus = (System.nanoTime() - aika) / 1000000;
            System.out.println("Pakkaukseen kului "+ajastus+" millisekuntia");
            
        }else if(args[0].equals("-a")){
            if(args.length > 2){
                kohde = args[2];
            }else{
                kohde = polku +".at";
            }
            long aika = System.nanoTime();
            avaa(polku, kohde);
            long ajastus = (System.nanoTime() - aika) / 1000000;
            System.out.println("Avaamiseen kului "+ajastus+" millisekuntia");
            
        }else{
            tulostaOhjeet();
            throw new IllegalArgumentException("Ensimmäinen parametri oli virheellinen.");
        }
    }
    
    /**
     *  Pakkaa polussa olevan tiedoston kohdepolkuun.
     *
     *  @param  pakattava   Pakattavan tiedoston polku.
     *  @param  kohde       Polku tiedostoon, jonne pakattu tieto kirjoitetaan.
     */
    public static void pakkaa(String pakattava, String kohde){
        try {
            Tiedosto t = new Tiedosto(pakattava);
            Sanakirja sk = new Sanakirja();
            Pakkaaja p = new Pakkaaja(t, sk);
            p.pakkaa(kohde);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     *  Avaa polussa olevan tiedoston kohdepolkuun.
     *
     *  @param  avattava    Avattavan tiedoston polku.
     *  @param  kohde       Polku tiedostoon, jonne avattu tieto kirjoitetaan.
     */
    public static void avaa(String avattava, String kohde){
        try {
            Tiedosto t = new Tiedosto(avattava);
            Sanakirja sk = new Sanakirja();
            Avaaja a = new Avaaja(t, sk);
            a.avaa(kohde);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     *  Tulostaa käyttöohjeet.
     */
    public static void tulostaOhjeet(){
        System.out.println("java -jar tira-tiivistys.jar [-parametrit]\n"
                            +"  Parametrit\n"
                            +"    -p <pakattava tiedosto> <kohdetiedosto (vapaaehtoinen)>\n"
                            +"      Pakkaa ensimmäisenä parametrina annetun tiedoston."
                            +"      Jos toista parametria ei anneta, pakataan tiedosto kohteeseen \"pakattava tiedosto.pt\". Kohdetiedostoa ei saa olla jo olemassa.\n"
                            
                            +"    -a <pakattava tiedosto> <kohdetiedosto (vapaaehtoinen)>\n"
                            +"      Avaa ensimmäisenä parametrina annetun tiedoston."
                            +"      Jos toista parametria ei anneta, avataan tiedosto kohteeseen \"avattava tiedosto.at\" Kohdetiedostoa ei saa olla jo olemassa.\n");
    }
    
    /**
     *  Koodin manuaaliseen ajoon tarkoitettu metodi.
     */
    private static void test(){
        sallitaanYlikirjoitus = true;
        String polku = TIEDOSTOPOLKU;
        pakkaa(polku, polku+".pt");
        avaa(polku+".pt", polku+".at");
        System.exit(0);
    }
}