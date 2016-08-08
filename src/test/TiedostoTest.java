import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pakkaus.Tiedosto;

public class TiedostoTest {
  
  @Test
  public void lukukelpoinenTiedosto() {  
    Tiedosto t = new Tiedosto("/home/sasami-san/Dev/git/github/Tira-Tiivistys/testitiedosto");
  }
  
  @Test
  public void olematonTiedosto() {
    Tiedosto t = new Tiedosto("/home/sasami-san/Dev/git/github/Tira-Tiivistys/olematontiedosto");
  }
  
}