package obiettiviModels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaObiettivo {
	private int id;
	private String tipo;
	private int punti;

	//Costruttore della classe CartaObiettivo
	public CartaObiettivo(int id, String tipo, int punti) {
		this.setId(id);
		this.setTipo(tipo);
		this.setPunti(punti);
	}
	
	//Diversi Getter e Setter
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getPunti() {
	    return punti;
	}
	public void setPunti(int punti) {
	    this.punti = punti;
	}

	
}
/*
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
    
*/
