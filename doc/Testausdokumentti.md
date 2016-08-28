#Testausdokumentti

Alustavissa testeissä käytän Gutenberg-projektista ilmaiseksi löytynyttä Kalevalaa. Tiedosto on projektissa mukana, joten sen pakkaamista on helppo kokeilla itse.

###Testejä
Ajoitettu Javan System.nanoTime-metodilla. Testasin muutaman kerran ja luvut pysyivät samanlaisina. Suoritin ohjelman useamman kerran tarkistaakseni, että ajat ovat suunnilleen oikein, mutten laskenut keskiarvoja tms. Korvaan yksittäiset ajat keskiarvoilla, kunhan ohjelma kehittyy.

####Java-toteutus
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala|567,754 tavua|233,566 tavua|~41%|389ms|363ms|753ms|
|Kalevala x3|1,703,264 tavua|655,978 tavua|~38.5%|771ms|785ms|1557ms|

####Oma toteutus
|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala|567,754 tavua|233,566 tavua|~41%|389ms|307ms|697ms|
|Kalevala x3|1,703,264 tavua|655,978 tavua|~38.5%|1185ms|792ms|1978ms|