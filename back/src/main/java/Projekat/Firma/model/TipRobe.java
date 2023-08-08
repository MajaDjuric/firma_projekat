package Projekat.Firma.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TipRobe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@ManyToOne
	private VrstaRobe vrsta;
	
	@OneToMany(mappedBy = "tip")
	private List<Roba> roba = new ArrayList<>();

	public List<Roba> getRoba() {
		return roba;
	}

	public void setRoba(List<Roba> roba) {
		this.roba = roba;
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

	public VrstaRobe getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaRobe vrsta) {
		this.vrsta = vrsta;
	}

	public TipRobe() {
		super();
	}
	
	
}
