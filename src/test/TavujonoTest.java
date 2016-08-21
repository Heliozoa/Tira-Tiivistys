

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import java.io.IOException;

import tietorakenteet.Tavujono;

public class TavujonoTest {
    
    /**
    *    Lasketaanko koko oikein kun listaan tehdään lisäys- ja poisto-operaatioita.
    */
    @Test
    public void kokoToimii(){
        Tavujono t = new Tavujono();
        int raja = 100000;
        
        for(int i = 0; i < raja; i++){
            assertTrue("Taulukon koon pitäisi olla "+i+", mutta oli "+t.koko(), t.koko() == i);
            t.lisaa((byte) i);
        }
        
        for(int i = raja; i > 0; i--){
            assertTrue("Taulukon koon pitäisi olla "+i+", mutta oli "+t.koko(), t.koko() == i);
            t.poista();
        }
        assertTyhja(t);
    }
    
    /**
    *    Toimiiko alkioiden lisäys.
    */
    @Test
    public void lisaysToimii(){
        Tavujono t = new Tavujono();
        int raja = 100000;
        
        for(int i = 0; i < raja; i++){
            t.lisaa((byte) i);
            assertEquals("Indeksissä "+i+" pitäisi olla "+(byte) i+" mutta oli "+t.hae(i), (byte) i, t.hae(i));
        }
    }
    
    /**
    *    Toimiiko alkioiden poisto.
    */
    @Test
    public void poistoToimii(){
        Tavujono t = new Tavujono();
        int raja = 100000;
        
        for(int i = 0; i < raja; i++){
            t.lisaa((byte) i);
        }
        
        for(int i = 0; i < raja; i++){
            t.poista();
        }
        
        assertTyhja(t);
    }
    
    /**
    *    Kun jonoon tehdään toistuvia poisto- ja lisäysoperaatioita, kiertääkö alku- ja loppu-arvot oikein taulukon ympäri eikä koko kasva.
    */
    @Test
    public void kiertoToimii(){
        Tavujono t = new Tavujono();
        int raja = 100000;
        
        for(int i = 0; i < raja; i++){
            t.lisaa((byte) i);
            assertEpatyhja(t);
            t.poista();
            assertTyhja(t);
            assertEquals(t.len(), 16);
        }
    }
    
    private void assertTyhja(Tavujono t){
        assertTrue("Tavujonon pitäisi olla tyhjä, muttei ole.", t.tyhja());
    }
    
    private void assertEpatyhja(Tavujono t){
        assertFalse("Tavujonon ei pitäisi olla tyhjä, mutta se on.", t.tyhja());
    }
}