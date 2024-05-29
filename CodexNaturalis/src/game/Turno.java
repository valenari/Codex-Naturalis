package game;

import Modello_giocatore.Giocatore;

public class Turno {
		
	private Giocatore[] giocatori;
	private int turnoCorrente;
	
	public Turno(Giocatore[] giocatori) {
		this.giocatori = giocatori;
		this.turnoCorrente = 0;
	}
	
	public Giocatore getGiocatoreCorrente() {
	     return giocatori[turnoCorrente];
	}

	public void prossimoTurno() {
	     turnoCorrente = (turnoCorrente + 1) % giocatori.length;
	}
	
	/*public boolean controlloPunteggio() {
		if(this.giocatori[turnoCorrente].getPunti()==20) {
				if()
		}
	}*/
}
