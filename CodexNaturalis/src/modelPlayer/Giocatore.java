package modelPlayer;

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
    private Contatori contatori;
    private ManoGiocatore manoGiocatore;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;
    private Scanner scanner;

    public Giocatore(String nome, CartaIniziale cartaIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro, boolean fronteIniziale) {
        this.nome = nome;
        this.punti = 0;
        this.contatori = new Contatori();
        this.manoGiocatore = new ManoGiocatore();
        this.scanner = new Scanner(System.in);
        if (!fronteIniziale) {
            cartaIniziale.giraCarta();
        }
        this.areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);
        this.areaDiPesca = new AreaDiPesca(mazzoRisorsa.getCarte(), mazzoOro.getCarte());

        for (int i = 0; i < 2; i++) {
            manoGiocatore.aggiungiCarta((CartaRisorsa) mazzoRisorsa.pescaCarta());
        }
        manoGiocatore.aggiungiCarta((CartaOro) mazzoOro.pescaCarta());
    }

    public String getNome() {
        return nome;
    }

    public int getPunti() {
        return punti;
    }

    public void aggiungiPunti(int puntiDaAggiungere) {
        this.punti += puntiDaAggiungere;
    }

    public void mostraAreaDiGioco() {
        areaDiGioco.visualizzaGriglia();
    }

    public void mostraMano() {
        manoGiocatore.stampaMano();
    }

    public void giocaCarta(int indiceCartaMano, int posizioneGriglia, boolean fronte) {
        Carta cartaDaGiocare = manoGiocatore.getCarta(indiceCartaMano);

        // Verifica se è una carta oro e se il giocatore ha abbastanza risorse
        if (cartaDaGiocare instanceof CartaOro && fronte) {
            CartaOro cartaOro = (CartaOro) cartaDaGiocare;
            if (!verificaRisorse(cartaOro)) {
                System.out.println("Non hai abbastanza risorse per giocare questa carta oro di fronte.");
                scegliAltraCarta();
                return;
            }
        }

        Carta cartaGiocata = manoGiocatore.rimuoviCarta(indiceCartaMano);
        if (cartaGiocata != null) {
            if (!fronte) {
                cartaGiocata.giraCarta();
            }
            areaDiGioco.posizionaCarta(cartaGiocata, posizioneGriglia, fronte);
        } else {
            System.out.println("Carta non valida.");
        }
    }

    private void scegliAltraCarta() {
        boolean cartaGiocata = false;
        while (!cartaGiocata) {
            System.out.println("\nScegli un'altra carta da giocare (1-3):");
            int cartaDaGiocare = scanner.nextInt() - 1;

            System.out.println("Vuoi giocare la carta di fronte (1) o di retro (2)?");
            boolean fronte = scanner.nextInt() == 1;

            System.out.println("Scegli la posizione (numero casella disponibile):");
            int posizione = scanner.nextInt();

            Carta cartaDaTentare = manoGiocatore.getCarta(cartaDaGiocare);
            if (!(cartaDaTentare instanceof CartaOro && fronte) || verificaRisorse((CartaOro) cartaDaTentare)) {
                giocaCarta(cartaDaGiocare, posizione, fronte);
                cartaGiocata = true;
            } else {
                System.out.println("Non hai abbastanza risorse per giocare questa carta oro di fronte. Scegli un'altra carta.");
            }
        }
    }

    public boolean verificaRisorse(CartaOro cartaOro) {
        return contatori.verificaRisorse(cartaOro.getRisorseRichieste());
    }

    public void mostraAreaDiPesca() {
        areaDiPesca.mostraAreaDiPesca();
    }

    public void pescaCarta(int indiceCartaPesca) {
        Carta cartaPescata = areaDiPesca.pescaCarta(indiceCartaPesca - 1); // Adjusting index for 0-based list
        if (cartaPescata != null) {
            manoGiocatore.aggiungiCarta(cartaPescata);
        } else {
            System.out.println("Carta non valida o indice fuori dai limiti.");
        }
    }
}
