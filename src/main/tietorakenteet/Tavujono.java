
package tietorakenteet;

import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

/**
 *  Järjestyksessä oleva tavujono, josta voi pyytää ensimmäisen tai viimeisen tavun.
 *  Sisäisesti tavut ovat taulukossa, jonka kokoa muutetaan tarvittaessa, ja jossa alku- ja loppu-osoittimet merkitsevät välin, jolla on jonoon kuuluvat alkiot.
 *  Alku ja loppu voivat kiertää taulukon ympäri jolloin useat lisäykset ja poistot eivät turhaan kasvata taulukkoa.
 */
public class Tavujono {
    
    private byte[] tavut;
    private int alku;
    private int loppu;
    private final int alkukoko = 16;
    
    public Tavujono() {
        tavut = new byte[alkukoko];
        alku = 0;
        loppu = 0;
    }
    
    /**
     *  Lisää alkion jonon perälle.
     *
     *  @see    kasvataTarvittaessa
     *  @param  b   Lisättävä tavu.
     */
    public void lisaa(byte b){
        kasvataTarvittaessa();
        if(loppu == tavut.length){
            loppu = 0;
        }
        tavut[loppu] = b;
        loppu++;
    }
    
    /**
     *  Lisää kokonaisluvun kaksi vähäisintä tavua jonoon.
     *
     *  @param  i   Kokonaisluku, jonka kaksi vähäisintä tavua lisätään jonoon.
     */
    public void lisaaInt(int i){
        lisaa((byte) ((i >> 8) & 0xFF));
        lisaa((byte) (i & 0xFF));
    }
    
    /**
     *  Poistaa ensiksi lisätyn alkion jonosta
     *
     *  @see    kutistaTarvittaessa
     *  @return poistettu alkio
     */
    public byte poista(){
        if(tyhja()){
            throw new NoSuchElementException("Tavujono on tyhjä.");
        }
        kutistaTarvittaessa();
        
        if(alku == tavut.length){
            alku = 0;
        }
        
        byte b = tavut[alku];
        alku++;
        
        
        return b;
    }
    
    /**
     *  Poistaa tavun lopusta.
     *
     *  @see    kutistaTarvittaessa
     *  @return poistettu alkio
     */
    public byte poistaLopusta(){
        if(tyhja()){
            throw new NoSuchElementException("Tavujono on tyhjä.");
        }
        kutistaTarvittaessa();
        
        if(loppu == 0){
            loppu = tavut.length;
        }
        
        loppu--;
        byte b = tavut[loppu];
        
        
        return b;
    }
    
    /**
     *  Palauttaa jonon ensimmäisen tavun.
     *
     *  @return Jonon ensimmäinen tavu.
     */
    public byte eka(){
        if(alku == tavut.length){
            alku = 0;
        }
        
        return tavut[alku];
    }
    
    /**
     *  Laskee jonon koon.
     *
     *  @return Jonon koko.
     */
    public int koko(){
        if(loppu < alku){
            return tavut.length - alku + loppu;
        } else {
            return loppu - alku;
        }
    }
    
    /**
     *  Tarkistaa onko jono tyhjä.
     *
     *  @return Onko jono tyhjä.
     */
    public boolean tyhja(){
        return koko() == 0;
    }
    
    /**
     *  Tyhjentää tavujonon.
     */
    public void tyhjenna(){
        tavut = new byte[alkukoko];
        alku = 0;
        loppu = 0;
    }
    
    /**
     * Tyhjentää tämän jonon parametrina annettuun jonoon.
     *
     * @param   jono    Jono, johon tämä jono tyhjennetään.
     */
    public void tyhjennaJonoon(Tavujono jono){
        while(!tyhja()) jono.lisaa(poista());
    }
    
    /**
     *  Palauttaa jonon taulukkona.
     *
     *  @return  Tavujono tavutaulukkona.
     */
    public byte[] taulukoksi(){
        byte[] taulukko = new byte[koko()];
        int indeksi = alku;
        if(indeksi == tavut.length) indeksi = 0;
        for(int i = 0; i < taulukko.length; i++){
            taulukko[i] = tavut[indeksi];
            indeksi++;
            if(indeksi == tavut.length){
                indeksi = 0;
            }
        }
        return taulukko;
    }
    
    /**
     *  Palauttaa tavujonon jossa on alkiot päinvastaisessa järjestyksessä.
     *
     *  @return Tämä tavujono käännettynä.
     */
    public Tavujono kaanna(){
        byte[] taulukko = taulukoksi();
        Tavujono kaannetty = new Tavujono();
        for(int i = taulukko.length-1; i >= 0; i--){
            kaannetty.lisaa(taulukko[i]);
        }
        return kaannetty;
    }
    
    /**
     *  Luo kopion tavujonosta.
     *
     *  @return Kopio tavujonosta.
     */
    @Override
    public Tavujono clone(){
        byte[] taulukko = taulukoksi();
        Tavujono klooni = new Tavujono();
        for(int i = 0; i < taulukko.length; i++){
            klooni.lisaa(taulukko[i]);
        }
        return klooni;
    }
    
    /**
     *  Tuplaa taulukon koon, jos se on täynnä.
     */
    private void kasvataTarvittaessa(){
        if(koko() == tavut.length - 1){
            vaihdaTaulukkoa(new byte[tavut.length * 2]);
        }
    }
    
    /**
     *  Puolittaa taulukon koon, jos se on enintään neljäsosan täytetty.
     */
    private void kutistaTarvittaessa(){
        int koko = koko();
        if(koko <= tavut.length / 4 && koko > 16){
            vaihdaTaulukkoa(new byte[tavut.length / 2]);
        }
    }
    
    /**
     *  Vaihtaa taulukon uuteen. Kopioi ensin vanhan alkiot ja korjaa sitten osoittimet oikeiksi.
     *
     *  @param  taulukko    Taulukko, johon vaihdetaan.
     */
    private void vaihdaTaulukkoa(byte[] taulukko){
        int koko = koko();
        int osoitin = alku;
        
        if(osoitin == tavut.length){
            osoitin = 0;
        }
        for(int i = 0; i < koko; i++){
            taulukko[i] = tavut[osoitin];
            osoitin++;
            if(osoitin == tavut.length) osoitin = 0;
        }
        tavut = taulukko;
        alku = 0;
        loppu = koko;
    }
    
    /**
     *  Vertaa jonoja taulukkomuodossa ja varmistaa, että ne ovat samanlaiset.
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Tavujono)) return false;
        Tavujono jono = (Tavujono) obj;
        byte[] tama = taulukoksi();
        byte[] toinen = jono.taulukoksi();
        if(tama.length != toinen.length) return false;
        for(int i = 0; i < tama.length; i++){
            if(tama[i] != toinen[i]) return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        String r = "{";
        int osoitin = alku;
        
        if(osoitin == tavut.length){
            osoitin = 0;
        }
        for(int i = 0; i <  koko()-1; i++){
            r += tavut[osoitin];
            r += ",";
            osoitin++;
            if(osoitin == tavut.length) osoitin = 0;
        }
        if(!tyhja()){
            r += tavut[osoitin];
        }
        r += "}";
        return r;
    }
}