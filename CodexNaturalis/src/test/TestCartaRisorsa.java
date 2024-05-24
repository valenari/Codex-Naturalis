package test;

import cardsModel.CartaRisorsa;
import Modello_giocatore.Caselleproibite;

public class TestCartaRisorsa {
    public static void main(String[] args) {
        // Crea una carta risorsa di prova
        CartaRisorsa cartaRisorsa1 = new CartaRisorsa(1, "Visibile - Vegetale - Insetto - Visibile", Caselleproibite.NULL, "Fungo", 1);
        CartaRisorsa cartaRisorsa2 = new CartaRisorsa(2, "Fungo - Visibile - Nascosto - Insetto", Caselleproibite.NULL, "Animale", 1);

        // Stampa la rappresentazione grafica della carta risorsa
        cartaRisorsa1.stampaCarta();
        cartaRisorsa1.stampaRetro();

        // Stampa un'altra carta per verifica
        cartaRisorsa2.stampaCarta();
        cartaRisorsa2.stampaRetro();
    }
}
