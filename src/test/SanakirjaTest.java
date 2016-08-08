import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pakkaus.Sanakirja;

public class SanakirjaTest {
  
  Sanakirja s = new Sanakirja();
  
  @Test
  public void alustus() {
    for(int i = 0; i <= 255; i++){
      assertTrue(s.sisaltaaAvaimen(i));
      assertEquals(s.hae(i),i);
    }
  }
  
  @Test
  public void lisays(){
    s.lisaa(1010);
    assertTrue(sp.sisaltaaAvaimen(1010));
    assertEquals(sa.hae(1010), 256);
  }
  
  
}