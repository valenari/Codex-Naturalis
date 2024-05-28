package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import java.util.ArrayList;
import java.util.List;

public class ManoGiocatore {
    private CartaRisorsa cartaR1;
    private CartaRisorsa cartaR2;
    private CartaOro cartaO1;

    public ManoGiocatore() {
        this.cartaR1 = null;
        this.cartaR2 = null;
        this.cartaO1 = null;
    }

    public void creaMano(CartaRisorsa cartaR1, CartaRisorsa cartaR2, CartaOro cartaO1) {
        this.cartaR1 = cartaR1;
        this.cartaR2 = cartaR2;
        this.cartaO1 = cartaO1;
    }

    public void rimuoviCarta(CartaRisorsa carta) {
        if (carta == cartaR1) {
            cartaR1 = null;
        } else if (carta == cartaR2) {
            cartaR2 = null;
        }
    }

    public void aggiungiCarta(CartaRisorsa carta) {
        if (cartaR1 == null) {
            cartaR1 = carta;
        } else if (cartaR2 == null) {
            cartaR2 = carta;
        }
    }

    public void rimuoviCarta(CartaOro carta) {
        if (carta == cartaO1) {
            cartaO1 = null;
        }
    }

    public void aggiungiCarta(CartaOro carta) {
        if (cartaO1 == null) {
            cartaO1 = carta;
        }
    }

    public List<Carta> getCarte() {
        List<Carta> carte = new ArrayList<>();
        if (cartaR1 != null) carte.add(cartaR1);
        if (cartaR2 != null) carte.add(cartaR2);
        if (cartaO1 != null) carte.add(cartaO1);
        return carte;
    }

    public void stampaMano() {
        for (Carta carta : getCarte()) {
            carta.stampaCarta();
        }
    }
}
