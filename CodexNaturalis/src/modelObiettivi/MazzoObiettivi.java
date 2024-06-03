package modelObiettivi;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MazzoObiettivi {
    private Stack<Obiettivo> mazzo;

    public MazzoObiettivi() {
        CaricatoreObiettivi caricatoreObiettivi = new CaricatoreObiettivi();
        List<Obiettivo> obiettiviDisposizione = caricatoreObiettivi.caricaObiettiviDisposizione("src/fileCarte/ObiettiviDisposizione.txt");
        List<Obiettivo> obiettiviRisorse = caricatoreObiettivi.caricaObiettiviRisorse("src/fileCarte/ObiettiviRisorse.txt");

        mazzo = new Stack<>();
        mazzo.addAll(obiettiviDisposizione);
        mazzo.addAll(obiettiviRisorse);
        
        mescolaMazzo();
    }

    public void mescolaMazzo() {
        Collections.shuffle(mazzo);
    }

    public Obiettivo pescaObiettivo() {
        if (!mazzo.isEmpty()) {
            return mazzo.pop();
        }
        return null; // O gestisci l'eventualità che il mazzo sia vuoto
    }

    public boolean isMazzoVuoto() {
        return mazzo.isEmpty();
    }

    public void mostraMazzo() {
        for (Obiettivo obiettivo : mazzo) {
            System.out.println(obiettivo);
        }
    }
}
