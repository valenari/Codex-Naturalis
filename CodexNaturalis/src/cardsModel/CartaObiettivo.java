package cardsModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Modello_giocatore.Caselleproibite;

public class CartaObiettivo extends Carta {
    private String tipoRegno;
    private int punti;

    // Costruttore della classe CartaObiettivo
    public CartaObiettivo(int idCarta, String tipoCarta, String fronte, String retro, Caselleproibite caselle, int punti, String tipoRegno) {
        super(idCarta, tipoCarta, fronte, retro, caselle);
        this.punti = punti;
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

    // Getter per tipoRegno
    public String getTipoRegno() {
        return tipoRegno;
    }

    // Setter per tipoRegno
    public void setTipoRegno(String tipoRegno) {
        this.tipoRegno = tipoRegno;
    }

    // Metodo statico per leggere carte risorsa dal file
    public static List<CartaObiettivo> leggiObiettivi(String filename) {
        List<CartaObiettivo> obbiettivi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String tipoCarta = "Obbiettivo"; 
            String fronte = " Null ";  
            String retro = " Null ";  
            String tipoRegno = null;
            String sequenza = null;
            List<int[]> posizioni = new ArrayList<>();
            int punti = 0;
            Caselleproibite caselle = Caselleproibite.NULL; 

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Obbiettivo ")) {
                    if (idCarta != -1) {
                        CartaObiettivo obbiettivo = new CartaObiettivo(idCarta, tipoCarta, fronte, retro, caselle, punti, tipoRegno);
                        obbiettivi.add(obbiettivo);
                    }
                    idCarta = Integer.parseInt(linea.split(" ")[1]);
                    posizioni = new ArrayList<>();
                } else if (linea.startsWith("Tipo/Regno: ")) {
                    tipoRegno = linea.split(": ")[1];
                } else if (linea.startsWith(" - Sequenza: ")) {
                    sequenza = linea.split(": ")[1];
                } else if (linea.startsWith(" - Posizioni: ")) {
                    String[] posStr = linea.split(": ")[1].split(", ");
                    for (String pos : posStr) {
                        pos = pos.replace("[", "").replace("]", "");
                        String[] coords = pos.split(",");
                        int[] posizione = { Integer.parseInt(coords[0]), Integer.parseInt(coords[1]) };
                        posizioni.add(posizione);
                    }
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                }
            }
            // Aggiungi l'ultimo obbiettivo letto
            if (idCarta != 0) {
                CartaObiettivo obbiettivo = new CartaObiettivo(idCarta, tipoCarta, fronte, retro, caselle, punti, tipoRegno);
                obbiettivi.add(obbiettivo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obbiettivi;
    }

    // Metodo per ottenere l'emoji 
    private String getEmoji(String emoji) {
        switch (emoji) {
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
    
    @Override
    public String toString() {
        return "CartaObiettivo{" +
                "idCarta=" + getIdCarta() +
                ", tipoCarta='" + getTipoCarta() + '\'' +
                ", fronte='" + getFronte() + '\'' +
                ", retro='" + getRetro() + '\'' +
                ", caselle=" + getCaselle() +
                ", tipoRegno='" + tipoRegno + '\'' +
                ", punti=" + punti +
                '}';
    }

	@Override
	public String toStringRetro() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void stampaCarta() {
        System.out.println(this.toString());
    }
    
    
}
