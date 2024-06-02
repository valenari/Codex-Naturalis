package modelObiettivi;

import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;

public abstract class Obiettivo {
    private String descrizione;
    private int punti;

    public Obiettivo(String descrizione, int punti) {
        this.descrizione = descrizione;
        this.punti = punti;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getPunti() {
        return punti;
    }

    public abstract boolean verificaObiettivo(AreaDiGioco areaDiGioco, Contatori contatori);

    @Override
    public String toString() {
        return "Descrizione: " + descrizione + ", Punti: " + punti;
    }
}
