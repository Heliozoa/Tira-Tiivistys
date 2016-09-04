
package util;

public class Asetukset {
    //Asetus koodin manuaalisen suorituksen helpottamiseksi.
    public static boolean sallitaanYlikirjoitus = true;
    
    //Käsiteltävän tiedoston polku yhdessä paikassa eri tiedostojen testaamisen helpottamiseksi.
    public static final String TIEDOSTOPOLKU = "etc/arw";
    //Generoidaanko testeissä random tiedosto.
    public static final boolean GENEROI_RANDOM_TIEDOSTO = false;
    //Ajetaanko ajastustestit.
    public static final boolean AJASTA_ALGORITMIT = false;
    //Monenko suorituskerran keskiarvot halutaan laskea, jos ajastustestit ajetaan.
    public static final int AJASTUS_SUORITUSKERRAT = 10;
    
    public static final int KOODI_RAJA = Integer.MAX_VALUE;
}