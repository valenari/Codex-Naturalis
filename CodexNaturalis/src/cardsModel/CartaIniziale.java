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
    public CartaIniziale(int idCarta, String fronte, Caselleproibite caselle, List<String> centrale, String retro) {
        super(idCarta, "Iniziale", fronte, retro, caselle);
        this.centrale = centrale;
    }

    // Getter per la lista centrale
    public List<String> getCentrale() {
        return centrale;
    }

    // Setter per la lista centrale
    public void setCentrale(List<String> centrale) {
        this.centrale = centrale;
    }

    // Metodo statico per leggere le carte iniziali dal file
    public static List<CartaIniziale> leggiCarteIniziali(String filename) {
        List<CartaIniziale> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String fronte = null;
            List<String> centrale = new ArrayList<>();
            String retro = null;
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id: ")) {
                    if (idCarta != -1) {
                        // Aggiungi la carta precedente alla lista
                        CartaIniziale carta = new CartaIniziale(idCarta, fronte, caselle, centrale, retro);
                        carte.add(carta);
                    }
                    // Inizia a leggere una nuova carta
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                    centrale = new ArrayList<>();
                } else if (linea.startsWith("Fronte: ")) {
                    fronte = linea.split(": ")[1];
                } else if (linea.startsWith("Centrale: ")) {
                    String[] centrali = linea.split(": ")[1].split(" - ");
                    for (String c : centrali) {
                        centrale.add(c);
                    }
                } else if (linea.startsWith("Retro: ")) {
                    retro = linea.split(": ")[1];
                }
            }
            // Aggiungi l'ultima carta letta
            if (idCarta != -1) {
                CartaIniziale carta = new CartaIniziale(idCarta, fronte, caselle, centrale, retro);
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
        List<String> centrale = getCentrale();

        sb.append("-------------------------\n");

        sb.append(String.format("[%s%s%17s%s%s]\n", 
            getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
            "", 
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        if (centrale.size() == 1) {
            sb.append(String.format("[%23s]\n", ""));
            sb.append(String.format("[%s]\n", centraStringa(getEmojiRegno(centrale.get(0)), 23)));
            sb.append(String.format("[%23s]\n", ""));
        } else if (centrale.size() == 2) {
            sb.append(String.format("[%23s]\n", centraStringa(getEmojiRegno(centrale.get(0)), 23)));
            sb.append(String.format("[%23s]\n", ""));
            sb.append(String.format("[%23s]\n", centraStringa(getEmojiRegno(centrale.get(1)), 23)));
        } else if (centrale.size() == 3) {
            sb.append(String.format("[%23s]\n", centraStringa(getEmojiRegno(centrale.get(0)), 23)));
            sb.append(String.format("[%23s]\n", centraStringa(getEmojiRegno(centrale.get(1)), 23)));
            sb.append(String.format("[%23s]\n", centraStringa(getEmojiRegno(centrale.get(2)), 23)));
        } else {
            sb.append(String.format("[%23s]\n", ""));
            sb.append(String.format("[%23s]\n", ""));
            sb.append(String.format("[%23s]\n", ""));
        }

        sb.append(String.format("[%s%s%17s%s%s]\n", 
            getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], false),
            "", 
            getBordoAngolo(angoli[3], true), getEmojiAngolo(angoli[3])
        ));

        sb.append("-------------------------\n");
        return sb.toString();
    }

    public String toStringRetro() {
        StringBuilder sb = new StringBuilder();
        String[] angoli = getRetro().split(" - ");

        sb.append("-------------------------\n");

        sb.append(String.format("[%s%s%17s%s%s]\n", 
            getEmojiAngolo(angoli[0]), getBordoAngolo(angoli[0], false),
            "", 
            getBordoAngolo(angoli[1], true), getEmojiAngolo(angoli[1])
        ));

        sb.append(String.format("[%23s]\n", ""));
        sb.append(String.format("[%23s]\n", ""));
        sb.append(String.format("[%23s]\n", ""));

        sb.append(String.format("[%s%s%17s%s%s]\n", 
            getEmojiAngolo(angoli[2]), getBordoAngolo(angoli[2], false),
            "", 
            getBordoAngolo(angoli[3], true), getEmojiAngolo(angoli[3])
        ));

        sb.append("-------------------------\n");
        return sb.toString();
    }

    public void stampaCarta() {
        System.out.println("CARTA INIZIALE: [id " + getIdCarta() + "]");
        System.out.println("FRONTE:");
        System.out.println(this.toString());
    }

    public void stampaRetro() {
        System.out.println("RETRO:");
        System.out.println(this.toStringRetro());
    }
}
