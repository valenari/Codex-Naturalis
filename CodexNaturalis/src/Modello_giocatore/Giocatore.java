package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import modelTavolo.AreaDiPesca;

public class Giocatore {
    private String nome;
    private int punti;
    private ManoGiocatore manoG;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;

    public Giocatore(String nome, MazzoCarte mazzoIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.nome = nome;
        this.punti = 0;
        this.manoG = new ManoGiocatore();
        this.areaDiGioco = new AreaDiGioco((CartaIniziale) mazzoIniziale.pescaCarta());
        this.areaDiPesca = new AreaDiPesca(mazzoRisorsa.pescaCarte(3), mazzoOro.pescaCarte(3));
        this.manoG.aggiungiCarta((CartaRisorsa) mazzoRisorsa.pescaCarta());
        this.manoG.aggiungiCarta((CartaRisorsa) mazzoRisorsa.pescaCarta());
        this.manoG.aggiungiCarta((CartaOro) mazzoOro.pescaCarta());
    }

    public void mostraAreaDiGioco() {
        areaDiGioco.visualizzaGriglia();
    }

    public void mostraMano() {
        manoG.stampaMano();
    }

    public void giocaCarta(Carta carta, int indicePosizioneVuota) {
        areaDiGioco.posizionaCarta(carta, indicePosizioneVuota);
        manoG.rimuoviCarta(carta);
    }

    public void mostraAreaDiPesca() {
        areaDiPesca.mostraAreaDiPesca();
    }

    public void pescaCarta(int indice) {
        Carta cartaPescata = areaDiPesca.pescaCarta(indice);
        if (cartaPescata instanceof CartaRisorsa) {
            manoG.aggiungiCarta((CartaRisorsa) cartaPescata);
        } else if (cartaPescata instanceof CartaOro) {
            manoG.aggiungiCarta((CartaOro) cartaPescata);
        }
        areaDiPesca.aggiornaPesca();
    }

    public Carta getCartaDallaMano(int indice) {
        return manoG.getCarta(indice);
    }
}
