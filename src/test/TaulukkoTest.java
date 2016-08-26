
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import tietorakenteet.Taulukko;

public class TaulukkoTest {
    
    int raja = 10000;
    
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