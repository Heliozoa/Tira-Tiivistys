
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import util.Bittikasittelija;

public class BittikasittelijaTest {

    /**
     *  Testaa, että bittikoko-metodi toimii odotetusti.
     */
    @Test
    public void bittikokoToimii(){
        long l = 0x1;
        
        int i = 1;
        while(l != 0){
            assertEquals(i, Bittikasittelija.bittikoko(l));
            l = l << 0x1;
            i++;
        }
    }
}