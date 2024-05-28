package modelTavolo;

import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.Carta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AreaDiPesca {
    private List<CartaRisorsa> carteRisorsaVisibili;
    private List<CartaOro> carteOroVisibili;
    private List<CartaRisorsa> mazzoRisorsaCoperto;
    private List<CartaOro> mazzoOroCoperto;

    // Costruttore della classe AreaDiPesca
    public AreaDiPesca(List<CartaRisorsa> mazzoRisorsa, List<CartaOro> mazzoOro) {
        this.mazzoRisorsaCoperto = new ArrayList<>(mazzoRisorsa);
        this.mazzoOroCoperto = new ArrayList<>(mazzoOro);
        this.carteRisorsaVisibili = new ArrayList<>();
        this.carteOroVisibili = new ArrayList<>();

        // Mescola i mazzi
        Collections.shuffle(mazzoRisorsaCoperto);
        Collections.shuffle(mazzoOroCoperto);

        // Pesca le prime due carte per ogni tipo e mettile tra le visibili
        pescaCarteRisorsa(2);
        pescaCarteOro(2);
    }

    // Metodo per pescare un certo numero di carte risorsa
    private void pescaCarteRisorsa(int numero) {
        for (int i = 0; i < numero && !mazzoRisorsaCoperto.isEmpty(); i++) {
            carteRisorsaVisibili.add(mazzoRisorsaCoperto.remove(0));
        }
    }

    // Metodo per pescare un certo numero di carte oro
    private void pescaCarteOro(int numero) {
        for (int i = 0; i < numero && !mazzoOroCoperto.isEmpty(); i++) {
            carteOroVisibili.add(mazzoOroCoperto.remove(0));
        }
    }

    // Metodo per stampare le righe delle carte con numeri
    private void stampaCarteOrizzontalmente(List<? extends Carta> carte, boolean mostraRetroMazzo, int offsetNumerazione) {
        int numCarteDaMostrare = Math.min(carte.size(), 2); // Mostra solo due carte
        String[][] carteStringhe = new String[numCarteDaMostrare + (mostraRetroMazzo ? 1 : 0)][];
        int maxLines = 0;

        // Aggiungi la prossima carta del mazzo (retro) se necessario
        if (mostraRetroMazzo && !carte.isEmpty()) {
            carteStringhe[0] = carte.get(0).toStringRetro().split("\n");
            maxLines = Math.max(maxLines, carteStringhe[0].length);
        }

        // Converti ogni carta in un array di righe
        for (int i = 0; i < numCarteDaMostrare; i++) {
            carteStringhe[i + (mostraRetroMazzo ? 1 : 0)] = carte.get(i).toString().split("\n");
            maxLines = Math.max(maxLines, carteStringhe[i + (mostraRetroMazzo ? 1 : 0)].length);
        }

        // Stampa le righe delle carte con uno spazio tra di loro
        for (int line = 0; line < maxLines; line++) {
            for (int i = 0; i < carteStringhe.length; i++) {
                String cardLine = (line < carteStringhe[i].length) ? carteStringhe[i][line] : " ".repeat(carteStringhe[0][0].length());
                if (line == carteStringhe[i].length - 1) { // Riga con i trattini per i numeri
                    int cardNumber = offsetNumerazione + i + 1;
                    int middle = (cardLine.length() - 1) / 2;
                    cardLine = cardLine.substring(0, middle - 1) + "{" + cardNumber + "}" + cardLine.substring(middle + 2);
                }
                System.out.print(cardLine);
                System.out.print("     "); // Spazio tra le carte
            }
            System.out.println();
        }
    }

    // Metodo per mostrare lo stato attuale dell'area di pesca
    public void mostraAreaDiPesca() {
        System.out.println("Carte Risorsa Visibili:");
        List<Carta> risorsaConRetro = new ArrayList<>();
        if (!mazzoRisorsaCoperto.isEmpty()) {
            risorsaConRetro.add(mazzoRisorsaCoperto.get(0));
        }
        risorsaConRetro.addAll(carteRisorsaVisibili.subList(0, Math.min(carteRisorsaVisibili.size(), 2)));
        stampaCarteOrizzontalmente(risorsaConRetro, true, 0);

        System.out.println("\nCarte Oro Visibili:");
        List<Carta> oroConRetro = new ArrayList<>();
        if (!mazzoOroCoperto.isEmpty()) {
            oroConRetro.add(mazzoOroCoperto.get(0));
        }
        oroConRetro.addAll(carteOroVisibili.subList(0, Math.min(carteOroVisibili.size(), 2)));
        stampaCarteOrizzontalmente(oroConRetro, true, 3);
    }

    // Metodo per pescare una carta risorsa
    public CartaRisorsa pescaCartaRisorsa(int indice) {
        if (indice >= 0 && indice < carteRisorsaVisibili.size()) {
            CartaRisorsa cartaPescata = carteRisorsaVisibili.remove(indice);
            if (!mazzoRisorsaCoperto.isEmpty()) {
                carteRisorsaVisibili.add(mazzoRisorsaCoperto.remove(0));
            }
            return cartaPescata;
        }
        return null;
    }

    // Metodo per pescare una carta oro
    public CartaOro pescaCartaOro(int indice) {
        if (indice >= 0 && indice < carteOroVisibili.size()) {
            CartaOro cartaPescata = carteOroVisibili.remove(indice);
            if (!mazzoOroCoperto.isEmpty()) {
                carteOroVisibili.add(mazzoOroCoperto.remove(0));
            }
            return cartaPescata;
        }
        return null;
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
