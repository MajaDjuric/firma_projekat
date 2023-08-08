package Projekat.Firma.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trebovanje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Korisnik komercijalista;
	
	@ManyToOne
	private Kupac kupac;
	 
//	 @ManyToOne
//	 private Dispozicija dispozicija;
	
	@OneToMany(mappedBy = "trebovanje", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TrebovanjeRobe> trebovanjeRobeList = new ArrayList<>();

	@ManyToMany(mappedBy = "trebovanja")
	private List<Roba> roba = new ArrayList<>();
	
	@Column
	private LocalDate datumTrebovanja;
	
	@Column
	private boolean disponirano;
	
	@Column
	private boolean isporuceno;
	 
	public List<Roba> getRoba() {
		return roba;
	}
	
	public LocalDate getDatumTrebovanja() {
		return datumTrebovanja;
	}

	public void setDatumTrebovanja(LocalDate datumTrebovanja) {
		this.datumTrebovanja = datumTrebovanja;
	}

	public boolean isDisponirano() {
		return disponirano;
	}

	public void setDisponirano(boolean disponirano) {
		this.disponirano = disponirano;
	}

	public boolean isIsporuceno() {
		return isporuceno;
	}

	public void setIsporuceno(boolean isporuceno) {
		this.isporuceno = isporuceno;
	}

	public void setRoba(List<Roba> roba) {
		this.roba = roba;
	}

//	public Dispozicija getDispozicija() {
//		return dispozicija;
//	}
//
//	public void setDispozicija(Dispozicija dispozicija) {
//		this.dispozicija = dispozicija;
//	}

	public List<TrebovanjeRobe> getTrebovanjeRobeList() {
		return trebovanjeRobeList;
	}

	public void setTrebovanjeRobeList(List<TrebovanjeRobe> trebovanjeRobeList) {
		this.trebovanjeRobeList = trebovanjeRobeList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Korisnik getKomercijalista() {
		return komercijalista;
	}

	public void setKomercijalista(Korisnik komercijalista) {
		this.komercijalista = komercijalista;
	}

	public Kupac getKupac() {
		return kupac;
	}

	public void setKupac(Kupac kupac) {
		this.kupac = kupac;
	}

	public Trebovanje() {
		super();
	}
	
	public void dodavanjeRobeSaKolicinom (Roba roba, int kolicina) {
		TrebovanjeRobe trebovanjeRobe = new TrebovanjeRobe(this, roba, kolicina);
	    trebovanjeRobeList.add(trebovanjeRobe);
	    roba.getTrebovanjeRobeList().add(trebovanjeRobe);
	  }
	
	
	
}
