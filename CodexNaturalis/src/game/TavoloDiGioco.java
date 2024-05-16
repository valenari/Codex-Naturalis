package game;

import java.util.ArrayList;
import java.util.List;

import Modello_giocatore.Giocatore;

public class TavoloDiGioco {
    private List<Giocatore> giocatori;

    public TavoloDiGioco(List<String> nomiGiocatori) {
        giocatori = new ArrayList<>();
        for (String nome : nomiGiocatori) {
            giocatori.add(new Giocatore(nome));
        }
    }

    // Metodo per eseguire il turno del giocatore corrente
    public void eseguiTurno() {
        // Aggiungi qui la logica per eseguire il turno del giocatore corrente
    }

    // Metodo per verificare se la partita è terminata
    public boolean isPartitaTerminata() {
        // Aggiungi qui la logica per determinare se la partita è terminata
        return false; // Esempio: restituisci true se la partita è terminata, altrimenti false
    }

    // Metodo per ottenere il vincitore della partita
    public int getVincitore() {
        // Aggiungi qui la logica per determinare il vincitore della partita
        return 0; // Esempio: restituisci l'indice del giocatore vincitore
    }

    // Metodo per ottenere l'elenco dei giocatori
    public List<Giocatore> getGiocatori() {
        return giocatori;
    }
}
