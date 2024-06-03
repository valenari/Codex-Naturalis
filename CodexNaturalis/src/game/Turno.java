package game;

import modelPlayer.Giocatore;
import cardsModel.MazzoCarte;

import java.util.List;

public class Turno {
    private List<Giocatore> giocatori;
    private int indiceGiocatoreCorrente;
    private boolean partitaTerminata;
    private MazzoCarte mazzoRisorsa;
    private MazzoCarte mazzoOro;

    public Turno(List<Giocatore> giocatori, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.giocatori = giocatori;
        this.indiceGiocatoreCorrente = 0;
        this.partitaTerminata = false;
        this.mazzoRisorsa = mazzoRisorsa;
        this.mazzoOro = mazzoOro;
    }

    public Giocatore getGiocatoreCorrente() {
        return giocatori.get(indiceGiocatoreCorrente);
    }

    public void prossimoTurno() {
        indiceGiocatoreCorrente = (indiceGiocatoreCorrente + 1) % giocatori.size();
    }

    public boolean isPartitaTerminata() {
        return partitaTerminata;
    }

    public void controllaPunteggio() {
        for (Giocatore giocatore : giocatori) {
            if (giocatore.getPunti() >= 20 || (mazzoRisorsa.isVuoto() && mazzoOro.isVuoto())) {
                partitaTerminata = true;
                break;
            }
        }
    }
}
