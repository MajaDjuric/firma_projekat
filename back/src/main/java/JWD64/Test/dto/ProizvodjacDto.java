package JWD64.Test.dto;

import javax.persistence.Column;

public class ProizvodjacDto {

	private Long id;
	private String naziv;
	private String pib;
	private String mb;
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
	public ProizvodjacDto() {
		super();
	}
	
	
}
