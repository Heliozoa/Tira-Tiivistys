#Käyttöohjeet

###Projektin sh-skriptin käyttöohjeet
Käynnistä skripti kutsumalla sitä ja seuraa ohjeita:  
``shell
    bash tira-tiivistys.sh
``

###Projektin jar-tiedoston käyttöohjeet
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
Suorittaminen: ant run (Voit kokeilla omalla tiedostolla parametrilla "-Dt polku")  
Testit: ant test

Huomaa, että main-metodissa tulee tällöin olla test()-kutsu pois kommenteista.