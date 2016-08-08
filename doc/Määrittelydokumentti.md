#Määrittelydokumentti
####Algoritmi
Suunnitelmana on toteuttaa ainakin Lempel-Ziv-Welch algoritmi. Jos aika riittää (tai yksittäinen algoritmi ei ole tarpeeksi laaja) aion kokeilla myös muita tiivistämisalgoritmeja, esim. aiheissa mainittua Huffmania.

LZW vaatii hajautustaulun. Huffman käyttää binääripuuta ja kekoa tai jotain järjestettyä listaa.

####Ongelma
Ongelmana on saada tiedosto pakattua noin puoleen sen alkuperäisestä koosta. Valitsin ongelman koska en tiennyt tiedon tiivistämisestä mitään ja olen joskus ohimennen miettinyt, miten se toimii. Algoritmit oli mainittu aiheissa ja ovat ilmeisen toimivia perustason algoritmeja aihealueeseen liittyen.

####Syöte
Ohjelma saa syötteenä tiedoston, joka halutaan tiivistää tai avata, jos kyseessä on jo pakattu tiedosto.

####Aika- ja tilavaativuus
Hyvin suuretkin tiedostot tulisi pystyä pakkaamaan järkevässä ajassa. Ilmeisesti LZWn aika- ja tilavaativuudet riippuuvat suuresti toteutustavasta, joten vaativuudet selkeytynevät tarkemmin kun alan toteuttaa ohjelmaa. Tavoitteena on alustavasti O(n) molempiin. Algoritmin pitäisi olla myös käytännössä nopea, erityisesti melko pienten tiedostojen pakkauksen ja avaamisen olisi hyvä onnistua alle sekunnissa.

####Lähteet
https://www.youtube.com/playlist?list=PLOU2XLYxmsIJGErt5rrCqaSGTMyyqNt2H  
https://www.youtube.com/playlist?list=PLzH6n4zXuckpKAj1_88VS-8Z6yn9zX_P6  
https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Welch  
https://en.wikipedia.org/wiki/Huffman_coding  
http://marknelson.us/2011/11/08/lzw-revisited/  
http://commandlinefanatic.com/cgi-bin/showarticle.cgi?article=art010  