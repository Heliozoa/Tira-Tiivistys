
package tietorakenteet;

import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

/**
 *  Järjestyksessä oleva tavujono, josta voi pyytää ensimmäisen tavun.
 *  Sisäisesti tavut ovat taulukossa, jonka kokoa muutetaan tarvittaessa, ja jossa alku- ja loppu-osoittimet merkitsevät välin, jolla on jonoon kuuluvat alkiot.
 *  Alku ja loppu voivat kiertää taulukon ympäri jolloin useat lisäykset ja poistot eivät turhaan kasvata taulukkoa.
 */
public class Tavujono {
    
    byte[] tavut;
    int alku;
    int loppu;
    
    public Tavujono() {
        tavut = new byte[16];
        alku = 0;
        loppu = 0;
    }
    
    /**
     *  Lisää alkion jonon perälle.
     *
     *  @see    tarkistaKoko
     */
    public void lisaa(byte b){
        tarkistaKoko();
        if(loppu == tavut.length){
            loppu = 0;
        }
        tavut[loppu] = b;
        loppu++;
        
    }
    
    /**
     *  Poistaa ensiksi lisätyn alkion jonosta
     *
     *  @see    tarkistaKoko
     *  @return poistettu alkio
     */
    public byte poista(){
        if(tyhja()){
            throw new NoSuchElementException("Tavujono on tyhjä.");
        }
        tarkistaKoko();
        
        if(alku == tavut.length){
            alku = 0;
        }
        
        byte b = tavut[alku];
        alku++;
        
        
        return b;
    }
    
    /**
     *  Laskee jonon koon.
     */
    public int koko(){
        if(tyhja()){
            return 0;
        } else if(loppu < alku){
            return tavut.length - alku - loppu;
        } else {
            return loppu - alku;
        }
    }
    
    /**
     *  Tarkistaa onko jono tyhjä.
     */
    public boolean tyhja(){
        return loppu == alku;
    }
    
    /**
     *  Vain testaamiseen.
     */
    public byte hae(int i){
        if(i >= loppu || i < alku){
            throw new IndexOutOfBoundsException("Tavujono ei sisällä indeksiä "+i);
        }
        return tavut[i];
    }
    
    /**
     *  Vain testaamiseen.
     */
    public int len(){
        return tavut.length;
    }
    
    /**
     *  Kasvattaa taulukon koon kaksinkertaiseksi, jos jono on täynnä.
     *  Puolittaa taulukon koon, jos jono on vain 1/4 täynnä.
     */
    private void tarkistaKoko(){
        int koko = koko();
        
        if(koko == tavut.length){
            kasvata();
        } else if(koko <= tavut.length / 4 && koko > 16){
            kutista();
        }
    }
    
    private void kasvata(){
        int koko = koko();
        byte[] uusi = new byte[tavut.length * 2];
        kopioiTaulukkoon(uusi);
        tavut = uusi;
        alku = 0;
        loppu = koko;
    }
    
    private void kutista(){
        int koko = koko();
        byte[] uusi = new byte[tavut.length / 2];
        kopioiTaulukkoon(uusi);
        tavut = uusi;
        alku = 0;
        loppu = koko;
    }
    
    private void kopioiTaulukkoon(byte[] taulukko){
        int koko = koko();
        int osoitin = alku;
        for(int i = 0; i < koko; i++){
            taulukko[i] = tavut[osoitin];
            osoitin++;
        }
    }
}