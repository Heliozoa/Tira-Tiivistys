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

Java-toteutus toimi, mutta oli hieman hitaampi. Tämä selittynee sillä, että tavut oli tallennettu sisäisesti String-muuttujiin, joiden käsittely on hidasta ja kömpelöä varsinkin jonojen kasvaessa.

####Oma toteutus kahden tavun koodin pituudella (siis max 65535), kymmenen suorituskerran keskiarvot
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala¹|658,369 tavua|256,258 tavua|~39%|404ms|128ms|532ms|
|Shakespeare²|5,589,889 tavua|2,270,626 tavua|~41%|3296ms|1039ms|4335ms|
|Raakavideo³|11,406,644 tavua|6,501,418 tavua|~57%|6408ms|2691ms|9099ms|
|Raakakuva⁴|12,934,656 tavua|16,040,422 tavua|~124%|7286ms|6242ms|13528ms|
|Poe⁵|7,298 tavua|5,258 tavua|~72%|6ms|4ms|10ms|
|Wav-tiedosto⁶|5,612,112 tavua|6,193,712 tavua|~110%|3093ms|2396ms|5489ms|

Ohjelma toimi selvästi parhaiten tekstitiedostoilla, joista löytyy helposti toistoa. Kooltaan kasvavat tiedostot johtunevat niiden huonosta pakkautuvuudesta, jolloin kahten tavun koodipituus toimii huonosti. Huomataan, että käsittelyyn kuluva aika näyttäisi olevan sekä pakkauksessa, että avauksessa lineaarisesti riippuvainen käsiteltävän tiedoston koosta:

![graafi](kuvat/kahdenTavunKoodit.png?raw=true "Yllä luetellut tiedostot kaaviossa")

####Oma toteutus vaihtelevalla koodin pituudella (6-Long.MAX_VALUE), kymmenen suorituskerran keskiarvot.
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala¹|658,369 tavua|243,104 tavua|~37%|405ms|136ms|541ms|
|Shakespeare²|5,589,889 tavua|1,943,536 tavua|~35%|3876ms|1305ms|5181ms|
|Raakavideo³|11,406,644 tavua|5,621,595 tavua|~49%|8718ms|3722ms|12440ms|
|Raakakuva⁴|12,934,656 tavua|13,629,231 tavua|~105%|13104ms|9302ms|22406ms|
|Poe⁵|7,298 tavua|3,589 tavua|~49%|8ms|4ms|12ms|
|Wav-tiedosto⁶|5,612,112 tavua|5,503,216 tavua|~98%|6022ms|4399ms|10421ms|

Ohjelma selkeästi hidastui hieman monimutkaisemman vaihtelevan koodin pituuden takia. Toisaalta pakkaus parantui huomattavasti, ja pääätös nopean ja hyvän pakkauksen välillä on tehtävä kaikilla pakkausohjelmilla. Suuret tiedostot hyötyivät yli 2-tavuisista koodeista, pienet taas mahdollisuudesta koodata aluksi 9-bitin koodeilla. Omia tietorakenteita ja saisi varmasti nopeammiksi, mutta olen joka tapauksessa tyytyväinen lopputulokseen. Osa hidastuksesta suuremmilla tiedostoilla johtunee siitä, että aiemmalla toteutuksella sanakirja täyttyi, jonka jälkeen sitä ei enää tarvinnut ylläpitää. Uudella toteutuksella sanakirjalla on exponentiaalisesti enemmän tilaa, jolloin sen täyttyminen vaatii paljon suurempia tiedostoja.

![graafi](kuvat/vaihtelevaKoodinPituus.png?raw=true "Yllä luetellut tiedostot kaaviossa")

¹ http://www.gutenberg.org/cache/epub/7000/pg7000.txt  
² http://www.gutenberg.org/cache/epub/100/pg100.txt  
³ https://media.xiph.org/video/derf/y4m/bowing_qcif.y4m  
⁴ https://www.rawsamples.ch/raws/sony/a700/RAW_SONY_A700.ARW  
⁵ http://www.gutenberg.org/cache/epub/1065/pg1065.txt ilman Gutenberg-pätkiä
⁶ "Happy 8bit Loop 01" by Tristan Lohengrin : http://tristanlohengrin.wix.com/studio
