package cardsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazzoCarte {
    private List<Carta> carte;
    private int puntatore;
    private String tipoMazzo;

    // Costruttore della classe MazzoCarte
    public MazzoCarte(String tipoMazzo) {
        this.carte = new ArrayList<>();
        this.tipoMazzo = tipoMazzo;
        this.puntatore = 0;
    }

    // Metodo per aggiungere una carta al mazzo
    public void aggiungiCarta(Carta carta) {
        if (carta.getTipoCarta().equals(tipoMazzo)) {
            carte.add(carta);
        }
    }

    // Metodo per pescare una carta dal mazzo
    public Carta pescaCarta() {
        if (puntatore < carte.size()) {
            return carte.get(puntatore++);
        }
        return null; // Se il mazzo è vuoto, ritorna null
    }

    // Metodo per mescolare le carte nel mazzo
    public void mescolaMazzo() {
        Collections.shuffle(carte);
    }

    // Metodo per caricare le carte da un file
    public void caricaCarteDaFile(String filename) {
        if (tipoMazzo.equals("Risorsa")) {
            carte.addAll(CartaRisorsa.leggiCarteRisorsa(filename));
        } else if (tipoMazzo.equals("Oro")) {
            carte.addAll(CartaOro.leggiCarteOro(filename));
        } else if (tipoMazzo.equals("Iniziale")) {
            carte.addAll(CartaIniziale.leggiCarteIniziali(filename));
        }
    }

    // Metodo per ottenere la lista di carte nel mazzo
    public List<Carta> getCarte() {
        return carte;
    }

    // Metodo per ottenere il tipo di mazzo
    public String getTipoMazzo() {
        return tipoMazzo;
    }
}
