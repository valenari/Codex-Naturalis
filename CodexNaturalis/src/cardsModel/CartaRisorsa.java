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

    // Getter e setter per tipoRegno
    public String getTipoRegno() {
        return tipoRegno;
    }

    public void setTipoRegno(String tipoRegno) {
        this.tipoRegno = tipoRegno;
    }

    // Getter e setter per punti
    public int getPunti() {
        return punti;
    }

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
}
