import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static vakiot.Vakiot.TIEDOSTOPOLKU;
import tiedosto.Tiedosto;
import java.io.IOException;

public class TiedostoTest {
  
  String polku = TIEDOSTOPOLKU;
  
  @Test
  public void lukukelpoinenTiedosto() throws IOException{  
    Tiedosto t = new Tiedosto(polku);
  }
}