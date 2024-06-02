package modelObiettivi;

import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;
import cardsModel.Carta;

import java.util.List;

public class ObiettivoDisposizione extends Obiettivo {
    private List<String> sequenza;
    private List<int[]> posizioni;

    public ObiettivoDisposizione(String descrizione, int punti, List<String> sequenza, List<int[]> posizioni) {
        super(descrizione, punti);
        this.sequenza = sequenza;
        this.posizioni = posizioni;
    }

    @Override
    public boolean verificaObiettivo(AreaDiGioco areaDiGioco, Contatori contatori) {
        int centroX = areaDiGioco.getCentro()[0];
        int centroY = areaDiGioco.getCentro()[1];

        for (int[] posizione : posizioni) {
            int x = centroX + posizione[0];
            int y = centroY + posizione[1];
            Carta carta = areaDiGioco.getCartaInPosizione(x, y);
            if (carta == null || !carta.getTipoCarta().equals(sequenza.get(posizioni.indexOf(posizione)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", Sequenza: " + sequenza + ", Posizioni: " + posizioni;
    }
}
