import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import pakkaus.Sanakirja;

public class SanakirjaTest {
  
  Sanakirja sa = new Sanakirja(true);
  Sanakirja sp = new Sanakirja(false);
  
  @Test
  public void alustustesti() {
    for(int i = 0; i <= 255; i++){
      assertTrue(sa.sisaltaaAvaimen(i));
      assertEquals(sa.hae(i),i);
    }
  }
  
  @Test
  public void lisaystesti(){
    sa.lisaa(1010);
    sp.lisaa(1010);
    assertTrue(sa.sisaltaaAvaimen(256));
    assertTrue(sp.sisaltaaAvaimen(1010));
    assertEquals(sa.hae(256), 1010);
  }
  
  
}