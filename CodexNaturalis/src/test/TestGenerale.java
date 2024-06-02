package test;

import modelPlayer.Giocatore;
import cardsModel.MazzoCarte;
import cardsModel.CartaIniziale;
import game.Turno;
import util.StampaCarta;

import java.util.Scanner;

public class TestGenerale {
    public static void main(String[] args) {
        // Creazione dei diversi mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteIniziali.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOro.txt");

        // Chiedi il numero di giocatori
        Scanner sc = new Scanner(System.in);
        int numeroGiocatori;
        do {
            System.out.println("Inserisci il numero di giocatori (2-4):");
            numeroGiocatori = sc.nextInt();
            if (numeroGiocatori < 2 || numeroGiocatori > 4) {
                System.out.println("Numero di giocatori non valido. Per favore inserisci un numero tra 2 e 4.");
            }
        } while (numeroGiocatori < 2 || numeroGiocatori > 4);

        // Creazione dei giocatori
        Giocatore[] giocatori = new Giocatore[numeroGiocatori];
        for (int i = 0; i < numeroGiocatori; i++) {
            sc.nextLine(); // Consuma il newline rimasto
            System.out.println("Inserisci il nome del Giocatore " + (i + 1) + ":");
            String nomeGiocatore = sc.nextLine();

            // Pesca una carta iniziale per mostrare fronte e retro
            CartaIniziale cartaInizialeTemp = (CartaIniziale) mazzoIniziale.pescaCarta();
            System.out.println("Fronte e Retro della carta iniziale:");
            StampaCarta.stampaOrizzontalmente(cartaInizialeTemp);

            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;

            if (!fronteIniziale) {
                cartaInizialeTemp.giraCarta();
            }

            giocatori[i] = new Giocatore(nomeGiocatore, cartaInizialeTemp, mazzoRisorsa, mazzoOro, fronteIniziale);
        }

        // Creazione e gestione dei turni
        Turno turno = new Turno(giocatori);

        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            System.out.println("Punteggio: " + giocatoreCorrente.getPunti());

            System.out.println("Area di Gioco:");
            giocatoreCorrente.mostraAreaDiGioco();

            System.out.println("\nMano Giocatore:");
            giocatoreCorrente.mostraMano();

            giocatoreCorrente.mostraContatori();

            boolean cartaGiocata = false;
            while (!cartaGiocata) {
                System.out.println("\nScegli una carta da giocare (1-3):");
                int cartaDaGiocare = sc.nextInt() - 1;

                System.out.println("Vuoi giocare la carta di fronte (1) o di retro (2)?");
                boolean fronte = sc.nextInt() == 1;

                System.out.println("Scegli la posizione (numero casella disponibile):");
                int posizione = sc.nextInt();

                if (giocatoreCorrente.giocaCarta(cartaDaGiocare, posizione, fronte)) {
                    cartaGiocata = true;
                }
            }

            System.out.println("\nArea di Pesca:");
            giocatoreCorrente.mostraAreaDiPesca();

            System.out.println("\nScegli una carta da pescare (1-6):");
            int cartaDaPescare = sc.nextInt();

            giocatoreCorrente.pescaCarta(cartaDaPescare);

            System.out.println("\nMano Giocatore aggiornata:");
            giocatoreCorrente.mostraMano();

            turno.controllaPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }

           
        }

        sc.close();
    }
}
