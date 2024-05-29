package obiettiviModels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazzoObiettivo {
	private List<CartaObiettivoDisp> obiettivi;
	private int puntatore;
	
	// Costruttore del Mazzo di obiettivi + carica le carte da un file
	public MazzoObiettivo(String filename) {
		this.obiettivi = new ArrayList<>();
		this.puntatore = 0;
		obiettivi.addAll(CartaObiettivoDisp.leggiObiettivi(filename));
	}
	
	// Metodo per pescare una carta dal mazzo
	public CartaObiettivoDisp pescaObiettivo(){
		if (puntatore < obiettivi.size()) {
			return obiettivi.get(puntatore++);
		}
		return null; // Se il mazzo è vuoto, ritorna null
	}
		
	// Metodo per mescolare le carte nel mazzo
	public void mescolaObiettivi() {
		Collections.shuffle(obiettivi);
	}
	
	// Metodo per ottenere la lista di obiettivi nel mazzo
	public List<CartaObiettivoDisp> getObiettivi() {
		return obiettivi;
	}

	// Getter per il puntatore
	public int getPuntatore() {
		return puntatore;
	}
}
