
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import tietorakenteet.Sanakirja;
import tietorakenteet.Tavujono;
import tietorakenteet.Taulukko;
import java.util.Random;

public class SanakirjaTest {
    
    Random random = new Random();
    int raja = 1000;
    
    /**
     *  Sanakirjan testaaminen vaikutti vähän hankalalta. Tämä testi varmistaa vain, ettei sen peruskäyttö aiheuta virheitä.
     */
    @Test
    public void lisaysJaHakuToimii(){
        Sanakirja sk = new Sanakirja();
        Taulukko<Tavujono> jonot = new Taulukko<>();
        byte[] tavut = new byte[raja];
        random.nextBytes(tavut);
        for(int i = 0; i < raja; i++){
            jonot.lisaa(randomJono(1));
            sk.lisaa(jonot.hae(i), tavut[i]);
        }
    }
    
    private Tavujono randomJono(int pituus){
        Tavujono jono = new Tavujono();
        for(int i = 0; i < pituus; i++){
            jono.lisaa(randomTavu());
        }
        return jono;
    }
    
    private byte randomTavu(){
        return (byte) random.nextInt(255);
    }
    
}