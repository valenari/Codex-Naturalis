package test;

import cardsModel.Carta;
import cardsModel.MazzoCarte;

import java.util.List;

public class TestMazzoCarte {
    public static void main(String[] args) {
        // Creazione di un mazzo di carte di risorsa
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        
        // Verifica della corretta inizializzazione
        System.out.println("Mazzo di Carte Risorsa Inizializzato:");
        List<Carta> carteRisorsa = mazzoRisorsa.getCarte();
        for (Carta carta : carteRisorsa) {
            System.out.println(carta);
        }
        System.out.println("Numero di carte nel mazzo: " + mazzoRisorsa.getCarteRimanenti());
        
        // Mescolamento delle carte
        mazzoRisorsa.mescolaMazzo();
        System.out.println("Mazzo di Carte Risorsa Mescolato:");
        List<Carta> carteRisorsaMescolate = mazzoRisorsa.getCarte();
        for (Carta carta : carteRisorsaMescolate) {
            System.out.println(carta);
        }

        // Pesca di una carta e verifica dell'aggiornamento del mazzo
        Carta cartaPescata = mazzoRisorsa.pescaCarta();
        System.out.println("Carta Pescata:");
        System.out.println(cartaPescata);
        System.out.println("Numero di carte rimanenti nel mazzo dopo la pesca: " + mazzoRisorsa.getCarteRimanenti());

        // Pesca di più carte e verifica dell'aggiornamento del mazzo
        List<Carta> cartePescate = mazzoRisorsa.pescaCarte(3);
        System.out.println("Carte Pescate:");
        for (Carta carta : cartePescate) {
            System.out.println(carta);
        }
        System.out.println("Numero di carte rimanenti nel mazzo dopo la pesca multipla: " + mazzoRisorsa.getCarteRimanenti());

        // Verifica del mescolamento
        boolean isShuffled = !carteRisorsa.equals(carteRisorsaMescolate);
        System.out.println("Il mazzo è stato mescolato correttamente: " + isShuffled);
    }
}
