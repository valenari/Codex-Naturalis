package game;

import modelPlayer.Giocatore;
import modelObiettivi.Obiettivo;
import java.util.List;

public class Turno {
    private List<Giocatore> giocatori;
    private List<Obiettivo> obiettiviComuni;
    private int turnoCorrente;
    private boolean partitaTerminata;
    private boolean turnoAddizionale;
    private int numeroTurniAddizionali;

    public Turno(List<Giocatore> giocatori, List<Obiettivo> obiettiviComuni) {
        this.giocatori = giocatori;
        this.obiettiviComuni = obiettiviComuni;
        this.turnoCorrente = 0;
        this.partitaTerminata = false;
        this.turnoAddizionale = false;
        this.numeroTurniAddizionali = 0;
    }

    public Giocatore getGiocatoreCorrente() {
        return giocatori.get(turnoCorrente);
    }

    public void prossimoTurno() {
        turnoCorrente = (turnoCorrente + 1) % giocatori.size();
        if (turnoAddizionale) {
            numeroTurniAddizionali--;
            if (numeroTurniAddizionali <= 0) {
                partitaTerminata = true;
                System.out.println("La partita è terminata! Il vincitore è: " + determinareVincitore());
            }
        }
    }

    public boolean isPartitaTerminata() {
        return partitaTerminata;
    }

    public void controllaPunteggio() {
        Giocatore giocatoreCorrente = getGiocatoreCorrente();
        if (giocatoreCorrente.getPunti() >= 20) {
            innescaFinePartita();
        }
    }

    public void controllaMazzi(boolean mazzoRisorsaVuoto, boolean mazzoOroVuoto) {
        if (mazzoRisorsaVuoto && mazzoOroVuoto) {
            innescaFinePartita();
        }
    }

    private void innescaFinePartita() {
        if (!turnoAddizionale) {
            turnoAddizionale = true;
            numeroTurniAddizionali = giocatori.size();
            System.out.println("Innescata la fine della partita. Ogni giocatore avrà un turno addizionale.");
        }
    }

    private String determinareVincitore() {
        Giocatore vincitore = null;
        int maxPunti = 0;
        int maxObiettiviRaggiunti = 0;

        for (Giocatore giocatore : giocatori) {
            int puntiFinali = giocatore.getPunti();
            for (Obiettivo obiettivo : obiettiviComuni) {
                if (obiettivo.verificaObiettivo(giocatore.getAreaDiGioco(), giocatore.getContatori())) {
                    puntiFinali += obiettivo.getPunti();
                }
            }
            if (giocatore.getObiettivoSegreto().verificaObiettivo(giocatore.getAreaDiGioco(), giocatore.getContatori())) {
                puntiFinali += giocatore.getObiettivoSegreto().getPunti();
            }

            giocatore.aggiungiPunti(puntiFinali - giocatore.getPunti());

            if (puntiFinali > maxPunti || (puntiFinali == maxPunti && giocatore.getObiettiviRaggiunti() > maxObiettiviRaggiunti)) {
                maxPunti = puntiFinali;
                maxObiettiviRaggiunti = giocatore.getObiettiviRaggiunti();
                vincitore = giocatore;
            }
        }
        return vincitore != null ? vincitore.getNome() : "Nessuno";
    }
}
