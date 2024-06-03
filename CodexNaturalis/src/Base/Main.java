package Base;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.MazzoCarte;
import modelObiettivi.Obiettivo;
import modelObiettivi.CaricatoreObiettivi;
import game.Turno;
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

        // Caricamento degli obiettivi
        CaricatoreObiettivi caricatoreObiettivi = new CaricatoreObiettivi();
        List<Obiettivo> obiettiviDisposizione = caricatoreObiettivi.caricaObiettiviDisposizione("src/fileObiettivi/ObiettiviDisposizione.txt");
        List<Obiettivo> obiettiviRisorse = caricatoreObiettivi.caricaObiettiviRisorse("src/fileObiettivi/ObiettiviRisorse.txt");

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
            Giocatore giocatore = new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, true);
            
            // Stampa della carta iniziale del giocatore
            System.out.println("Carta iniziale di " + nomeGiocatore + ":");
            giocatore.mostraCartaIniziale();
            
            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;
            if (!fronteIniziale) {
                giocatore.getCartaIniziale().giraCarta();
            }
            
            giocatori.add(giocatore);
        }

        // Assegnazione degli obiettivi
        List<Obiettivo> obiettiviComuni = new ArrayList<>();
        obiettiviComuni.add(obiettiviDisposizione.get(0));
        obiettiviComuni.add(obiettiviDisposizione.get(1));
        
        System.out.println("Obiettivi comuni:");
        for (Obiettivo obiettivo : obiettiviComuni) {
            System.out.println(obiettivo.getDescrizione());
        }

        for (Giocatore giocatore : giocatori) {
            System.out.println(giocatore.getNome() + ", scegli il tuo obiettivo segreto:");
            System.out.println("1. " + obiettiviDisposizione.get(2).getDescrizione());
            System.out.println("2. " + obiettiviDisposizione.get(3).getDescrizione());
            int sceltaObiettivo = sc.nextInt();
            if (sceltaObiettivo == 1) {
                giocatore.setObiettivoSegreto(obiettiviDisposizione.get(2));
            } else {
                giocatore.setObiettivoSegreto(obiettiviDisposizione.get(3));
            }
        }

        Turno turno = new Turno(giocatori);

        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            // Logica di gioco per ogni turno
            boolean cartaGiocataCorrettamente = false;
            while (!cartaGiocataCorrettamente) {
                giocatoreCorrente.mostraMano();
                System.out.println("Scegli una carta da giocare (1-3):");
                int cartaDaGiocare = sc.nextInt();
                System.out.println("Scegli una posizione nella griglia:");
                int posizioneGriglia = sc.nextInt();
                System.out.println("Vuoi giocarla di fronte (1) o di retro (2)?");
                boolean fronte = sc.nextInt() == 1;

                cartaGiocataCorrettamente = giocatoreCorrente.giocaCarta(cartaDaGiocare - 1, posizioneGriglia, fronte);
            }

            // Pescare una carta
            System.out.println("Scegli una carta da pescare:");
            areaDiPesca.mostraAreaDiPesca();
            int cartaDaPescare = sc.nextInt();

            giocatoreCorrente.pescaCarta(cartaDaPescare);

            turno.controllaPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }
        }

        System.out.println("La partita è terminata!");
        turno.dichiaraVincitore();
    }
}
