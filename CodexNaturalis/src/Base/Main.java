package Base;

import game.Turno;
import modelPlayer.Giocatore;
import cardsModel.CartaIniziale;
import cardsModel.MazzoCarte;
import util.StampaCarta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            String nome = sc.next();
            CartaIniziale cartaIniziale = (CartaIniziale) mazzoIniziale.pescaCarta();
            StampaCarta.stampaOrizzontalmente(cartaIniziale);
            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;
            Giocatore giocatore = new Giocatore(nome, cartaIniziale, mazzoRisorsa, mazzoOro, fronteIniziale);
            giocatori.add(giocatore);
        }

        Giocatore[] arrayGiocatori = giocatori.toArray(new Giocatore[0]);
        Turno turno = new Turno(arrayGiocatori);

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

                giocatoreCorrente.giocaCarta(cartaDaGiocare, posizione, fronte);
                cartaGiocata = true;
            }

            System.out.println("\nArea di Pesca:");
            giocatoreCorrente.mostraAreaDiPesca();

            System.out.println("\nScegli una carta da pescare (1-6):");
            int cartaDaPescare = sc.nextInt();

            giocatoreCorrente.pescaCarta(cartaDaPescare);

            System.out.println("\nMano Giocatore aggiornata:");
            giocatoreCorrente.mostraMano();

            turno.controlloPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }
        }

        sc.close();
    }
}
