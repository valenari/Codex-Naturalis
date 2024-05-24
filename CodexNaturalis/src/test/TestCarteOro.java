package test;

import cardsModel.CartaOro;

import java.util.List;

public class TestCarteOro {
    public static void main(String[] args) {
        // Percorso del file di testo contenente le carte oro
        String filename = "src/fileCarte/CarteOro.txt";
        
        // Leggi le carte oro dal file
        List<CartaOro> carteOro = CartaOro.leggiCarteOro(filename);
        
        // Verifica se le carte sono state lette correttamente e stampale
        for (CartaOro carta : carteOro) {
            carta.stampaCarta();
            System.out.println();
        }
    }
}
