package Base;

import modelObiettivi.MazzoObiettivi;
import modelObiettivi.Obiettivo;
import modelPlayer.Giocatore;
import modelPlayer.Contatori;
import cardsModel.MazzoCarte;
import game.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\t- 🍄 - ☘️ - CODEX NATURALIS - 🐺 - 🦋 -");
        System.out.println("\t                 📜  𓆰  🧪\n\n");

        Scanner sc = new Scanner(System.in);

        System.out.println("Premere qualsiasi tasto per giocare, o premere 0 per uscire");
        String risposta = sc.nextLine();
        if (risposta.equalsIgnoreCase("0")) System.exit(0);

        // Creazione dei diversi mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteIniziali.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOro.txt");

        // Creazione del mazzo obiettivi
        String filenameDisposizione = "src/fileCarte/ObiettiviDisposizione.txt";
        String filenameRisorse = "src/fileCarte/ObiettiviRisorse.txt";
        MazzoObiettivi mazzoObiettivi = new MazzoObiettivi(filenameDisposizione, filenameRisorse);

        // Creazione dei Giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        int g;
        do {
            System.out.println("Inserisci il numero di giocatori (almeno 2 e massimo 4)");
            g = sc.nextInt();
            if (g < 2 || g > 4) System.out.println("ERRORE: il numero di giocatori non è valido");
        } while (g < 2 || g > 4);

        sc.nextLine(); // Consume newline

        for (int i = 0; i < g; i++) {
            System.out.println("Inserisci il nome del Giocatore " + (i + 1) + ":");
            String nome = sc.nextLine();
            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;
            sc.nextLine(); // Consume newline

            Giocatore giocatore = new Giocatore(nome, mazzoIniziale, mazzoRisorsa, mazzoOro, fronteIniziale);

            List<Obiettivo> obiettiviGiocatore = mazzoObiettivi.pescaObiettivi(2);
            System.out.println("Obiettivi segreti per " + nome + ":");
            for (int j = 0; j < obiettiviGiocatore.size(); j++) {
                System.out.println((j + 1) + ". " + obiettiviGiocatore.get(j));
            }

            System.out.println("Scegli il tuo obiettivo segreto (1 o 2):");
            int sceltaObiettivo = sc.nextInt() - 1;
            sc.nextLine(); // Consume newline

            giocatore.setObiettivoSegreto(obiettiviGiocatore.get(sceltaObiettivo));
            giocatori.add(giocatore);
        }

        // Pesca due obiettivi comuni
        List<Obiettivo> obiettiviComuni = mazzoObiettivi.pescaObiettivi(2);
        System.out.println("Obiettivi comuni:");
        for (Obiettivo obiettivo : obiettiviComuni) {
            System.out.println(obiettivo);
        }

        Turno turno = new Turno(giocatori, obiettiviComuni);

        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            giocatoreCorrente.mostraAreaDiGioco();
            giocatoreCorrente.mostraMano();

            boolean cartaGiocata = false;
            while (!cartaGiocata) {
                System.out.println("\nScegli una carta da giocare (1-3):");
                int cartaDaGiocare = sc.nextInt() - 1;

                System.out.println("Vuoi giocare la carta di fronte (1) o di retro (2)?");
                boolean fronte = sc.nextInt() == 1;

                System.out.println("Scegli la posizione (numero casella disponibile):");
                int posizione = sc.nextInt();

                cartaGiocata = giocatoreCorrente.giocaCarta(cartaDaGiocare, posizione, fronte);
                if (!cartaGiocata) {
                    System.out.println("Non è possibile giocare la carta selezionata. Riprova.");
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
