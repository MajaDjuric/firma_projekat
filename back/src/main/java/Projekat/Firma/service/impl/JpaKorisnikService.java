package Projekat.Firma.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Projekat.Firma.enumeration.KorisnickaUloga;
import Projekat.Firma.model.Korisnik;
import Projekat.Firma.repository.KorisnikRepository;
import Projekat.Firma.service.KorisnikService;

@Service
public class JpaKorisnikService implements KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Korisnik> findOne(Long id) {
        return korisnikRepository.findById(id);
    }

    @Override
    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    @Override
    public Page<Korisnik> findAll(int brojStranice) {
        return korisnikRepository.findAll(PageRequest.of(brojStranice,10));
    }

    @Override
    public Korisnik save(Korisnik korisnik) {
        korisnik.setUloga(korisnik.getUloga()); ////ovo sam menjala
        return korisnikRepository.save(korisnik);
    }

    @Override
    public void delete(Long id) {
        korisnikRepository.deleteById(id);
    }

    @Override
    public Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme) {
        return korisnikRepository.findFirstByKorisnickoIme(korisnickoIme);
    }

	@Override
	public List<Korisnik> findByUloga(KorisnickaUloga uloga) {
		return korisnikRepository.findByUloga(uloga);
	}
	
	public String getKorisnikIdForUser(UserDetails userDetails) {
	   String username = userDetails.getUsername();
        Korisnik korisnik = findbyKorisnickoIme(username).orElse(null);
        if (korisnik != null) {
            return korisnik.getId().toString();
        } else {
            return "unknown";
        }
	  }

//    @Override
//    public boolean changePassword(Long id, KorisnikPromenaLozinkeDto korisnikPromenaLozinkeDto) {
//        Optional<Korisnik> rezultat = korisnikRepository.findById(id);
//
//        if(!rezultat.isPresent()) {
//            throw new EntityNotFoundException();
//        }
//
//        Korisnik korisnik = rezultat.get();
//
//        boolean passwordsMatch = BCrypt.checkpw(korisnikPromenaLozinkeDto.getStaraLozinka(), korisnik.getLozinka());
//        if(!korisnik.getKorisnickoIme().equals(korisnikPromenaLozinkeDto.getKorisnickoIme()) || !passwordsMatch){
//            return false;
//        }
//
//        // dodatak za zadatak 2
//        String password = korisnikPromenaLozinkeDto.getLozinka();
//        if (!korisnikPromenaLozinkeDto.getLozinka().equals("")) {
//            password = passwordEncoder.encode(korisnikPromenaLozinkeDto.getLozinka());
//        }
//
//        korisnik.setLozinka(password);
//
//        korisnikRepository.save(korisnik);
//
//        return true;
//    }
}
