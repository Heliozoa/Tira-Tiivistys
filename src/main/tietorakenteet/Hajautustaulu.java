
package tietorakenteet;

public class Hajautustaulu {
    int koko;
    int alkioita;
    Tavusolmu[] taulukko;
    
    public Hajautustaulu() {
        koko = 16;
        alkioita = 0;
        taulukko = new Tavusolmu[koko];
    }
    
    public void lisaa(byte b, int koodi){
        Tavusolmu uusi = new Tavusolmu(b, koodi);
        lisaa(uusi);
    }
    
    public void lisaa(Tavusolmu solmu){
        if(alkioita > koko * 2){
            uudelleenHajauta(koko * 2);
        }
        
        int hash = hash(solmu);
        
        if(taulukko[hash] == null){
            taulukko[hash] = solmu;
        } else {
            taulukko[hash].lisaaYlivuotoon(solmu);
        }
        alkioita++;
    }
    
    public void uudelleenHajauta(int uusiKoko){
        koko = uusiKoko;
        Tavusolmu[] temp = taulukko;
        taulukko = new Tavusolmu[koko];
        for(int i = 0; i < temp.length; i++){
            if(temp[i] != null){
                Tavusolmu ylivuoto = temp[i].ylivuoto();
                while(ylivuoto != null){
                    lisaa(ylivuoto);
                    Tavusolmu vanha = ylivuoto;
                    ylivuoto = ylivuoto.ylivuoto();
                    vanha.poistaYlivuoto();
                }
                temp[i].poistaYlivuoto();
                lisaa(temp[i]);
            }
        }
    }
    
    public Tavusolmu hae(byte b){
        return hae(new Tavusolmu(b));
    }
    
    public Tavusolmu hae(Tavusolmu haettava){
        int hash = hash(haettava);
        Tavusolmu solmu = taulukko[hash];
        while(solmu != null){
            if(solmu.equals(haettava)) return solmu;
            solmu = solmu.ylivuoto();
        }
        
        return null;
    }
    
    public int hash(Tavusolmu solmu){
        return solmu.hashCode() % koko;
    }
}