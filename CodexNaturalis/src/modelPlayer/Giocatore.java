package modelPlayer;

import java.util.List;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import modelObiettivi.Obiettivo;
import modelTavolo.AreaDiPesca;
import util.StampaCarta;

public class Giocatore {
    private String nome;
    private int punti;
    private Contatori contatori;
    private ManoGiocatore manoGiocatore;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;
    private CartaIniziale cartaIniziale;
    private Obiettivo obiettivoSegreto;
    private List<Obiettivo> obiettiviComuni;

    public Giocatore(String nome, MazzoCarte mazzoIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro, AreaDiPesca areaDiPesca, boolean fronteIniziale) {
        this.nome = nome;
        this.punti = 0;
        this.contatori = new Contatori();
        this.manoGiocatore = new ManoGiocatore();
        this.areaDiPesca = areaDiPesca;
        this.cartaIniziale = (CartaIniziale) mazzoIniziale.pescaCarta();
        if (!fronteIniziale) {
            cartaIniziale.giraCarta();
        }
        this.areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);

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
            int punti = cartaGiocata instanceof CartaRisorsa ? ((CartaRisorsa) cartaGiocata).getPunti() : 0;

            if (cartaGiocata instanceof CartaOro) {
            	 CartaOro cartaOro = (CartaOro) cartaGiocata;
                 int angoliCoperti = areaDiGioco.getAngoliCoperti();
                 int oggettiVisibili = contatori.getContatore(cartaOro.getTipoRegno());
                 punti = cartaOro.calcolaPunti(angoliCoperti, oggettiVisibili);
            }
            aggiungiPunti(punti);
            return true;
        } else {
            System.out.println("Carta non valida.");
            return false;
        }
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

    public void mostraCartaIniziale() {
        StampaCarta.stampaOrizzontalmente(cartaIniziale);
    }

    public CartaIniziale getCartaIniziale() {
        return cartaIniziale;
    }

    public void setObiettivoSegreto(Obiettivo obiettivoSegreto) {
        this.obiettivoSegreto = obiettivoSegreto;
    }
    
    public Obiettivo getObiettivoSegreto() {
        return obiettivoSegreto;
    }

    public void calcolaPuntiObiettivi(List<Obiettivo> obiettiviComuni) {
        int puntiObiettivi = 0;

        // Calcola punti per obiettivo segreto
        if (obiettivoSegreto.verificaObiettivo(areaDiGioco, contatori)) {
            puntiObiettivi += obiettivoSegreto.getPunti();
        }

        // Calcola punti per obiettivi comuni
        for (Obiettivo obiettivo : obiettiviComuni) {
            if (obiettivo.verificaObiettivo(areaDiGioco, contatori)) {
                puntiObiettivi += obiettivo.getPunti();
            }
        }

        aggiungiPunti(puntiObiettivi);
    }

    public int getNumeroObiettivi() {
        int numeroObiettivi = 0;

        if (obiettivoSegreto.verificaObiettivo(areaDiGioco, contatori)) {
            numeroObiettivi++;
        }

        for (Obiettivo obiettivo : obiettiviComuni) {
            if (obiettivo.verificaObiettivo(areaDiGioco, contatori)) {
                numeroObiettivi++;
            }
        }

        return numeroObiettivi;
    }
}
