package test;

import cardsModel.*;
import modelTavolo.AreaDiPesca;
import util.StampaCarta;

import java.util.Random;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        // Creazione dei mazzi di carte
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa.getCarte(), mazzoOro.getCarte());

        // Verifica della corretta inizializzazione delle carte visibili
        System.out.println("Carte Risorsa Visibili Iniziali:");
        StampaCarta.stampaAreaDiPesca(areaDiPesca.getCarteRisorsaVisibili(), areaDiPesca.getMazzoRisorsaCoperto(), true);
        System.out.println("\nCarte Oro Visibili Iniziali:");
        StampaCarta.stampaAreaDiPesca(areaDiPesca.getCarteOroVisibili(), areaDiPesca.getMazzoOroCoperto(), false);

        // Creazione di un oggetto Random per pescate casuali
        Random random = new Random();

        // Eseguiamo un ciclo per pescare carte casualmente
        for (int i = 0; i < 5; i++) {
            // Pescata casuale di una carta risorsa o oro
            int tipoCarta = random.nextInt(2); // 0 per Risorsa, 1 per Oro
            int indiceCarta = random.nextInt(3) + (tipoCarta == 0 ? 1 : 4); // Indici validi per pesca

            Carta cartaPescata = areaDiPesca.pescaCarta(indiceCarta);

            // Stampa della carta pescata e aggiornamento delle carte visibili
            if (tipoCarta == 0) {
                System.out.println("\nCarta Risorsa Pescata:");
                System.out.println(cartaPescata);
                System.out.println("\nCarte Risorsa Visibili Dopo Pesca:");
                StampaCarta.stampaAreaDiPesca(areaDiPesca.getCarteRisorsaVisibili(), areaDiPesca.getMazzoRisorsaCoperto(), true);
            } else {
                System.out.println("\nCarta Oro Pescata:");
                System.out.println(cartaPescata);
                System.out.println("\nCarte Oro Visibili Dopo Pesca:");
                StampaCarta.stampaAreaDiPesca(areaDiPesca.getCarteOroVisibili(), areaDiPesca.getMazzoOroCoperto(), false);
            }
        }

        // Verifica del metodo mostraAreaDiPesca finale
        System.out.println("\nArea di Pesca Completa:");
        areaDiPesca.mostraAreaDiPesca();
    }
}
