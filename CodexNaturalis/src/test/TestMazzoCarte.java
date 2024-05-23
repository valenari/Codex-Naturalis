package test;

import cardsModel.Carta;
import cardsModel.MazzoCarte;

public class TestMazzoCarte {
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
        
        // Stampa delle carte nei mazzi mescolati
        System.out.println("Mazzo Risorsa:");
        stampaMazzo(mazzoRisorsa);
        System.out.println("\nMazzo Oro:");
        stampaMazzo(mazzoOro);
        System.out.println("\nMazzo Iniziale:");
        stampaMazzo(mazzoIniziale);
    }
    
    // Metodo per stampare tutte le carte in un mazzo
    private static void stampaMazzo(MazzoCarte mazzo) {
        Carta carta;
        while ((carta = mazzo.pescaCarta()) != null) {
            carta.stampaCarta();
        }
    }
}
