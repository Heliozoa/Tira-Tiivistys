
package pakkaus;

import java.util.HashMap;

/**
 *    Sanakirjaa käytetään koodaamaan ja avaamaan tiedosto.
 *    Jos sanakirjaa käytetään pakkaamiseen, halutaan nopeasti tietää, löytyykö tietty jono jo sanakirjasta.
 *    Avatessa koodi toimii avaimena koska oleellista on löytää koodia vastaava jono nopeasti.
 *    Sanakirja sisältää aluksi kaikki mahdolliset tavut, ja algoritmin edetessä sinne lisätään toistuvia jonoja.
 *    Tämä ainakin tämänhetkinen idea.
 */
public class Sanakirja {
    private HashMap<Integer,Integer> sanakirja;
    private int koodi;
    private boolean avataan;
    
    /**
     *  Sanakirja alustetaan ensin arvoilla 0-255 missä koodi ja jono ovat samat. Sanakirja siis tuntee aluksi kaikki yhden mittaisen tavujonot.
     */
    public Sanakirja() {
        sanakirja = new HashMap<>();
        for(int i = 0; i <= 255; i++) sanakirja.put(i,i);
        
        koodi = 256;
    }
    
    /**
     *  @param tavujono Sanakirjaan lisättävä jono
     *  Uusin lisättävä tavujono halutaan aina yhdistää juoksevaan koodiin.
     */
    public void lisaa(int tavujono){
        sanakirja.put(tavujono, koodi);
        koodi++;
    }
    
    public boolean sisaltaaAvaimen(int avain){
        return sanakirja.containsKey(avain);
    }
    
    public int hae(int avain){
        return sanakirja.get(avain);
    }
}