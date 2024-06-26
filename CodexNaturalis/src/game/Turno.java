package game;

import modelPlayer.Giocatore;
import modelObiettivi.Obiettivo;
import modelTavolo.AreaDiPesca;
import cardsModel.MazzoCarte;

import java.util.ArrayList;
import java.util.List;

public class Turno {
    private List<Giocatore> giocatori;
    private int indiceGiocatoreCorrente;
    private boolean partitaTerminata;
    private AreaDiPesca areaDiPesca;
    private MazzoCarte mazzoRisorsa;
    private MazzoCarte mazzoOro;
    private boolean finePartitaInnescata;

    public Turno(List<Giocatore> giocatori, AreaDiPesca areaDiPesca, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.giocatori = giocatori;
        this.indiceGiocatoreCorrente = 0;
        this.partitaTerminata = false;
        this.areaDiPesca = areaDiPesca;
        this.mazzoRisorsa = mazzoRisorsa;
        this.mazzoOro = mazzoOro;
        this.finePartitaInnescata = false;
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
                finePartitaInnescata = true;
                break;
            }
        }
        if (finePartitaInnescata) {
            partitaTerminata = true;
        }
    }

    public void calcolaPuntiFinali(List<Obiettivo> obiettiviComuni) {
        for (Giocatore giocatore : giocatori) {
            giocatore.calcolaPuntiObiettivi(obiettiviComuni);
        }

        int maxPunti = giocatori.stream().mapToInt(Giocatore::getPunti).max().orElse(0);
        List<Giocatore> vincitori = new ArrayList<>();

        for (Giocatore giocatore : giocatori) {
            if (giocatore.getPunti() == maxPunti) {
                vincitori.add(giocatore);
            }
        }

        if (vincitori.size() > 1) {
            // Gestione del caso di pareggio
            int maxObiettivi = vincitori.stream().mapToInt(Giocatore::getNumeroObiettivi).max().orElse(0);
            List<Giocatore> vincitoriFinali = new ArrayList<>();

            for (Giocatore vincitore : vincitori) {
                if (vincitore.getNumeroObiettivi() == maxObiettivi) {
                    vincitoriFinali.add(vincitore);
                }
            }

            if (vincitoriFinali.size() > 1) {
                System.out.println("La partita è terminata in pareggio tra i seguenti giocatori:");
                for (Giocatore vincitore : vincitoriFinali) {
                    System.out.println(vincitore.getNome());
                }
            } else {
                System.out.println("Il vincitore è: " + vincitoriFinali.get(0).getNome());
            }
        } else {
            System.out.println("Il vincitore è: " + vincitori.get(0).getNome());
        }
    }

    public void dichiaraVincitore() {
        Giocatore vincitore = null;
        int maxPunti = -1;
        for (Giocatore giocatore : giocatori) {
            int punti = giocatore.getPunti();
            if (punti > maxPunti) {
                maxPunti = punti;
                vincitore = giocatore;
            } else if (punti == maxPunti) {
                if (vincitore != null && giocatore.getNumeroObiettivi() > vincitore.getNumeroObiettivi()) {
                    vincitore = giocatore;
                }
            }
        }
        if (vincitore != null) {
            System.out.println("Il vincitore è: " + vincitore.getNome() + " con " + vincitore.getPunti() + " punti!");
        } else {
            System.out.println("La partita è terminata in parità!");
        }
    }
}
