package Projekat.Firma.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class TrebovanjeDto {

	private Long id;
	private Long komercijalistaId;
	private String komercijalistaIme;
	private String komercijlistaPrezime;
	private boolean disponirano;
	private boolean isporuceno;
	private String datumTrebovanja;
	
	@NotNull
	private KupacDto kupacDto;
	
	public KupacDto getKupacDto() {
		return kupacDto;
	}
	public void setKupacDto(KupacDto kupacDto) {
		this.kupacDto = kupacDto;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getKomercijalistaId() {
		return komercijalistaId;
	}
	public void setKomercijalistaId(Long komercijalistaId) {
		this.komercijalistaId = komercijalistaId;
	}
	public String getKomercijalistaIme() {
		return komercijalistaIme;
	}
	public void setKomercijalistaIme(String komercijalistaIme) {
		this.komercijalistaIme = komercijalistaIme;
	}
	public String getKomercijlistaPrezime() {
		return komercijlistaPrezime;
	}
	public void setKomercijlistaPrezime(String komercijlistaPrezime) {
		this.komercijlistaPrezime = komercijlistaPrezime;
	}
	public String getDatumTrebovanja() {
		return datumTrebovanja;
	}
	public void setDatumTrebovanja(String datumTrebovanja) {
		this.datumTrebovanja = datumTrebovanja;
	}
	public TrebovanjeDto() {
		super();
	}
	
	
	
}
