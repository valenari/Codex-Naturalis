package test;

import cardsModel.MazzoCarte;
import modelTavolo.AreaDiPesca;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        // Creazione dei diversi mazzi
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOro.txt");

        // Creazione dell'Area di Pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Mostra l'area di pesca iniziale
        System.out.println("Area di Pesca Iniziale:");
        areaDiPesca.mostraAreaDiPesca();

        // Pesca più carte randomicamente per testare
        for (int i = 0; i < 10; i++) {
            int indicePesca = (int) (Math.random() * 6) + 1; // Pesca da 1 a 6
            System.out.println("\nPescando carta con indice {" + indicePesca + "}");
            areaDiPesca.pescaCarta(indicePesca);
            areaDiPesca.mostraAreaDiPesca();
        }

        // Mostra l'area di pesca dopo diverse pescate
        System.out.println("\nArea di Pesca dopo diverse pescate:");
        areaDiPesca.mostraAreaDiPesca();
    }
}
