package modelObiettivi;

import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;
import cardsModel.Carta;
import cardsModel.CartaRisorsa;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ObiettivoDisposizione extends Obiettivo {
    private List<String> sequenza;
    private List<int[]> posizioni;
    private Set<Carta> carteUsate;

    public ObiettivoDisposizione(String descrizione, int punti, List<String> sequenza, List<int[]> posizioni) {
        super(descrizione, punti);
        this.sequenza = sequenza;
        this.posizioni = posizioni;
        this.carteUsate = new HashSet<>();
    }

    @Override
    public boolean verificaObiettivo(AreaDiGioco areaDiGioco, Contatori contatori) {
        int dimensione = areaDiGioco.getDimensione();
        Carta[][] griglia = areaDiGioco.getGriglia();

        boolean obiettivoRaggiunto = false;

        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (verificaSequenza(griglia, i, j)) {
                    obiettivoRaggiunto = true;
                }
            }
        }
        return obiettivoRaggiunto;
    }

    private boolean verificaSequenza(Carta[][] griglia, int x, int y) {
        for (int k = 0; k < sequenza.size(); k++) {
            int[] posizione = posizioni.get(k);
            int newX = x + posizione[0];
            int newY = y - posizione[1];

            if (newX < 0 || newY < 0 || newX >= griglia.length || newY >= griglia[0].length) {
                return false;
            }

            Carta carta = griglia[newX][newY];
            if (carta == null || !(carta instanceof CartaRisorsa) || !((CartaRisorsa) carta).getTipoRegno().equals(sequenza.get(k)) || carteUsate.contains(carta)) {
                return false;
            }
        }

        for (int k = 0; k < sequenza.size(); k++) {
            int[] posizione = posizioni.get(k);
            int newX = x + posizione[0];
            int newY = y - posizione[1];
            carteUsate.add(griglia[newX][newY]);
        }

        return true;
    }

    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(super.toString());
    	sb.append(", Sequenza: ");
    	sb.append(sequenza);
    	sb.append(", Posizioni: ");
    	for(int[] pos : posizioni) {
    		sb.append(Arrays.toString(pos));
    	}
    	String result = sb.toString();
        return result;
    }
}
