package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;

import java.util.HashMap;
import java.util.Map;

public class Contatori {
    private Map<String, Integer> contatori;

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

    private void contaAngoli(Carta carta) {
        String[] angoli = carta.isFronte() ? carta.getFronte().split(" - ") : carta.getRetro().split(" - ");
        for (String angolo : angoli) {
            if (!angolo.equals("Nascosto") && !angolo.equals("❌")) {
                contatori.put(angolo, contatori.getOrDefault(angolo, 0) + 1);
            }
        }
    }

    private void contaElementoCentrale(String elemento) {
        if (!elemento.equals("Visibile")) {
            contatori.put(elemento, contatori.getOrDefault(elemento, 0) + 1);
        }
    }

    public void decrementaContatore(String elemento) {
        if (contatori.containsKey(elemento) && contatori.get(elemento) > 0) {
            contatori.put(elemento, contatori.get(elemento) - 1);
        }
    }

    private void resetContatori() {
        for (String key : contatori.keySet()) {
            contatori.put(key, 0);
        }
    }

    public void mostraContatori() {
        System.out.println("Contatori:");
        for (Map.Entry<String, Integer> entry : contatori.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
