package cardsModel;

import Modello_giocatore.Caselleproibite;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaIniziale extends Carta {
    private List<String> centrale;

    // Costruttore della classe CartaIniziale
    public CartaIniziale(int idCarta, String fronte, List<String> centrale, Caselleproibite caselle, String retro) {
        super(idCarta, "Iniziale", fronte, retro, caselle);
        this.centrale = centrale;
    }

    // Getter per centrale
    public List<String> getCentrale() {
        return centrale;
    }

    // Setter per centrale
    public void setCentrale(List<String> centrale) {
        this.centrale = centrale;
    }

    // Metodo statico per leggere carte iniziali dal file
    public static List<CartaIniziale> leggiCarteIniziali(String filename) {
        List<CartaIniziale> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String fronte = null;
            List<String> centrale = null;
            String retro = null;
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id: ")) {
                    if (idCarta != -1) {
                        // Aggiungi la carta precedente alla lista
                        CartaIniziale carta = new CartaIniziale(idCarta, fronte, centrale, caselle, retro);
                        carte.add(carta);
                    }
                    // Inizia a leggere una nuova carta
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                } else if (linea.startsWith("Fronte: ")) {
                    fronte = linea.split(": ")[1];
                } else if (linea.startsWith("Centrale: ")) {
                    centrale = List.of(linea.split(": ")[1].split(" - "));
                } else if (linea.startsWith("Retro: ")) {
                    retro = linea.split(": ")[1];
                }
            }
            // Aggiungi l'ultima carta letta
            if (idCarta != -1) {
                CartaIniziale carta = new CartaIniziale(idCarta, fronte, centrale, caselle, retro);
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
        List<String> centraleList = getCentrale();

        sb.append("----------------------------\n");

        sb.append(String.format("[%s%s%20s%s%s]\n", 
            getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
            "", 
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        sb.append(String.format("[%s]\n", centraStringa(centraleList.size() > 0 ? getEmojiRegno(centraleList.get(0)) : "", 26)));
        sb.append(String.format("[%s]\n", centraStringa(centraleList.size() > 1 ? getEmojiRegno(centraleList.get(1)) : "", 26)));
        sb.append(String.format("[%s]\n", centraStringa(centraleList.size() > 2 ? getEmojiRegno(centraleList.get(2)) : "", 26)));
        
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
        sb.append(String.format("[%26s]\n", ""));
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
