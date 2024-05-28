package modelTavolo;

import java.util.ArrayList;
import java.util.List;

import obiettiviModels.CartaObiettivo;
import obiettiviModels.MazzoObiettivo;

public class ObiettiviComuni {
    private MazzoObiettivo mazzoObiettivi;
    private List<CartaObiettivo> obiettivi;

    // Costruttore della classe ObiettiviComuni
    public ObiettiviComuni(MazzoObiettivo mazzoObiettivi) {
        this.mazzoObiettivi = mazzoObiettivi;
        obiettivi = new ArrayList<>();
    }

    // Metodo per inizializzare gli obiettivi comuni
    public void inizializza() {
        obiettivi.clear();
        for (int i = 0; i < 2; i++) {
            obiettivi.add(mazzoObiettivi.pescaCarta());
        }
    }

    // Getter per ottenere gli obiettivi comuni
    public List<CartaObiettivo> getObiettivi() {
        return obiettivi;
    }
}

