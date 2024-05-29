package cardsModel;

import Modello_giocatore.Caselleproibite;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaRisorsa extends Carta {
    private String tipoRegno;
    private int punti;

    // Costruttore della classe CartaRisorsa
    public CartaRisorsa(int idCarta, String fronte, Caselleproibite caselle, String tipoRegno, int punti) {
        super(idCarta, "Risorsa", fronte, generaRetro(tipoRegno), caselle);
        this.tipoRegno = tipoRegno;
        this.punti = punti;
    }

    // Metodo statico per generare il retro della carta
    private static String generaRetro(String tipoRegno) {
        return "Visibile - Visibile - Visibile - Visibile - " + tipoRegno;
    }

    // Getter per tipoRegno
    public String getTipoRegno() {
        return tipoRegno;
    }

    // Setter per tipoRegno
    public void setTipoRegno(String tipoRegno) {
        this.tipoRegno = tipoRegno;
    }

    // Getter per punti
    public int getPunti() {
        return punti;
    }

    // Setter per punti
    public void setPunti(int punti) {
        this.punti = punti;
    }

    // Metodo statico per leggere carte risorsa dal file
    public static List<CartaRisorsa> leggiCarteRisorsa(String filename) {
        List<CartaRisorsa> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String tipoRegno = null;
            String angoliFronte = null;
            int punti = 0;
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("N° Risorsa: ")) {
                    if (idCarta != -1) {
                        // Aggiungi la carta precedente alla lista
                        CartaRisorsa carta = new CartaRisorsa(idCarta, angoliFronte, caselle, tipoRegno, punti);
                        carte.add(carta);
                    }
                    // Inizia a leggere una nuova carta
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                } else if (linea.startsWith("Regno: ")) {
                    tipoRegno = linea.split(": ")[1];
                } else if (linea.startsWith("Angoli Fronte: ")) {
                    angoliFronte = linea.split(": ")[1];
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                }
            }
            // Aggiungi l'ultima carta letta
            if (idCarta != -1) {
                CartaRisorsa carta = new CartaRisorsa(idCarta, angoliFronte, caselle, tipoRegno, punti);
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

    // Metodo per ottenere l'emoji dei punti
    private String getEmojiPunti(int punti) {
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

    // Metodo per ottenere il carattere di bordo per un angolo
    private String getBordoAngolo(String angolo, boolean sinistra) {
        if (angolo.equals("Nascosto")) {
            return "  ";
        }
        return sinistra ? "[" : "]";
    }

    // Metodo per centrare una stringa
    private String centraStringa(String str, int larghezza) {
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

    public void stampaCarta() {
        System.out.println(this.toString());
    }

    public void stampaRetro() {
        System.out.println(this.toStringRetro());
    }
}
