package Base;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.MazzoCarte;
import game.Turno;
import modelObiettivi.CaricatoreObiettivi;
import modelObiettivi.MazzoObiettivi;
import modelObiettivi.Obiettivo;

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
        MazzoObiettivi mazzoObiettivi = new MazzoObiettivi();

        // Creazione dei Giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        int numeroGiocatori;

        do {
            System.out.println("Inserisci il numero di giocatori (almeno 2 e massimo 4):");
            numeroGiocatori = sc.nextInt();
            if (numeroGiocatori < 2 || numeroGiocatori > 4)
                System.out.println("ERRORE: il numero di giocatori non è valido");
        } while (numeroGiocatori < 2 || numeroGiocatori > 4);

        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        for (int i = 0; i < numeroGiocatori; i++) {
            System.out.println("Inserisci il nome del Giocatore " + (i + 1) + ":");
            String nomeGiocatore = sc.next();
            Giocatore giocatore = new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, true);
            giocatore.mostraCartaIniziale(); // Stampa della carta iniziale
            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;
            giocatore = new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, fronteIniziale);
            giocatori.add(giocatore);
        }

        // Selezione degli obiettivi comuni
        List<Obiettivo> obiettiviComuni = new ArrayList<>();
        obiettiviComuni.add(mazzoObiettivi.pescaObiettivo());
        obiettiviComuni.add(mazzoObiettivi.pescaObiettivo());

        System.out.println("Obiettivi comuni:");
        for (Obiettivo obiettivo : obiettiviComuni) {
            System.out.println(obiettivo);
        }

        // Selezione dell'obiettivo segreto per ogni giocatore
        
        for (int i = 0; i < numeroGiocatori; i++) {
        Obiettivo scelta1, scelta2;
        for (Giocatore giocatore : giocatori) {
        	scelta1=mazzoObiettivi.pescaObiettivo();
        	scelta2=mazzoObiettivi.pescaObiettivo();
            System.out.println(giocatore.getNome() + ", scegli il tuo obiettivo segreto:");
            System.out.println("1. " + scelta1);
            System.out.println("2. " + scelta2);
            int sceltaObiettivo = sc.nextInt();
            if (sceltaObiettivo == 1) {
                giocatore.setObiettivoSegreto(scelta1);
            } else {
                giocatore.setObiettivoSegreto(scelta2);
            }
            
        }

        Turno turno = new Turno(giocatori, areaDiPesca, mazzoRisorsa, mazzoOro);

        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            // Logica di gioco per ogni turno
            giocatoreCorrente.mostraAreaDiGioco();
            giocatoreCorrente.mostraMano();
            System.out.println("Scegli una carta da giocare:");
            int sceltaCarta = sc.nextInt();
            System.out.println("Scegli la posizione nella griglia:");
            int posizioneGriglia = sc.nextInt();
            System.out.println("Vuoi giocare la carta sul fronte (1) o sul retro (2)?");
            boolean fronte = sc.nextInt() == 1;

            while (!giocatoreCorrente.giocaCarta(sceltaCarta, posizioneGriglia, fronte)) {
                System.out.println("Selezione non valida. Scegli un'altra carta da giocare:");
                sceltaCarta = sc.nextInt();
                System.out.println("Scegli la posizione nella griglia:");
                posizioneGriglia = sc.nextInt();
                System.out.println("Vuoi giocare la carta sul fronte (1) o sul retro (2)?");
                fronte = sc.nextInt() == 1;
            }

            areaDiPesca.mostraAreaDiPesca();
            System.out.println("Scegli una carta da pescare (1 per carta coperta risorsa, 2-3 per carte visibili risorsa, 4 per carta coperta oro, 5-6 per carte visibili oro):");
            int sceltaPesca = sc.nextInt();
            giocatoreCorrente.pescaCarta(sceltaPesca);
            giocatoreCorrente.mostraMano();
            
            System.out.println("Punteggio del giocatore " + giocatoreCorrente.getNome() + ": " + giocatoreCorrente.getPunti());

            turno.controllaPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }
        }

        System.out.println("La partita è terminata!");
        turno.calcolaPuntiFinali(obiettiviComuni);
        
        turno.dichiaraVincitore();

        sc.close();
    }
    }
}
