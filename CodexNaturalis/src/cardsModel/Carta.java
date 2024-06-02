package cardsModel;

public abstract class Carta {
    private int idCarta;
    private String tipoCarta;
    private String fronte;
    private String retro;
    private boolean fronteCarta; // true se la carta è sul fronte, false se è sul retro

    public Carta(int idCarta, String tipoCarta, String fronte, String retro) {
        this.idCarta = idCarta;
        this.tipoCarta = tipoCarta;
        this.fronte = fronte;
        this.retro = retro;
        this.fronteCarta = true; // La carta è inizialmente sul fronte
    }

    public void giraCarta() {
        this.fronteCarta = !this.fronteCarta;
    }

    public boolean isFronte() {
        return fronteCarta;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public String getTipoCarta() {
        return tipoCarta;
    }

    public String getFronte() {
        return fronte;
    }

    public String getRetro() {
        return retro;
    }

    public String getAngolo(int indice) {
        String[] angoli = fronteCarta ? fronte.split(" - ") : retro.split(" - ");
        return angoli[indice];
    }

    public boolean isAngoloNascosto(String angolo) {
        return "Nascosto".equals(angolo);
    }

    public void sostituisciAngolo(int indice, String sostituto) {
        String[] angoli = fronteCarta ? fronte.split(" - ") : retro.split(" - ");
        angoli[indice] = sostituto;
        if (fronteCarta) {
            fronte = String.join(" - ", angoli);
        } else {
            retro = String.join(" - ", angoli);
        }
    }

    protected String getEmojiRegno(String regno) {
        switch (regno) {
            case "Vegetale":
                return "☘️";
            case "Fungo":
                return "🍄";
            case "Animale":
                return "🐺";
            case "Insetto":
                return "🦋";
            default:
                return "";
        }
    }

    protected String getEmojiAngolo(String angolo) {
        switch (angolo) {
            case "Vegetale":
                return "☘️";
            case "Fungo":
                return "🍄";
            case "Animale":
                return "🐺";
            case "Insetto":
                return "🦋";
            case "Piuma":
                return "𓆰";
            case "Pergamena":
                return "📜";
            case "Inchiostro":
                return "🧪";
            case "Visibile":
                return "  ";
            case "Nascosto":
                return " ";
            case "❌":
                return "❌";
            default:
                return "";
        }
    }

    protected String getEmojiPunti(int punti) {
        switch (punti) {
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
            case 4:
                return "4";
            case 5:
                return "5";
            default:
                return "";
        }
    }

    protected String getEmojiCriterio(String criterio) {
        switch (criterio) {
            case "Piuma":
                return "𓆰";
            case "Pergamena":
                return "📜";
            case "Inchiostro":
                return "🧪";
            case "Angoli":
                return "『』";
            default:
                return "";
        }
    }

    protected String getBordoAngolo(String angolo, boolean sinistra) {
        if ("Nascosto".equals(angolo)) {
            return "  ";
        }
        return sinistra ? "[" : "]";
    }

    protected String centraStringa(String str, int larghezza) {
        int spazi = larghezza - str.length();
        if (spazi <= 0) {
            return str;
        }
        int spaziPrima = spazi / 2;
        int spaziDopo = spazi - spaziPrima;
        return " ".repeat(spaziPrima) + str + " ".repeat(spaziDopo);
    }

    @Override
    public String toString() {
        return String.format("ID: %d\nTipo Carta: %s\nFronte: %s\nRetro: %s", idCarta, tipoCarta, fronte, retro);
    }

    public abstract String toStringRetro();
}
