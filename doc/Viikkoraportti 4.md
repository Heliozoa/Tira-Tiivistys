#Viikkoraportti 4
Kävin ohjauksessa ja sain ohjelmaa eteenpäin.  
Ostin [paketin kirjoja](https://www.humblebundle.com/books/joy-of-coding-book-bundle) ja sattumoisin Write Great Code käsittelee paljolti tietokoneen toimintaa alhaisella tasolla, mm. tavuja sekä lukujen ja merkkijonojen esitystä tietokoneen sisällä.  
Kirjoitin testit joiden läpi meneminen edellyttää ohjelman oikeellisuutta.  
Pakkaus ja avaus toimii! 567.8 kB kokoinen Kalevala tiivistyy 233.6 kB kokoiseksi ja aukeaa tasan samanlaiseksi kuin alkuperäinen. Ajattelin, ettei testit olisi kovin hyödyllisiä kun tiedän jo, ettei ohjelma toiminut, mutta ne auttoivatkin hyvin paljon ongelmien löytämiseen. Muistetaan tämä jatkossa.

Aloitin omien tietorakenteiden suunnittelun. Tärkeimmät työkalut ohjelmassa tällä hetkellä on HashMap ja ArrayList, en kuitenkaan usko että on tarpeen kopioida suoraan näitä luokkia.  
HashMapia korvaamaan tarvitaan tietorakenne, jolla voi nopeasti...
1. selvittää, löytyykö sieltä jokin tavujono
2. hakea tiettyä tavujonoa vastaava koodi
3. hakea koodilla (käytännössä indeksillä) sitä vastaavan tavujonon
Koska koko jonoa ei oikeastaan tarvitse tallentaa, vaan riittää uusi tavu ja viite jonoon johon tavu on lisätty, pelkän jonojen hajautuksen sijaan voisi miettiä jotain muuta. Ehkä taulukko, jonka indekseissä on "jakautuvat" linkitetyt listat? Esim. indeksissä 255 on tavut 234 ja 189, ja 234:n listassa on 111, jolloin jonot ovat 255, 255234, 255189, 255234111. 234 on siis erityisesti 255 jälkeen tuleva 234 eikä "samalla tasolla" oleva tavu kuin 255. Kohtaan 3 riittää taulukko joka sisältää joko yllä kuvattuja listoja, tai alla kuvattuja jonoja.

ArrayListin tilalle käy oikeastaan yksinkertainen tavujono, jolla voidaan ehkä myös korvata kokonaan kömpelöt String-muuttujat, hitaat substringit ja katenaatio. Aloitin jonosta, koska sen toteutus tuntui melko suoraviivaiselta verrattuna sanakirjaan.

Kirjoitin koodikatselmoinnin.

Käytetty tuntimäärä: 16