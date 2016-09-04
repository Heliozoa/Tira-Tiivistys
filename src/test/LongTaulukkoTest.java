
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import tietorakenteet.LongTaulukko;

public class LongTaulukkoTest {
    
    private long raja;
    
    @Before
    public void alustus(){
        raja = Integer.MAX_VALUE+1;
    }
    
    /**
     *  Lisääminen ja haku ei aiheuta ongelmia.
     */
    @Test
    public void lisaaminenJaHakuToimii(){
        LongTaulukko<Long> taulukko = new LongTaulukko<>();
        for(long i = 0; i < raja; i++){
            taulukko.lisaa(new Long(i));
        }
        for(long i = 0; i < raja; i++){
            assertEquals(new Long(i), taulukko.hae(i));
        }
    }
}