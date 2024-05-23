package modelTavolo;

import java.util.ArrayList;
import java.util.List;

import cardsModel.Carta;
import cardsModel.MazzoCarte;

public class AreaDiPesca {
    private MazzoCarte mazzoRisorsa;
    private MazzoCarte mazzoOro;
    private List<Carta> carteVisibiliRisorsa;
    private List<Carta> carteVisibiliOro;

    // Costruttore della classe AreaDiPesca
    public AreaDiPesca(MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro, MazzoCarte mazzoIniziale) {
        this.mazzoRisorsa = mazzoRisorsa;
        this.mazzoOro = mazzoOro;
        this.carteVisibiliRisorsa = new ArrayList<>();
        this.carteVisibiliOro = new ArrayList<>();
    }

    // Metodo per inizializzare le carte visibili nell'area di pesca
    public void inizializzaPesca() {
        aggiornaCarteVisibiliRisorsa();
        aggiornaCarteVisibiliOro();
    }

    // Metodo per pescare una carta risorsa
    public Carta pescaCartaRisorsa() {
        Carta carta = mazzoRisorsa.pescaCarta();
        aggiornaCarteVisibiliRisorsa();
        return carta;
    }

    // Metodo per pescare una carta oro
    public Carta pescaCartaOro() {
        Carta carta = mazzoOro.pescaCarta();
        aggiornaCarteVisibiliOro();
        return carta;
    }

    // Metodo per aggiornare le carte risorsa visibili
    private void aggiornaCarteVisibiliRisorsa() {
        carteVisibiliRisorsa.clear();
        for (int i = 0; i < 2; i++) {
            Carta carta = mazzoRisorsa.pescaCarta();
            if (carta != null) {
                carteVisibiliRisorsa.add(carta);
            }
        }
    }

    // Metodo per aggiornare le carte oro visibili
    private void aggiornaCarteVisibiliOro() {
        carteVisibiliOro.clear();
        for (int i = 0; i < 2; i++) {
            Carta carta = mazzoOro.pescaCarta();
            if (carta != null) {
                carteVisibiliOro.add(carta);
            }
        }
    }

    // Getter per le carte visibili risorsa
    public List<Carta> getCarteVisibiliRisorsa() {
        return carteVisibiliRisorsa;
    }

    // Getter per le carte visibili oro
    public List<Carta> getCarteVisibiliOro() {
        return carteVisibiliOro;
    }
}


