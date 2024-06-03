package modelObiettivi;

import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Integer> contatoriTemp = new HashMap<>();
        for (Map.Entry<String, Integer> entry : contatori.getContatori().entrySet()) {
            contatoriTemp.put(entry.getKey(), entry.getValue());
        }

        int punteggioTotale = 0;
        boolean obiettivoSoddisfatto = true;

        while (obiettivoSoddisfatto) {
            obiettivoSoddisfatto = true;
            for (int i = 0; i < tipiRisorse.size(); i++) {
                String risorsa = tipiRisorse.get(i);
                int quantitaRichiesta = quantita.get(i);

                if (contatoriTemp.getOrDefault(risorsa, 0) < quantitaRichiesta) {
                    obiettivoSoddisfatto = false;
                    break;
                }
            }

            if (obiettivoSoddisfatto) {
                punteggioTotale += getPunti();
                for (int i = 0; i < tipiRisorse.size(); i++) {
                    String risorsa = tipiRisorse.get(i);
                    int quantitaRichiesta = quantita.get(i);
                    contatoriTemp.put(risorsa, contatoriTemp.get(risorsa) - quantitaRichiesta);
                }
            }
        }

        return punteggioTotale > 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipi Risorse: " + tipiRisorse + ", Quantità: " + quantita;
    }
}
