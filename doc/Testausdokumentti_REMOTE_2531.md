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
|Kalevala¹|658,369 tavua|256,258 tavua|~39%|404ms|128ms|532ms|
|Shakespeare²|5,589,889 tavua|2,270,626 tavua|~41%|3296ms|1039ms|4335ms|
|Raakavideo³|11,406,644 tavua|6,501,418 tavua|~57%|6408ms|2691ms|9099ms|
|Raakakuva⁴|12,934,656 tavua|16,040,422 tavua|~124%|7286ms|6242ms|13528ms|
|Poe⁵|7,298|5,258|~72%|6ms|4ms|10ms|

¹ http://www.gutenberg.org/cache/epub/7000/pg7000.txt  
² http://www.gutenberg.org/cache/epub/100/pg100.txt  
³ https://media.xiph.org/video/derf/y4m/bowing_qcif.y4m  
⁴ https://www.rawsamples.ch/raws/sony/a700/RAW_SONY_A700.ARW  
⁵ http://www.gutenberg.org/cache/epub/1065/pg1065.txt ilman Gutenberg-pätkiä  