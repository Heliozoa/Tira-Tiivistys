
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import java.util.HashMap;
import java.io.IOException;

import avaus.Avaaja;
import avaus.AvausSanakirja;
import pakkaus.Pakkaaja;
import pakkaus.PakkausSanakirja;
import tiedosto.Tiedosto;

public class SanakirjaTest {
  
  /**
   *  Testaa, ovatko pakkaus- ja avaussanakirjojen sisältävät sanakirjat tasan samat, niinkuin pitäisi olla.
   */
  @Test
  public void sanakirjatRakentuvatSamoin() throws IOException{
    PakkausSanakirja ps = new PakkausSanakirja();
    AvausSanakirja as = new AvausSanakirja();
  
    Tiedosto t = new Tiedosto("/home/sasami-san/Dev/github/Tira-Tiivistys/minitesti");
    Pakkaaja p = new Pakkaaja(t, ps);
    p.pakkaa();
    
    t = new Tiedosto("/home/sasami-san/Dev/github/Tira-Tiivistys/minitesti.tt");
    Avaaja a = new Avaaja(t, as);
    a.avaa();
    
    HashMap<Integer,String> pakkaus = ps.kaanna();
    HashMap<Integer,String> avaus = as.sanakirja();
    
    for(int i = 0; i < pakkaus.keySet().size(); i++){
      assertTrue("Koodia "+i+" ei löydy avaussanakirjasta vaikka se löytyy pakkaussanakirjasta.", avaus.containsKey(i));
      assertEquals("Koodia "+i+" vastaavat jonot eivät ole samoja.\n"
                   +"Pakkaus: "+pakkaus.get(i)+"\n"
                   +"Avaus: "+avaus.get(i)+"\n",
                   pakkaus.get(i), avaus.get(i));
    }
    assertFalse(avaus.containsKey(pakkaus.keySet().size()));
  }
  
  
}