package modelTavolo;

import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;
import cardsModel.Carta;
import util.StampaCarta;

import java.util.ArrayList;
import java.util.List;

public class AreaDiPesca {
    private List<CartaRisorsa> carteRisorsaVisibili;
    private List<CartaOro> carteOroVisibili;
    private List<Carta> mazzoRisorsaCoperto;
    private List<Carta> mazzoOroCoperto;

    public AreaDiPesca(MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.mazzoRisorsaCoperto = new ArrayList<>(mazzoRisorsa.getCarte());
        this.mazzoOroCoperto = new ArrayList<>(mazzoOro.getCarte());
        this.carteRisorsaVisibili = new ArrayList<>();
        this.carteOroVisibili = new ArrayList<>();

        pescaCarteIniziali();
    }

    private void pescaCarteIniziali() {
        for (int i = 0; i < 2; i++) {
            pescaCartaDalMazzoRisorsa();
            pescaCartaDalMazzoOro();
        }
    }

    private void pescaCartaDalMazzoRisorsa() {
        if (!mazzoRisorsaCoperto.isEmpty()) {
            Carta carta = mazzoRisorsaCoperto.remove(0);
            if (carta instanceof CartaRisorsa) {
                carteRisorsaVisibili.add((CartaRisorsa) carta);
            }
        }
    }

    private void pescaCartaDalMazzoOro() {
        if (!mazzoOroCoperto.isEmpty()) {
            Carta carta = mazzoOroCoperto.remove(0);
            if (carta instanceof CartaOro) {
                carteOroVisibili.add((CartaOro) carta);
            }
        }
    }

    public Carta pescaCarta(int indice) {
        Carta cartaPescata = null;
        if ((indice == 1 || indice == 2) && carteRisorsaVisibili.size() >= indice) {
            cartaPescata = carteRisorsaVisibili.remove(indice - 1);
            pescaCartaDalMazzoRisorsa();
        } else if (indice == 3 && !mazzoOroCoperto.isEmpty()) {
            cartaPescata = mazzoOroCoperto.remove(0);
        } else if ((indice == 4 || indice == 5) && carteOroVisibili.size() >= (indice - 3)) {
            cartaPescata = carteOroVisibili.remove(indice - 4);
            pescaCartaDalMazzoOro();
        } else if (indice == 6 && !mazzoOroCoperto.isEmpty()) {
            cartaPescata = mazzoOroCoperto.remove(0);
        }
        return cartaPescata;
    }

    public void mostraAreaDiPesca() {
        System.out.println("Carte Risorsa Visibili:");
        StampaCarta.stampaAreaDiPesca(carteRisorsaVisibili, mazzoRisorsaCoperto, true);

        System.out.println("\nCarte Oro Visibili:");
        StampaCarta.stampaAreaDiPesca(carteOroVisibili, mazzoOroCoperto, false);
    }

    public boolean isMazzoRisorsaVuoto() {
        return mazzoRisorsaCoperto.isEmpty() && carteRisorsaVisibili.isEmpty();
    }

    public boolean isMazzoOroVuoto() {
        return mazzoOroCoperto.isEmpty() && carteOroVisibili.isEmpty();
    }
    
    // Metodi aggiuntivi per il test
    public List<CartaRisorsa> getCarteRisorsaVisibili() {
        return carteRisorsaVisibili;
    }

    public List<CartaOro> getCarteOroVisibili() {
        return carteOroVisibili;
    }

    public List<Carta> getMazzoRisorsaCoperto() {
        return mazzoRisorsaCoperto;
    }

    public List<Carta> getMazzoOroCoperto() {
        return mazzoOroCoperto;
    }
}
