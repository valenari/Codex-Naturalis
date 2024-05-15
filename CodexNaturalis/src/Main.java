import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import game.TavoloDiGioco;
import ui.SchermataDiGioco;
import ui.SchermataIniziale;

public class Main {
    public static void main(String[] args) {
        // Lista dei nomi dei giocatori
        List<String> nomiGiocatori = new ArrayList<>();
        int g;
        Scanner sc=new Scanner(System.in);
        do{
        	System.out.println("Inserisci il numero di giocatori (almeno 2 e massimo 4)");
            g=sc.nextInt();
            if(g<2 || g>4)System.out.println("ERRORE: il numero di giocatori non è valido");
        }while(g<2 || g>4);
        for(int i=0; i<g; i++) {
        	System.out.println("Inserisci il nome del Giocatore "+(i+1)+":");
        	String n=sc.next();
        	nomiGiocatori.add(n);
        }
        // Creazione del tavolo di gioco
        TavoloDiGioco tavolo = new TavoloDiGioco(nomiGiocatori);

        // Creazione della schermata iniziale
        SchermataIniziale schermataIniziale = new SchermataIniziale();
        schermataIniziale.setVisible(true); // Visualizza la schermata iniziale

        // Attendi che l'utente selezioni un'opzione dalla schermata iniziale
        while (!schermataIniziale.isPartitaIniziata()) {
            try {
                Thread.sleep(100); // Attendi brevemente prima di controllare di nuovo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Creazione della schermata di gioco
        SchermataDiGioco schermataDiGioco = new SchermataDiGioco(tavolo.getGiocatori());
        schermataDiGioco.setVisible(true); // Visualizza la schermata di gioco

        // Avvio del gioco
        while (!tavolo.isPartitaTerminata()) {
            tavolo.eseguiTurno(); // Esegui il turno del giocatore corrente

            // Aggiorna le informazioni dei giocatori nella schermata di gioco dopo ogni turno
            schermataDiGioco.aggiornaInformazioniGiocatori(tavolo.getGiocatori());
        }

        // Fine del gioco
        int vincitore = tavolo.getVincitore();
        // Visualizza un messaggio di fine partita con il vincitore
        schermataDiGioco.mostraMessaggioFinePartita("Il giocatore " + vincitore + " ha vinto!");
    }
}
