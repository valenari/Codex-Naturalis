package Modello_giocatore;

import Base.PedinaC;
import cardsModel.Iniziale;


public class Giocatore {
    private String nome;
    private int punti;
    private PedinaC pedina;
    private Iniziale cartaI;
    //private ObbiettiPersonale ObbiettivoP;
    private ManoGiocatore manoG;
    private boolean pedinaPrimoGiocatore;
    private AreaDiGioco areaG;
    // Altri attributi e metodi necessari per gestire lo stato del giocatore

    public Giocatore(String nome) {
    	
    }
    public Giocatore(String nome, PedinaC pedina, boolean pedinaPrimoGiocatore) {
        this.nome = nome;
        this.punti = 0;
        this.pedina = pedina;
        this.cartaI = new Iniziale();
        //this.ObbiettivoP = new ObbiettivoPersonale();
        this.manoG = new ManoGiocatore();
        this.pedinaPrimoGiocatore = pedinaPrimoGiocatore;
        this.areaG= new AreaDiGioco();
        // Inizializza gli altri attributi del giocatore se necessario
    }

    // Metodo per ottenere il nome del giocatore
    public String getNome() {
        return nome;
    }

    // Metodo per ottenere i punti del giocatore
    public int getPunti() {
        return punti;
    }

    // Metodo per aggiungere punti al giocatore
    public void aggiungiPunti(int puntiDaAggiungere) {
        this.punti += puntiDaAggiungere;
    }
    
    public boolean controlloPunteggio() {
    	if(this.punti>=20) {
    		return true;
    	}
    	return false;
    }

    // Altri metodi per gestire le azioni del giocatore, come giocare una carta, ottenere carte bonus, ecc.
}
