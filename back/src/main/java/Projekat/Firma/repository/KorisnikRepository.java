package Projekat.Firma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Projekat.Firma.enumeration.KorisnickaUloga;
import Projekat.Firma.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findFirstByKorisnickoIme(String korisnickoIme);

    Optional<Korisnik> findFirstByKorisnickoImeAndLozinka(String korisnickoIme,String lozinka);
    
    List<Korisnik> findByUloga (KorisnickaUloga uloga);
}
