package modelTavolo;

import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
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

    // Metodo per mostrare lo stato attuale dell'area di pesca
    public void mostraAreaDiPesca() {
        System.out.println("Carte Risorsa Visibili:");
        for (CartaRisorsa carta : carteRisorsaVisibili) {
            carta.stampaCarta();
        }
        if (!mazzoRisorsaCoperto.isEmpty()) {
            System.out.println("Prossima Carta Risorsa del Mazzo:");
            mazzoRisorsaCoperto.get(0).stampaRetro();
        }

        System.out.println("\nCarte Oro Visibili:");
        for (CartaOro carta : carteOroVisibili) {
            carta.stampaCarta();
        }
        if (!mazzoOroCoperto.isEmpty()) {
            System.out.println("Prossima Carta Oro del Mazzo:");
            mazzoOroCoperto.get(0).stampaRetro();
        }
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
