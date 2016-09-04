
package tietorakenteet;

/**
 *  Taulukko, josta voi hakea long-arvoilla. Sisäisesti toteutettu Taulukko-taulukolla.
 *  Rajoitettu kooltaan Long.MAX_VALUEen alkioon.
 */
public class LongTaulukko<T> {
    private Taulukko<T>[] taulukko;
    private int ylaindeksi;
    private int indeksi;
    
    public LongTaulukko(){
        taulukko = new Taulukko[32];
        ylaindeksi = 0;
        taulukko[ylaindeksi] = new Taulukko<T>();
    }
    
    /**
     *  Lisää alkion taulukkoon.
     *  
     *  @throws IllegalStateException Jos ollaan taulukon nro. Integer.MAX_VALUE viimeisessä indeksissä, metodi heittää poikkeuksen.
     */
    public void lisaa(T t) throws IllegalStateException{
        if(indeksi == Integer.MAX_VALUE){
            if(ylaindeksi == Integer.MAX_VALUE){
                throw new IllegalStateException("Taulukko on täynnä!!");
            }
            kasvata();
            indeksi = 0;
            ylaindeksi++;
            taulukko[ylaindeksi] = new Taulukko<T>();
        }
        taulukko[ylaindeksi].lisaa(t);
        indeksi++;
    }
    
    /**
     *  Hakee alkion taulukosta indeksistä i.
     *
     *  @param  i   Indeksi, josta haetaan.
     *  @return     Haettu alkio.
     */
    public T hae(long i){
        int haluttuTaulukko = 0;
        int haluttuIndeksi = 0;
        while(i > Integer.MAX_VALUE){
            i -= Integer.MAX_VALUE;
            haluttuTaulukko++;
        }
        haluttuIndeksi = (int) i;
        return taulukko[haluttuTaulukko].hae(haluttuIndeksi);
    }
    
    /**
     *  Tuplaa taulukon koon ja kopioi sinne vanhat alkiot.
     */
    private void kasvata(){
        Taulukko[] temp = new Taulukko[taulukko.length * 2];
        for(int i = 0; i < taulukko.length; i++){
            temp[i] = taulukko[i];
        }
        taulukko = temp;
    }
}