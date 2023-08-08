package Projekat.Firma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import Projekat.Firma.enumeration.KorisnickaUloga;
import Projekat.Firma.model.Korisnik;

public interface KorisnikService {

    Optional<Korisnik> findOne(Long id);

    List<Korisnik> findAll();

    Page<Korisnik> findAll(int brojStranice);

    Korisnik save(Korisnik korisnik);

    void delete(Long id);

    Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme);
    
    List<Korisnik> findByUloga (KorisnickaUloga uloga);
    
    public String getKorisnikIdForUser(UserDetails userDetails);


}
