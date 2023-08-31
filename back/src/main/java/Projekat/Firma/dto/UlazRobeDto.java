package Projekat.Firma.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UlazRobeDto {

	private Long id;
	private Long ulazId;
	private String ulazBrojFakture;
	
	@NotNull
	private Long robaId;
	private double robaPakovanje;
	private String robaNaziv;
	private String robaJedinicaMere;
	
	@NotNull
	@Positive
	
	private int kolicina;
	
	@NotNull
	private double cenaPoJediniciMere;
	
	@NotNull
	private double rabat;
	private int pdv;
	private double krajnjaCena;
	private double krajnjaCenaPoJediniciMere;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUlazBrojFakture() {
		return ulazBrojFakture;
	}
	public double getKrajnjaCenaPoJediniciMere() {
		return krajnjaCenaPoJediniciMere;
	}
	public void setKrajnjaCenaPoJediniciMere(double krajnjaCenaPoJediniciMere) {
		this.krajnjaCenaPoJediniciMere = krajnjaCenaPoJediniciMere;
	}
	public void setUlazBrojFakture(String ulazBrojFakture) {
		this.ulazBrojFakture = ulazBrojFakture;
	}
	public double getRobaPakovanje() {
		return robaPakovanje;
	}
	public void setRobaPakovanje(double robaPakovanje) {
		this.robaPakovanje = robaPakovanje;
	}
	public String getRobaNaziv() {
		return robaNaziv;
	}
	public void setRobaNaziv(String robaNaziv) {
		this.robaNaziv = robaNaziv;
	}
	public String getRobaJedinicaMere() {
		return robaJedinicaMere;
	}
	public void setRobaJedinicaMere(String robaJedinicaMere) {
		this.robaJedinicaMere = robaJedinicaMere;
	}
	public Long getUlazId() {
		return ulazId;
	}
	public void setUlazId(Long ulazId) {
		this.ulazId = ulazId;
	}
	public Long getRobaId() {
		return robaId;
	}
	public void setRobaId(Long robaId) {
		this.robaId = robaId;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
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
	public UlazRobeDto() {
		super();
	}
	
	
}
