
package util;

public class Asetukset {
    //Asetus koodin manuaalisen suorituksen helpottamiseksi.
    public static boolean sallitaanYlikirjoitus = true;
    
    //Käsiteltävän tiedoston polku yhdessä paikassa eri tiedostojen testaamisen helpottamiseksi.
    public static final String TIEDOSTOPOLKU = "etc/wav";
    //Generoidaanko testeissä random tiedosto.
    public static final boolean GENEROI_RANDOM_TIEDOSTO = false;
    //Ajetaanko ajastustestit.
    public static final boolean AJASTA_ALGORITMIT = true;
    //Monenko suorituskerran keskiarvot halutaan laskea, jos ajastustestit ajetaan.
    public static final int AJASTUS_SUORITUSKERRAT = 10;
    //Montako koodi-tavujono-paria sanakirjaan talletetaan.
    public static final long KOODI_RAJA = Long.MAX_VALUE;
}