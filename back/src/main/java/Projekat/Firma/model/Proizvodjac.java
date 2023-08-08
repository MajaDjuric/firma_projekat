package Projekat.Firma.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Proizvodjac {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private String pib;
	
	@Column
	private String mb;
	
	@OneToMany(mappedBy = "proizvodjac")
	private List<Roba> roba = new ArrayList<>();
	
	@OneToMany (mappedBy = "proizvodjac")
	public List<Ulaz> ulazi = new ArrayList<>();
	
	
	
	public List<Ulaz> getUlazi() {
		return ulazi;
	}

	public void setUlazi(List<Ulaz> ulazi) {
		this.ulazi = ulazi;
	}

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

	public List<Roba> getRoba() {
		return roba;
	}

	public void setRoba(List<Roba> roba) {
		this.roba = roba;
	}

	public Proizvodjac() {
		super();
	}
	
	
	
	
}
