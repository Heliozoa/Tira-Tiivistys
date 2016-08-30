

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;

import static vakiot.Vakiot.TIEDOSTOPOLKU;
import static vakiot.Vakiot.GENEROI_RANDOM_TIEDOSTO;
import java.io.IOException;
import java.util.Random;
import tietorakenteet.Sanakirja;
import tietorakenteet.Tavujono;
import tiedosto.Tiedosto;
import lzw.Avaaja;
import lzw.Pakkaaja;

public class PakkausAvausTest {
    
    String polku = TIEDOSTOPOLKU;
    Random random = new Random();
    
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
    }
  
  /**
   *  Testaa onko avattu tiedosto tasan sama kuin alkuperäinen.
   */
  //@Test
  public void pakkausJaAvausToimii() throws IOException{
    Sanakirja sk = new Sanakirja();
  
    Tiedosto t1 = new Tiedosto(polku);
    Pakkaaja p = new Pakkaaja(t1, sk);
    p.pakkaa();
    
    sk = new Sanakirja();
    Tiedosto t2 = new Tiedosto(polku+".tt");
    Avaaja a = new Avaaja(t2, sk);
    a.avaa();
    
    t1 = new Tiedosto(polku);
    Tiedosto t3 = new Tiedosto(polku+".ttt");
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
            p.pakkaa();
            
            t = new Tiedosto(polku+".tt");
            Sanakirja ask = new Sanakirja();
            Avaaja a = new Avaaja(t, ask);
            a.avaa();
        
            Tavujono k = new Tavujono();
            int i = 0;
            while(true){
                boolean pakkausSisaltaa = psk.sisaltaa(i);
                boolean avausSisaltaa = ask.sisaltaa(i);
                assertEquals("Toinen sanakirja sisältää koodin "+i+" mitä ei löydy toisesta", pakkausSisaltaa, avausSisaltaa);
                
                if(pakkausSisaltaa){
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