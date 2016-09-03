#Käyttöohjeet

###Projektin sh-skriptin käyttöohjeet
Skripti "tira-tiivistys.sh" sijaitsee projektin juuresta. Se vaatii toimiakseen alla mainitun jar-tiedoston samaan hakemistoon.
Käynnistä skripti kutsumalla sitä ja seuraa ohjeita:  
``shell
    bash tira-tiivistys.sh
``

###Projektin jar-tiedoston käyttöohjeet
Jar-tiedosto "tira-tiivistys.jar" löytyy projektin juuresta.
```shell
    java -jar tira-tiivistys.jar [-parametrit]
```

#####Parametrit
-p &lt;pakattava tiedosto&gt; &lt;kohdetiedosto (vapaaehtoinen)&gt;  
Pakkaa ensimmäisenä parametrina annetun tiedoston. Jos toista parametria ei anneta, pakataan tiedosto kohteeseen "pakattava tiedosto.pt". Kohdetiedostoa ei saa olla jo olemassa.

-a &lt;pakattava tiedosto&gt; &lt;kohdetiedosto (vapaaehtoinen)&gt;  
Avaa ensimmäisenä parametrina annetun tiedoston. Jos toista parametria ei anneta, avataan tiedosto kohteeseen "avattava tiedosto.at" Kohdetiedostoa ei saa olla jo olemassa.

###Projektin kääntämis- ja suoritusohjeet
Komennot suoritettava projektin juuressa, vaativat Apache Ant-kirjaston.  
Kääntäminen: ant  
Suorittaminen: ant run (Käyttää koodissa määriteltyä tiedostoa, huomaa, että main-metodissa tulee tällöin olla test()-kutsu pois kommenteista.)  
Testit: ant test
Jar:in luonti: ant jar

###Hyväksytyt syötteet
Ohjelmalle voi antaa minkä tahansa tiedoston syötteenä. Avaus toimii luonnollisesti oikein vain, jos se on tiedosto joka on pakattu samalla ohjelmalla.