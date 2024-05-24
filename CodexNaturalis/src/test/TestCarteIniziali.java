package test;

import cardsModel.CartaIniziale;
import java.util.List;

public class TestCarteIniziali {
    public static void main(String[] args) {
        // File di esempio per il test
        String filename = "src/fileCarte/CarteIniziali.txt";

        // Leggi le carte iniziali dal file
        List<CartaIniziale> carteIniziali = CartaIniziale.leggiCarteIniziali(filename);

        // Stampa tutte le carte lette
        for (CartaIniziale carta : carteIniziali) {
            carta.stampaCarta();
            carta.stampaRetro();
        }
    }
}
