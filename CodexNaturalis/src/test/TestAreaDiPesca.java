package test;

import cardsModel.Carta;
import cardsModel.MazzoCarte;
import modelTavolo.AreaDiPesca;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        // Percorsi dei file di testo contenenti le carte
        String fileCarteRisorsa = "src/fileCarte/CarteRisorsa.txt";
        String fileCarteOro = "src/fileCarte/CarteOro.txt";
        String fileCarteIniziali = "src/fileCarte/CarteIniziali.txt";
        
        // Creazione dei mazzi
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        MazzoCarte mazzoOro = new MazzoCarte("Oro");
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale");
        
        // Caricamento delle carte nei mazzi
        mazzoRisorsa.caricaCarteDaFile(fileCarteRisorsa);
        mazzoOro.caricaCarteDaFile(fileCarteOro);
        mazzoIniziale.caricaCarteDaFile(fileCarteIniziali);
        
        // Mescolamento dei mazzi
        mazzoRisorsa.mescolaMazzo();
        mazzoOro.mescolaMazzo();
        mazzoIniziale.mescolaMazzo();
        
        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro, mazzoIniziale);
        
        // Inizializzazione dell'area di pesca
        areaDiPesca.inizializzaPesca();
        
        // Stampa delle carte visibili nell'area di pesca
        System.out.println("Carte Visibili Risorsa:");
        for (Carta carta : areaDiPesca.getCarteVisibiliRisorsa()) {
            carta.stampaCarta();
        }
        System.out.println("\nCarte Visibili Oro:");
        for (Carta carta : areaDiPesca.getCarteVisibiliOro()) {
            carta.stampaCarta();
        }
    }
}
