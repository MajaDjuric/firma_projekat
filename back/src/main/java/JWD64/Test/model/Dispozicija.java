package JWD64.Test.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Dispozicija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "dispozicija")
	private List<TrebovanjeRobe> trebovanjaRobe = new ArrayList<>();
	
	@Column 
	private LocalDate datumIsporuke;
	
	@ManyToOne
	@JoinColumn
	private Korisnik vozac;
	
	@ManyToOne 
	@JoinColumn
	private Vozilo vozilo;
	
	@Column
	private boolean isporuceno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public List<TrebovanjeRobe> getTrebovanjaRobe() {
		return trebovanjaRobe;
	}

	public void setTrebovanjaRobe(List<TrebovanjeRobe> trebovanjaRobe) {
		this.trebovanjaRobe = trebovanjaRobe;
	}

	public LocalDate getDatumIsporuke() {
		return datumIsporuke;
	}

	public void setDatumIsporuke(LocalDate datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}

	public Korisnik getVozac() {
		return vozac;
	}

	public void setVozac(Korisnik vozac) {
		this.vozac = vozac;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

	public boolean isIsporuceno() {
		return isporuceno;
	}

	public void setIsporuceno(boolean isporuceno) {
		this.isporuceno = isporuceno;
	}

	public Dispozicija() {
		super();
	}
	
	

}