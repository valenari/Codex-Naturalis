package test;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.MazzoCarte;
import game.Turno;

import java.util.ArrayList;
import java.util.List;

public class TestTurno {

    public static void main(String[] args) {
        // Creazione dei mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteInizialiTest.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Creazione di un giocatore
        Giocatore giocatore1 = new Giocatore("Mario", mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, true);

        // Creazione della lista dei giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        giocatori.add(giocatore1);

        // Creazione del turno
        Turno turno = new Turno(giocatori);

        // Loop dei turni
        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("\n\nÈ il turno di: " + giocatoreCorrente.getNome());
            
         // Gioca una carta
            giocatoreCorrente.mostraMano();
            System.out.println("Il giocatore gioca una carta...");
            giocatoreCorrente.giocaCarta(0, 1, true);
            System.out.println("Area di gioco del giocatore dopo aver giocato una carta:");
            giocatoreCorrente.mostraAreaDiGioco();
            
            // Mostra la mano del giocatore
            System.out.println("Mano del giocatore:");
            giocatoreCorrente.mostraMano();

            // Mostra l'area di pesca
            System.out.println("Area di pesca:");
            areaDiPesca.mostraAreaDiPesca();

            // Pesca una carta
            System.out.println("Il giocatore pesca una carta dall'area di pesca...");
            giocatoreCorrente.pescaCarta(1);
            System.out.println("Mano del giocatore dopo la pesca:");
            giocatoreCorrente.mostraMano();

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
    }
}
