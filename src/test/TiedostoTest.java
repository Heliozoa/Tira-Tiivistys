import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static util.Vakiot.TIEDOSTOPOLKU;
import tiedosto.Tiedosto;
import java.io.IOException;

public class TiedostoTest {
  
  private String polku = TIEDOSTOPOLKU;
  
  @Test
  public void lukukelpoinenTiedosto() throws IOException{  
    Tiedosto t = new Tiedosto(polku);
  }
}