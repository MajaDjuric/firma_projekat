package JWD64.Test.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TrebovanjeRobe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne
	 @JoinColumn(name = "trebovanje_id")
	 private Trebovanje trebovanje;

	 @ManyToOne
	 @JoinColumn(name = "roba_id")
	  private Roba roba;
	 
	 @Column
	 private int kolicina;

	 @ManyToOne
	 private Dispozicija dispozicija;
	 
	 @Column
	 private boolean disponirano;
		
	 @Column
	 private boolean isporuceno;
	 
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dispozicija getDispozicija() {
		return dispozicija;
	}

	public void setDispozicija(Dispozicija dispozicija) {
		this.dispozicija = dispozicija;
	}

	public Trebovanje getTrebovanje() {
		return trebovanje;
	}

	public void setTrebovanje(Trebovanje trebovanje) {
		this.trebovanje = trebovanje;
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

	public TrebovanjeRobe() {
		super();
	}

	public TrebovanjeRobe(Trebovanje trebovanje, Roba roba, int kolicina) {
		super();
		this.trebovanje = trebovanje;
		this.roba = roba;
		this.kolicina = kolicina;
	}
	 
	 
}
