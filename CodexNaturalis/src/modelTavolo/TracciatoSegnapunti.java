package modelTavolo;

import java.util.HashMap;
import java.util.Map;

public class TracciatoSegnapunti {
    private Map<String, Integer> segnalini;

    // Costruttore della classe TracciatoSegnapunti
    public TracciatoSegnapunti() {
        segnalini = new HashMap<>();
    }

    // Metodo per inizializzare il tracciato segnapunti
    public void inizializza() {
        segnalini.clear();
    }

    // Metodo per aggiornare i punti di un giocatore
    public void aggiornaPunti(String giocatore, int punti) {
        segnalini.put(giocatore, segnalini.getOrDefault(giocatore, 0) + punti);
    }

    // Metodo per ottenere i punti di un giocatore
    public int getPunti(String giocatore) {
        return segnalini.getOrDefault(giocatore, 0);
    }
}
