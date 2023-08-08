package Projekat.Firma.dto;

public class TrebovanjeRobeDto {

	private Long id;
	private Long robaId;
	private String robaNaziv;
	private double robaPakovanje;
	private String robaJedinicaMere;
	private Long trebovanjeId;
	private boolean disponirano;
	private long dispozicijaId;
	private boolean isporuceno;
	private int kolicina;
	private KupacDto kupac;
	public Long getId() {
		return id;
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
	public void setId(Long id) {
		this.id = id;
	}
	
	public KupacDto getKupac() {
		return kupac;
	}
	public void setKupac(KupacDto kupac) {
		this.kupac = kupac;
	}
	public Long getRobaId() {
		return robaId;
	}
	public void setRobaId(Long robaId) {
		this.robaId = robaId;
	}
	public String getRobaNaziv() {
		return robaNaziv;
	}
	public void setRobaNaziv(String robaNaziv) {
		this.robaNaziv = robaNaziv;
	}
	public double getRobaPakovanje() {
		return robaPakovanje;
	}
	public void setRobaPakovanje(double robaPakovanje) {
		this.robaPakovanje = robaPakovanje;
	}
	public String getRobaJedinicaMere() {
		return robaJedinicaMere;
	}
	public void setRobaJedinicaMere(String robaJedinicaMere) {
		this.robaJedinicaMere = robaJedinicaMere;
	}
	public Long getTrebovanjeId() {
		return trebovanjeId;
	}
	public void setTrebovanjeId(Long trebovanjeId) {
		this.trebovanjeId = trebovanjeId;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public TrebovanjeRobeDto() {
		super();
	}
	public long getDispozicijaId() {
		return dispozicijaId;
	}
	public void setDispozicijaId(long dispozicijaId) {
		this.dispozicijaId = dispozicijaId;
	}
	
}
