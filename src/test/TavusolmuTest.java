
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import tietorakenteet.Tavusolmu;
import java.util.Random;

public class TavusolmuTest {
    
    private Random rand = new Random();
    private int raja = 1000;
    
    /**
     *  Varmistaa, että equals-metodi toimii odotetusti.
     */
    @Test
    public void equalsToimii(){
        Tavusolmu t1 = new Tavusolmu(111,222);
        Tavusolmu t2 = new Tavusolmu(111,111);
        Tavusolmu t3 = new Tavusolmu(222,111);
        assertEquals("Solmut "+t1+" ja "+t2+" pitäisi tulkita yhtäsuuriksi", t1,t2);
        assertFalse("Solmuja "+t1+" ja "+t2+" ei pitäisi tulkita yhtäsuuriksi", t1.equals(t3));
    }
    
    /**
     *  Lisää solmuja ylivuotoketjuun ja tarkistaa, että se toimii odotetusti.
     */
    @Test
    public void ylivuotoToimii(){
        Tavusolmu eka = randomSolmu();
        Tavusolmu[] solmut = randomSolmut();
        for(int i = 0; i < raja; i++){
            eka.lisaaYlivuotoon(solmut[i]);
        }
        for(int i = 0; i < raja; i++){
            assertNotNull("Solmun "+eka+" ylivuodon ei pitäisi olla null", eka.ylivuoto());
            assertEquals("Solmujen "+solmut[i]+" ja "+eka.ylivuoto()+" pitäisi olla samat", solmut[i], eka.ylivuoto());
            eka = eka.ylivuoto();
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