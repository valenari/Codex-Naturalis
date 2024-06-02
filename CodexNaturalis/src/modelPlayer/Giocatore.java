package modelPlayer;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import modelTavolo.AreaDiPesca;
import modelObiettivi.Obiettivo;

import java.util.List;

public class Giocatore {
    private String nome;
    private int punti;
    private Contatori contatori;
    private ManoGiocatore manoGiocatore;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;
    private CartaIniziale cartaIniziale;
    private Obiettivo obiettivoSegreto;
    private int obiettiviRaggiunti;

    public Giocatore(String nome, MazzoCarte mazzoIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro, boolean fronteIniziale) {
        this.nome = nome;
        this.punti = 0;
        this.contatori = new Contatori();
        this.manoGiocatore = new ManoGiocatore();
        this.cartaIniziale = (CartaIniziale) mazzoIniziale.pescaCarta();
        this.obiettiviRaggiunti = 0;
        if (!fronteIniziale) {
            cartaIniziale.giraCarta();
        }
        this.areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);
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

    public boolean giocaCarta(int indiceCartaMano, int posizioneGriglia, boolean fronte) {
        Carta cartaDaGiocare = manoGiocatore.getCarta(indiceCartaMano);

        if (cartaDaGiocare instanceof CartaOro && fronte) {
            CartaOro cartaOro = (CartaOro) cartaDaGiocare;
            if (!verificaRisorse(cartaOro)) {
                System.out.println("Non hai abbastanza risorse per giocare questa carta oro di fronte.");
                return false;
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
            return false;
        }

        if (fronte && cartaGiocata instanceof CartaRisorsa) {
            aggiungiPunti(((CartaRisorsa) cartaGiocata).getPunti());
        }

        return true;
    }
    
    public boolean verificaRisorse(CartaOro cartaOro) {
        return contatori.verificaRisorse(cartaOro.getRisorseRichieste());
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

    public CartaIniziale getCartaIniziale() {
        return cartaIniziale;
    }

    public void setObiettivoSegreto(Obiettivo obiettivo) {
        this.obiettivoSegreto = obiettivo;
    }

    public Obiettivo getObiettivoSegreto() {
        return obiettivoSegreto;
    }

    public int getObiettiviRaggiunti() {
        return obiettiviRaggiunti;
    }

    public void incrementaObiettiviRaggiunti() {
        this.obiettiviRaggiunti++;
    }

    public AreaDiGioco getAreaDiGioco() {
        return areaDiGioco;
    }

    public Contatori getContatori() {
        return contatori;
    }

    public boolean isMazzoRisorsaVuoto() {
        return areaDiPesca.isMazzoRisorsaVuoto();
    }

    public boolean isMazzoOroVuoto() {
        return areaDiPesca.isMazzoOroVuoto();
    }
}
