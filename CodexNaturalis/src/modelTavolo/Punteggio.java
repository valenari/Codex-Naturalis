package modelTavolo;

import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;

public class Punteggio {

    public static int calcolaPuntiCartaRisorsa(CartaRisorsa cartaRisorsa) {
        return cartaRisorsa.getPunti();
    }

    public static int calcolaPuntiCartaOro(CartaOro cartaOro, Contatori contatori, AreaDiGioco areaDiGioco) {
        int puntiTotali = 0;
        String criterioPunti = cartaOro.getCriterioPunti();

        switch (criterioPunti) {
            case "Oggetto":
                puntiTotali = cartaOro.getPunti() * contatori.getContatore(cartaOro.getTipoRegno());
                break;
            case "Angoli":
                puntiTotali = cartaOro.getPunti() * areaDiGioco.getAngoliCoperti();
                break;
            default:
                puntiTotali = cartaOro.getPunti();
                break;
        }

        return puntiTotali;
    }
}
