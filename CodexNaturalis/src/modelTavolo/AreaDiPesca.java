package modelTavolo;

import java.util.ArrayList;
import java.util.List;

public class AreaDiPesca {
    private MazzoCarte mazzoRisorse;
    private MazzoCarte mazzoOro;
    private List<Carta> carteVisibiliRisorse;
    private List<Carta> carteVisibiliOro;

    // Costruttore della classe AreaDiPesca
    public AreaDiPesca(MazzoCarte mazzoRisorse, MazzoCarte mazzoOro) {
        this.mazzoRisorse = mazzoRisorse;
        this.mazzoOro = mazzoOro;
        carteVisibiliRisorse = new ArrayList<>();
        carteVisibiliOro = new ArrayList<>();
    }

    // Metodo per inizializzare l'area di pesca
    public void inizializza() {
        carteVisibiliRisorse.clear();
        carteVisibiliOro.clear();
        for (int i = 0; i < 2; i++) {
            carteVisibiliRisorse.add(mazzoRisorse.pescaCarta());
            carteVisibiliOro.add(mazzoOro.pescaCarta());
        }
    }

    // Metodo per pescare una carta risorsa
    public Carta pescaCartaRisorsa() {
        return mazzoRisorse.pescaCarta();
    }

    // Metodo per pescare una carta oro
    public Carta pescaCartaOro() {
        return mazzoOro.pescaCarta();
    }

    // Getter per ottenere le carte visibili delle risorse
    public List<Carta> getCarteVisibiliRisorse() {
        return carteVisibiliRisorse;
    }

    // Getter per ottenere le carte visibili dell'oro
    public List<Carta> getCarteVisibiliOro() {
        return carteVisibiliOro;
    }
}

