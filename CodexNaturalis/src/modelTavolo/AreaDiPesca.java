package modelTavolo;

import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.Carta;
import util.StampaCarta;

import java.util.ArrayList;
import java.util.List;

public class AreaDiPesca {
    private List<CartaRisorsa> carteRisorsaVisibili;
    private List<CartaOro> carteOroVisibili;
    private List<CartaRisorsa> mazzoRisorsaCoperto;
    private List<CartaOro> mazzoOroCoperto;

    public AreaDiPesca(List<Carta> carteRisorsa, List<Carta> carteOro) {
        this.mazzoRisorsaCoperto = new ArrayList<>();
        this.mazzoOroCoperto = new ArrayList<>();
        for (Carta carta : carteRisorsa) {
            this.mazzoRisorsaCoperto.add((CartaRisorsa) carta);
        }
        for (Carta carta : carteOro) {
            this.mazzoOroCoperto.add((CartaOro) carta);
        }
        this.carteRisorsaVisibili = new ArrayList<>();
        this.carteOroVisibili = new ArrayList<>();

        pescaCarteIniziali();
    }

    private void pescaCarteIniziali() {
        pescaCartaDalMazzoRisorsa();
        pescaCartaDalMazzoRisorsa();
        pescaCartaDalMazzoOro();
        pescaCartaDalMazzoOro();
    }

    private void pescaCartaDalMazzoRisorsa() {
        if (!mazzoRisorsaCoperto.isEmpty()) {
            carteRisorsaVisibili.add(mazzoRisorsaCoperto.remove(0));
        }
    }

    private void pescaCartaDalMazzoOro() {
        if (!mazzoOroCoperto.isEmpty()) {
            carteOroVisibili.add(mazzoOroCoperto.remove(0));
        }
    }

    public Carta pescaCarta(int indice) {
        Carta cartaPescata = null;
        if (indice == 0) {
            cartaPescata = mazzoRisorsaCoperto.remove(0);
            pescaCartaDalMazzoRisorsa();
        } else if (indice == 1 || indice == 2) {
            cartaPescata = carteRisorsaVisibili.remove(indice - 1);
            pescaCartaDalMazzoRisorsa();
        } else if (indice == 3) {
            cartaPescata = mazzoOroCoperto.remove(0);
            pescaCartaDalMazzoOro();
        } else if (indice == 4 || indice == 5) {
            cartaPescata = carteOroVisibili.remove(indice - 4);
            pescaCartaDalMazzoOro();
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
}
