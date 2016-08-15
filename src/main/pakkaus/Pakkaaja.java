
package pakkaus;

import tiedosto.Tiedosto;
import pakkaus.PakkausSanakirja;
import util.*;
import java.util.List;
import java.util.ArrayList;

/**
 *  Hoitaa tiedon pakkaamisen. Vastaanottaa Tiedosto- ja PakkausSanakirja-luokkien oliot joiden avulla pakkaus hoidetaan.
 */
public class Pakkaaja {
    private Tiedosto t;
    private PakkausSanakirja s;
    
    /**
     *  @params tiedosto    Tiedostosta saadaan tieto joka halutaan pakata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon koodaamiseen.
     */
    public Pakkaaja(Tiedosto tiedosto, PakkausSanakirja sanakirja) {
        t = tiedosto;
        s = sanakirja;
    }
    
    /**
     *  Koodaa Tiedoston t sisällön byte-tyypin taulukkoon, jonka se antaa tiedostolle kirjoitettavaksi muistiin.
     */
    public void pakkaa() {
        List<Integer> tavut = new ArrayList<>();
        koodaaListaan(tavut);
        int[] taulu = tavutTauluksi(tavut);
        byte[] tavutaulu = kaannaTavuiksi(taulu);
        for(int i = 0; i < tavutaulu.length; i++){
            System.out.println(tavutaulu[i]);
        }
        try{
            t.kirjoita(tavutaulu);
        } catch (Exception e){
            System.out.println("Virhe: "+e);
        }
    }
    
    
    /**
     *  Koodaa Tiedoston t sisällön listaan ja muodostaa samalla sanakirjan.
     *  
     *  @param  tavut   Lista johon tavut lisätään.
     */
    private void koodaaListaan(List<Integer> tavut){
        String edellinen = lue();
        String jono = lue();
        tavut.add(s.hae(edellinen));
        tavut.add(s.hae(jono));
        s.lisaa(edellinen+jono);
        
        while(!t.loppu()){
            jono = lue();
            if(s.sisaltaa(edellinen+jono)){
                edellinen += jono;
            } else {
                s.lisaa(edellinen+jono);
                tavut.add(s.hae(jono));
                edellinen = jono;
            }
        }
    }
    
    /**
     *  Muuttaa listan joka sisältää pakatut tavut taulukoksi.
     *
     *  @param  tavut   Lista joka sisältää tiedoston tavut.
     */
    private int[] tavutTauluksi(List<Integer> tavut){
        int[] taulu = new int[tavut.size()*2];
        int n = 0;
        for(Integer i : tavut){
            int k = i.intValue();
            taulu[n+1] = k & 0xFF;
            taulu[n] = (k >> 8) & 0xFF;
            n += 2;
        }
        return taulu;
    }
    
    /**
     *  Kääntää int[]-tyyppisen taulukon byte[]-tyyppiseksi taulukoksi.
     *
     *  @params taulu   Taulukko joka käännetään.
     */
    private byte[] kaannaTavuiksi(int[] taulu){
        byte[] t = new byte[taulu.length];
        for(int i = 0; i < taulu.length; i++){
            t[i] = (byte) taulu[i];
        }
        return t;
    }
    
    /**
     *  Lukee tavun (String-tyyppinen) Tiedostosta t ja lisää siihen tarvittaessa etunollat. 
     */
    private String lue(){
        return Muotoilu.etunollat(t.lue());
    }
}