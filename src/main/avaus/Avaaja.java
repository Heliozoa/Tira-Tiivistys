
package avaus;

import tiedosto.*;
import util.*;
import avaus.AvausSanakirja;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


/**
 *  Avaa tiedoston, joka on pakattu Pakkaaja-luokalla.
 */
public class Avaaja {
    private Tiedosto t;
    private AvausSanakirja s;
    
    /**
     *  @params tiedosto    Pakattu tiedosto joka halutaan avata.
     *  @params sanakirja   Sanakirja jota käytetään tiedon avaamiseen.
     */
    public Avaaja (Tiedosto tiedosto, AvausSanakirja sanakirja){
        t = tiedosto;
        s = sanakirja;
    }
    
    /**
     *  Avaa koodatun tiedoston muodostamalla sanakirjan samalla tavalla kuin koodatessa.
     */
    public void avaa() throws IOException{
        List<String> tavut = new ArrayList<>();
        lueTavutListaan(tavut);
        
        List<Integer> oikeatTavut = new ArrayList<>();
        pilkoJonotListaan(tavut, oikeatTavut);
        
        int[] taulukko = Taulukot.lukulistaTaulukkoon(oikeatTavut);
        byte[] tavutaulu = kaannaTavuiksi(taulukko);
        
        try{
            Tiedostokasittelija.kirjoita(tavutaulu, t.polku()+"t");
        } catch(Exception e){
            System.out.println("Virhe: "+e);
        }
    }
    
    /**
     *  Lukee kaikki koodit tiedostosta ja lisää ne listaan.
     */
    private void lueTavutListaan(List<String> tavut) throws IOException{
        String edellinen = lue();
        String jono = "";
        tavut.add(edellinen);
        
        int i = 0;
        while(!t.loppu()){
            jono = lue();
            if(s.sisaltaaJonon(edellinen+jono)){
                edellinen += jono;
            }else{
                s.lisaa(edellinen+jono);
                edellinen = jono;
            }
            i++;
        }
        tavut.add(jono);
    }
    
    /**
     *  Pätkii tavujonon yhden tavun mittaisiin osiin.
     *
     *  @params tavut       Lista, joka sisältää tavujonot
     *  @params pilkotut    Lista, johon yksittäiset tavut lisätään.
     */
    private void pilkoJonotListaan(List<String> tavut, List<Integer> pilkotut){
        for(String s : tavut){
            if(s == null) continue;
            if(s.contains("nul")) continue;
            for(int i = 0; i+3 < s.length(); i += 3){
                pilkotut.add(Integer.parseInt(s.substring(i,i+3)));
            }
        }
    }
    
    /**
     *  Kääntää int-tyyppisen taulun byte-tyyppiseksi.
     *
     *  @params taulu   Taulu, joka käännetään.
     */
    private byte[] kaannaTavuiksi(int[] taulu){
        byte[] t = new byte[taulu.length];
        for(int i = 0; i < taulu.length; i++){
            t[i] = (byte) taulu[i];
        }
        return t;
    }
    
    /**
     *  Lukee kahden tavun pituisen koodin tiedostosta ja hakee sitä vastaavan tavujonon.
     */
    private String lue() throws IOException{
        int eka = t.lue();
        eka *= 256;
        int toka = t.lue();
        return s.hae(eka+toka);
    }
}