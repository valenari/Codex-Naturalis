package cardsModel;

import java.util.ArrayList;
import java.util.List;

public class CartaOro extends Carta {
    private String tipoRegno;
    private int punti;
    private String criterioPunti;
    private List<String> risorseRichieste;

    public CartaOro(int idCarta, String fronte, String tipoRegno, int punti, String criterioPunti, List<String> risorseRichieste) {
        super(idCarta, "Oro", fronte, generaRetro(tipoRegno));
        this.tipoRegno = tipoRegno;
        this.punti = punti;
        this.criterioPunti = criterioPunti;
        this.risorseRichieste = new ArrayList<>(risorseRichieste);
    }

    private static String generaRetro(String tipoRegno) {
        return "Visibile - Visibile - Visibile - Visibile - " + tipoRegno;
    }

    public String getTipoRegno() {
        return tipoRegno;
    }

    public void setTipoRegno(String tipoRegno) {
        this.tipoRegno = tipoRegno;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public String getCriterioPunti() {
        return criterioPunti;
    }

    public void setCriterioPunti(String criterioPunti) {
        this.criterioPunti = criterioPunti;
    }

    public List<String> getRisorseRichieste() {
        return new ArrayList<>(risorseRichieste);
    }

    public void setRisorseRichieste(List<String> risorseRichieste) {
        this.risorseRichieste = new ArrayList<>(risorseRichieste);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] angoli = getFronte().split(" - ");
        String puntiECriterio = getEmojiPunti(punti) + " " + getEmojiCriterio(criterioPunti);
        String risorseRichiesteStr = String.join(" ", risorseRichieste.stream().map(this::getEmojiAngolo).toArray(String[]::new));

        sb.append("----------------------------\n");

        sb.append(String.format("[%s%s%21s%s%s]\n",
            getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
            centraStringa(puntiECriterio, 18),
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        sb.append(String.format("[%26s]\n", ""));
        sb.append(String.format("[%s]\n", centraStringa(getEmojiRegno(tipoRegno), 26)));
        sb.append(String.format("[%26s]\n", ""));

        String angoloDxBasso = getBordoAngolo(angoli[3], true) + getEmojiAngolo(angoli[3]);
        if (angoloDxBasso.trim().length() == 2) {
            angoloDxBasso = getBordoAngolo(angoli[3], true) + " " + getEmojiAngolo(angoli[3]);
        }

        sb.append(String.format("[%s%s%21s%s]\n",
            getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], false),
            centraStringa(risorseRichiesteStr, 18),
            angoloDxBasso
        ));

        sb.append("----------------------------\n");
        return sb.toString();
    }

    @Override
    public String toStringRetro() {
        StringBuilder sb = new StringBuilder();
        String[] angoli = getRetro().split(" - ");

        sb.append("----------------------------\n");

        sb.append(String.format("[%s%s%20s%s%s]\n",
            getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
            "",
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        sb.append(String.format("[%26s]\n", ""));
        sb.append(String.format("[%s]\n", centraStringa(getEmojiRegno(tipoRegno), 26)));
        sb.append(String.format("[%26s]\n", ""));

        String angoloDxBasso = getBordoAngolo(angoli[3], true) + getEmojiAngolo(angoli[3]);
        if (angoloDxBasso.trim().length() == 2) {
            angoloDxBasso = getBordoAngolo(angoli[3], true) + " " + getEmojiAngolo(angoli[3]);
        }

        sb.append(String.format("[%s%s%20s%s]\n",
            getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], false),
            "",
            angoloDxBasso
        ));

        sb.append("----------------------------\n");
        return sb.toString();
    }
}
