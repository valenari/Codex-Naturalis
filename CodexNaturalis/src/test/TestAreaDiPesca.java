package test;

import modelTavolo.AreaDiPesca;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.MazzoCarte;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        // Carica i mazzi dalle carte
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        mazzoRisorsa.caricaCarteDaFile("src/fileCarte/CarteRisorsa.txt");
        mazzoRisorsa.mescolaMazzo();

        MazzoCarte mazzoOro = new MazzoCarte("Oro");
        mazzoOro.caricaCarteDaFile("src/fileCarte/CarteOro.txt");
        mazzoOro.mescolaMazzo();

        // Crea l'area di pesca con i mazzi caricati
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa.getCarteRisorsa(), mazzoOro.getCarteOro());

        // Mostra l'area di pesca
        areaDiPesca.mostraAreaDiPesca();

        // Pesca una carta risorsa e una carta oro
        CartaRisorsa cartaRisorsaPescata = areaDiPesca.pescaCartaRisorsa(1); // esempio per pescare la prima carta visibile
        CartaOro cartaOroPescata = areaDiPesca.pescaCartaOro(1); // esempio per pescare la prima carta visibile

        // Stampa le carte pescate
        System.out.println("\nCarta Risorsa Pescata:");
        if (cartaRisorsaPescata != null) {
            cartaRisorsaPescata.stampaCarta();
        } else {
            System.out.println("Nessuna carta risorsa pescata.");
        }

        System.out.println("\nCarta Oro Pescata:");
        if (cartaOroPescata != null) {
            cartaOroPescata.stampaCarta();
        } else {
            System.out.println("Nessuna carta oro pescata.");
        }

        // Mostra l'area di pesca aggiornata
        areaDiPesca.mostraAreaDiPesca();
    }
}
