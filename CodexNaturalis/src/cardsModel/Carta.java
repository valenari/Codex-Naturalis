package cardsModel;

import Modello_giocatore.Caselleproibite;

public abstract class Carta {
    private int idCarta;
    private String tipoCarta;
    private String fronte;
    private String retro;
    private Caselleproibite caselle;
    private boolean fronteCarta; // true se la carta è sul fronte, false se è sul retro

    // Costruttore della classe Carta
    public Carta(int idCarta, String tipoCarta, String fronte, String retro, Caselleproibite caselle) {
        this.idCarta = idCarta;
        this.tipoCarta = tipoCarta;
        this.fronte = fronte;
        this.retro = retro;
        this.caselle = caselle;
        this.fronteCarta = true; // La carta è inizialmente sul fronte
    }

    public void giraCarta() {
        String temp = this.fronte;
        this.fronte = this.retro;
        this.retro = temp;
    }

    public boolean isFronte() {
        return fronteCarta;
    }

 // Setter per fronte
    public void setFronte(String fronte) {
        this.fronte = fronte;
    }

    // Getter e setter per gli altri attributi
    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public String getTipoCarta() {
        return tipoCarta;
    }

    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    public String getFronte() {
        return fronte;
    }

    public String getRetro() {
        return retro;
    }

    public void setRetro(String retro) {
        this.retro = retro;
    }

    public Caselleproibite getCaselle() {
        return caselle;
    }

    public void setCaselle(Caselleproibite caselle) {
        this.caselle = caselle;
    }

    public String getAngolo(int indice) {
        String[] angoli = fronteCarta ? fronte.split(" - ") : retro.split(" - ");
        return angoli[indice];
    }

    public boolean isAngoloNascosto(String angolo) {
        return "Nascosto".equals(angolo);
    }

    public void sostituisciAngolo(int indice, String sostituto) {
        String[] angoli = fronteCarta ? fronte.split(" - ") : retro.split(" - ");
        angoli[indice] = sostituto;
        if (fronteCarta) {
            fronte = String.join(" - ", angoli);
        } else {
            retro = String.join(" - ", angoli);
        }
    }

    public void stampaCarta() {
        System.out.println("ID: " + idCarta);
        System.out.println("Tipo Carta: " + tipoCarta);
        System.out.println("Fronte: " + fronte);
        System.out.println("Retro: " + retro);
        System.out.println("Caselle: " + caselle);
    }

    @Override
    public String toString() {
        return "ID: " + idCarta + "\nTipo Carta: " + tipoCarta + "\nFronte: " + fronte + "\nRetro: " + retro + "\nCaselle: " + caselle;
    }

    public abstract String toStringRetro();
}
