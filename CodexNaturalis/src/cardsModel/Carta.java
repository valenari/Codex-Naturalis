package cardsModel;

import Modello_giocatore.Caselleproibite;

public class Carta {
	private int idCarta;
	private String tipoCarta;
	private String fronte;
	private String retro; 
	private Caselleproibite caselle;
	
	// da ricordarsi che il retro di carte risorsa e oro ha una risorsa centrale oltre ai 4 angoli liberi
	public int getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(int idCarta) {
		this.idCarta = idCarta;
	}
	public String getTipoCarta() {
		return tipoCarta;
	}
	public void setTipoCarta(String tipoCarta) {
		this.tipoCarta = tipoCarta;
	}
	public String getFronte() {
		return fronte;
	}
	public void setFronte(String fronte) {
		this.fronte = fronte;
	}
	public String getRetro() {
		return retro;
	}
	public void setRetro(String retro) {
		this.retro = retro;
	}
	public Caselleproibite getCaselle() {
		return caselle;
	}
	public void setCaselle(Caselleproibite caselle) {
		this.caselle = caselle;
	}
	
	
}
