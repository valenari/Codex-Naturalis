package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;

import java.util.HashMap;
import java.util.Map;

public class Contatori {
    private Map<String, Integer> contatori;

    // Costruttore della classe Contatori
    public Contatori() {
        contatori = new HashMap<>();
        contatori.put("Vegetale", 0);
        contatori.put("Fungo", 0);
        contatori.put("Animale", 0);
        contatori.put("Insetto", 0);
        contatori.put("Piuma", 0);
        contatori.put("Pergamena", 0);
        contatori.put("Inchiostro", 0);
    }

    // Metodo per aggiornare i contatori in base alle carte nell'area di gioco
    public void aggiornaContatori(AreaDiGioco areaDiGioco) {
        resetContatori();
        Carta[][] griglia = areaDiGioco.getGriglia();

        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                Carta carta = griglia[i][j];
                if (carta != null) {
                    contaAngoli(carta);

                    if (carta instanceof CartaRisorsa) {
                        CartaRisorsa cartaRisorsa = (CartaRisorsa) carta;
                        if (!cartaRisorsa.isFronte()) {
                            contaElementoCentrale(cartaRisorsa.getTipoRegno());
                        }
                    } else if (carta instanceof CartaOro) {
                        CartaOro cartaOro = (CartaOro) carta;
                        if (!cartaOro.isFronte()) {
                            contaElementoCentrale(cartaOro.getTipoRegno());
                        }
                    } else if (carta instanceof CartaIniziale) {
                        CartaIniziale cartaIniziale = (CartaIniziale) carta;
                        if (cartaIniziale.isFronte()) {
                            for (String elemento : cartaIniziale.getCentrale()) {
                                contaElementoCentrale(elemento);
                            }
                        }
                    }
                }
            }
        }
    }

    // Metodo per contare gli elementi sugli angoli delle carte
    private void contaAngoli(Carta carta) {
        String[] angoli = carta.isFronte() ? carta.getFronte().split(" - ") : carta.getRetro().split(" - ");
        for (String angolo : angoli) {
            if (!angolo.equals("Nascosto")) {
                contatori.put(angolo, contatori.getOrDefault(angolo, 0) + 1);
            }
        }
    }

    // Metodo per contare l'elemento centrale delle carte risorsa e oro giocate di retro, e gli elementi centrali delle carte iniziali giocate di fronte
    private void contaElementoCentrale(String elemento) {
        if (!elemento.equals("Visibile")) {
            contatori.put(elemento, contatori.getOrDefault(elemento, 0) + 1);
        }
    }

    // Metodo per resettare i contatori
    private void resetContatori() {
        for (String key : contatori.keySet()) {
            contatori.put(key, 0);
        }
    }

    // Metodo per mostrare i valori attuali dei contatori
    public void mostraContatori() {
        System.out.println("Contatori:");
        for (Map.Entry<String, Integer> entry : contatori.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
