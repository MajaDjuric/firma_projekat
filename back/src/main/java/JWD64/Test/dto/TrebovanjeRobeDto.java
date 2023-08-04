package JWD64.Test.dto;

public class TrebovanjeRobeDto {

	private Long id;
	private Long robaId;
	private String robaNaziv;
	private double robaPakovanje;
	private String robaJedinicaMere;
	private Long trebovanjeId;
	private boolean disponirano;
	private boolean isporuceno;
	private int kolicina;
	private KupacDto kupac;
//	private String kupacTeren;
//	private String kupacGrad;
//	private String komercijalistaTeren;
//	private String kupacAdresa;
//	private String kupacNaziv;	
//	private String komercijalistaIme;
//	private String komercijalistaPrezime;
	
	
//	public String getKupacNaziv() {
//		return kupacNaziv; 
//	}
//	public void setKupacNaziv(String kupacNaziv) {
//		this.kupacNaziv = kupacNaziv;
//	}
//	public String getKomercijalistaIme() {
//		return komercijalistaIme;
//	}
//	public void setKomercijalistaIme(String komercijalistaIme) {
//		this.komercijalistaIme = komercijalistaIme;
//	}
//	public String getKomercijalistaPrezime() {
//		return komercijalistaPrezime;
//	}
//	public void setKomercijalistaPrezime(String komercijalistaPrezime) {
//		this.komercijalistaPrezime = komercijalistaPrezime;
//	}
//	public String getKupacAdresa() {
//		return kupacAdresa;
//	}
//	public void setKupacAdresa(String kupacAdresa) {
//		this.kupacAdresa = kupacAdresa;
//	}
//	public String getKupacTeren() {
//		return kupacTeren;
//	}
//	public void setKupacTeren(String kupacTeren) {
//		this.kupacTeren = kupacTeren;
//	}
//	public String getKupacGrad() {
//		return kupacGrad;
//	}
//	public void setKupacGrad(String kupacGrad) {
//		this.kupacGrad = kupacGrad;
//	}
//	public String getKomercijalistaTeren() {
//		return komercijalistaTeren;
//	}
//	public void setKomercijalistaTeren(String komercijalistaTeren) {
//		this.komercijalistaTeren = komercijalistaTeren;
//	}
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
	
}
