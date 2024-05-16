package Modello_giocatore;

import model.CartaOro;
import model.CartaRisorsa;

public class ManoGiocatore {

	private CartaRisorsa CartaR1;
	private CartaRisorsa CartaR2;
	private CartaOro CartaO1;
	
	public ManoGiocatore() {
		this.CartaR1=new CartaRisorsa();
		this.CartaR2=new CartaRisorsa();
		this.CartaO1=new CartaOro();
	}
	
	public void creaMano (CartaRisorsa CartaR1, CartaRisorsa CartaR2, CartaOro CartaO1) {
		this.CartaR1=CartaR1;
		this.CartaR2=CartaR2;
		this.CartaO1=CartaO1;
	}
	
	public void rimuoviCartaR (CartaRisorsa CR) {
		if(CR==CartaR1)
		{
			CartaR1=null;
		}
		else
		{
			CartaR2=null;
		}
	}
	
	public void aggiungiCartaR (CartaRisorsa CR) {
		if(CartaR1==null)
		{
			CartaR1=CR;
		}
		else
		{
			CartaR2=CR;
		}
	}
	
	public void rimuoviCartaO (CartaOro CO) {
		CartaO1=null;
	}
	
	public void aggiungiCartaO (CartaOro CO) {
		CartaO1=CO;
	}
	
	//creare stampa mano se non c'è l'interfaccia

		
}
