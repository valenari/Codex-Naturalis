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
    public CartaIniziale(int idCarta, String fronte, String retro, Caselleproibite caselle, List<String> centrale) {
        super(idCarta, "Iniziale", fronte, retro, caselle);
        this.centrale = centrale;
    }

    // Getter e Setter per centrale
    public List<String> getCentrale() {
        return centrale;
    }

    public void setCentrale(List<String> centrale) {
        this.centrale = centrale;
    }

    // Metodo statico per leggere carte iniziali dal file
    public static List<CartaIniziale> leggiCarteIniziali(String filename) {
        List<CartaIniziale> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String angoliFronte = null;
            String angoliRetro = null;
            List<String> centrale = new ArrayList<>();
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id: ")) {
                    if (idCarta != -1) {
                        // Aggiungi la carta precedente alla lista
                        CartaIniziale carta = new CartaIniziale(idCarta, angoliFronte, angoliRetro, caselle, centrale);
                        carte.add(carta);
                    }
                    // Inizia a leggere una nuova carta
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                    centrale = new ArrayList<>();
                } else if (linea.startsWith("Fronte: ")) {
                    angoliFronte = linea.split(": ")[1];
                } else if (linea.startsWith("Centrale: ")) {
                    String[] centrali = linea.split(": ")[1].split(" - ");
                    for (String c : centrali) {
                        centrale.add(c);
                    }
                } else if (linea.startsWith("Retro: ")) {
                    angoliRetro = linea.split(": ")[1];
                }
            }
            // Aggiungi l'ultima carta letta
            if (idCarta != -1) {
                CartaIniziale carta = new CartaIniziale(idCarta, angoliFronte, angoliRetro, caselle, centrale);
                carte.add(carta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carte;
    }

    // Metodo per ottenere l'emoji dell'angolo e centrale
    private String getEmoji(String tipo) {
        switch (tipo) {
            case "Vegetale":
                return "☘️";
            case "Fungo":
                return "🍄";
            case "Animale":
                return "🐺";
            case "Insetto":
                return "🦋";
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

    // Metodo per stampare la rappresentazione grafica della carta iniziale
    @Override
    public void stampaCarta() {
        System.out.println("CARTA INIZIALE: [id " + getIdCarta() + "]");
        System.out.println("FRONTE:");
        stampaLato(getFronte(), centrale);
        System.out.println("RETRO:");
        stampaLato(getRetro(), null);
    }

    private void stampaLato(String lato, List<String> centrale) {
        String[] angoli = lato.split(" - ");

        System.out.println("-------------------------");
        // Riga superiore con angoli superiori
        String topLine = String.format("|%s%s%s%16s%s%s%s|\n", 
            getBordoAngolo(angoli[0], false), getEmoji(angoli[0]), getBordoAngolo(angoli[0], true),
            "", 
            getBordoAngolo(angoli[1], true), getEmoji(angoli[1]), getBordoAngolo(angoli[1], false)
        );

        // Riga centrale con il tipo centrale
        String midLine1 = String.format("|%22s|\n", centrale != null && centrale.size() > 0 ? centraStringa(getEmoji(centrale.get(0)), 22) : "");
        String midLine2 = String.format("|%22s|\n", centrale != null && centrale.size() > 1 ? centraStringa(getEmoji(centrale.get(1)), 22) : "");
        String midLine3 = String.format("|%22s|\n", centrale != null && centrale.size() > 2 ? centraStringa(getEmoji(centrale.get(2)), 22) : "");

        // Riga inferiore con angoli inferiori
        String bottomLine = String.format("|%s%s%s%16s%s%s%s|\n", 
            getBordoAngolo(angoli[2], false), getEmoji(angoli[2]), getBordoAngolo(angoli[2], true),
            "", 
            getBordoAngolo(angoli[3], true), getEmoji(angoli[3]), getBordoAngolo(angoli[3], false)
        );

        bottomLine = bottomLine.replaceAll("\\|\\|", "|  |");
        // Stampa le linee
        System.out.print(topLine);
        System.out.print(midLine1);
        System.out.print(midLine2);
        System.out.print(midLine3);
        System.out.print(bottomLine);
        System.out.println("-------------------------");
    }
}
