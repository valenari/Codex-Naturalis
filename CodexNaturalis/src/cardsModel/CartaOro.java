package cardsModel;

import Modello_giocatore.Caselleproibite;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaOro extends Carta {
    private String tipoRegno;
    private List<String> risorseNecessarie;
    private int punti;
    private String criterioPunti;

    // Costruttore della classe CartaOro
    public CartaOro(int idCarta, String fronte, String retro, Caselleproibite caselle, String tipoRegno, List<String> risorseNecessarie, int punti, String criterioPunti) {
        super(idCarta, "Oro", fronte, retro, caselle);
        this.tipoRegno = tipoRegno;
        this.risorseNecessarie = risorseNecessarie;
        this.punti = punti;
        this.criterioPunti = criterioPunti;
    }

    // Getter e Setter per tipoRegno, risorseNecessarie, punti, criterioPunti

    public String getTipoRegno() {
        return tipoRegno;
    }

    public void setTipoRegno(String tipoRegno) {
        this.tipoRegno = tipoRegno;
    }

    public List<String> getRisorseNecessarie() {
        return risorseNecessarie;
    }

    public void setRisorseNecessarie(List<String> risorseNecessarie) {
        this.risorseNecessarie = risorseNecessarie;
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

    // Metodo statico per leggere carte oro dal file
    public static List<CartaOro> leggiCarteOro(String filename) {
        List<CartaOro> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String tipoRegno = null;
            String angoliFronte = null;
            List<String> risorseNecessarie = new ArrayList<>();
            int punti = 0;
            String criterioPunti = null;
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("N° ORO: ")) {
                    if (idCarta != -1) {
                        // Aggiungi la carta precedente alla lista
                        CartaOro carta = new CartaOro(idCarta, angoliFronte, "", caselle, tipoRegno, risorseNecessarie, punti, criterioPunti);
                        carte.add(carta);
                    }
                    // Inizia a leggere una nuova carta
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                    risorseNecessarie = new ArrayList<>();
                } else if (linea.startsWith("Regno: ")) {
                    tipoRegno = linea.split(": ")[1];
                } else if (linea.startsWith("Angoli Fronte: ")) {
                    angoliFronte = linea.split(": ")[1];
                } else if (linea.startsWith("Risorse Richieste: ")) {
                    String[] risorse = linea.split(": ")[1].split(" - ");
                    for (String risorsa : risorse) {
                        risorseNecessarie.add(risorsa);
                    }
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                } else if (linea.startsWith("Criterio Punti: ")) {
                    criterioPunti = linea.split(": ")[1];
                }
            }
            // Aggiungi l'ultima carta letta
            if (idCarta != -1) {
                CartaOro carta = new CartaOro(idCarta, angoliFronte, "", caselle, tipoRegno, risorseNecessarie, punti, criterioPunti);
                carte.add(carta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carte;
    }

    // Metodo per ottenere l'emoji del tipo di regno
    private String getEmojiRegno(String regno) {
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

    // Metodo per ottenere l'emoji dell'angolo
    private String getEmojiAngolo(String angolo) {
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
            default:
                return "";
        }
    }

    // Metodo per ottenere il simbolo dei punti
    private String getSimboloPunti(int punti) {
        switch (punti) {
            case 1:
                return "❶";
            case 2:
                return "❷";
            case 3:
                return "❸";
            case 4:
                return "❹";
            case 5:
                return "❺";
            default:
                return "";
        }
    }

    // Metodo per ottenere l'emoji del criterio punti
    private String getEmojiCriterio(String criterio) {
        switch (criterio) {
            case "Piuma":
                return "𓆰";
            case "Pergamena":
                return "📜";
            case "Inchiostro":
                return "🧪";
            case "Angoli":
                return "A";
            case "Nessuno":
                return "";
            default:
                return "";
        }
    }

    // Metodo per ottenere il carattere di bordo per un angolo
    private String getBordoAngolo(String angolo, boolean interno) {
        if (angolo.equals("Nascosto")) {
            return " ";
        }
        return interno ? "|" : "";
    }

 // Metodo per centrare una stringa in uno spazio di larghezza fissa
    private String centraStringa(String str, int width) {
        int padding = (width - str.length()) / 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        sb.append(str);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }

    // Metodo per stampare la rappresentazione grafica della carta oro
    @Override
    public void stampaCarta() {
        //System.out.println("CARTA ORO: [id " + getIdCarta() + "]");
        System.out.println("-------------------------");
        String[] angoli = getFronte().split(" - ");

        // Riga superiore con punti, criterio e angoli superiori
        String puntiECriterio = getSimboloPunti(punti) + " " + getEmojiCriterio(criterioPunti);
        String topLine = String.format("|%s%s%2s%2s%s%s|\n", 
            getBordoAngolo(angoli[0], false), getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], true),
            centraStringa(puntiECriterio, 16), 
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1]), getBordoAngolo(angoli[1], false)
        );

        // Riga centrale con il tipo di regno
        String midLine1 = String.format("|%22s|\n", "");
        String midLine2 = String.format("|          %s          |\n", getEmojiRegno(tipoRegno));
        String midLine3 = String.format("|%22s|\n", "");

        // Riga inferiore con angoli inferiori e risorse richieste
        String risorseRichiesteStr = String.join(" ", risorseNecessarie.stream().map(this::getEmojiAngolo).toArray(String[]::new));
        String bottomLine = String.format("|%s%s%2s%2s%s%s%s|\n", 
            getBordoAngolo(angoli[2], false), getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], true),
            centraStringa(risorseRichiesteStr, 16), 
            getBordoAngolo(angoli[3], true), getEmojiAngolo(angoli[3]), getBordoAngolo(angoli[3], false)
        );

        // Stampa le linee
        System.out.print(topLine);
        System.out.print(midLine1);
        System.out.print(midLine2);
        System.out.print(midLine3);
        System.out.print(bottomLine);
        System.out.println("-------------------------");
    }
}
