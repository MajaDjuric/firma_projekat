package Projekat.Firma.dto;

public class VrstaRobeDto {

	private Long id;
	private String naziv;
	private int pdv;
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
	public int getPdv() {
		return pdv;
	}
	public void setPdv(int pdv) {
		this.pdv = pdv;
	}
	public VrstaRobeDto() {
		super();
	}
	
	
	
}
