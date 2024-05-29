package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import cardsModel.CartaIniziale;
import modelTavolo.AreaDiPesca;

public class Giocatore {
    private String nome;
    private int punti;
    private Contatori contatori;
    private ManoGiocatore manoGiocatore;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;

    public Giocatore(String nome, MazzoCarte mazzoIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.nome = nome;
        this.punti = 0;
        this.contatori = new Contatori();
        this.manoGiocatore = new ManoGiocatore();
        this.areaDiGioco = new AreaDiGioco((CartaIniziale) mazzoIniziale.pescaCarta());
        this.areaDiPesca = new AreaDiPesca(mazzoRisorsa.getCarte(), mazzoOro.getCarte());

        // Pesca le prime carte per la mano del giocatore
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
        aggiornaContatori();
    }

    public void mostraMano() {
        manoGiocatore.stampaMano();
    }

    public void giocaCarta(int indiceCartaMano, int posizioneGriglia, boolean fronte) {
        Carta cartaGiocata = manoGiocatore.rimuoviCarta(indiceCartaMano);
        if (cartaGiocata != null) {
            if (!fronte) {
                cartaGiocata.giraCarta();
            }
            areaDiGioco.posizionaCarta(cartaGiocata, posizioneGriglia);
        } else {
            System.out.println("Carta non valida.");
        }
    }

    private void aggiornaContatori() {
        contatori.aggiornaContatori(areaDiGioco);
    }

    public void mostraAreaDiPesca() {
        areaDiPesca.mostraAreaDiPesca();
    }

    public void pescaCarta(int indiceCartaPesca) {
        Carta cartaPescata = areaDiPesca.pescaCarta(indiceCartaPesca);
        manoGiocatore.aggiungiCarta(cartaPescata);
    }

    public void mostraContatori() {
        contatori.mostraContatori();
    }
}
