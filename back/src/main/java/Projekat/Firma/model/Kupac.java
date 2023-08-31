package Projekat.Firma.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Kupac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv; //ime i prezime ili firma
	
	@Column(unique = true)
	private String pib;
	
	@Column(unique = true)
	private String mb;
	
	@Column
	private String adresa;
	
	@Column
	private String grad;
	
	@Column 
	private String teren;
	
	@ManyToMany(mappedBy = "kupci")
	private List<Roba> roba = new ArrayList<>();
	
	@ManyToOne
	private Korisnik komercijalista;
	
	@OneToMany(mappedBy = "kupac")
	private List<Trebovanje> trebovanja = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getMb() {
		return mb;
	}

	public void setMb(String mb) {
		this.mb = mb;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getTeren() {
		return teren;
	}

	public void setTeren(String teren) {
		this.teren = teren;
	}

	public List<Roba> getRoba() {
		return roba;
	}

	public void setRoba(List<Roba> roba) {
		this.roba = roba;
	}

	public Korisnik getKomercijalista() {
		return komercijalista;
	}

	public void setKomercijalista(Korisnik komercijalista) {
		this.komercijalista = komercijalista;
	}

	public Kupac() {
		super();
	}
	
	
}
