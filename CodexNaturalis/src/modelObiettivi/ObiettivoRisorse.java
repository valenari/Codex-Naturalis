package modelObiettivi;

import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;

import java.util.List;

public class ObiettivoRisorse extends Obiettivo {
    private List<String> tipiRisorse;
    private List<Integer> quantita;

    public ObiettivoRisorse(String descrizione, int punti, List<String> tipiRisorse, List<Integer> quantita) {
        super(descrizione, punti);
        this.tipiRisorse = tipiRisorse;
        this.quantita = quantita;
    }

    public List<String> getTipiRisorse() {
        return tipiRisorse;
    }

    public List<Integer> getQuantita() {
        return quantita;
    }

    @Override
    public boolean verificaObiettivo(AreaDiGioco areaDiGioco, Contatori contatori) {
        for (int i = 0; i < tipiRisorse.size(); i++) {
            if (contatori.getContatore(tipiRisorse.get(i)) < quantita.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipi Risorse: " + tipiRisorse + ", Quantità: " + quantita;
    }
}
