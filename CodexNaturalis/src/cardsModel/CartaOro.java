package cardsModel;

public class CartaOro extends Carta{
	private String tipoRegno;
	private int punti;
	private int risorseNecessarie[]; //forse meglio usare string anche qua?
	private String criterioPunti;
	private boolean usata = false; // indicatore per verificare se la carta è già stata usata in un obbiettivo di disposizione
	
	
	
	public String getTipoRegno() {
		return tipoRegno;
	}
	public void setTipoRegno(String tipoRegno) {
		this.tipoRegno = tipoRegno;
	}
	
	
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	
	
	public boolean isUsata() {
		return usata;
	}
	public void setUsata(boolean usata) {
		this.usata = true;
	}
	
	
	public int[] getRisorseNecessarie() {
		return risorseNecessarie;
	}
	public void setRisorseNecessarie(int risorseNecessarie[]) {
		this.risorseNecessarie = risorseNecessarie;
	}
	
	
	public String getCriterioPunti() {
		return criterioPunti;
	}
	public void setCriterioPunti(String criterioPunti) {
		this.criterioPunti = criterioPunti;
	}
}
