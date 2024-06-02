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
        this.caricaCarteDaFile(filename);
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

    private void caricaCarteDaFile(String filename) {
        switch (tipoMazzo) {
            case "Risorsa":
                carte.addAll(FileReaderUtil.leggiCarteRisorsa(filename));
                break;
            case "Oro":
                carte.addAll(FileReaderUtil.leggiCarteOro(filename));
                break;
            case "Iniziale":
                carte.addAll(FileReaderUtil.leggiCarteIniziali(filename));
                break;
            default:
                throw new IllegalArgumentException("Tipo di mazzo non valido: " + tipoMazzo);
        }
    }

    public List<Carta> getCarte() {
        return new ArrayList<>(carte);
    }

    public String getTipoMazzo() {
        return tipoMazzo;
    }

    public int getCarteRimanenti() {
        return carte.size() - puntatore;
    }

    public boolean isVuoto() {
        return puntatore >= carte.size();
    }
}
