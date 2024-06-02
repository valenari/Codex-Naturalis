package game;

import modelPlayer.Giocatore;
import modelObiettivi.Obiettivo;

import java.util.List;

public class Turno {
    private List<Giocatore> giocatori;
    private List<Obiettivo> obiettiviComuni;
    private int turnoCorrente;
    private boolean partitaTerminata;

    public Turno(List<Giocatore> giocatori, List<Obiettivo> obiettiviComuni) {
        this.giocatori = giocatori;
        this.obiettiviComuni = obiettiviComuni;
        this.turnoCorrente = 0;
        this.partitaTerminata = false;
    }

    public Giocatore getGiocatoreCorrente() {
        return giocatori.get(turnoCorrente);
    }

    public void prossimoTurno() {
        turnoCorrente = (turnoCorrente + 1) % giocatori.size();
    }

    public boolean isPartitaTerminata() {
        return partitaTerminata;
    }

    public void controllaPunteggio() {
        Giocatore giocatoreCorrente = getGiocatoreCorrente();
        if (giocatoreCorrente.getPunti() >= 20) {
            partitaTerminata = true;
            System.out.println("La partita è terminata! Il vincitore è: " + giocatoreCorrente.getNome());
            calcolaPunteggiFinali();
        }
    }

    private void calcolaPunteggiFinali() {
        for (Giocatore giocatore : giocatori) {
            int puntiObiettivi = giocatore.calcolaPuntiObiettivi(obiettiviComuni);
            giocatore.aggiungiPunti(puntiObiettivi);
            System.out.println("Punti finali di " + giocatore.getNome() + ": " + giocatore.getPunti());
        }
        Giocatore vincitore = giocatori.stream().max((g1, g2) -> Integer.compare(g1.getPunti(), g2.getPunti())).orElse(null);
        System.out.println("Il vincitore è: " + (vincitore != null ? vincitore.getNome() : "Nessuno"));
    }
}
