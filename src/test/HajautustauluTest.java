
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import tietorakenteet.Hajautustaulu;
import tietorakenteet.Tavusolmu;
import java.util.Random;

public class HajautustauluTest {
    
    Random rand = new Random();
    int raja = 1000;
    
    /**
     *  Lisää solmuja ja hakee ne.
     */
    @Test
    public void lisaaminenJaHakuToimii(){
        Hajautustaulu taulu = new Hajautustaulu();
        Tavusolmu[] solmut = randomSolmut();
        for(int i = 0; i < raja; i++){
            taulu.lisaa(solmut[i]);
        }
        for(int i = 0; i < raja; i++){
            Tavusolmu tulos = taulu.hae(solmut[i]);
            assertNotNull("Solmua "+solmut[i]+" ei löytynyt vaikka olisi pitänyt", tulos);
            assertEquals("Hakiessa solmua "+solmut[i]+" palautettiin väärä solmu "+tulos, solmut[i], tulos);
        }
    }
    
    private Tavusolmu randomSolmu(){
        return new Tavusolmu(rand.nextInt(255), rand.nextInt());
    }
    
    private Tavusolmu[] randomSolmut(){
        Tavusolmu[] solmut = new Tavusolmu[raja];
        for(int i = 0; i < raja; i++){
            solmut[i] = randomSolmu();
        }
        return solmut;
    }
}