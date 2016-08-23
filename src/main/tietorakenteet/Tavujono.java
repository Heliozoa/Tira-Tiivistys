
package tietorakenteet;

import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

/**
 *  Järjestyksessä oleva tavujono, josta voi pyytää ensimmäisen tavun.
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
     *  @see    tarkistaKoko
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
     *  Poistaa ensiksi lisätyn alkion jonosta
     *
     *  @see    tarkistaKoko
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
     *  Laskee jonon koon.
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
     */
    public boolean tyhja(){
        return koko() == 0;
    }
    
    /**
     *  Tyhjentää tavujonon.
     */
    public void tyhjenna(){
        tavut = new byte[alkukoko];
    }
    
    /**
     *  Palauttaa jonon taulukkona.
     */
    public byte[] taulukoksi(){
        byte[] taulukko = new byte[koko()];
        int indeksi = alku;
        for(int i = 0; i < taulukko.length; i++){
            taulukko[i] = tavut[indeksi];
            indeksi++;
            if(indeksi == taulukko.length){
                indeksi = 0;
            }
        }
        return taulukko;
    }
    
    /**
     *  Tuplaa taulukon koon, jos se on täynnä.
     */
    private void kasvataTarvittaessa(){
        int koko = koko();
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
     */
    private void vaihdaTaulukkoa(byte[] taulukko){
        int koko = koko();
        int osoitin = alku;
        for(int i = 0; i < koko; i++){
            taulukko[i] = tavut[osoitin];
            osoitin++;
            if(osoitin == tavut.length){
                osoitin = 0;
            }
        }
        tavut = taulukko;
        alku = 0;
        loppu = koko;
    }
    
    /**
     *  Vain testejä varten
     */
    public int len(){
        return tavut.length;
    }
}