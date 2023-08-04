package JWD64.Test.dto;

import java.util.ArrayList;
import java.util.List;

public class UlazDto {

	private Long id;
	private String datumUlaza;
	private String brojFakture;
	private String brojOtpremnice;
//	private int kolicina;
//	private double cenaPoJediniciMere;
//	private double rabat;
	private Long proizvodjacId;
	private String proizvodjacNaziv;
	private List<RobaDto> roba = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDatumUlaza() {
		return datumUlaza;
	}
	public void setDatumUlaza(String datumUlaza) {
		this.datumUlaza = datumUlaza;
	}
	public String getBrojFakture() {
		return brojFakture;
	}
	public void setBrojFakture(String brojFakture) {
		this.brojFakture = brojFakture;
	}
	public String getBrojOtpremnice() {
		return brojOtpremnice;
	}
	public void setBrojOtpremnice(String brojOtpremnice) {
		this.brojOtpremnice = brojOtpremnice;
	}
//	public int getKolicina() {
//		return kolicina;
//	}
//	public void setKolicina(int kolicina) {
//		this.kolicina = kolicina;
//	}
//	public double getCenaPoJediniciMere() {
//		return cenaPoJediniciMere;
//	}
//	public void setCenaPoJediniciMere(double cenaPoJediniciMere) {
//		this.cenaPoJediniciMere = cenaPoJediniciMere;
//	}
//	public double getRabat() {
//		return rabat;
//	}
//	public void setRabat(double rabat) {
//		this.rabat = rabat;
//	}
	public Long getProizvodjacId() {
		return proizvodjacId;
	}
	public void setProizvodjacId(Long proizvodjacId) {
		this.proizvodjacId = proizvodjacId;
	}
	public String getProizvodjacNaziv() {
		return proizvodjacNaziv;
	}
	public void setProizvodjacNaziv(String proizvodjacNaziv) {
		this.proizvodjacNaziv = proizvodjacNaziv;
	}
	public List<RobaDto> getRoba() {
		return roba;
	}
	public void setRoba(List<RobaDto> roba) {
		this.roba = roba;
	}
	
}
