package modelPlayer;

import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import modelTavolo.Punteggio;
import modelTavolo.AreaDiPesca;

public class Giocatore {
    private String nome;
    private int punti;
    private Contatori contatori;
    private ManoGiocatore manoGiocatore;
    private AreaDiGioco areaDiGioco;
    private AreaDiPesca areaDiPesca;

    public Giocatore(String nome, CartaIniziale cartaIniziale, MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro, boolean fronteIniziale) {
        this.nome = nome;
        this.punti = 0;
        this.contatori = new Contatori();
        this.manoGiocatore = new ManoGiocatore();

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
                System.out.println("Non hai abbastanza risorse per giocare questa carta oro di fronte. Scegli un'altra carta.");
                return false;
            }
        }

        Carta cartaGiocata = manoGiocatore.rimuoviCarta(indiceCartaMano);
        if (cartaGiocata != null) {
        	if (!fronte) {
                cartaGiocata.giraCarta();
            }
        	
            areaDiGioco.posizionaCarta(cartaGiocata, posizioneGriglia, fronte);

            if (fronte) {
                int puntiGuadagnati = 0;
                if (cartaGiocata instanceof CartaRisorsa) {
                    puntiGuadagnati = Punteggio.calcolaPuntiCartaRisorsa((CartaRisorsa) cartaGiocata);
                } else if (cartaGiocata instanceof CartaOro) {
                    puntiGuadagnati = Punteggio.calcolaPuntiCartaOro((CartaOro) cartaGiocata, contatori, areaDiGioco);
                }
                aggiungiPunti(puntiGuadagnati);
            }
            

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
}
