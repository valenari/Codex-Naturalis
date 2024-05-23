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
                return " 𓆰";
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

 // Metodo per ottenere il carattere di bordo per un angolo
    private String getBordoAngolo(String angolo, boolean interno) {
        if (angolo.equals("Nascosto")) {
            return " ";
        }
        return interno ? "|" : "";
    }

    // Metodo per stampare la rappresentazione grafica della carta risorsa
    @Override
    public void stampaCarta() {
        //System.out.println("CARTA RISORSA: [id " + getIdCarta() + "]");
        System.out.println("-------------------------");
        String[] angoli = getFronte().split(" - ");

        // Costruisci le linee con le condizioni sugli angoli nascosti
        String topLine = String.format("|%s%s%s%17s%s%s%s|\n", 
            getBordoAngolo(angoli[0], false), getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], true),
            "", 
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1]), getBordoAngolo(angoli[1], false)
        );

        String midLine1 = String.format("|%23s|\n", "");
        String midLine2 = String.format("|          %s           |\n", getEmojiRegno(tipoRegno));
        String midLine3 = String.format("|%23s|\n", "");

        String bottomLine = String.format("|%s%s%s%17s%s%s%s|\n", 
            getBordoAngolo(angoli[2], false), getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], true),
            "", 
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
