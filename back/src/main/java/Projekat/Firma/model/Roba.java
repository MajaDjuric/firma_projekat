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
import javax.persistence.OneToMany;

@Entity
public class Roba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private VrstaRobe vrsta;
	
	@ManyToOne
	private TipRobe tip; //herbicid, kukuruz...
	
	@Column
	private String naziv;
	
	@ManyToOne
	private Proizvodjac proizvodjac;
	
	@Column
	private double ulaznaCena;
	
	@Column
	private double prodajnaCena; //bez pdv-a
	
	@Column
	private int ulaz;

	@Column
	private int izlaz;
	
	@Column
	private int stanje;
	
	@Column
	private double pakovanje;
	
	@Column
	private LocalDate datumProizvodnje;
	
	@Column
	private int rokTrajanja;
	
	@Column
	private String jedinicaMere;
	
	@Column
	private String tretman;
	
	@ManyToMany
	@JoinTable(name = "kupac_roba", joinColumns = @JoinColumn(name = "kupacId", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "robaId", referencedColumnName = "id"))
	private List<Kupac> kupci = new ArrayList<>();
	
	
	@ManyToMany(mappedBy = "roba")
	private List<Ulaz> ulazi = new ArrayList<>();
	
	@OneToMany(mappedBy = "trebovanje")
	private List<TrebovanjeRobe> trebovanjeRobeList = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "trebovanje_roba")
	private List<Trebovanje> trebovanja = new ArrayList<>();

	public Long getId() {
		return id;
	} 
	

	public List<Trebovanje> getTrebovanja() {
		return trebovanja;
	}


	public void setTrebovanja(List<Trebovanje> trebovanja) {
		this.trebovanja = trebovanja;
	}


	public List<TrebovanjeRobe> getTrebovanjeRobeList() {
		return trebovanjeRobeList;
	}



	public void setTrebovanjeRobeList(List<TrebovanjeRobe> trebovanjeRobeList) {
		this.trebovanjeRobeList = trebovanjeRobeList;
	}



	public List<Ulaz> getUlazi() {
		return ulazi;
	}

	public void setUlazi(List<Ulaz> ulazi) {
		this.ulazi = ulazi;
	}

	public String getTretman() {
		return tretman;
	}

	public void setTretman(String tretman) {
		this.tretman = tretman;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VrstaRobe getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaRobe vrsta) {
		this.vrsta = vrsta;
	}

	public TipRobe getTip() {
		return tip;
	}

	public void setTip(TipRobe tip) {
		this.tip = tip;
	}

	public String getNaziv() {
		return naziv;
	}
	
	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public double getUlaznaCena() {
		return ulaznaCena;
	}

	public void setUlaznaCena(double ulaznaCena) {
		this.ulaznaCena = ulaznaCena;
	}

	public double getProdajnaCena() {
		return prodajnaCena;
	}

	public void setProdajnaCena(double prodajnaCena) {
		this.prodajnaCena = prodajnaCena;
	}

	public int getUlaz() {
		return ulaz;
	}

	public void setUlaz(int ulaz) {
		this.ulaz = ulaz;
	}

	public int getIzlaz() {
		return izlaz;
	}

	public void setIzlaz(int izlaz) {
		this.izlaz = izlaz;
	}

	public int getStanje() {
		return stanje;
	}

	public void setStanje(int stanje) {
		this.stanje = stanje;
	}

	public double getPakovanje() {
		return pakovanje;
	}

	public void setPakovanje(double pakovanje) {
		this.pakovanje = pakovanje;
	}

	public LocalDate getDatumProizvodnje() {
		return datumProizvodnje;
	}

	public void setDatumProizvodnje(LocalDate datumProizvodnje) {
		this.datumProizvodnje = datumProizvodnje;
	}

	public int getRokTrajanja() {
		return rokTrajanja;
	}

	public void setRokTrajanja(int rokTrajanja) {
		this.rokTrajanja = rokTrajanja;
	}

	public String getJedinicaMere() {
		return jedinicaMere;
	}

	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}

	public List<Kupac> getKupci() {
		return kupci;
	}

	public void setKupci(List<Kupac> kupci) {
		this.kupci = kupci;
	}

	public Proizvodjac getProizvodjac() {
		return proizvodjac;
	}

	public Roba() {
		super();
	}
	
	
	
}
