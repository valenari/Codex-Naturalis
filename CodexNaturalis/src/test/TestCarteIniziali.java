package test;

import cardsModel.CartaIniziale;

import java.util.List;

public class TestCarteIniziali {
    public static void main(String[] args) {
        // Percorso del file di testo contenente le carte iniziali
        String filename = "src/fileCarte/CarteIniziali.txt";
        
        // Leggi le carte iniziali dal file
        List<CartaIniziale> carteIniziali = CartaIniziale.leggiCarteIniziali(filename);
        
        // Verifica se le carte sono state lette correttamente e stampale
        for (CartaIniziale carta : carteIniziali) {
            carta.stampaCarta();
            System.out.println();
        }
    }
}
