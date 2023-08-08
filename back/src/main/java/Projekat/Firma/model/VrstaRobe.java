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
public class VrstaRobe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;
	
	@Column
	private int pdv;
	
	@OneToMany(mappedBy = "vrsta")
	private List<TipRobe> tipovi = new ArrayList<>();
	
	@OneToMany(mappedBy = "vrsta")
	private List<Roba> roba = new ArrayList<>();

	public VrstaRobe() {
		super();
	}

	public List<Roba> getRoba() {
		return roba;
	}

	public void setRoba(List<Roba> roba) {
		this.roba = roba;
	}
	
	

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
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

	public List<TipRobe> getTipovi() {
		return tipovi;
	}

	public void setTipovi(List<TipRobe> tipovi) {
		this.tipovi = tipovi;
	}
	
	
	
}
