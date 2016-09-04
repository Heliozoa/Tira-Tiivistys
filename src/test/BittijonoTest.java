
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import tietorakenteet.Bittijono;

public class BittijonoTest {
    
    private int raja;
    
    @Before
    public void alustus(){
        raja = 10000;
    }
    
    /**
     *  Lisääminen ja poisto toimii oletetusti. Testaa samalla, että jono tunnistetaan tyhjäksi oikein.
     */
    @Test
    public void lisaaminenJaPoistoToimii(){
        Bittijono jono = new Bittijono();
        assertTyhja(jono);
        //bitteinä 10101010101010101010 josta 10 vähäisintä 1010101010 == 682
        jono.lisaa(699050, 10);
        assertEpatyhja(jono);
        //8 ensimmäistä poistettavaa bittiä muodostavat tavun 10101010 == 170 (
        assertEquals("Poistetun tavun olisi pitänyt olla 170, muttei ollut", (byte) 170, jono.poistaTavu());
        assertEpatyhja(jono);
        //jonossa on 2 bittiä jäljellä: 10. Poiston pitäisi täyttää loppupää 'fillerillä' ja palauttaa tavu 10000000 == 128
        assertEquals("Poistetun tavun olisi pitänyt olla 128, muttei ollut", (byte) 128, jono.poistaTavu());
    }
    
    private void assertTyhja(Bittijono jono){
        assertTrue("Jonon olisi pitänyt olla tyhjä, muttei ollut", jono.tyhja());
    }
    
    private void assertEpatyhja(Bittijono jono){
        assertFalse("Jononei olisi pitänyt olla tyhjä, mutta oli", jono.tyhja());
    }
}