package test;

import cardsModel.*;
import modelPlayer.Giocatore;
import util.StampaCarta;

public class TestGiocatore {
    public static void main(String[] args) {
        // Creazione dei mazzi di carte
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteInizialiTest.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione di un giocatore
        Giocatore giocatore = new Giocatore("Giocatore1", mazzoIniziale, mazzoRisorsa, mazzoOro, true);

        // Verifica della corretta inizializzazione
        System.out.println("Stato iniziale del giocatore:");
        giocatore.mostraAreaDiGioco();
        giocatore.mostraMano();
        giocatore.mostraAreaDiPesca();
        giocatore.mostraContatori();

        // Giocare una carta dalla mano
        System.out.println("\nGiocare la prima carta dalla mano nella posizione 1, fronte:");
        giocatore.giocaCarta(0, 1, true);
        giocatore.mostraAreaDiGioco();
        giocatore.mostraMano();
        giocatore.mostraContatori();

        // Pescare una carta
        System.out.println("\nPescare una carta dall'area di pesca (indice 1):");
        giocatore.pescaCarta(1);
        giocatore.mostraMano();
        giocatore.mostraContatori();

        // Verifica finale dello stato del giocatore
        System.out.println("\nStato finale del giocatore:");
        giocatore.mostraAreaDiGioco();
        giocatore.mostraMano();
        giocatore.mostraAreaDiPesca();
        giocatore.mostraContatori();
    }
}
