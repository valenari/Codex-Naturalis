package test;

import cardsModel.*;
import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;

public class TestGiocatore {
    public static void main(String[] args) {
        // Creazione dei diversi mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteIniziali.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOro.txt");
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Creazione del giocatore
        Giocatore giocatore = new Giocatore("Giocatore1", mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, true);

        // Verifica dell'area di gioco iniziale e della carta iniziale
        System.out.println("Area di Gioco Iniziale:");
        giocatore.mostraAreaDiGioco();

        // Verifica della mano del giocatore iniziale
        System.out.println("\nMano Iniziale del Giocatore:");
        giocatore.mostraMano();

        // Verifica dell'area di pesca iniziale
        System.out.println("\nArea di Pesca Iniziale:");
        giocatore.mostraAreaDiPesca();

        // Giocare una carta risorsa dalla mano
        System.out.println("\nGiocare una carta risorsa dalla mano:");
        giocatore.giocaCarta(0, 1, true); // Gioca la prima carta della mano in posizione 1
        giocatore.mostraAreaDiGioco();

        // Pesca una carta risorsa dall'area di pesca
        System.out.println("\nPesca una carta risorsa dall'area di pesca (indice {1}):");
        giocatore.pescaCarta(1);
        giocatore.mostraMano();
        giocatore.mostraAreaDiPesca();

        // Giocare una carta oro dalla mano
        System.out.println("\nGiocare una carta oro dalla mano:");
        giocatore.giocaCarta(1, 2, true); // Gioca la seconda carta della mano (carta oro) in posizione 2
        giocatore.mostraAreaDiGioco();

        // Pesca una carta oro dall'area di pesca
        System.out.println("\nPesca una carta oro dall'area di pesca (indice {4}):");
        giocatore.pescaCarta(4);
        giocatore.mostraMano();
        giocatore.mostraAreaDiPesca();

        // Mostra i contatori aggiornati
        System.out.println("\nContatori aggiornati:");
        giocatore.mostraContatori();
    }
}
