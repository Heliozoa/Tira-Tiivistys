
package tietorakenteet;

/**
 *  Sanakirja, joka sisältää avain-arvopareissa koodeja ja tavujonoja.
 *  Hajautustaulu sisältää kaikki tallennetut tavujonot solmuketjuina. Solmuihin on myös tallennettu niihin liittyvä koodi.
 *  Esim. jonot ACC, ACCD ja ACCE olisi tallennettu jonoksi A-C-C, jossa viimeisestä C-solmusta pääsee sekä D- ja E-solmuihin. 
 *  @see    Hajautustaulu
 */
public class Sanakirja {
    private Hajautustaulu sanasto;
    private Taulukko<Tavusolmu> koodit;
    private int koodi;
    private int raja;
    
    /**
     *  Alustaa sanakirjan ensimmäiset 255 paria. Raja on suurin luku, mitä kahteen tavuun voi koodata.
     *  Koska koodaus tapahtuu kahteen tavuun, rajan ylitys aiheuttaisi ylivuodon ja sotkisi asioita.
     */
    public Sanakirja(){
        sanasto = new Hajautustaulu();
        koodit = new Taulukko<>();
        koodi = 0;
        raja = 65535;
        for(int i = 0; i < 256; i++){
            byte b = (byte) i;
            Tavusolmu uusi = new Tavusolmu(b, koodi);
            sanasto.lisaa(uusi);
            koodit.lisaa(uusi);
            koodi++;
        }
    }
    
    /**
     *  Lisää tavu sanakirjaan jonon perään.
     *
     *  @param tavujono    Tavujono, jonka perään halutaan lisätä tavu. Oletus on, että tavujono löytyy jo sanakirjasta
     */
    public void lisaa(Tavujono tavujono, byte vika){
        if(koodi <= raja){
            Tavusolmu solmu = haeViimeinenSolmu(tavujono);
            Tavusolmu uusi = new Tavusolmu(vika, koodi, solmu);
            solmu.lisaaSolmu(uusi);
            koodit.lisaa(uusi);
            koodi++;
        }
    }
    
    /**
     *  Hakee koodia vastaavan tavujonon.
     *
     *  @param  koodi   Koodi jolla haetaan.
     *  @return Haettu tavujono.
     */
    public Tavujono hae(int koodi){
        Tavusolmu solmu = koodit.hae(koodi);
        Tavujono jono = new Tavujono();
        while(solmu != null){
            jono.lisaa(solmu.tavu());
            solmu = solmu.edellinen();
        }
        return jono.kaanna();
    }
    
    /**
     *  Hakee tavujonoa vastaavan koodin.
     *
     *  @param  tavujono    Tavujono jolla haetaan.
     *  @return  Haettu koodi.
     */
    public int hae(Tavujono tavujono){
        Tavusolmu solmu = haeViimeinenSolmu(tavujono);
        return solmu.koodi();
    }
    
    /**
     *  Käy tavujonoa läpi tavu kerrallaan ja etenee sitä mukaan solmuja pitkin hajautustaulussa. Viimeisestä solmusta saadaan koko ketjun koodi ja sen sisältö.
     *
     *  @param  tavujono    Tavujono, jota vastaava viimeinen solmu haetaan.
     *  @return Jonoa vastaavan ketjun viimeinen solmu.
     */
    private Tavusolmu haeViimeinenSolmu(Tavujono tavujono){
        byte[] tavut = tavujono.taulukoksi();
        Tavusolmu solmu = sanasto.hae(tavut[0]);
        for(int i = 1; i < tavut.length; i++){
            solmu = solmu.hae(tavut[i]);
            if(solmu == null) return null;
        }
        
        return solmu;
    }
    
    /**
     *  Tarkistaa, löytyykö koodi sanakirjasta.
     *
     *  @param  koodi   Koodi jota halutaan etsiä.
     *  @return Löytyykö koodi sanakirjasta.
     */
    public boolean sisaltaa(int koodi){
        return koodit.hae(koodi) != null;
    }
    
    /**
     *  Tarkistaa, onko sanakirja saavuttanut rajan.
     *
     *  @return Onko sanakirja täynnä.
     */
    public boolean taynna(){
        return koodi == raja;
    }
    
    /**
     *  Tarkistaa, löytyykö tavujono+seuraava sanakirjasta.
     *
     *  @param  tavujono    Jono, jota seuraavan kanssa etsitään sanakirjasta.
     *  @param  seuraava    Tavu, jota jonon kanssa etsitään sanakirjasta.
     *  @return Löytyykö tavujono+seuraava sanakirjasta.
     */
    public boolean sisaltaa(Tavujono tavujono, byte seuraava){
        tavujono.lisaa(seuraava);
        Tavusolmu solmu = haeViimeinenSolmu(tavujono);
        tavujono.poistaLopusta();
        return solmu != null;
    }
    
    @Override
    public String toString(){
        return sanasto.toString();
    }
}