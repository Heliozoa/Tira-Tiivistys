
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import tietorakenteet.Sanakirja;
import tietorakenteet.Taulukko;
import tietorakenteet.Tavujono;

import java.util.Random;

public class SanakirjaTest {
    
    private Random random;
    private int raja;
    
    @Before
    public void alustus(){
        random = new Random();
        raja = 1000;
    }
    
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
    
    /**
     *  Testaa lisätyn solmun hakua (haku- ja sisaltaa-metodeilla) sekä koodilla että jonolla.
     */
    @Test
    public void lisattyLoytyySisaltaaJaHakuMetodeilla(){
        Sanakirja sk = new Sanakirja();
        Tavujono tj = new Tavujono();
        tj.lisaa((byte) 111);
        tj.lisaa((byte) 222);
        
        byte vika = tj.poistaLopusta();
        assertFalse("Sanakirja on alustettu väärin, sillä jonon ei pitäisi vielä löytyä sieltä", sk.sisaltaa(tj, vika));
        assertFalse("Sanakirja on alustettu väärin, sillä koodin ei pitäisi vielä löytyä sieltä", sk.sisaltaa(258));
        
        sk.lisaa(tj, vika);
        
        assertTrue("Jono on lisätty sanakirjaan, mutta se ei löydy sieltä", sk.sisaltaa(tj, vika));
        assertTrue("Koodin pitäisi löytyä sanakirjasta, muttei löydy", sk.sisaltaa(258));
        
        tj.lisaa(vika);
        assertEquals("Tavujonolla hakemalla löytyi väärä koodi", 258, sk.hae(tj));
        assertEquals("Koodilla hakemalla löytyi väärä tavujono", tj, sk.hae(258));
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