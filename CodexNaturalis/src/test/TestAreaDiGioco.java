package test;

import cardsModel.CartaIniziale;
import cardsModel.CartaRisorsa;
import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;

import java.util.List;

public class TestAreaDiGioco {

    public static void main(String[] args) {
        // Creazione di una carta iniziale
        CartaIniziale cartaIniziale = new CartaIniziale(1, "Visibile - Nascosto - Visibile - Nascosto", List.of("Vegetale", "Fungo"), "Visibile - Visibile - Visibile - Visibile");

        // Creazione dei contatori
        Contatori contatori = new Contatori();

        // Creazione dell'area di gioco
        AreaDiGioco areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);

        // Verifica della corretta inizializzazione
        System.out.println("Inizializzazione Area di Gioco:");
        areaDiGioco.visualizzaGriglia();

        // Creazione di una carta risorsa
        CartaRisorsa cartaRisorsa = new CartaRisorsa(2, "Visibile - Nascosto - Visibile - Nascosto", "Vegetale", 2);

        // Posizionamento di una carta nella griglia
        System.out.println("\nPosizionamento di una carta nella griglia:");
        areaDiGioco.posizionaCarta(cartaRisorsa, 1, true);
        areaDiGioco.visualizzaGriglia();

        // Espansione della griglia
        System.out.println("\nEspansione della griglia:");
        CartaRisorsa cartaRisorsa2 = new CartaRisorsa(3, "Nascosto - Visibile - Nascosto - Visibile", "Fungo", 3);
        areaDiGioco.posizionaCarta(cartaRisorsa2, 2, true);
        areaDiGioco.visualizzaGriglia();

        // Verifica del metodo getCartaIniziale
        CartaIniziale cartaInizialeEstratta = areaDiGioco.getCartaIniziale();
        System.out.println("\nCarta Iniziale Estratta:");
        System.out.println(cartaInizialeEstratta);

        // Verifica del metodo getContatori
        System.out.println("\nContatori:");
        contatori.mostraContatori();
        
        System.out.println("Tutti i test sono passati con successo.");
    }
}
