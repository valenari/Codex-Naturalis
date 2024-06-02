package test;

import java.util.List;

import cardsModel.Carta;
import cardsModel.MazzoCarte;

public class Test {
    public static void main(String[] args) {
        // Creiamo un mazzo di carte iniziali
        MazzoCarte mazzoIniziali = new MazzoCarte("Iniziale", "src/fileCarte/CarteIniziali.txt");
        System.out.println("Carte Iniziali Caricate:");
        for (Carta carta : mazzoIniziali.getCarte()) {
            System.out.println(carta);
        }

        // Creiamo un mazzo di carte oro
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOro.txt");
        System.out.println("\nCarte Oro Caricate:");
        for (Carta carta : mazzoOro.getCarte()) {
            System.out.println(carta);
        }

        // Creiamo un mazzo di carte risorsa
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        System.out.println("\nCarte Risorsa Caricate:");
        for (Carta carta : mazzoRisorsa.getCarte()) {
            System.out.println(carta);
        }

        // Testiamo la pesca di una carta
        Carta cartaPescata = mazzoRisorsa.pescaCarta();
        System.out.println("\nCarta Pescata dal Mazzo Risorsa:");
        System.out.println(cartaPescata);

        // Testiamo la pesca di più carte
        List<Carta> cartePescate = mazzoOro.pescaCarte(3);
        System.out.println("\nCarte Pescate dal Mazzo Oro:");
        for (Carta carta : cartePescate) {
            System.out.println(carta);
        }
    }
}
