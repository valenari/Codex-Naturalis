package test;

import cardsModel.CartaRisorsa;

import java.util.List;

public class testCarteRisorsa {
    public static void main(String[] args) {
        // Percorso del file di testo contenente le carte risorsa
        String filename = "src/fileCarte/CarteRisorsa.txt";
        
        // Leggi le carte risorsa dal file
        List<CartaRisorsa> carteRisorsa = CartaRisorsa.leggiCarteRisorsa(filename);
        
        // Verifica se le carte sono state lette correttamente e stampale
        for (CartaRisorsa carta : carteRisorsa) {
            carta.stampaCarta();
            System.out.println();
        }
    }
}
