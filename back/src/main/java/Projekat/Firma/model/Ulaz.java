package Projekat.Firma.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ulaz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private LocalDate datumUlaza;
	
	@Column
	private String brojOtpremnice;
	
	@Column
	private String brojFakture;
	
	@Column
	private int kolicina;
	
	@Column
	private double cenaPoJediniciMere;
	
	@Column
	private double rabat;
	
	@ManyToOne
	private Proizvodjac proizvodjac;
	
	@ManyToMany
	@JoinTable(name = "ulaz_roba", joinColumns = @JoinColumn(name = "ulazId", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "robaId", referencedColumnName = "id"))
	private List<Roba> roba = new ArrayList<>();
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDatumUlaza() {
		return datumUlaza;
	}

	public void setDatumUlaza(LocalDate datumUlaza) {
		this.datumUlaza = datumUlaza;
	}

	public String getBrojOtpremnice() {
		return brojOtpremnice;
	}

	public void setBrojOtpremnice(String brojOtpremnice) {
		this.brojOtpremnice = brojOtpremnice;
	}

	public String getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(String brojFakture) {
		this.brojFakture = brojFakture;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public double getCenaPoJediniciMere() {
		return cenaPoJediniciMere;
	}

	public void setCenaPoJediniciMere(double cenaPoJediniciMere) {
		this.cenaPoJediniciMere = cenaPoJediniciMere;
	}

	public double getRabat() {
		return rabat;
	}

	public void setRabat(double rabat) {
		this.rabat = rabat;
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<Roba> getRoba() {
		return roba;
	}

	public void setRoba(List<Roba> roba) {
		this.roba = roba;
	}

	public Ulaz() {
		super();
	}
	
	
	
	
	
	
	
	
	
}
