
package avaus;

import tiedosto.Tiedosto;
import avaus.AvausSanakirja;
import util.Muotoilu;
import util.Taulukot;
import java.util.List;
import java.util.ArrayList;


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
    public void avaa(){
        List<String> tavut = new ArrayList<>();
        lueTavutListaan(tavut);
        
        List<Integer> oikeatTavut = new ArrayList<>();
        pilkoJonotListaan(tavut, oikeatTavut);
        
        int[] taulukko = Taulukot.lukulistaTaulukkoon(oikeatTavut);
        byte[] tavutaulu = kaannaTavuiksi(taulukko);
        
        try{
            t.kirjoita(tavutaulu);
        } catch(Exception e){
            System.out.println("Virhe: "+e);
        }
    }
    
    /**
     *  Lukee kaikki koodit tiedostosta ja lisää ne listaan.
     */
    private void lueTavutListaan(List<String> tavut){
        String edellinen = lue();
        String jono = lue();
        tavut.add(edellinen);
        tavut.add(jono);
        s.lisaa(edellinen+jono);
        edellinen = jono;
        
        
        while(!t.loppu()){
            jono = lue();
            if(jono == null){
                System.out.println(edellinen);
                s.lisaa(edellinen+edellinen);
                tavut.add(edellinen+edellinen);
                continue;
            }
            if(!s.sisaltaaJonon(edellinen+jono)){
                s.lisaa(edellinen+jono);
                edellinen = jono;
            }else{
                edellinen += jono;
            }
            tavut.add(jono);
        }
    }
    
    /**
     *  Pätkii tavujonon yhden tavun mittaisiin osiin.
     *
     *  @params tavut       Lista, joka sisältää tavujonot
     *  @params pilkotut    Lista, johon yksittäiset tavut lisätään.
     */
    private void pilkoJonotListaan(List<String> tavut, List<Integer> pilkotut){
        for(String s : tavut){
            for(int i = 0; i < s.length(); i += 3){
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
    private String lue(){
        int eka = t.lue();
        eka *= 256;
        int toka = t.lue();
        if(s.hae(eka+toka) == null) System.out.println("null "+eka+toka);
        return s.hae(eka+toka);
    }
}