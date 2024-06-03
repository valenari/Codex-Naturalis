package test;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;

public class TestGiocatore {

    public static void main(String[] args) {
        // Creazione dei mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteInizialiTest.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Creazione di un giocatore
        Giocatore giocatore = new Giocatore("Mario", mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, true);

        // Mostra le carte iniziali del giocatore
        System.out.println("Carte iniziali del giocatore:");
        giocatore.mostraMano();

        // Gioca una carta
        System.out.println("\nGioca una carta:");
        giocatore.giocaCarta(0, 1, true);
        System.out.println("\nArea di gioco del giocatore dopo aver giocato una carta:");
        giocatore.mostraAreaDiGioco();
        giocatore.mostraMano();
        
        // Mostra l'area di pesca
        System.out.println("\nArea di pesca iniziale:");
        giocatore.mostraAreaDiPesca();
        
     // Pesca una carta dall'area di pesca
        System.out.println("\nPesca una carta dall'area di pesca:");
        giocatore.pescaCarta(1);
        System.out.println("\nCarte del giocatore dopo la pesca:");
        giocatore.mostraMano();

        // Mostra i contatori
        System.out.println("\nContatori del giocatore:");
        giocatore.mostraContatori();

        // Aggiungi punti al giocatore
        System.out.println("\nAggiungi punti al giocatore:");
        giocatore.aggiungiPunti(5);
        System.out.println("Punti del giocatore: " + giocatore.getPunti());
    }
}
