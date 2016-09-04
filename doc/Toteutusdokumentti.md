#Toteutusdokumentti
Ohjelman voi jakaa kolmeen osaan: pakkaus, avaus, ja sanakirja. Pakkaus ja avaus suorittavat oleellisen logiikan, sanakirja mahdollistaa niiden nopean toiminnan. Tiedosto-luokan rooli on yksinkertaisesti antaa pakkaus- ja avausalgoritmeille lukea ja tallentaa tavuja, Tavujono-luokka taas helpottaa tavujen "siirtelyä" ohjelman eri vaiheiden välillä.

LongTaulukko tehtiin tukemaan Long.MAX_VALUE määrää koodeja. Sisäisesti se on toteutettu usealla Taulukko-luokan oliolla. Bittijono toimii samalla periaatteella kuin Tavujono, paitsi että sinne voi tallentaa eripituisia bittijonoja, kun tavujonoon voi lisätä vain 8 bitin pituisia bytejä.

###Aikavaativuus

#####Pakkaus
Pakkaaja käy tiedoston tavu kerrallaan läpi. Tavuille suoritetaan operaatioita, jotka ovat enintään luokkaa O(n) missä n on pakattavan tiedoston koko. Pseudokooditasolla:

```
jono = uusi Tavujono
lue tavu jonoon //O(1)
while(tiedosto ei ole loppu)    //O(n) pakattavan tiedoston kokoon nähden.
    lue tavu //O(1)
    if(sanakirja sisältää jonon+tavun)  //O(n), sanakirjassa on tavuja enintään tiedoston koon verran.
                                        //Hajautuksen ansiosta käytännössä haku on paljon nopeampi.
        lisää tavu jonoon   //O(1)
    else
        koodi = hae sanakirjalta jonon koodi    //O(n) jonon pituuden suhteen. Jono on pisimmilläänkin pienempi kuin tiedosto itse, käytännössä aina huomattavasti pienempi.
                                                //Sanakirja käy läpi yhtäaikaisesti jonoa ja omaa hajautustauluaan ja palauttaa jonon viimeistä tavua vastaavan solmun koodin.
        lisää koodi bittijonoon ja tarkista koko    //O(n) koodin pituuden suhteen, joka on max. longin bittiesityksen pituus
        lisää tavuihin koodi    //O(1)
        lisää sanakirjaan jono+tavu //O(n) jonon pituuden suhteen. Sanakirja hakee samoin kuin yllä jonon viimeistä tavua vastaavan solmun ja lisää sille uutta tavua vastaavan solmun.
        tyhjennä jono //O(1)
        lisää tavu jonoon //(O)1

jos viimeksi ennen while-loopin loppumista suoritettiin if-lauseen sisältö
    koodi = hae sanakirjalta jonon koodi    //O(n) jonon pituuden suhteen, ks. sama yllä
    lisää tavuihin koodi                    //O(1)
muuten
    tavu = poista tavu jonon lopusta    //O(1)
    lisää tavu uuteen tavujonoon    //O(1)
    hae sanakirjalta jonon koodi    //O(n) jonon pituuden suhteen, ks. sama yllä
    lisää koodi bittijonoon ja tarkista koko    //O(n)
    hae sanakirjalta uuden jonon koodi  //O(1). Uusi jono sisältää vain yhden tavun, tällöin sen koodi saadaan haettua vakioajassa.
    lisää koodi bittijonoon ja tarkista koko    //O(n)
```

####Avaus
Avaaja toimii aikavaativuuden kannalta tasan samoin kuin pakkaaja. Huomionarvoista on, että avattava tiedosto on usein huomattavasti pakattavaa tiedostoa pienempi, jolloin "sama" tiedosto avautuu nopeammin kuin pakkautuu.
```
koodi = lue koodi   //O(1)
edellinen = hae sanakirjasta koodilla   //O(n), missä n on haettavan tavujonon pituus
jono = kopioi tavujono //O(n), missä n on tavujonon pituus
tyhjennä tavujono toiseen jonoon    //O(n), missä n on tavujonon pituus

while(tiedosto ei ole loppu)    //O(n) avattavan tiedoston koon suhteen
    lue koodi   //O(n) koodin pituuden suhteen
    if(koodi on 256)
        lisää koodin pituutta, palaa loopin alkuun
    if(koodi on 257)
        tiedosto on luettu loppuun
    if(sanakirja ei sisällä koodia) //O(1)
        lisää sanakirjaan edellinen tavujono+edellisen tavujonon ensimmäinen tavu   //O(n) jonon pituuden suhteen
        jono = hae sanakirjasta koodilla    //O(n) haettavan jonon koon suhteen
    else
        jono = hae sanakirjasta koodilla    //O(n)
        lisää sanakirjaan edellinen+jonon eka tavu  //O(n) jonon koon suhteen
    edellinen = kopioi jono //O(n) jonon pituuden suhteen
    tyhjennä jono tavuihin  //O(n) jonon pituuden suhteen
    
```

###Tilavaativuus
Tiedosto kirjoitetaan tällä hetkellä "kerralla", eli muistissa on kaikki kirjoitettavan tiedoston tavut. Siis tilavaativuus on vähintäänkin O(n) missä n on pakatessa pakatun tiedoston koko ja avatessa avatun. Sanakirjaan talletetaan jokainen kohdattu tavujono, mutta se uudelleenkäyttää solmuja, ja todellisuudessa lisäys lisää aina vain yhden tavusolmun. Esim. jonot {100,200,300}, {100,200,400}, ja {100,200,300,400} tarvitsevat 5 solmua. Pahimmassa tapauksessa tiedostossa solmuja ei päästä uudelleenkäyttämään paljoa, jolloin sanakirja kasvaa tiedoston mukana, muttei koskaan sen yli. Myös Taulukoita, LongTaulukoita, Bittijonoja ja Tavujonoja käytetään vain tiedoston tavujen tallettamiseen, joten niidenkään sisältö ei voi ylittää tiedoston tavumäärää. Tallennuksessa voi esiintyä jonkin verran toistoa, esim. sama tavu voi löytyä Bittijonosta ja Tavujonosta, mutta kuitenkin max. 3 paikasta (sanakirja, tavujono, bittijono) kerralla. Ohjelman tilavaativuus on siis O(n) missä n on käsiteltävän tiedoston koko.

###Puutteet ja parannusehdotukset
Tiedoston kirjoitus kannattaisi muuttaa niin, ettei sitä kirjoiteta kerralla vaan aina kun mahdollista. Tavujono-luokka sisältää paljon typerän näköisiä tarkistuksia ja sekalaisia metodeja, enkä ole sen laatuun kovin tyytyväinen.
Vaihteleva koodin pituus lisättiin aika loppusuoralla, joten voi olla ettei siihen liittyvää koodia ole testattu, kommentoitu tai toteutettu yhtä hyvin kuin vanhempaa osiota. Aika- ja tilavaativuuden arviot ovat aika epämääräisiä.
En ehtinyt toteuttaa Huffman-koodausta.

#####Lähteet
Tietorakenteet ja algoritmit, kevät 2016 luentokalvot
Write Great Code, Volume 1, 2004 No Starch Press