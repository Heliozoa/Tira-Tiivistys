
package tietorakenteet;

/**
 *  Hajautustaulu koostuu taulukosta ja Tavusolmuista.
 *  Tavusolmut hajautetaan tavun arvon perusteella taulukkoon joka kasvaa tarvittaessa. Pienentämistä ei ole toteutettu, koska kyseisessä algoritmissa taulusta ei koskaan poisteta mitään.
 *  Yhteentörmäykset on hoidettu ylivuotolistalla.
 *
 *  @see    Tavusolmu
 */
public class Hajautustaulu {
    private int alkioita;
    private Tavusolmu[] taulukko;
    
    /**
     *  Taulukon koko on aluksi 16.
     */
    public Hajautustaulu() {
        alkioita = 0;
        taulukko = new Tavusolmu[16];
    }
    
    /**
     *  Luo parametreista tavusolmun ja lisää sen tauluun.
     *
     *  @see    lisaa(Tavusolmu)
     *  @param  b   Lisättävä tavu.
     *  @param  koodi   Lisättävä koodi.
     */
    public void lisaa(byte b, int koodi){
        Tavusolmu uusi = new Tavusolmu(b, koodi);
        lisaa(uusi);
    }
    
    /**
     *  Lisää tavusolmun tauluun. Kasvattaa taulukkoa tarvittaessa ja uudelleenhajauttaa alkiot.
     *  Jos huomataan yhteentörmäys, lisätään tavusolmu ylivuotolistaan
     *  
     *  @see uudelleenHajauta(int)
     *  @see Tavusolmu#lisaaYlivuotoon(Tavusolmu)
     *  @param  solmu   Tavusolmu, joka lisätään.
     */
    public void lisaa(Tavusolmu solmu){
        if(alkioita > taulukko.length * 2){
            uudelleenHajauta(taulukko.length * 2);
        }
        
        int hash = hash(solmu);
        
        if(taulukko[hash] == null){
            taulukko[hash] = solmu;
        } else {
            taulukko[hash].lisaaYlivuotoon(solmu);
        }
        alkioita++;
    }
    
    /**
     *  Uudelleenhajauttaa taulukon alkiot ja niiden ylivuotolistat halutun kokoiseen taulukkoon.
     *
     *  @param  uusiKoko    Taulukon uusi koko uudelleenhajautuksen jälkeen.
     */
    public void uudelleenHajauta(int uusiKoko){
        Tavusolmu[] temp = taulukko;
        taulukko = new Tavusolmu[uusiKoko];
        for(int i = 0; i < temp.length; i++){
            if(temp[i] != null){
                Tavusolmu ylivuoto = temp[i].ylivuoto();
                while(ylivuoto != null){
                    lisaa(ylivuoto);
                    Tavusolmu vanha = ylivuoto;
                    ylivuoto = ylivuoto.ylivuoto();
                    vanha.poistaYlivuoto();
                }
                temp[i].poistaYlivuoto();
                lisaa(temp[i]);
            }
        }
    }
    
    /**
     *  Luo uuden tavusolmun ja hakee sen taulusta.
     *
     *  @see hae(Tavusolmu)
     *  @param  b   Haettava tavu.
     */
    public Tavusolmu hae(byte b){
        return hae(new Tavusolmu(b));
    }
    
    /**
     *  Hakee taulusta haettavaa vastaavan tavusolmun, jolla on sama arvo.
     *
     *  @param  haettava    Haettava tavusolmu.
     *  @return Taulussa oleva vastaava tavusolmu. Null jos sellaista ei löydy.
     */
    public Tavusolmu hae(Tavusolmu haettava){
        int hash = hash(haettava);
        Tavusolmu solmu = taulukko[hash];
        while(solmu != null){
            if(solmu.equals(haettava)) return solmu;
            solmu = solmu.ylivuoto();
        }
        
        return null;
    }
    
    /**
     *  Hajautusarvo solmulle
     *
     *  @param  solmu   Solmu, jolle halutaan laskea hajautusarvo.
     *  @return Solmun hajautusarvo.
     */
    public int hash(Tavusolmu solmu){
        return solmu.hashCode() % taulukko.length;
    }
    
    @Override
    public String toString(){
        String r = "{";
        for(int i = 0; i < taulukko.length-1; i++){
            r += taulukko[i]+",";
        }
        r += taulukko[taulukko.length-1]+"}";
        return r;
    }
}