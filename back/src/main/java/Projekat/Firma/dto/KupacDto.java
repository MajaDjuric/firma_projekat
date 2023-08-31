package Projekat.Firma.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class KupacDto {

	private Long id;
	
	@NotNull
	private String naziv;
	
	@NotNull
	@Length(min = 9, max = 9)
	private String pib;
	
	@NotNull
	@Length(min = 8, max = 8)
	private String mb;
	
	@NotNull
	private String adresa;
	
	@NotNull
	private String grad;
	
	@NotNull
	private String teren;
	
	private Long komercijalistaId;
	
	private String komercijalistaImeIPrezime;
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
	public Long getKomercijalistaId() {
		return komercijalistaId;
	}
	public void setKomercijalistaId(Long komercijalistaId) {
		this.komercijalistaId = komercijalistaId;
	}
	public String getKomercijalistaImeIPrezime() {
		return komercijalistaImeIPrezime;
	}
	public void setKomercijalistaImeIPrezime(String komercijalistaImeIPrezime) {
		this.komercijalistaImeIPrezime = komercijalistaImeIPrezime;
	}
	public KupacDto() {
		super();
	}
	
	
	
}
