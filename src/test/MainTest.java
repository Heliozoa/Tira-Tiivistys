

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import java.io.IOException;

import avaus.Avaaja;
import avaus.AvausSanakirja;
import pakkaus.Pakkaaja;
import pakkaus.PakkausSanakirja;
import tiedosto.Tiedosto;

public class MainTest {
  
  String tiedostonimi = "kalevala";
  
  /**
   *  Testaa onko avattu tiedosto tasan sama kuin alkuperäinen.
   */
  @Test
  public void pakkausJaAvausToimii() throws IOException{
    PakkausSanakirja ps = new PakkausSanakirja();
    AvausSanakirja as = new AvausSanakirja();
  
    Tiedosto t1 = new Tiedosto("./etc/"+tiedostonimi);
    Pakkaaja p = new Pakkaaja(t1, ps);
    p.pakkaa();
    
    Tiedosto t2 = new Tiedosto("./etc/"+tiedostonimi+".tt");
    Avaaja a = new Avaaja(t2, as);
    a.avaa();
    
    t1 = new Tiedosto("./etc/"+tiedostonimi);
    Tiedosto t3 = new Tiedosto("./etc/"+tiedostonimi+".ttt");
    while(!t1.loppu()){
      assertFalse("Avattu tiedosto on lyhyempi kuin alkuperäinen.", t3.loppu());
      assertEquals("Tiedostot eroavat kohdassa "+t1.kohta(), t1.lue(), t3.lue());
    }
  }
}