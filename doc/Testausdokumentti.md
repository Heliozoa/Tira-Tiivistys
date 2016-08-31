#Testausdokumentti

Alustavissa testeissä käytän Gutenberg-projektista ilmaiseksi löytynyttä Kalevalaa. Tiedosto on projektissa mukana, joten sen pakkaamista on helppo kokeilla itse.

###Testejä
Ajoitettu Javan System.nanoTime-metodilla. Java-toteutuksella suoritin ohjelman useamman kerran tarkistaakseni, että ajat ovat suunnilleen oikein, mutten laskenut keskiarvoja.

#####Java-toteutus vertailun vuoksi
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala|567,754 tavua|233,566 tavua|~41%|389ms|363ms|753ms|
|Kalevala x3|1,703,264 tavua|655,978 tavua|~38.5%|771ms|785ms|1557ms|

####Oma toteutus, kymmenen suorituskerran keskiarvot
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala*|567,754 tavua|233,566 tavua|~41%|329ms|113ms|442ms|
|Shakespeare*|5,582,336 tavua|2,276,620 tavua|~41%|3119ms|1006ms|4125ms|
|Raakavideo**|11,406,644 tavua|6,501,418 tavua|~57%|6328ms|2640ms|8968ms|

*www.gutenberg.net
**https://media.xiph.org/video/derf/y4m/bowing_qcif.y4m