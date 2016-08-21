#Testausdokumentti

Alustavissa testeissä käytän Gutenberg-projektista ilmaiseksi löytynyttä Kalevalaa. Tiedosto on projektissa mukana, joten sen pakkaamista on helppo kokeilla itse.

###Testejä
Ajoitettu Javan System.nanoTime-metodilla. Testasin muutaman kerran ja luvut pysyivät samanlaisina. Korvaan yksittäiset ajat keskiarvoilla, kunhan ohjelma kehittyy.

|Syöte|Alkuperäinen koko|Pakattu koko|Pakkaussuhde|Pakkaus|Avaus|Yhteensä|
|--------|--------|--------|--------|--------|--------|--------|
|Kalevala|567,754 tavua|233,566 tavua|~41%|389ms|363ms|753ms|
|Kalevala x3|1,703,264 tavua|655,978 tavua|~38.5%|771ms|785ms|1557ms|