package Projekat.Firma.dto;

import java.util.List;

public class DispozicijaDto {

	private Long id;
	private String datumIsporuke;
	private Long vozacId;
	private String vozacImeIPrezime;
	private VoziloDto vozilo;
	private boolean isporuceno;
	private List<Long> trebovanjaIds;
	private List<TrebovanjeRobeDto> trebovanjaRobe;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getTrebovanjaIds() {
		return trebovanjaIds;
	}
	public void setTrebovanjaIds(List<Long> trebovanjaIds) {
		this.trebovanjaIds = trebovanjaIds;
	}
	public String getDatumIsporuke() {
		return datumIsporuke;
	}
	public void setDatumIsporuke(String datumIsporuke) {
		this.datumIsporuke = datumIsporuke;
	}
	public Long getVozacId() {
		return vozacId;
	}
	public void setVozacId(Long vozacId) {
		this.vozacId = vozacId;
	}
	public String getVozacImeIPrezime() {
		return vozacImeIPrezime;
	}
	public void setVozacImeIPrezime(String vozacImeIPrezime) {
		this.vozacImeIPrezime = vozacImeIPrezime;
	}
	public VoziloDto getVozilo() {
		return vozilo;
	}
	public void setVozilo(VoziloDto vozilo) {
		this.vozilo = vozilo;
	}
	public boolean isIsporuceno() {
		return isporuceno;
	}
	public void setIsporuceno(boolean isporuceno) {
		this.isporuceno = isporuceno;
	}

	public List<TrebovanjeRobeDto> getTrebovanjaRobe() {
		return trebovanjaRobe;
	}
	public void setTrebovanjaRobe(List<TrebovanjeRobeDto> trebovanjaRobe) {
		this.trebovanjaRobe = trebovanjaRobe;
	}
	public DispozicijaDto() {
		super();
	}
	
	
}
