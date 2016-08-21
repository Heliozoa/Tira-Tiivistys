import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pakkaus.Tiedosto;

public class TiedostoTest {
  
  String tiedostonimi = "kalevala";
  
  @Test
  public void lukukelpoinenTiedosto() {  
    Tiedosto t = new Tiedosto("./etc/"+tiedostonimi);
  }
}