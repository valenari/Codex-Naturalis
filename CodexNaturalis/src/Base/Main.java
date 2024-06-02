package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelPlayer.Giocatore;
import modelObiettivi.MazzoObiettivi;
import modelObiettivi.Obiettivo;
import cardsModel.MazzoCarte;
import game.Turno;
import util.StampaCarta;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n\t- 🍄 - ☘️ - CODEX NATURALIS - 🐺 - 🦋 -");
        System.out.println("\t                 📜  𓆰  🧪\n\n");

        System.out.println("Premere qualsiasi tasto per giocare, o premere 0 per uscire");
        Scanner sc = new Scanner(System.in);
        String risposta = sc.nextLine();
        if (risposta.equalsIgnoreCase("0")) System.exit(0);

        // Creazione dei diversi mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteIniziali.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsa.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOro.txt");
        MazzoObiettivi mazzoObiettivi = new MazzoObiettivi("src/fileCarte/ObiettiviDisposizione.txt", "src/fileCarte/ObiettiviRisorse.txt");

        // Creazione dei Giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        int g;
        do {
            System.out.println("Inserisci il numero di giocatori (almeno 2 e massimo 4)");
            g = sc.nextInt();
            if (g < 2 || g > 4) System.out.println("ERRORE: il numero di giocatori non è valido");
        } while (g < 2 || g > 4);

        for (int i = 0; i < g; i++) {
            System.out.println("Inserisci il nome del Giocatore " + (i + 1) + ":");
            String nomeGiocatore = sc.next();
            
            // Stampa il fronte e il retro della carta iniziale
            Giocatore tempGiocatore = new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, true);
            StampaCarta.stampaOrizzontalmente(tempGiocatore.getCartaIniziale());

            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;

            Giocatore giocatore = new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, fronteIniziale);
            giocatori.add(giocatore);
        }

        // Assegna e mostra due obiettivi comuni
        List<Obiettivo> obiettiviComuni = mazzoObiettivi.pescaObiettivi(2);
        System.out.println("Obiettivi comuni:");
        for (Obiettivo obiettivo : obiettiviComuni) {
            System.out.println(obiettivo);
        }

        // Assegna obiettivi segreti
        for (Giocatore giocatore : giocatori) {
            List<Obiettivo> obiettiviSegreti = mazzoObiettivi.pescaObiettivi(2);
            System.out.println(giocatore.getNome() + ", scegli il tuo obiettivo segreto:");
            for (int i = 0; i < obiettiviSegreti.size(); i++) {
                System.out.println((i + 1) + ": " + obiettiviSegreti.get(i));
            }
            int sceltaObiettivo = sc.nextInt() - 1;
            giocatore.setObiettivoSegreto(obiettiviSegreti.get(sceltaObiettivo));
        }

        Turno turno = new Turno(giocatori, obiettiviComuni);

        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            System.out.println("Area di Gioco:");
            giocatoreCorrente.mostraAreaDiGioco();

            System.out.println("\nMano Giocatore:");
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
            }

            System.out.println("\nArea di Pesca:");
            giocatoreCorrente.mostraAreaDiPesca();

            System.out.println("\nScegli una carta da pescare (1-6):");
            int cartaDaPescare = sc.nextInt();

            giocatoreCorrente.pescaCarta(cartaDaPescare);

            System.out.println("\nMano Giocatore aggiornata:");
            giocatoreCorrente.mostraMano();

            turno.controllaPunteggio();
            turno.controllaMazzi(giocatoreCorrente.isMazzoRisorsaVuoto(), giocatoreCorrente.isMazzoOroVuoto());

            turno.prossimoTurno();
        }

        sc.close();
    }
}
