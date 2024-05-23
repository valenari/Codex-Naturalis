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
    public CartaRisorsa(int idCarta, String fronte, String retro, Caselleproibite caselle, String tipoRegno, int punti) {
        super(idCarta, "Risorsa", fronte, retro, caselle);
        this.tipoRegno = tipoRegno;
        this.punti = punti;
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
                        CartaRisorsa carta = new CartaRisorsa(idCarta, angoliFronte, "", caselle, tipoRegno, punti);
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
                CartaRisorsa carta = new CartaRisorsa(idCarta, angoliFronte, "", caselle, tipoRegno, punti);
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
                return "🪰";
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
                return "🪰";
            case "Piuma":
                return "🪶";
            case "Pergamena":
                return "📜";
            case "Inchiostro":
                return "🫙";
            case "Visibile":
                return " ";
            case "Nascosto":
                return "";
            default:
                return "";
        }
    }

    // Metodo per stampare la rappresentazione grafica della carta risorsa
    @Override
    public void stampaCarta() {
        System.out.println("CARTA RISORSA: [id " + getIdCarta() + "]");
        System.out.println("——————————————————————————————————");
        String[] angoli = getFronte().split(" - ");
        System.out.printf("|  %s  |%24s|  %s  |\n", getEmojiAngolo(angoli[0]), "", getEmojiAngolo(angoli[1]));
        System.out.printf("|———————————————|———————————————|\n");
        System.out.printf("|%40s|\n", "");
        System.out.printf("|%20s%s%20s|\n", "", getEmojiRegno(tipoRegno), "");
        System.out.printf("|%40s|\n", "");
        System.out.printf("|———————————————|———————————————|\n");
        System.out.printf("|  %s  |%24s|  %s  |\n", getEmojiAngolo(angoli[2]), "", getEmojiAngolo(angoli[3]));
        System.out.println("——————————————————————————————————");
    }
}
