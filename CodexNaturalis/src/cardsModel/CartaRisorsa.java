package cardsModel;

public class CartaRisorsa extends Carta {
    private String tipoRegno;
    private int punti;

    public CartaRisorsa(int idCarta, String fronte, String tipoRegno, int punti) {
        super(idCarta, "Risorsa", fronte, generaRetro(tipoRegno));
        this.tipoRegno = tipoRegno;
        this.punti = punti;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] angoli = getFronte().split(" - ");
        String puntiStr = getEmojiPunti(punti);

        sb.append("----------------------------\n");

        sb.append(String.format("[%s%s%20s%s%s]\n",
            getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
            centraStringa(puntiStr, 18),
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
        sb.append(String.format("[%s]\n", centraStringa("[" + getEmojiRegno(tipoRegno) + "]", 26)));
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
