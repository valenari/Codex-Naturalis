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
    private CartaRisorsa cartaRisorsaCoperta;
    private CartaOro cartaOroCoperta;
    private MazzoCarte mazzoRisorsa;
    private MazzoCarte mazzoOro;

    public AreaDiPesca(MazzoCarte mazzoRisorsa, MazzoCarte mazzoOro) {
        this.mazzoRisorsa = mazzoRisorsa;
        this.mazzoOro = mazzoOro;
        this.carteRisorsaVisibili = new ArrayList<>();
        this.carteOroVisibili = new ArrayList<>();

        pescaCarteIniziali();
    }

    private void pescaCarteIniziali() {
        for (int i = 0; i < 2; i++) {
            pescaCartaDalMazzoRisorsa();
            pescaCartaDalMazzoOro();
        }
        aggiornaCartaCopertaRisorsa();
        aggiornaCartaCopertaOro();
    }

    private void pescaCartaDalMazzoRisorsa() {
        if (!mazzoRisorsa.isVuoto()) {
            Carta carta = mazzoRisorsa.pescaCarta();
            if (carta instanceof CartaRisorsa) {
                carteRisorsaVisibili.add((CartaRisorsa) carta);
            }
        }
    }

    private void pescaCartaDalMazzoOro() {
        if (!mazzoOro.isVuoto()) {
            Carta carta = mazzoOro.pescaCarta();
            if (carta instanceof CartaOro) {
                carteOroVisibili.add((CartaOro) carta);
            }
        }
    }

    private void aggiornaCartaCopertaRisorsa() {
        if (!mazzoRisorsa.isVuoto()) {
            cartaRisorsaCoperta = (CartaRisorsa) mazzoRisorsa.pescaCarta();
        } else {
            cartaRisorsaCoperta = null;
        }
    }

    private void aggiornaCartaCopertaOro() {
        if (!mazzoOro.isVuoto()) {
            cartaOroCoperta = (CartaOro) mazzoOro.pescaCarta();
        } else {
            cartaOroCoperta = null;
        }
    }

    public Carta pescaCarta(int indice) {
        Carta cartaPescata = null;
        if ((indice == 2 || indice == 3) && carteRisorsaVisibili.size() >= indice) {
            cartaPescata = carteRisorsaVisibili.remove(indice - 1);
            if (cartaRisorsaCoperta != null) {
                carteRisorsaVisibili.add(cartaRisorsaCoperta);
                aggiornaCartaCopertaRisorsa();
            } else {
                pescaCartaDalMazzoRisorsa();
            }
        } else if (indice == 1) {
            cartaPescata = cartaRisorsaCoperta;
            cartaPescata.giraCarta();
            aggiornaCartaCopertaOro();
        } else if ((indice == 5 || indice == 6) && carteOroVisibili.size() >= (indice - 3)) {
            cartaPescata = carteOroVisibili.remove(indice - 4);
            if (cartaOroCoperta != null) {
                carteOroVisibili.add(cartaOroCoperta);
                aggiornaCartaCopertaOro();
            } else {
                pescaCartaDalMazzoOro();
            }
        }else if (indice == 4) {
            cartaPescata = cartaOroCoperta;
            cartaPescata.giraCarta();
            aggiornaCartaCopertaOro();
        }

        if (cartaPescata != null) {
            cartaPescata.giraCarta(); // Gira la carta pescata sul fronte
        }

        return cartaPescata;
    }

    public void mostraAreaDiPesca() {
        System.out.println("Carte Risorsa Visibili:");
        StampaCarta.stampaAreaDiPesca(carteRisorsaVisibili, cartaRisorsaCoperta, true);

        System.out.println("\nCarte Oro Visibili:");
        StampaCarta.stampaAreaDiPesca(carteOroVisibili, cartaOroCoperta, false);
    }

    public boolean isMazzoRisorsaVuoto() {
        return mazzoRisorsa.isVuoto() && carteRisorsaVisibili.isEmpty();
    }

    public boolean isMazzoOroVuoto() {
        return mazzoOro.isVuoto() && carteOroVisibili.isEmpty();
    }

    // Metodi aggiuntivi per il test
    public List<CartaRisorsa> getCarteRisorsaVisibili() {
        return carteRisorsaVisibili;
    }

    public List<CartaOro> getCarteOroVisibili() {
        return carteOroVisibili;
    }

    public CartaRisorsa getCartaRisorsaCoperta() {
        return cartaRisorsaCoperta;
    }

    public CartaOro getCartaOroCoperta() {
        return cartaOroCoperta;
    }
}
