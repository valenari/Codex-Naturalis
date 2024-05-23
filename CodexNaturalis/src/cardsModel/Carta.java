package cardsModel;

import Modello_giocatore.Caselleproibite;

public class Carta {
    private int idCarta;
    private String tipoCarta;
    private String fronte;
    private String retro;
    private Caselleproibite caselle;

    // Costruttore della classe Carta
    public Carta(int idCarta, String tipoCarta, String fronte, String retro, Caselleproibite caselle) {
        this.idCarta = idCarta;
        this.tipoCarta = tipoCarta;
        this.fronte = fronte;
        this.retro = retro;
        this.caselle = caselle;
    }

    // Getter e setter per idCarta
    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    // Getter e setter per tipoCarta
    public String getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    // Getter e setter per fronte
    public String getFronte() {
        return fronte;
    }

    public void setFronte(String fronte) {
        this.fronte = fronte;
    }

    // Getter e setter per retro
    public String getRetro() {
        return retro;
    }

    public void setRetro(String retro) {
        this.retro = retro;
    }

    // Getter e setter per caselle
    public Caselleproibite getCaselle() {
        return caselle;
    }

    public void setCaselle(Caselleproibite caselle) {
        this.caselle = caselle;
    }
}
