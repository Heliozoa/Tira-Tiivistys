#Toteutusdokumentti
Ohjelman voi jakaa kolmeen osaan: pakkaus, avaus, ja sanakirja. Pakkaus ja avaus suorittavat oleellisen logiikan, sanakirja mahdollistaa niiden nopean toiminnan. Tiedosto-luokan rooli on yksinkertaisesti antaa pakkaus- ja avausalgoritmeille lukea ja tallentaa tavuja, Tavujono-luokka taas helpottaa tavujen "siirtelyä" ohjelman eri vaiheiden välillä.

###Aikavaativuus

#####Lyhyt analyysi
Pakkaaja käy tiedoston tavu kerrallaan läpi, ja avaaja pakatun tiedoston. Parhaimmillaan puhutaan siis aikavaativuudesta O(n) missä n on käsiteltävän tiedoston koko. Tavoitteena on  saada ohjelman muut osat nopeiksi jotta koko ohjelman aikavaativuudeksi tulisi O(n).


###Tilavaativuus
Tiedosto kirjoitetaan tällä hetkellä "kerralla", eli muistissa on kaikki kirjoitettavan tiedoston tavut. Siis tilavaativuus on vähintäänkin O(n) missä n on pakatessa pakatun tiedoston koko ja avatessa avatun. Sanakirjan tilavaativuus lienee monimutkaisempi juttu. Tavoitteena sille on vähintään O(n).


###Puutteet
Pitkään toistuvat samat tavut aiheuttavat edelleen ongelmia. Ohjelma on tällä hetkellä hitaampi kuin Java-luokilla, vaikka Java-toteutus käytti paljon verrattain hitaita operaatioita, esim merkkijonojen käsittelyä.

#####Lähteet
Tietorakenteet ja algoritmit, kevät 2016 luentokalvot
Write Great Code, Volume 1, 2004 No Starch Press