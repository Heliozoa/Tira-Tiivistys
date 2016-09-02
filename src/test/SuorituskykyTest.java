
import static org.junit.Assume.assumeTrue;
import org.junit.Before;
import org.junit.Test;

import static util.Asetukset.sallitaanYlikirjoitus;
import static util.Vakiot.AJASTA_ALGORITMIT;
import static util.Vakiot.AJASTUS_SUORITUSKERRAT;
import static util.Vakiot.TIEDOSTOPOLKU;

import main.Main;
import lzw.Avaaja;
import lzw.Pakkaaja;
import tiedosto.Tiedosto;
import tietorakenteet.Sanakirja;

import java.io.IOException;

public class SuorituskykyTest {
    
    @Before
    public void alustus(){
        sallitaanYlikirjoitus = true;
    }
    
    
    /**
     *  Ajastaa algoritmin ja laskee suorituskertojen keskiarvon.
     */
    @Test
    public void ajastusTest() throws IOException{
        assumeTrue(AJASTA_ALGORITMIT);
        String polku = TIEDOSTOPOLKU;
        int suorituskertoja = AJASTUS_SUORITUSKERRAT;
        
        long pakkausAika = 0;
        long avausAika = 0;
        long aika;
        for(int i = 1; i <= suorituskertoja; i++){
            aika = System.nanoTime();
            Main.pakkaa(polku, polku+".pt");
            pakkausAika += System.nanoTime() - aika;
            aika = System.nanoTime();
            Main.avaa(polku+".pt", polku+".at");
            avausAika += System.nanoTime() - aika;
        }
        pakkausAika /= 1000000 * suorituskertoja;
        avausAika /= 1000000 * suorituskertoja;
        
        System.out.println("Suorituskertoja: "+suorituskertoja+"\n"
                            +"Pakkaus: "+pakkausAika+"ms\n"
                            +"Avaus: "+avausAika+"ms\n"
                            +"Yhteensä: "+(pakkausAika+avausAika)+"ms");
    }
}