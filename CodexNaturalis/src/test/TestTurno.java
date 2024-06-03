package test;

import modelPlayer.Giocatore;
import modelTavolo.AreaDiPesca;
import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import modelObiettivi.Obiettivo;
import modelObiettivi.CaricatoreObiettivi;
import game.Turno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestTurno {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Creazione dei mazzi
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale", "src/fileCarte/CarteInizialiTest.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa", "src/fileCarte/CarteRisorsaTest.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro", "src/fileCarte/CarteOroTest.txt");

        // Creazione dell'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Creazione dei giocatori
        List<Giocatore> giocatori = new ArrayList<>();
        System.out.println("Inserisci il numero di giocatori (2-4):");
        int numeroGiocatori = sc.nextInt();
        for (int i = 0; i < numeroGiocatori; i++) {
            System.out.println("Inserisci il nome del Giocatore " + (i + 1) + ":");
            String nomeGiocatore = sc.next();
            System.out.println("Vuoi posizionare la carta iniziale di fronte (1) o di retro (2)?");
            boolean fronteIniziale = sc.nextInt() == 1;
            Giocatore giocatore = new Giocatore(nomeGiocatore, mazzoIniziale, mazzoRisorsa, mazzoOro, areaDiPesca, fronteIniziale);
            giocatori.add(giocatore);
            System.out.println("Carta iniziale del giocatore " + nomeGiocatore + ":");
            giocatore.mostraCartaIniziale();
        }

        // Caricamento degli obiettivi
        CaricatoreObiettivi caricatoreObiettivi = new CaricatoreObiettivi();
        List<Obiettivo> obiettiviDisposizione = caricatoreObiettivi.caricaObiettiviDisposizione("src/fileCarte/ObiettiviDisposizione.txt");
        List<Obiettivo> obiettiviRisorse = caricatoreObiettivi.caricaObiettiviRisorse("src/fileCarte/ObiettiviRisorse.txt");

        // Selezione degli obiettivi segreti
        for (Giocatore giocatore : giocatori) {
            System.out.println("Giocatore " + giocatore.getNome() + ", scegli il tuo obiettivo segreto:");
            for (int i = 0; i < obiettiviRisorse.size(); i++) {
                System.out.println((i + 1) + ": " + obiettiviRisorse.get(i));
            }
            int sceltaObiettivo = sc.nextInt() - 1;
            giocatore.setObiettivoSegreto(obiettiviRisorse.get(sceltaObiettivo));
        }

        // Creazione del turno
        Turno turno = new Turno(giocatori, areaDiPesca, mazzoRisorsa, mazzoOro);

        // Gioco dei turni
        while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());

            // Mostra la mano e l'area di gioco del giocatore corrente
            giocatoreCorrente.mostraMano();
            giocatoreCorrente.mostraAreaDiGioco();

            // Giocatore sceglie una carta da giocare
            boolean cartaGiocataConSuccesso;
            do {
                System.out.println("Scegli una carta da giocare (1-3):");
                int cartaScelta = sc.nextInt() - 1;
                System.out.println("Scegli una posizione nella griglia:");
                int posizioneGriglia = sc.nextInt();
                System.out.println("Vuoi giocare la carta di fronte (1) o di retro (2)?");
                boolean fronte = sc.nextInt() == 1;
                cartaGiocataConSuccesso = giocatoreCorrente.giocaCarta(cartaScelta, posizioneGriglia, fronte);
            } while (!cartaGiocataConSuccesso);

            // Mostra l'area di gioco aggiornata
            giocatoreCorrente.mostraAreaDiGioco();

            // Giocatore pesca una carta
            System.out.println("Scegli una carta da pescare (1-4):");
            int cartaDaPescare = sc.nextInt();
            giocatoreCorrente.pescaCarta(cartaDaPescare);

            // Mostra la mano aggiornata
            giocatoreCorrente.mostraMano();

            // Controlla il punteggio e termina il turno
            turno.controllaPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
            }
        }

        // Calcola i punti finali e dichiara il vincitore
        turno.calcolaPuntiFinali(obiettiviDisposizione);
        turno.dichiaraVincitore();
        sc.close();
    }
}
