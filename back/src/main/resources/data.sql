--nsagroPass
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(1, 'tamara@nsagro.com', 'Tamara', 'tamara', '$2a$12$VBsUlXJeMG1J0mtPi3GGUuNVITAqPHxLs36lqNGcD3/krBLt6FrDy', 'Bojanic', 'Severna Backa', 'KOMERCIJALA');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(3, 'nenad@nsagro.com', 'Nenad', 'nenad', '$2a$12$h4A7fFL8NEfCzzSSw/Mzd.xZkPcU49yKNlMoefjWkffyFdIU88fya', 'Drljaca', 'Srednja Backa', 'KOMERCIJALA');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(2, 'milenko@nsagro.com', 'Milenko', 'milenko', '$2a$12$ChzvFmHIXEDcuXV3a876OOnAlqiH4YqwqlIzzgkWD8kyN8pcvMmPq', 'Erdevik', null , 'LOGISTIKA');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(4, 'miroslav@nsagro.com', 'Miroslav', 'miroslav', '$2a$12$fzbRlhPIgxAs6EAewFCBKe8M1gMTbSdIK.5nz/rEtAjKc4vr9Paee', 'Kljestan', null, 'VOZAC');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(5, 'dragan@nsagro.com', 'Dragan', 'dragan', '$2a$12$bxvwJX2R/RaU5og6UKe./eMXXD8ZH9YvQ0Hz6EdjIkYk1NrhB5aCu', 'Zeljkovic', null, 'VOZAC');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(6, 'milan@nsagro.com', 'Milan', 'milan', '$2a$12$aZs6CeCpxO1J8Zp58tVlWe3CflrgWXxviV717M0uMAlKFTSAsjp3W', 'Milosevic', null, 'VOZAC');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(7, 'magacin@nsagro.com', 'Miljana', 'miljana', '$2a$12$WGtsEWoAKFczq0j5KY8Ny.i8AtdyouWzi1IFkmGYRu0RW0yJzNJIG', 'Jovic', null, 'MAGACIN');
INSERT INTO korisnik (id, e_mail, ime, korisnicko_ime, lozinka, prezime, teren, uloga)
	VALUES(8, 'radmila@nsagro.com', 'Radmila', 'radmila', '$2a$12$9CVx1ENFncGAT/D7iC6gyetd5fO3mw6CyW68WZ3TbGegbnLoH1M3e', 'Buha', null, 'FINANSIJE');
	
INSERT INTO vrsta_robe (id, naziv, pdv) VALUES (1, 'PESTICIDI', 15);
INSERT INTO vrsta_robe (id, naziv, pdv) VALUES (2, 'SEMENA', 10);
INSERT INTO vrsta_robe (id, naziv, pdv) VALUES (3, 'DJUBRIVA', 10);
 
INSERT INTO tip_robe (id, naziv, vrsta_id) VALUES (1, 'HERBICID', 1);
INSERT INTO tip_robe (id, naziv, vrsta_id) VALUES (2, 'FUNGICID', 1);
INSERT INTO tip_robe (id, naziv, vrsta_id) VALUES (3, 'KUKURUZ', 2);	

INSERT INTO proizvodjac (id, pib, mb, naziv) VALUES (1, '100041221', '07557159', 'SYNGENTA AGRO DOO');
INSERT INTO proizvodjac (id, pib, mb, naziv) VALUES (2, '102698401', '17459660', 'KWS SRBIJA DOO');
	
INSERT INTO roba (id, datum_proizvodnje, jedinica_mere, pakovanje, prodajna_cena, proizvodjac_id, rok_trajanja, stanje, vrsta_id, tip_id, tretman, naziv, ulaz, izlaz) 
	VALUES(1, '2022-01-07', 'L', 1, 1700.00, 1, 3, 0, 1, 1, null, 'AXIAL',  120, 20);
INSERT INTO roba (id, datum_proizvodnje, jedinica_mere, pakovanje, prodajna_cena, proizvodjac_id, rok_trajanja, stanje, vrsta_id, tip_id, tretman,  naziv, ulaz, izlaz) 
	VALUES(2, '2021-08-12', 'KG', 5, 2050.00, 1 , 2, 55, 1, 2 ,null,  'NORDOX WG',  100, 45);
INSERT INTO roba (id, datum_proizvodnje, jedinica_mere, pakovanje, prodajna_cena, proizvodjac_id, rok_trajanja, stanje, vrsta_id, tip_id, tretman,  naziv, ulaz, izlaz) 
	VALUES(3, '2022-06-30', 'KG', 25, 5850.00, 2, 3, 1000, 2, 3,  'INITIO BIRD PROTECT', 'KWS KASHMIR', 1000, 0);
	
INSERT INTO ulaz (id, broj_fakture, broj_otpremnice, datum_ulaza, proizvodjac_id) VALUES (1, 'S01-23', 'S01', '2023-04-08', 1);
INSERT INTO ulaz (id, broj_fakture, broj_otpremnice, datum_ulaza, proizvodjac_id) VALUES (2, 'KWS02-23', 'KWS02', '2023-04-10', 2);

INSERT INTO kupac (id, adresa, grad, pib, mb, naziv, teren, komercijalista_id)
	VALUES(1, 'Beogradska 146', '24415 Backi Vinogradi', '102453025', '08659206', 'ZZ PRIMA', 'Severna Backa', 1);
INSERT INTO kupac (id, adresa, grad, pib, mb, naziv, teren, komercijalista_id)
	VALUES(2, 'Put narodnih heroja 10', '24420 Kanjiza', '106830610', '20688866', 'AGRO METAL ALATI DOO', 'Severni Banat', 1);
	
INSERT INTO ulaz_roba (ulaz_id, roba_id) VALUES (1, 2);
INSERT INTO ulaz_roba (ulaz_id, roba_id) VALUES (1, 1); 
INSERT INTO ulaz_roba (ulaz_id, roba_id) VALUES (2, 3); 

INSERT INTO ulaz_robe (id, ulaz_id, roba_id, kolicina, cena_po_jedinici_mere, krajnja_cena, krajnja_cena_po_jedinici_mere, pdv, rabat) VALUES (1, 1, 1, 120, 1200,149040, 1242, 15, 10);
INSERT INTO ulaz_robe (id, ulaz_id, roba_id, kolicina, cena_po_jedinici_mere, krajnja_cena, krajnja_cena_po_jedinici_mere, pdv, rabat) VALUES (2, 1, 2, 100, 1700, 166175, 1661.75, 15, 15);
INSERT INTO ulaz_robe (id, ulaz_id, roba_id, kolicina, cena_po_jedinici_mere, krajnja_cena, krajnja_cena_po_jedinici_mere, pdv, rabat) VALUES (3, 2, 3, 1000, 4900, 5120500, 5120.5, 10, 5);

INSERT INTO trebovanje (id, komercijalista_id, kupac_id, disponirano, isporuceno, datum_trebovanja) VALUES (1, 1, 1, false, false, '2023-07-12');
INSERT INTO trebovanje (id, komercijalista_id, kupac_id, disponirano, isporuceno, datum_trebovanja) VALUES (2, 1, 2, false, false, '2023-07-05');

INSERT INTO trebovanje_roba (trebovanja_id, roba_id) VALUES (1, 1);
INSERT INTO trebovanje_roba (trebovanja_id, roba_id) VALUES (1, 2); 
INSERT INTO trebovanje_roba (trebovanja_id, roba_id) VALUES (2, 2);
INSERT INTO trebovanje_roba (trebovanja_id, roba_id) VALUES (2, 3);

INSERT INTO trebovanje_robe (id, kolicina, roba_id, trebovanje_id, disponirano, isporuceno) VALUES (1, 4, 1, 1, false, false);
INSERT INTO trebovanje_robe (id, kolicina, roba_id, trebovanje_id, disponirano, isporuceno) VALUES (2, 2, 2, 1, false, false);
INSERT INTO trebovanje_robe (id, kolicina, roba_id, trebovanje_id, disponirano, isporuceno) VALUES (3, 10, 2, 2, false, false);
INSERT INTO trebovanje_robe (id, kolicina, roba_id, trebovanje_id, disponirano, isporuceno) VALUES (4, 2, 3, 2, false, false);

INSERT INTO vozilo (id, markaitip, registracija) VALUES (1, 'Mercedes Atego ', 'NS-547-JZ');
INSERT INTO vozilo (id, markaitip, registracija) VALUES (2, 'Mercedes Atego 2', 'NS-874-MA');
INSERT INTO vozilo (id, markaitip, registracija) VALUES (3, 'Mercedes Arocs', 'NS-198-KK');




