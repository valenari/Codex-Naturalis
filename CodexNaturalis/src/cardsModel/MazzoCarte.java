package cardsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazzoCarte {
    private List<Carta> carte;
    private int puntatore;
    private String tipoMazzo;

    public MazzoCarte(String tipoMazzo, String filename) {
        this.carte = new ArrayList<>();
        this.tipoMazzo = tipoMazzo;
        this.puntatore = 0;
        //Riempimento Mazzo da file
        this.caricaCarteDaFile(filename);
        //Mescola il Mazzo
        this.mescolaMazzo();
    }

    public void aggiungiCarta(Carta carta) {
        if (carta.getTipoCarta().equals(tipoMazzo)) {
            carte.add(carta);
        }
    }

    public Carta pescaCarta() {
        if (puntatore < carte.size()) {
            return carte.get(puntatore++);
        }
        return null;
    }

    public List<Carta> pescaCarte(int numero) {
        List<Carta> pescate = new ArrayList<>();
        for (int i = 0; i < numero && puntatore < carte.size(); i++) {
            pescate.add(carte.get(puntatore++));
        }
        return pescate;
    }

    public void mescolaMazzo() {
        Collections.shuffle(carte);
    }

    public void caricaCarteDaFile(String filename) {
        if (tipoMazzo.equals("Risorsa")) {
            carte.addAll(CartaRisorsa.leggiCarteRisorsa(filename));
        } else if (tipoMazzo.equals("Oro")) {
            carte.addAll(CartaOro.leggiCarteOro(filename));
        } else if (tipoMazzo.equals("Iniziale")) {
            carte.addAll(CartaIniziale.leggiCarteIniziali(filename));
        }
    }

    public List<Carta> getCarte() {
        return carte;
    }

    public String getTipoMazzo() {
        return tipoMazzo;
    }
}
