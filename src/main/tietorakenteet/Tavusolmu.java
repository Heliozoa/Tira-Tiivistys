
package tietorakenteet;

/**
 *  Solmu, joka sisältää tavun ja koodin. Solmu on osa jotain ketjua, jossa sillä voi olla edellinen solmu Tavusolmussa edellinen, seuraavia solmuja Hajautustaulussa solmut
 *  ja ylivuotoketjun seuraava alkio Tavusolmussa ylivuoto.
 */
public class Tavusolmu {
    private byte tavu;
    private int koodi;
    private Tavusolmu edellinen;
    private Tavusolmu ylivuoto;
    private Hajautustaulu solmut;
    
    /**
     *  @see    Tavusolmu(byte)
     */
    public Tavusolmu(int tavu){
        this((byte) tavu);
    }
    
    /**
     *  @see    Tavusolmu(byte, int)
     */
    public Tavusolmu(int tavu, int koodi){
        this((byte) tavu, koodi);
    }
    
    /**
     *  @see    Tavusolmu(byte, int, Tavusolmu)
     */
    public Tavusolmu(int tavu, int koodi, Tavusolmu edellinen){
        this((byte) tavu, koodi, edellinen);
    }
    
    /**
     *  @param  tavu    Tavusolmun tavu.
     */
    public Tavusolmu(byte tavu){
        this.tavu = tavu;
        solmut = new Hajautustaulu();
    }
    
    /**
     *  @param  tavu    Tavusolmun tavu.
     *  @param  koodi   Tavusolmun koodi.
     */
    public Tavusolmu(byte tavu, int koodi) {
        this(tavu);
        this.koodi = koodi;
    }
    
    /**
     *  @param  tavu    Tavusolmun tavu.
     *  @param  koodi   Tavusolmun koodi.
     *  @param  edellinen   Tavusolmua edeltävä solmu.
     */
    public Tavusolmu(byte tavu, int koodi, Tavusolmu edellinen) {
        this(tavu);
        this.koodi = koodi;
        this.edellinen = edellinen;
    }
    
    /**
     *  Lisää ylivuotoketjuun uuden solmun. Jos ketjua ei ole, uusi solmu sijoitetaan ylivuoto-muuttujaan. Muuten solmu lähetetään ylivuotoketjua ylöspäin.
     *
     *  @param  solmu   Lisättävä solmu.
     */
    public void lisaaYlivuotoon(Tavusolmu solmu){
        if(ylivuoto == null){
            ylivuoto = solmu;
        } else {
            ylivuoto.lisaaYlivuotoon(solmu);
        }
    }
    
    /**
     *  Poistaa ylivuotoketjun.
     */
    public void poistaYlivuoto(){
        ylivuoto = null;
    }
    
    /**
     *  Lisää solmun, johon tästä solmusta pääsee.
     *
     *  @param  solmu   Lisättävä solmu.
     */
    public void lisaaSolmu(Tavusolmu solmu){
        solmut.lisaa(solmu);
    }
    
    /**
     *  @see hae(Tavusolmu)
     */
    public Tavusolmu hae(byte b){
        return hae(new Tavusolmu(b));
    }
    
    /**
     *  Hakee solmut-taulusta tietyn tavun.
     *
     *  @param  solmu   Haettava solmu.
     *  @return Löytynyt tavusolmu.
     */
    public Tavusolmu hae(Tavusolmu solmu){
        return solmut.hae(solmu);
    }
    
    /**
     *  @return Tavusolmun tavu.
     */
    public byte tavu(){
        return tavu;
    }
    
    /**
     *  @return Tavusolmun koodi.
     */
    public int koodi(){
        return koodi;
    }
    
    /**
     *  @return Tavusolmun edellinen solmu.
     */
    public Tavusolmu edellinen(){
        return edellinen;
    }
    
    /**
     *  @return Tavusolmun ylivuotoketju.
     */
    public Tavusolmu ylivuoto(){
        return ylivuoto;
    }
    
    @Override
    public int hashCode(){
        int hash = (int) tavu;
        return hash + 128;
    }
    
    /**
     *  Tavujen yhtäsuuruus riittää Tavusolmujen yhtäsuuruudeksi.
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Tavusolmu)) return false;
        Tavusolmu solmu = (Tavusolmu) obj;
        return this.tavu() == solmu.tavu();
    }
    
    @Override
    public String toString(){
        return tavu+",k"+koodi;
    }
}