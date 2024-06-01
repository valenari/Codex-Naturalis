package obiettiviModels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CartaObiettivoDisp {
	private int IdCarta;
	private int punti;
	private String sequenza;
	//Costruttore della classe CartaObiettivo
	public CartaObiettivoDisp(int idCarta,int punti,String sequenza) {
		this.IdCarta = idCarta;
		this.punti = punti;
		this.sequenza = sequenza;
	}

	//Diversi Getter e Setter
	public int getPunti() {
	    return punti;
	}
	public void setPunti(int punti) {
	    this.punti = punti;
	}
    public int getIdCarta() {
		return IdCarta;
	}

	public void setIdCarta(int idCarta) {
		IdCarta = idCarta;
	}

	// Metodo statico per leggere carte risorsa dal file
    public static List<CartaObiettivoDisp> leggiObiettivi(String filename) {
        List<CartaObiettivoDisp> obbiettiviList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	String linea;
        	int idCarta = -1;  
            String sequenza = null;
            List<int[]> posizioni = new ArrayList<>();
            int punti = 0;
           // Caselleproibite caselle = Caselleproibite.NULL; 

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Obbiettivo ")) {
                    if (idCarta != -1) {
                    	CartaObiettivoDisp obbiettivo = new CartaObiettivoDisp(idCarta, punti, sequenza);
                    	obbiettiviList.add(obbiettivo);
                    }
                    idCarta = Integer.parseInt(linea.split(" ")[1]);
                    posizioni = new ArrayList<>();
                } 
                 else if (linea.startsWith(" - Sequenza: ")) {
                    sequenza = linea.split(": ")[1];
                } else if (linea.startsWith(" - Posizioni: ")) {
                	// Save the position for the cart[x,y]
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
            if (idCarta != -1) {
            	CartaObiettivoDisp obbiettivo = new CartaObiettivoDisp(idCarta, punti, sequenza);
            	obbiettiviList.add(obbiettivo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obbiettiviList;
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
    public String getSequenza() {
        return sequenza;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String puntiStr = getEmojiPunti(punti);
        String[] Sequenza = getSequenza().split(" - ");
        
        sb.append("----------------------------\n");

        sb.append(String.format("[%s]\n", puntiStr));

        sb.append(String.format("[%26s]\n", ""));
        
        for (String sequenzaPart : Sequenza) {
            sb.append(String.format("[%s]\n", sequenzaPart));
        }
        
        sb.append(String.format("[%26s]\n", ""));

        sb.append("----------------------------\n");
        return sb.toString();
    }
    
    public void stampaCarta() {
        System.out.println(this.toString());
    }
}

