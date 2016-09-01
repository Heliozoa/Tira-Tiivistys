

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;

import static util.Asetukset.sallitaanYlikirjoitus;
import static util.Vakiot.TIEDOSTOPOLKU;
import static util.Vakiot.GENEROI_RANDOM_TIEDOSTO;
import java.io.IOException;
import java.util.Random;
import tietorakenteet.Sanakirja;
import tietorakenteet.Tavujono;
import tiedosto.Tiedosto;
import lzw.Avaaja;
import lzw.Pakkaaja;

public class PakkausAvausTest {
    
    private String polku = TIEDOSTOPOLKU;
    private Random random = new Random();
    
    /**
     *  Täyttää tiedostopolun satunnaisilla tavuilla.
     */
    @Before
    public void alustus() throws IOException{
        if(GENEROI_RANDOM_TIEDOSTO){
            byte[] tavut = new byte[1000000];
            random.nextBytes(tavut);
            Tiedosto.kirjoita(tavut, polku);
        }
        sallitaanYlikirjoitus = true;
    }
  
  /**
   *  Testaa onko avattu tiedosto tasan sama kuin alkuperäinen.
   */
  @Test
  public void pakkausJaAvausToimii() throws IOException{
    Sanakirja sk = new Sanakirja();
  
    Tiedosto t1 = new Tiedosto(polku);
    Pakkaaja p = new Pakkaaja(t1, sk);
    p.pakkaa(polku+".pt");
    
    sk = new Sanakirja();
    Tiedosto t2 = new Tiedosto(polku+".pt");
    Avaaja a = new Avaaja(t2, sk);
    a.avaa(polku+".at");
    
    t1 = new Tiedosto(polku);
    Tiedosto t3 = new Tiedosto(polku+".at");
    int kohta = 0;
    while(!t1.loppu()){
        kohta++;
        assertFalse("Avattu tiedosto on lyhyempi kuin alkuperäinen.", t3.loppu());
        assertEquals("Tiedostot eroavat kohdassa "+kohta, t1.lue(), t3.lue());
    }
  }
    
    /**
     *  Pakkauksessa ja avauksessa muodostettujen sanakirjojen pitäisi olla sisällöltään tasan samat.
     */
    @Test
    public void sanakirjatRakentuvatSamoin(){
        try {
            Tiedosto t = new Tiedosto(polku);
            Sanakirja psk = new Sanakirja();
            Pakkaaja p = new Pakkaaja(t, psk);
            p.pakkaa(polku+".pt");
            
            t = new Tiedosto(polku+".pt");
            Sanakirja ask = new Sanakirja();
            Avaaja a = new Avaaja(t, ask);
            a.avaa(polku+".at");
        
            Tavujono k = new Tavujono();
            int i = 0;
            while(true){
                boolean pakkausSisaltaa = psk.sisaltaa(i);
                boolean avausSisaltaa = ask.sisaltaa(i);
                
                if(pakkausSisaltaa){
                    assertEquals("Toinen sanakirja sisältää koodin "+i+" mitä ei löydy toisesta. Pakkaus: "+psk.hae(i)+", Avaus: "+ask.hae(i), pakkausSisaltaa, avausSisaltaa);
                    k = psk.hae(i);
                    assertEquals("Koodin "+i+" takaa löytyvät tavujonot olivat eri", k, ask.hae(i));
                    i++;
                }else{
                    break;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}