package modelTavolo;

import cardsModel.Carta;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import java.util.ArrayList;
import java.util.List;

public class AreaDiPesca {
    private MazzoCarte mazzoRisorsa;
    private MazzoCarte mazzoOro;
    private List<CartaRisorsa> carteRisorsaVisibili;
    private List<CartaOro> carteOroVisibili;

    // Costruttore della classe AreaDiPesca
    public AreaDiPesca(MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.mazzoRisorsa = mazzoRisorsa;
        this.mazzoOro = mazzoOro;
        this.carteRisorsaVisibili = new ArrayList<>();
        this.carteOroVisibili = new ArrayList<>();

        // Pesca le prime due carte per ogni tipo e mettile tra le visibili
        pescaCarteRisorsa(2);
        pescaCarteOro(2);
    }

    // Metodo per pescare un certo numero di carte risorsa
    private void pescaCarteRisorsa(int numero) {
        for (int i = 0; i < numero; i++) {
            CartaRisorsa carta = (CartaRisorsa) mazzoRisorsa.pescaCarta();
            if (carta != null) {
                carteRisorsaVisibili.add(carta);
            }
        }
    }

    // Metodo per pescare un certo numero di carte oro
    private void pescaCarteOro(int numero) {
        for (int i = 0; i < numero; i++) {
            CartaOro carta = (CartaOro) mazzoOro.pescaCarta();
            if (carta != null) {
                carteOroVisibili.add(carta);
            }
        }
    }

    // Metodo per aggiungere un numero alla rappresentazione della carta
    private String toStringConNumero(Carta carta, int numero, boolean isRetro) {
        String cartaStr = isRetro ? carta.toStringRetro() : carta.toString();
        String[] lineeCarta = cartaStr.split("\n");
        String numeroStr = String.format("{%d}", numero);
        String lineaConNumero = lineeCarta[lineeCarta.length - 1];
        int pos = (lineaConNumero.length() - numeroStr.length()) / 2;

        // Aggiungi il numero al centro della riga inferiore
        lineeCarta[lineeCarta.length - 1] = lineaConNumero.substring(0, pos) + numeroStr + lineaConNumero.substring(pos + numeroStr.length());
        return String.join("\n", lineeCarta);
    }

    // Metodo per stampare le carte orizzontalmente
    private void stampaCarteOrizzontalmente(List<String> carteStringhe) {
        int maxLines = 0;
        List<String[]> carteLinee = new ArrayList<>();

        // Converti ogni rappresentazione della carta in un array di righe
        for (String cartaStr : carteStringhe) {
            String[] linee = cartaStr.split("\n");
            carteLinee.add(linee);
            maxLines = Math.max(maxLines, linee.length);
        }

        // Stampa le righe delle carte con uno spazio tra di loro
        for (int line = 0; line < maxLines; line++) {
            for (String[] linee : carteLinee) {
                if (line < linee.length) {
                    System.out.print(linee[line]);
                } else {
                    System.out.print(" ".repeat(linee[0].length())); // Spazio vuoto se la carta ha meno righe
                }
                System.out.print("\t"); // Spazio tra le carte
            }
            System.out.println();
        }
    }

    // Metodo per mostrare lo stato attuale dell'area di pesca
    public void mostraAreaDiPesca() {
        List<String> rappresentazioneCarteRisorsa = new ArrayList<>();
        List<String> rappresentazioneCarteOro = new ArrayList<>();

        // Aggiungi il retro della prossima carta risorsa
        if (mazzoRisorsa.getCarte().size() > mazzoRisorsa.getPuntatore()) {
            rappresentazioneCarteRisorsa.add(toStringConNumero(mazzoRisorsa.getCarte().get(mazzoRisorsa.getPuntatore()), 1, true));
        }

        // Aggiungi le carte risorsa visibili
        for (int i = 0; i < carteRisorsaVisibili.size(); i++) {
            rappresentazioneCarteRisorsa.add(toStringConNumero(carteRisorsaVisibili.get(i), i + 2, false));
        }

        // Aggiungi il retro della prossima carta oro
        if (mazzoOro.getCarte().size() > mazzoOro.getPuntatore()) {
            rappresentazioneCarteOro.add(toStringConNumero(mazzoOro.getCarte().get(mazzoOro.getPuntatore()), 4, true));
        }

        // Aggiungi le carte oro visibili
        for (int i = 0; i < carteOroVisibili.size(); i++) {
            rappresentazioneCarteOro.add(toStringConNumero(carteOroVisibili.get(i), i + 5, false));
        }

        // Stampa le carte risorsa e oro su due righe separate
        System.out.println("Carte Risorsa:");
        stampaCarteOrizzontalmente(rappresentazioneCarteRisorsa);

        System.out.println("\nCarte Oro:");
        stampaCarteOrizzontalmente(rappresentazioneCarteOro);
    }

    // Getter per le carte risorsa visibili
    public List<CartaRisorsa> getCarteRisorsaVisibili() {
        return carteRisorsaVisibili;
    }

    // Getter per le carte oro visibili
    public List<CartaOro> getCarteOroVisibili() {
        return carteOroVisibili;
    }
}
