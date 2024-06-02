package modelObiettivi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazzoObiettivi {
    private List<Obiettivo> obiettivi;
    private int puntatore;

    public MazzoObiettivi(String filenameDisposizione, String filenameRisorse) {
        this.obiettivi = new ArrayList<>();
        this.puntatore = 0;
        this.caricaObiettiviDaFile(filenameDisposizione, filenameRisorse);
        this.mescolaMazzo();
    }

    public void aggiungiObiettivo(Obiettivo obiettivo) {
        obiettivi.add(obiettivo);
    }

    public Obiettivo pescaObiettivo() {
        if (puntatore < obiettivi.size()) {
            return obiettivi.get(puntatore++);
        }
        return null;
    }

    public List<Obiettivo> pescaObiettivi(int numero) {
        List<Obiettivo> pescati = new ArrayList<>();
        for (int i = 0; i < numero && puntatore < obiettivi.size(); i++) {
            pescati.add(obiettivi.get(puntatore++));
        }
        return pescati;
    }

    public void mescolaMazzo() {
        Collections.shuffle(obiettivi);
    }

    private void caricaObiettiviDaFile(String filenameDisposizione, String filenameRisorse) {
        obiettivi.addAll(CaricatoreObiettivi.caricaObiettiviDisposizione(filenameDisposizione));
        obiettivi.addAll(CaricatoreObiettivi.caricaObiettiviRisorse(filenameRisorse));
    }

    public List<Obiettivo> getObiettivi() {
        return new ArrayList<>(obiettivi);
    }

    public int getObiettiviRimanenti() {
        return obiettivi.size() - puntatore;
    }

    public boolean isVuoto() {
        return puntatore >= obiettivi.size();
    }
}
