import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tiedosto.Tiedosto;
import java.io.IOException;

public class TiedostoTest {
  
  String tiedostonimi = "kalevala";
  
  @Test
  public void lukukelpoinenTiedosto() throws IOException{  
    Tiedosto t = new Tiedosto("./etc/"+tiedostonimi);
  }
}