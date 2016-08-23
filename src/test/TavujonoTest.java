
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import java.io.IOException;
import java.util.Random;

import tietorakenteet.Tavujono;

public class TavujonoTest {
    
    final int pituusVakio = 10000;
    byte[] tavut = new byte[pituusVakio];
    
    /**
     *  Alustaa tavut-taulukon satunnaisilla tavuilla.
     */
    @Before
    public void alustus() {
        new Random().nextBytes(tavut);
    }
    
    /**
     *    Lasketaanko koko oikein kun listaan tehdään lisäys- ja poisto-operaatioita.
     */
    @Test
    public void kokoToimii(){
        Tavujono t = new Tavujono();
        
        for(int i = 0; i < tavut.length; i++){
            assertTrue("Taulukon koon pitäisi olla "+i+", mutta oli "+t.koko(), t.koko() == i);
            t.lisaa(tavut[i]);
        }
        
        for(int i = tavut.length; i > 0; i--){
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
        
        for(int i = 0; i < tavut.length; i++){
            t.lisaa(tavut[i]);
        }
        
        assertEpatyhja(t);
    }
    
    /**
     *    Toimiiko alkioiden poisto.
     */
    @Test
    public void poistoToimii(){
        Tavujono t = taysiTavujono();
        
        for(int i = 0; i < tavut.length; i++){
            byte b = t.poista();
            assertEquals("Tavun olisi pitänyt olla "+tavut[i]+", mutta oli "+b, tavut[i], b);
        }
        
        assertTyhja(t);
    }
    
    /**
     *    Kun jonoon tehdään toistuvia poisto- ja lisäysoperaatioita, kiertääkö alku- ja loppu-arvot oikein taulukon ympäri eikä koko kasva.
     */
    @Test
    public void kiertoToimii(){
        Tavujono t = new Tavujono();
        
        for(int i = 0; i < tavut.length; i++){
            t.lisaa(tavut[i]);
            assertEpatyhja(t);
            t.poista();
            assertTyhja(t);
            assertEquals("Taulukon koon ei pitäisi muuttua alkuarvosta 16, mutta se oli "+t.len(), t.len(), 16);
        }
    }
    
    /**
     *  Testaa, että jono palauttaa oikeat alkiot kun sinne ensin lisätään niitä ja sitten poistetaan.
     */
    @Test
    public void palauttaaOikeatAlkiot(){
        Tavujono t = taysiTavujono();
        
        for(int i = 0; i < tavut.length; i++){
            byte b = t.poista();
            assertEquals("Palautetun alkion olisi pitänyt olla "+tavut[i]+", mutta se oli "+b, tavut[i], b);
        }
        
    }
    
    /**
     *  Testaa että jonon taulukoksi kääntäminen toimii oikein.
     */
    @Test
    public void taulukoksiKaantoToimii(){
        Tavujono t = taysiTavujono();
        byte[] taulukko = t.taulukoksi();
        
        assertEquals("Taulukon pitäisi olla samankokoinen ("+t.koko()+") kuin jonon, mutta oli "+taulukko.length, taulukko.length, t.koko());
        
        for(int i = 0; i < taulukko.length; i++){
            byte b = t.poista();
            assertEquals("Taulukon "+i+". alkion olisi pitänyt olla "+b+", mutta oli "+taulukko[i], taulukko[i], b);
        }
    }
    
    /**
     *  Testaa kasvatusta taulukon eri kohdissa ja varmistaa, ettei mikään aiheuta ongelmia.
     */
    @Test
    public void kasvatusToimii(){
        for(int i = 0; i < 16; i++){
            Tavujono t = new Tavujono();
            //Siirtää alku- ja loppuosoittimet kohtaan i.
            for(int j = 0; j < i; j++){
                t.lisaa(tavut[0]);
                t.poista();
            }
            //Lisää 16 alkiota, jolloin jonon pitäisi kasvaa. Tarkistaa joka vaiheessa että koko on odotettu.
            for(int k = 0; k < 17; k++){
                assertEquals("Koon pitäisi olla "+k+", mutta on "+t.koko(), k, t.koko());
                t.lisaa(tavut[0]);
            }
        }
    }
    
    /**
     *  Testaa kutistusta taulukon eri kohdissa ja varmistaa, ettei mikään aiheuta ongelmia.
     */
    @Test
    public void kutistusToimii(){
        for(int i = 0; i < 16; i++){
            Tavujono t = new Tavujono();
            //Lisää 16 alkiota, jolloin sisäinen taulukko kasvaa.
            for(int d = 0; d <= 15; d++){
                t.lisaa(tavut[0]);
            }
            //Siirtää alku- ja loppuosoittimia i kohtaa eteenpäin.
            for(int j = 0; j < i; j++){
                t.lisaa(tavut[0]);
                t.poista();
            }
            //Poistaa 16 alkiota, jolloin jonon pitäisi välissä kutistua. Tarkistaa joka vaiheessa että koko on odotettu.
            for(int k = 16; k > 0; k--){
                assertEquals("Koon pitäisi olla "+k+", mutta on "+t.koko(), k, t.koko());
                t.poista();
            }
        }
    }
    
    
    /**
     *  Palauttaa uuden tavujonon, joka sisältää tavut-taulukon satunnaiset tavut.
     */
    private Tavujono taysiTavujono(){
        Tavujono t = new Tavujono();
        
        for(int i = 0; i < tavut.length; i++){
            t.lisaa(tavut[i]);
        }
        
        return t;
    }
    
    private void assertTyhja(Tavujono t){
        assertTrue("Tavujonon pitäisi olla tyhjä, muttei ole.", t.tyhja());
    }
    
    private void assertEpatyhja(Tavujono t){
        assertFalse("Tavujonon ei pitäisi olla tyhjä, mutta se on.", t.tyhja());
    }
}