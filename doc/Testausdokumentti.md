#Testausdokumentti

Alustavissa testeissä käytin Gutenberg-projektista ilmaiseksi löytynyttä Kalevalaa. Kun algoritmi ei aluksi toiminut, testasin lähinnä manuaalisesti katsomalla avatun tiedoston tavuja. Tämänhetkiset testit ovat javan junit testejä. Ohjeet sekä junit-testien ja ohjelman ajamiseen löytyvät [käyttöohjeista](Käyttöohjeet.md). Suorituskykytestausta varten tulee muuttaa util.Asetukset-luokassa oleva vakio AJASTA_ALGORITMIT = true.

lzw-paketin luokkia on testattu PakkausAvausTestissä. Koska luokkien ainoa ulos tarjoama toiminta on tiedon avaus ja pakkaus, testejä on vain kaksi: toinen yksinkertaisesti varmistaa, että avattu tiedosto vastaa tavu tavulta alkuperäistä, ja toinen varmistaa, että algoritmit muodostavat sanakirjat tasan samalla tavalla. Sattumalta kyseessä ovat myös projektin hyödyllisimmät testit.

Tiedosto-paketin ainoata luokkaa testataan TiedostoTestissä. Luokan ainoa testi varmistaa, että kun se kirjoittaa tavu-taulukon, lopputuloksena on halutunlainen tiedosto.

Tietorakenteet-paketin luokilla on jokaisella oma testiluokkansa. Kaikki julkiset metodit pitäisi olla testattuina. Tietorakenteiden sisäistä toimintaa ei ole testattu, koska en keksinyt järkevää tapaa privaattien kenttien ja metodien testaamiseen, ja google-haun mukaan sitä ei kannatakaan tehdä.

###Suorituskykytestit
Ajoitettu Javan System.nanoTime-metodilla. Java-toteutuksella suoritin ohjelman useamman kerran tarkistaakseni, että ajat ovat suunnilleen oikein, mutten laskenut keskiarvoja. Ohjelma ajastaa pakkauksen ja avauksen, joten tiedostoja on helppo kokeilla myös manuaalisesti ilman yksikkötestejä.

#####Java-toteutus vertailun vuoksi
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala|567,754 tavua|233,566 tavua|~41%|389ms|363ms|753ms|
|Kalevala x3|1,703,264 tavua|655,978 tavua|~38.5%|771ms|785ms|1557ms|

####Oma toteutus, kymmenen suorituskerran keskiarvot
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala¹|658,369 tavua|256,258 tavua|~39%|404ms|128ms|532ms|
|Shakespeare²|5,589,889 tavua|2,270,626 tavua|~41%|3296ms|1039ms|4335ms|
|Raakavideo³|11,406,644 tavua|6,501,418 tavua|~57%|6408ms|2691ms|9099ms|
|Raakakuva⁴|12,934,656 tavua|16,040,422 tavua|~124%|7286ms|6242ms|13528ms|
|Poe⁵|7,298 tavua|5,258 tavua|~72%|6ms|4ms|10ms|
|Wav-tiedosto⁶|5,612,112 tavua|6,193,712 tavua|~110%|3093ms|2396ms|5489ms|

Ohjelma toimii selvästi parhaiten tekstitiedostoilla, joista löytyy helposti toistoa. Huomataan, että käsittelyyn kuluva aika näyttäisi olevan sekä pakkauksessa, että avauksessa lineaarisesti riippuvainen käsiteltävän tiedoston koosta:

[graafi](kuvat/chart.png?raw=true "Yllä luetellut tiedostot kaaviossa")

¹ http://www.gutenberg.org/cache/epub/7000/pg7000.txt  
² http://www.gutenberg.org/cache/epub/100/pg100.txt  
³ https://media.xiph.org/video/derf/y4m/bowing_qcif.y4m  
⁴ https://www.rawsamples.ch/raws/sony/a700/RAW_SONY_A700.ARW  
⁵ http://www.gutenberg.org/cache/epub/1065/pg1065.txt ilman Gutenberg-pätkiä
⁶ "Happy 8bit Loop 01" by Tristan Lohengrin : http://tristanlohengrin.wix.com/studio
