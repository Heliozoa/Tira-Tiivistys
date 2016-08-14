
package avaus;

import tiedosto.Tiedosto;
import avaus.AvausSanakirja;
import util.Muotoilu;
import java.util.List;
import java.util.ArrayList;

public class Avaaja {
    private Tiedosto t;
    private AvausSanakirja s;
    
    public Avaaja (Tiedosto tiedosto, AvausSanakirja sanakirja){
        t = tiedosto;
        s = sanakirja;
    }
    
    public void avaa(){
        List<String> tavut = new ArrayList<>();
        
        String edellinen = lue();
        String jono = lue();
        tavut.add(edellinen);
        tavut.add(jono);
        s.lisaa(edellinen+jono);
        edellinen = jono;
        
        
        while(!t.loppu()){
            jono = lue();
            if(!s.sisaltaaJonon(edellinen+jono)){
                s.lisaa(edellinen+jono);
            }
            tavut.add(jono);
            edellinen = jono;
        }
        
        List<Integer> oikeattavut = new ArrayList<>();
        for(String s : tavut){
            if(s == null) continue;
            for(int i = 0; i < s.length(); i += 3){
                System.out.println(s.substring(i,i+3) +","+(char)Integer.parseInt(s.substring(i,i+3)));
                oikeattavut.add(Integer.parseInt(s.substring(i,i+3)));
            }
        }
        
        int[] taulukko = new int[oikeattavut.size()];
        
        for(int i = 0; i < taulukko.length; i++){
            taulukko[i] = oikeattavut.get(i);
        }
        
        byte[] tavutaulu = kaannaTavuiksi(taulukko);
        
        try{
            t.kirjoita(tavutaulu);
        } catch(Exception e){
            System.out.println("Virhe: "+e);
        }
    }
    
    private byte[] kaannaTavuiksi(int[] taulu){
        byte[] t = new byte[taulu.length];
        for(int i = 0; i < taulu.length; i++){
            t[i] = (byte) taulu[i];
        }
        return t;
    }
    
    /**
     *  Lukee kahden tavun pituisen koodin tiedostosta ja hakee sitä vastaavan jonon.
     */
    private String lue(){
        int eka = t.lue();
        eka *= 256;
        int toka = t.lue();
        return s.hae(eka+toka);
    }
}