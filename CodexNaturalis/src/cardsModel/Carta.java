package cardsModel;

import Modello_giocatore.Caselleproibite;

public abstract class Carta {
    private int idCarta;
    private String tipoCarta;
    private String fronte;
    private String retro;
    private Caselleproibite caselle;
    private boolean fronteVisibile;

    // Costruttore della classe Carta
    public Carta(int idCarta, String tipoCarta, String fronte, String retro, Caselleproibite caselle) {
        this.idCarta = idCarta;
        this.tipoCarta = tipoCarta;
        this.fronte = fronte;
        this.retro = retro;
        this.caselle = caselle;
        this.fronteVisibile = true;
    }
    
    public void giraCarta() {
        this.fronteVisibile = !this.fronteVisibile;
    }

    // Getter per idCarta
    public int getIdCarta() {
        return idCarta;
    }

    // Setter per idCarta
    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    // Getter per tipoCarta
    public String getTipoCarta() {
        return tipoCarta;
    }

    // Setter per tipoCarta
    public void setTipoCarta(String tipoCarta) {
        this.tipoCarta = tipoCarta;
    }

    // Getter per fronte
    public String getFronte() {
        return fronte;
    }

    // Setter per fronte
    public void setFronte(String fronte) {
        this.fronte = fronte;
    }

    // Getter per retro
    public String getRetro() {
        return retro;
    }

    // Setter per retro
    public void setRetro(String retro) {
        this.retro = retro;
    }

    // Getter per caselle
    public Caselleproibite getCaselle() {
        return caselle;
    }

    // Setter per caselle
    public void setCaselle(Caselleproibite caselle) {
        this.caselle = caselle;
    }
    
    public boolean isFronte() {
        return fronteVisibile;
    }

    public void setFronte(boolean fronteVisible) {
        this.fronteVisibile = fronteVisible;
    }

    // Metodo per verificare se un angolo specifico è nascosto
    public String getAngolo(int indice) {
        String[] angoli = getFronte().split(" - ");
        return angoli[indice];
    }

    // Metodo per verificare se l'angolo è nascosto
    public boolean isAngoloNascosto(String angolo) {
        return "Nascosto".equals(angolo);
    }
    
    public void copriAngolo(int indice) {
        String[] angoli = isFronte() ? fronte.split(" - ") : retro.split(" - ");
        angoli[indice] = "❌";
        if (isFronte()) {
            this.fronte = String.join(" - ", angoli);
        } else {
            this.retro = String.join(" - ", angoli);
        }
    }

    // Metodo per stampare le informazioni della carta
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
    
    // Metodo astratto per stampare il retro della carta
    public String toStringRetro() {
        return retro;
    }
}
