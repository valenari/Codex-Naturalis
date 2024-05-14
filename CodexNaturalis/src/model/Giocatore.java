package model;

public class Giocatore {
    private String nome;
    private int punti;
    // Altri attributi e metodi necessari per gestire lo stato del giocatore

    public Giocatore(String nome) {
        this.nome = nome;
        this.punti = 0;
        // Inizializza gli altri attributi del giocatore se necessario
    }

    // Metodo per ottenere il nome del giocatore
    public String getNome() {
        return nome;
    }

    // Metodo per ottenere i punti del giocatore
    public int getPunti() {
        return punti;
    }

    // Metodo per aggiungere punti al giocatore
    public void aggiungiPunti(int puntiDaAggiungere) {
        this.punti += puntiDaAggiungere;
    }

    // Altri metodi per gestire le azioni del giocatore, come giocare una carta, ottenere carte bonus, ecc.
}
