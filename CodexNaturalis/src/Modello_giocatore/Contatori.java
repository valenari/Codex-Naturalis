package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;

import java.util.HashMap;
import java.util.Map;

public class Contatori {
    private Map<String, Integer> contatoriRegni;
    private Map<String, Integer> contatoriOggetti;

    public Contatori() {
        this.contatoriRegni = new HashMap<>();
        this.contatoriOggetti = new HashMap<>();
    }

    public void aggiornaContatori(AreaDiGioco areaDiGioco) {
        resetContatori();

        Carta[][] griglia = areaDiGioco.getGriglia();
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                Carta carta = griglia[i][j];
                if (carta != null) {
                    contaElementiCarta(carta);
                }
            }
        }
    }

    private void resetContatori() {
        contatoriRegni.clear();
        contatoriOggetti.clear();
    }

    private void contaElementiCarta(Carta carta) {
        if (carta instanceof CartaIniziale) {
            CartaIniziale cartaIniziale = (CartaIniziale) carta;
            for (String elemento : cartaIniziale.getCentrale()) {
                if (isRegno(elemento)) {
                    incrementaContatoreRegni(elemento);
                } else if (isOggetto(elemento)) {
                    incrementaContatoreOggetti(elemento);
                }
            }
        }

        if (carta instanceof CartaRisorsa || carta instanceof CartaOro) {
            String[] elementi = carta.getFronte().split(" - ");
            for (String elemento : elementi) {
                if (isRegno(elemento)) {
                    incrementaContatoreRegni(elemento);
                } else if (isOggetto(elemento)) {
                    incrementaContatoreOggetti(elemento);
                }
            }
        }
    }

    private boolean isRegno(String elemento) {
        return elemento.equals("Vegetale") || elemento.equals("Fungo") || elemento.equals("Animale") || elemento.equals("Insetto");
    }

    private boolean isOggetto(String elemento) {
        return elemento.equals("Piuma") || elemento.equals("Pergamena") || elemento.equals("Inchiostro");
    }

    private void incrementaContatoreRegni(String regno) {
        contatoriRegni.put(regno, contatoriRegni.getOrDefault(regno, 0) + 1);
    }

    private void incrementaContatoreOggetti(String oggetto) {
        contatoriOggetti.put(oggetto, contatoriOggetti.getOrDefault(oggetto, 0) + 1);
    }

    public void mostraContatori() {
        System.out.println("Contatori dei Regni:");
        contatoriRegni.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("Contatori degli Oggetti:");
        contatoriOggetti.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
