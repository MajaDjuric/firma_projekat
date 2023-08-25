package Projekat.Firma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UlazRobe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ulaz_id")
	private Ulaz ulaz;
	
	@ManyToOne
	@JoinColumn(name = "roba_id")
	private Roba roba;
	
	@Column
	private int kolicina;
	
	@Column
	private double cenaPoJediniciMere;
	
	@Column
	private double rabat;
	
	@Column
	private int pdv;
	
	@Column
	private double krajnjaCenaPoJediniciMere;
	
	// cena po jedinici mere * kolicina
	@Column
	private double krajnjaCena;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ulaz getUlaz() {
		return ulaz;
	}

	public void setUlaz(Ulaz ulaz) {
		this.ulaz = ulaz;
	}

	public Roba getRoba() {
		return roba;
	}

	public void setRoba(Roba roba) {
		this.roba = roba;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	
	public double getKrajnjaCenaPoJediniciMere() {
		return krajnjaCenaPoJediniciMere;
	}

	public void setKrajnjaCenaPoJediniciMere(double krajnjaCenaPoJediniciMere) {
		this.krajnjaCenaPoJediniciMere = krajnjaCenaPoJediniciMere;
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

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	public double getKrajnjaCena() {
		return krajnjaCena;
	}

	public void setKrajnjaCena(double krajnjaCena) {
		this.krajnjaCena = krajnjaCena;
	}

	public UlazRobe() {
		super();
	}
	
	
	
	
}
