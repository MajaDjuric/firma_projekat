# Firma projekat

Glavna ideja projekta je olakšanje rada zaposlenih u firmi koja se bavi distribucijom semena, <br>
pesticida i đubriva, sa ciljem da svi zaposleni rade u okviru istog programa, gde svako na <br>
osnovu uloge u firmi ima pristup određenim podacima i funkcionalnostima. 

 * Tehnologije <br>
  Backend- SpringBoot <br>
  Frontend- React<br>

- Za pokretanje frontend dela aplikacije- npm install<br>

* Login <br>
  “korisnik” tabela u bazi podataka (šema “firma”)<br>
  Svaka lozinka je ista kao korisničko ime korisnika<br>
  
 * Trebovanja:<br>
   Prikaz detalja jednog trebovanja: klik bilo gde unutar reda u tabeli<br>
   Komercijala: <br>
  -Dodavanje novog trebovanja (u padajućem meniju izabere se kupac (učitavaju se samo kupci ulogovanog komercijaliste) i<br>
  potvrđuje se klikom na “Kreiraj trebovanje”, nakon čega se dodaju proiizvodi i količine u trebovanja i potvrđuju klikom na dugme “Završi”
   - brisanje 
   - dopuna (Pri dodavanju određenog proizvoda u trebovanje, u polju za količinu se ispisuje količina tog proizvoda na stanju)
    - izmena trebovane količine (u polje u koloni količina unese se nova količina i potvrđuje se klikom na dugme izmeni)
    - brisanje pojedinačnih proizvoda iz trebovanja
    - dodavanje novog kupca (mb mora imati 8, pib 9 brojeva)
    
    Pri kreiranju i brisanju trebovanja proizvoda, njihova količina u tabeli "Roba", koloni "stanje", se automatski smanjuje ili povećava.

  * Dispozicije (nalozi za isporuku robe kupcima): <br>
  Logistika:<br>
 - Kreiranje nove dispozicije (dugme “Kreiraj dispoziciju”)<br>
 - Dodavanje proizvoda iz trebovanja u dispoziciju (dugme “Dodaj trebovanja”, označavanje checkbox-a proizvoda koji se <br>
  dodaje u dispoziciju, i potvrda klikom na dugme “Dodaj u dispoziciju”)	<br> (Nakon ovog postupka u prikazu detalja trebovanja čekiran je checkbox u koloni “Disponirano”)
  Uklanjanje proizvoda ukoliko dispozicija tj. isporuka robe nije već obavljena(Nakon ovog postupka u prikazu detalja trebovanja checkbox u koloni “Disponirano” nije više čekiran)
  
  Magacin:
  Nakon što logistika kreira dispoziciju, magacin ima uvid u nju, i nakon isporuke robe, magacin potvrđuje koja roba je stigla do kupca tj. koja roba je isporučena, a koja nije
  -	Klik na dugme “Detaljnije”, čekiranje checkbox-a u koloni “Isporučeno” i klik na dugme “Završi”	(Nakon ovog postupka u tabeli “Trebovanja”, ukoliko  su svi proizvodi iz trebovanja isporučena,  <br>
  u koloni “Isporučeno” pojavljuje se čekirani checkbox. Ukoliko neki proizvod iz datog trebovanja nije isporučen, a bio je u dispoziciji, njegovo stanje u koloni “Disponirano” se vraća na početno, <br>
 kako bi logistika pri kreiranju nove dispozicije imala uvid da ta roba nije isporučena <br>
  Nakon ovog postupka, logistika više nema mogućnost da dodaje trebovanja u dispoziciju, a pregledom detalja ima uvid koja roba je isporučena, a koja ne  <br>
  (radi lakšeg uočavanja neisporučene robe, tekst u toj koloni postaje crven) 


    * Ulazi: <br>
  -  Ukoliko roba koja je stigla ne postoji u sistemu, Finansije mogu prvo da unesu novu robu u sistem (klik na dugme “Dodaj proizvod” kod prikaza robe), a potom da unesu ulaz te robe <br>
  - Mogu da menjaju prodajnu cenu proizvoda unosom nove vrednosti u polje i klikom na dugme “Izmeni” <br>
 -  Finansije mogu da unose nove ulaze (roba koja je isporučena u magacin, stigla uz fakturu) klikom na dugme “Dodaj ulaz” <br>
  	Pri dodavanju proizvoda u ulaz pdv se učitava sam (15% za pesticide i 10% za đubriva i semena), a na osnovu cene, pdv-a i rabata, računa se krajnji iznos ((cena*količina) + pdv – rabat)
  Pri dodavanju novog ulaza, stanje proizvoda se povećava
  
  
