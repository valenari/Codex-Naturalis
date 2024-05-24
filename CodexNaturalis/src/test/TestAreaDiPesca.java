package test;

import cardsModel.CartaRisorsa;
import cardsModel.CartaOro;
import modelTavolo.AreaDiPesca;
import java.util.List;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        // Carica le carte dai file
        List<CartaRisorsa> mazzoRisorsa = CartaRisorsa.leggiCarteRisorsa("src/fileCarte/CarteRisorsa.txt");
        List<CartaOro> mazzoOro = CartaOro.leggiCarteOro("src/fileCarte/CarteOro.txt");

        // Crea l'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Mostra l'area di pesca iniziale
        areaDiPesca.mostraAreaDiPesca();
    }
}
