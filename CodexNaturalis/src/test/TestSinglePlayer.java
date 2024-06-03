package test;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.MazzoCarte;
import game.Turno;

import java.util.ArrayList;
import java.util.List;

public class TestSinglePlayer {

    public static void main(String[] args) {
        // Creazione dei mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteInizialiTest.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Creazione di un giocatore
        Giocatore giocatore = new Giocatore("Mario", mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, true);

        // Creazione della lista dei giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        giocatori.add(giocatore);

        // Creazione del turno
        Turno turno = new Turno(giocatori, mazzoRisorsa, mazzoOro);

        // Simulazione di una partita completa
        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("\n\nÈ il turno di: " + giocatoreCorrente.getNome());

            // Mostra l'area di gioco del giocatore
            System.out.println("Area di gioco del giocatore:");
            giocatoreCorrente.mostraAreaDiGioco();

            // Mostra la mano del giocatore
            System.out.println("Mano del giocatore:");
            giocatoreCorrente.mostraMano();

            // Mostra l'area di pesca
            System.out.println("Area di pesca:");
            giocatoreCorrente.mostraAreaDiPesca();

            // Simula pesca di una carta
            System.out.println("Il giocatore pesca una carta dall'area di pesca...");
            giocatoreCorrente.pescaCarta(1);
            System.out.println("Mano del giocatore dopo la pesca:");
            giocatoreCorrente.mostraMano();

            // Simula giocata di una carta
            System.out.println("Il giocatore gioca una carta...");
            giocatoreCorrente.giocaCarta(0, 1, true);
            System.out.println("Area di gioco del giocatore dopo aver giocato una carta:");
            giocatoreCorrente.mostraAreaDiGioco();

            // Mostra i contatori
            System.out.println("Contatori del giocatore:");
            giocatoreCorrente.mostraContatori();

            // Aggiungi punti al giocatore
            System.out.println("Il giocatore aggiunge 5 punti.");
            giocatoreCorrente.aggiungiPunti(5);
            System.out.println("Punti del giocatore: " + giocatoreCorrente.getPunti());

            // Controlla fine partita
            turno.controllaPunteggio();

            // Passa al prossimo turno
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }
        }

        System.out.println("Partita terminata.");
        System.out.println("Punti finali del giocatore: " + giocatore.getPunti());
    }
}
