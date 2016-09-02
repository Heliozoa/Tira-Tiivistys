
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import tietorakenteet.Taulukko;

public class TaulukkoTest {
    
    private int raja;
    
    @Before
    public void alustus(){
        raja = 10000;
    }
    
    /**
     *  Lisääminen ja haku ei aiheuta ongelmia.
     */
    @Test
    public void lisaaminenJaHakuToimii(){
        Taulukko<Integer> taulukko = new Taulukko<>();
        for(int i = 0; i < raja; i++){
            taulukko.lisaa(new Integer(i));
        }
        for(int i = 0; i < raja; i++){
            assertEquals(new Integer(i), taulukko.hae(i));
        }
    }
}