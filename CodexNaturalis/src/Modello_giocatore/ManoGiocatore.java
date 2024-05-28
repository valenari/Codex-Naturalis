package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;

import java.util.ArrayList;
import java.util.List;

public class ManoGiocatore {
    private List<Carta> carte;

    public ManoGiocatore() {
        this.carte = new ArrayList<>();
    }

    public void aggiungiCarta(Carta carta) {
        if (carte.size() < 3) {
            carte.add(carta);
        } else {
            System.out.println("Hai già 3 carte in mano.");
        }
    }

    public void rimuoviCarta(Carta carta) {
        carte.remove(carta);
    }

    public Carta getCarta(int indice) {
        if (indice >= 0 && indice < carte.size()) {
            return carte.get(indice);
        }
        return null;
    }

    public void stampaMano() {
        System.out.println("Carte nella mano:");
        stampaCarteOrizzontalmenteConNumeri(carte);
    }

    private void stampaCarteOrizzontalmenteConNumeri(List<Carta> carte) {
        String[][] carteStringhe = new String[carte.size()][];
        int maxLines = 0;

        // Converti ogni carta in un array di righe
        for (int i = 0; i < carte.size(); i++) {
            carteStringhe[i] = carte.get(i).toString().split("\n");
            maxLines = Math.max(maxLines, carteStringhe[i].length);
        }

        // Stampa le righe delle carte con uno spazio tra di loro
        for (int line = 0; line < maxLines; line++) {
            for (int i = 0; i < carteStringhe.length; i++) {
                if (line < carteStringhe[i].length) {
                    if (line == carteStringhe[i].length - 1) {
                        String riga = carteStringhe[i][line];
                        int centerIndex = riga.length() / 2;
                        String numeroCarta = "{" + (i + 1) + "}";
                        int start = centerIndex - numeroCarta.length() / 2;
                        StringBuilder sb = new StringBuilder(riga);
                        sb.replace(start, start + numeroCarta.length(), numeroCarta);
                        System.out.print(sb.toString());
                    } else {
                        System.out.print(carteStringhe[i][line]);
                    }
                } else {
                    System.out.print(" ".repeat(carteStringhe[0][0].length())); // Spazio vuoto se la carta ha meno righe
                }
                System.out.print("\t"); // Spazio tra le carte
            }
            System.out.println();
        }
    }

    public List<Carta> getCarte() {
        return carte;
    }
}
