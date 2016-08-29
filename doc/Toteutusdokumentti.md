#Toteutusdokumentti
Ohjelman voi jakaa kolmeen osaan: pakkaus, avaus, ja sanakirja. Pakkaus ja avaus suorittavat oleellisen logiikan, sanakirja mahdollistaa niiden nopean toiminnan. Tiedosto-luokan rooli on yksinkertaisesti antaa pakkaus- ja avausalgoritmeille lukea ja tallentaa tavuja, Tavujono-luokka taas helpottaa tavujen "siirtelyä" ohjelman eri vaiheiden välillä.

###Aikavaativuus

#####Lyhyt analyysi
Pakkaaja käy tiedoston tavu kerrallaan läpi, ja avaaja pakatun tiedoston. Parhaimmillaan puhutaan siis aikavaativuudesta O(n) missä n on käsiteltävän tiedoston koko. Tavoitteena on  saada ohjelman muut osat nopeiksi jotta koko ohjelman aikavaativuudeksi tulisi O(n).


###Tilavaativuus
Tiedosto kirjoitetaan tällä hetkellä "kerralla", eli muistissa on kaikki kirjoitettavan tiedoston tavut. Siis tilavaativuus on vähintäänkin O(n) missä n on pakatessa pakatun tiedoston koko ja avatessa avatun. Sanakirjaan talletetaan jokainen kohdattu tavujono, ja se "uudelleenkäyttää" solmuja. Esim. jonot {100,200,300}, {100,200,400}, ja {100,200,300,400} tarvitsevat 5 solmua. Pahimmassa tapauksessa tiedostossa solmuja ei päästä uudelleenkäyttämään paljoa, jolloin sanakirja kasvaa tiedoston mukana. 


###Puutteet
Pitkään toistuvat samat tavut aiheuttavat edelleen ongelmia. Ohjelma on tällä hetkellä hitaampi kuin Java-luokilla, vaikka Java-toteutus käytti paljon verrattain hitaita operaatioita, esim merkkijonojen käsittelyä.

#####Lähteet
Tietorakenteet ja algoritmit, kevät 2016 luentokalvot
Write Great Code, Volume 1, 2004 No Starch Press