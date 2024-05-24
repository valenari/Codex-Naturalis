package modelTavolo;

import cardsModel.Carta;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Area di Pesca:\n");
        sb.append("Carte Risorsa Visibili:\n");
        sb.append(stampaCarteOrizzontali(carteRisorsaVisibili, !mazzoRisorsaCoperto.isEmpty() ? mazzoRisorsaCoperto.get(0).toStringRetro() : null));
        sb.append("\nCarte Oro Visibili:\n");
        sb.append(stampaCarteOrizzontali(carteOroVisibili, !mazzoOroCoperto.isEmpty() ? mazzoOroCoperto.get(0).toStringRetro() : null));

        System.out.println(sb.toString());
    }

    // Metodo per stampare le carte in orizzontale con il mazzo coperto
    private String stampaCarteOrizzontali(List<? extends Carta> carteVisibili, String cartaCoperta) {
        StringBuilder sb = new StringBuilder();
        int maxRighe = 7; // Numero massimo di righe per la rappresentazione grafica di una carta
        List<String[]> carteRighe = new ArrayList<>();

        // Aggiungi la carta coperta se presente
        if (cartaCoperta != null) {
            String[] righeCoperta = cartaCoperta.split("\n");
            carteRighe.add(righeCoperta);
        }

        // Aggiungi le carte visibili
        for (Carta carta : carteVisibili) {
            String[] righe = carta.toString().split("\n");
            carteRighe.add(righe);
        }

        // Costruisci le righe orizzontali
        for (int i = 0; i < maxRighe; i++) {
            for (String[] righeCarta : carteRighe) {
                if (i < righeCarta.length) {
                    sb.append(righeCarta[i]).append("  "); // Aggiungi due spazi tra le carte
                } else {
                    sb.append(" ".repeat(26)).append("  "); // Aggiungi spazi vuoti per allineare
                }
            }
            sb.append("\n");
        }

        return sb.toString();
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
