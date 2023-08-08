package Projekat.Firma.dto;

public class RobaDto {

	private Long id;
	private Long vrstaId;
	private String vrstaNaziv;
	private Long tipId;
	private String tipNaziv;
	private String naziv;
	private Long proizvodjacId;
	private String proizvodjacNaziv;
	private double ulaznaCena;
	private double prodajnaCena;
	private int ulaz;
	private int stanje;
	private int izlaz;
	private double pakovanje;
	private String datumProizvodnje;
	private int rokTrajanja;
	private String jedinicaMere;
	private String tretman;
	private int kolicina;
	
//	private Map<Long, String> kupci = new HashMap<>();
		
	public Long getId() {
		return id;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStanje() {
		return stanje;
	}
	public void setStanje(int stanje) {
		this.stanje = stanje;
	}
	public Long getVrstaId() {
		return vrstaId;
	}
	public void setVrstaId(Long vrstaId) {
		this.vrstaId = vrstaId;
	}
	public String getVrstaNaziv() {
		return vrstaNaziv;
	}
	public void setVrstaNaziv(String vrstaNaziv) {
		this.vrstaNaziv = vrstaNaziv;
	}
	public Long getTipId() {
		return tipId;
	}
	public void setTipId(Long tipId) {
		this.tipId = tipId;
	}
	public String getTipNaziv() {
		return tipNaziv;
	}
	public void setTipNaziv(String tipNaziv) {
		this.tipNaziv = tipNaziv;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

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
	public double getUlaznaCena() {
		return ulaznaCena;
	}
	public void setUlaznaCena(double ulaznaCena) {
		this.ulaznaCena = ulaznaCena;
	}
	public double getProdajnaCena() {
		return prodajnaCena;
	}
	public void setProdajnaCena(double prodajnaCena) {
		this.prodajnaCena = prodajnaCena;
	}
	public int getUlaz() {
		return ulaz;
	}
	public void setUlaz(int ulaz) {
		this.ulaz = ulaz;
	}
	public int getIzlaz() {
		return izlaz;
	}
	public void setIzlaz(int izlaz) {
		this.izlaz = izlaz;
	}
	public double getPakovanje() {
		return pakovanje;
	}
	public void setPakovanje(double pakovanje) {
		this.pakovanje = pakovanje;
	}
	public String getDatumProizvodnje() {
		return datumProizvodnje;
	}
	public void setDatumProizvodnje(String datumProizvodnje) {
		this.datumProizvodnje = datumProizvodnje;
	}
	public int getRokTrajanja() {
		return rokTrajanja;
	}
	public void setRokTrajanja(int rokTrajanja) {
		this.rokTrajanja = rokTrajanja;
	}
	public String getJedinicaMere() {
		return jedinicaMere;
	}
	public void setJedinicaMere(String jedinicaMere) {
		this.jedinicaMere = jedinicaMere;
	}
	public String getTretman() {
		return tretman;
	}
	public void setTretman(String tretman) {
		this.tretman = tretman;
	}
//	public Map<Long, String> getKupci() {
//		return kupci;
//	}
//	public void setKupci(Map<Long, String> kupci) {
//		this.kupci = kupci;
//	}
	public RobaDto() {
		super();
	}
	
	
	
	
}
