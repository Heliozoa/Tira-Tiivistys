####Algoritmi
Suunnitelmana on toteuttaa Lempel-Ziv-Welch algoritmi. Jos aika riittää (tai yksittäinen algoritmi ei ole tarpeeksi laaja) aion kokeilla myös muita tiivistämisalgoritmeja, esim. aiheissa mainittu Huffman tai muita LZ-variaatioita.

####Ongelma
Ongelmana on saada tiedosto pakattua noin puoleen sen alkuperäisestä koosta. Valitsin ongelman koska en tiennyt tiedon tiivistämisestä mitään ja olen joskus ohimennen miettinyt, miten se toimii.

####Syöte
Ohjelma saa syötteenä tiedoston, joka halutaan tiivistää tai avata, jos kyseessä on jo pakattu tiedosto.

####Aika- ja tilavaativuus
Hyvin suuretkin tiedostot tulisi pystyä pakkaamaan järkevässä ajassa. Ilmeisesti LZWn aika- ja tilavaativuudet riippuuvat suuresti toteutustavasta, joten vaativuudet selkeytynevät tarkemmin kun alan toteuttaa ohjelmaa. Tavoitteena on alustavasti O(n) molempiin.

####Lähteet
