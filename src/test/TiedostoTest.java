import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static util.Vakiot.TIEDOSTOPOLKU;

import tiedosto.Tiedosto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class TiedostoTest {
  
  private String polku;
  private Random random;
  
  @Before
  public void alustus(){
    polku = TIEDOSTOPOLKU;
    random = new Random();
  }
  
  @Test
  public void kirjoitusJaLukuToimii() throws IOException{
    byte[] tavut = new byte[10000];
    random.nextBytes(tavut);
    Tiedosto.kirjoita(tavut, "TIEDOSTO_TESTI_TIEDOSTO");
    Tiedosto t = new Tiedosto("TIEDOSTO_TESTI_TIEDOSTO");
    for(int i = 0; i < tavut.length; i++){
      byte tavu = (byte)t.lue();
      assertEquals("Tavujen pitäisi olla samat, mutteivät olleet kohdassa "+i, tavut[i], tavu);
    }
  }
  
  @After
  public void poistaTiedosto() throws IOException{
    Files.deleteIfExists(new File("TIEDOSTO_TESTI_TIEDOSTO").toPath());
  }
}