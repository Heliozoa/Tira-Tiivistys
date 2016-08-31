
import static vakiot.Vakiot.AJASTA_ALGORITMIT;
import static vakiot.Vakiot.AJASTUS_SUORITUSKERRAT;
import static vakiot.Vakiot.TIEDOSTOPOLKU;

import java.io.IOException;
import static org.junit.Assume.assumeTrue;
import org.junit.Test;

import main.Main;
import lzw.Avaaja;
import lzw.Pakkaaja;
import tietorakenteet.Sanakirja;
import tiedosto.Tiedosto;

public class SuorituskykyTest {
    
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
            Main.pakkaa(polku);
            pakkausAika += System.nanoTime() - aika;
            aika = System.nanoTime();
            Main.avaa(polku);
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