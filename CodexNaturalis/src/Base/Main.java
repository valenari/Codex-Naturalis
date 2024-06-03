package Base;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.MazzoCarte;
import game.Turno;

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
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteInizialiTest.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Creazione dei Giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        int numeroGiocatori;

        do {
            System.out.println("Inserisci il numero di giocatori (almeno 2 e massimo 4):");
            numeroGiocatori = sc.nextInt();
            if (numeroGiocatori < 2 || numeroGiocatori > 4)
                System.out.println("ERRORE: il numero di giocatori non è valido");
        } while (numeroGiocatori < 2 || numeroGiocatori > 4);

        for (int i = 0; i < numeroGiocatori; i++) {
            System.out.println("Inserisci il nome del Giocatore " + (i + 1) + ":");
            String nomeGiocatore = sc.next();
            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;
            giocatori.add(new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, fronteIniziale));
        }

        sc.close();

        Turno turno = new Turno(giocatori);

        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            // Logica di gioco per ogni turno

            turno.controllaPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }
        }
    }
}
