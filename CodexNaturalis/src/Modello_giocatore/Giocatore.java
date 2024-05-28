package Modello_giocatore;

import Base.PedinaC;
import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import modelTavolo.AreaDiPesca;
import java.util.Scanner;

public class Giocatore {
    private String nome;
    private int punti;
    private PedinaC pedina;
    private ManoGiocatore manoGiocatore;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;

    public Giocatore(String nome, MazzoCarte mazzoIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.nome = nome;
        this.punti = 0;
        //this.pedina = new PedinaC();
        this.manoGiocatore = new ManoGiocatore();
        this.areaDiGioco = new AreaDiGioco((CartaIniziale) mazzoIniziale.pescaCarta());
        this.areaDiPesca = new AreaDiPesca(mazzoRisorsa.getCarte(), mazzoOro.getCarte());

        // Inizializza la mano del giocatore con carte iniziali
        this.manoGiocatore.creaMano(
                (CartaRisorsa) mazzoRisorsa.pescaCarta(),
                (CartaRisorsa) mazzoRisorsa.pescaCarta(),
                (CartaOro) mazzoOro.pescaCarta()
        );
    }

    public void giocaCarta(Carta carta, int x, int y) {
        areaDiGioco.giocaCarta(carta, x, y);
        manoGiocatore.rimuoviCarta(carta);
        selezionaNuovaCarta();
    }

    public void selezionaNuovaCarta() {
        Scanner scanner = new Scanner(System.in);
        areaDiPesca.mostraAreaDiPesca();
        System.out.println("Seleziona una carta da pescare (1-6):");
        int scelta = scanner.nextInt();
        Carta nuovaCarta = null;
        if (scelta >= 1 && scelta <= 3) {
            nuovaCarta = areaDiPesca.pescaCartaRisorsa(scelta - 1);
        } else if (scelta >= 4 && scelta <= 6) {
            nuovaCarta = areaDiPesca.pescaCartaOro(scelta - 4);
        }
        if (nuovaCarta != null) {
            manoGiocatore.aggiungiCarta(nuovaCarta);
        }
    }

    public void stampaInfoGiocatore() {
        System.out.println("Giocatore: " + nome);
        System.out.println("Punti: " + punti);
        System.out.println("Area di Gioco:");
        areaDiGioco.visualizzaGriglia();
        System.out.println("Mano del Giocatore:");
        manoGiocatore.stampaMano();
    }

    public ManoGiocatore getManoGiocatore() {
        return manoGiocatore;
    }
}
