package obiettiviModels;

import Modello_giocatore.Caselleproibite;
import cardsModel.Carta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaObiettivoRisorsa {
    private int punti;
    private int quantita;
    private String tipoRisorsa;

}
    /*
    // Costruttore della classe CartaObiettivoRisorsa
    public CartaObiettivoRisorsa( int punti) {
        this.quantita = quantita;
        this.tipoRisorsa = tipoRisorsa;
        this.punti = punti;
    }

    // Metodo statico per generare il retro della carta
    private static String generaRetro(String tipoRegno) {
        return "Obiettivo - " + tipoRegno;
    }

    // Getter e Setter

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getTipoRisorsa() {
        return tipoRisorsa;
    }

    public void setTipoRisorsa(String tipoRisorsa) {
        this.tipoRisorsa = tipoRisorsa;
    }

    // Metodo statico per leggere obiettivi dal file
    public static List<CartaObiettivoRisorsa> leggiObiettiviRisorsa(String filename) {
        List<CartaObiettivoRisorsa> obiettivi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String tipoRegno = null;
            int quantita = 0;
            String tipoRisorsa = null;
            int punti = 0;
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Obbiettivo ")) {
                    if (idCarta != -1) {
                        // Aggiungi l'obiettivo precedente alla lista
                        CartaObiettivoRisorsa obiettivo = new CartaObiettivoRisorsa(idCarta, "", caselle, tipoRegno, quantita, tipoRisorsa, punti);
                        obiettivi.add(obiettivo);
                    }
                    // Inizia a leggere un nuovo obiettivo
                    idCarta = Integer.parseInt(linea.split(" ")[1]);
                } else if (linea.startsWith("Tipo/Regno: ")) {
                    tipoRegno = linea.split(": ")[1];
                } else if (linea.startsWith(" - Quantità: ")) {
                    quantita = Integer.parseInt(linea.split(": ")[1].trim());
                } else if (linea.startsWith(" - Tipo: ")) {
                    tipoRisorsa = linea.split(": ")[1].trim();
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                }
            }
            // Aggiungi l'ultimo obiettivo letto
            if (idCarta != -1) {
                CartaObiettivoRisorsa obiettivo = new CartaObiettivoRisorsa(idCarta, "", caselle, tipoRegno, quantita, tipoRisorsa, punti);
                obiettivi.add(obiettivo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obiettivi;
    }
*/