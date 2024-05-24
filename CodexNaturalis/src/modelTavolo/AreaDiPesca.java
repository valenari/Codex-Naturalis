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

    // Metodo per stampare le righe delle carte
    private void stampaCarteOrizzontalmente(List<? extends Carta> carte, boolean mostraRetroMazzo) {
        String[][] carteStringhe = new String[carte.size() + (mostraRetroMazzo ? 1 : 0)][];
        int maxLines = 0;

        // Aggiungi la prossima carta del mazzo (retro) se necessario
        if (mostraRetroMazzo) {
            carteStringhe[0] = carte.get(0).toStringRetro().split("\n");
            maxLines = Math.max(maxLines, carteStringhe[0].length);
        }

        // Converti ogni carta in un array di righe
        for (int i = 0; i < carte.size(); i++) {
            carteStringhe[i + (mostraRetroMazzo ? 1 : 0)] = carte.get(i).toString().split("\n");
            maxLines = Math.max(maxLines, carteStringhe[i + (mostraRetroMazzo ? 1 : 0)].length);
        }

        // Stampa le righe delle carte con uno spazio tra di loro
        for (int line = 0; line < maxLines; line++) {
            for (int i = 0; i < carteStringhe.length; i++) {
                if (line < carteStringhe[i].length) {
                    System.out.print(carteStringhe[i][line]);
                } else {
                    System.out.print(" ".repeat(carteStringhe[0][0].length())); // Spazio vuoto se la carta ha meno righe
                }
                System.out.print("\t"); // Spazio tra le carte
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
        risorsaConRetro.addAll(carteRisorsaVisibili);
        stampaCarteOrizzontalmente(risorsaConRetro, false);

        System.out.println("\nCarte Oro Visibili:");
        List<Carta> oroConRetro = new ArrayList<>();
        if (!mazzoOroCoperto.isEmpty()) {
            oroConRetro.add(mazzoOroCoperto.get(0));
        }
        oroConRetro.addAll(carteOroVisibili);
        stampaCarteOrizzontalmente(oroConRetro, false);
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
