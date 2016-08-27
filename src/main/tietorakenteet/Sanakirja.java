
package tietorakenteet;

/**
 *  Sanakirja, joka sisältää avain-arvopareissa koodeja ja tavujonoja.
 */
public class Sanakirja {
    
    private Hajautustaulu sanasto;
    private Taulukko<Tavusolmu> koodit;
    int koodi;
    int raja;
    
    /**
     *  Alustaa sanakirjan ensimmäiset 255 paria. Raja on suurin luku, mitä kahteen tavuun voi koodata. Koska koodaus tapahtuu kahteen tavuun, rajan ylitys aiheuttaa ylivuodon ja sotkee asioita.
     */
    public Sanakirja(){
        sanasto = new Hajautustaulu();
        koodit = new Taulukko<>();
        koodi = 0;
        raja = 65536;
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
     *  @params tavujono    Tavujono, jonka perään halutaan lisätä tavu. Oletus on, että tavujono löytyy jo sanakirjasta
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
     */
    public int hae(Tavujono tavujono){
        Tavusolmu solmu = haeViimeinenSolmu(tavujono);
        return solmu.koodi();
    }
    
    /**
     *  Käy tavujonoa läpi tavu kerrallaan ja etenee sitä mukaan solmuja pitkin hajautustaulussa. Viimeisestä solmusta saadaan koko ketjun koodi ja sen sisältö.
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
     */
    public boolean sisaltaa(int koodi){
        return koodit.hae(koodi) != null;
    }
    
    /**
     *  Tarkistaa, löytyykö tavujono sanakirjasta.
     */
    public boolean sisaltaa(Tavujono tavujono, byte seuraava){
        Tavujono jono = tavujono.clone();
        jono.lisaa(seuraava);
        Tavusolmu solmu = haeViimeinenSolmu(jono);
        return solmu != null;
    }
}