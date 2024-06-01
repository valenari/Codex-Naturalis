package obiettiviModels;

import Modello_giocatore.Caselleproibite;
import cardsModel.Carta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartaObiettivoRisorsa {
	private int IdCarta;
	private int punti;
    private int quantita;
    private String tipo;

    // Costruttore della classe CartaObiettivoRisorsa
    public CartaObiettivoRisorsa( int idCarta, int punti, int quantita, String tipo) {
    	this.IdCarta = idCarta;
        this.quantita = quantita;
        this.tipo = tipo;
        this.punti = punti;
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
        return tipo;
    }
    public void setTipoRisorsa(String tipo) {
        this.tipo = tipo;
    }
    public int getIdCarta() {
		return IdCarta;
	}
	public void setIdCarta(int idCarta) {
		IdCarta = idCarta;
	}

	// Metodo statico per leggere obiettivi dal file
    public static List<CartaObiettivoRisorsa> leggiObiettiviRisorsa(String filename) {
        List<CartaObiettivoRisorsa> obiettivirisorsaList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            int quantita = 0;
            String tipo = null;
            int punti = 0;
            Caselleproibite caselle = Caselleproibite.NULL; // Placeholder, modificare secondo necessità

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Obbiettivo ")) {
                    if (idCarta != -1) {
                        // Aggiungi l'obiettivo precedente alla lista
                        CartaObiettivoRisorsa obiettivo = new CartaObiettivoRisorsa(idCarta, punti, quantita, tipo);
                        obiettivirisorsaList.add(obiettivo);
                    }
                    // Inizia a leggere un nuovo obiettivo
                    idCarta = Integer.parseInt(linea.split(" ")[1]);
            
                } else if (linea.startsWith(" - Quantità: ")) {
                    quantita = Integer.parseInt(linea.split(": ")[1].trim());
                } else if (linea.startsWith(" - Tipo: ")) {
                	tipo = linea.split(": ")[1].trim();
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                }
            }
            // Aggiungi l'ultimo obiettivo letto
            if (idCarta != -1) {
                CartaObiettivoRisorsa obiettivoRisorsa = new CartaObiettivoRisorsa(idCarta, punti, quantita, tipo);
                obiettivirisorsaList.add(obiettivoRisorsa);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obiettivirisorsaList;
        }
    
    // Metodo per ottenere l'emoji 
    private String getEmoji(String emoji) {
        switch (emoji) {
        case "Vegetale":
            return "☘️";
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
 // Metodo per ottenere l'emoji dei Quantità
    private String getEmojiQuantità(int quantita) {
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
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String puntiStr = getEmojiPunti(punti);
        String QuntiStr = getEmojiPunti(quantita);
        String Tipo = tipo;
        
        sb.append("----------------------------\n");

        sb.append(String.format("[%s]\n", puntiStr));

        sb.append(String.format("[%26s]\n", ""));
        sb.append(String.format("[%s]\n", Tipo));
        sb.append(String.format("[%26s]\n", ""));
        sb.append(String.format("[%s]\n", QuntiStr));

        sb.append(String.format("[%26s]\n", ""));

        sb.append("----------------------------\n");
        return sb.toString();
    }
    public void stampaCarta() {
        System.out.println(this.toString());
    }
    
}


