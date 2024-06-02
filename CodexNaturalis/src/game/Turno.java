package game;

import modelPlayer.Giocatore;

public class Turno {

    private Giocatore[] giocatori;
    private int turnoCorrente;
    private boolean partitaTerminata;

    public Turno(Giocatore[] giocatori) {
        this.giocatori = giocatori;
        this.turnoCorrente = 0;
        this.partitaTerminata = false;
    }

    public Giocatore getGiocatoreCorrente() {
        return giocatori[turnoCorrente];
    }

    public void prossimoTurno() {
        turnoCorrente = (turnoCorrente + 1) % giocatori.length;
    }

    public boolean isPartitaTerminata() {
        return partitaTerminata;
    }

    public void controllaPunteggio() {
        Giocatore giocatoreCorrente = getGiocatoreCorrente();
        if (giocatoreCorrente.getPunti() >= 20) {
            partitaTerminata = true;
            System.out.println("La partita è terminata! Il vincitore è: " + giocatoreCorrente.getNome());
        }
    }
}
