package test;

import cardsModel.MazzoCarte;
import modelTavolo.AreaDiPesca;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        // Creare i mazzi e caricare le carte dai file
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        MazzoCarte mazzoOro = new MazzoCarte("Oro");

        mazzoRisorsa.caricaCarteDaFile("src/fileCarte/CarteRisorsa.txt");
        mazzoOro.caricaCarteDaFile("src/fileCarte/CarteOro.txt");

        // Mescolare i mazzi
        mazzoRisorsa.mescolaMazzo();
        mazzoOro.mescolaMazzo();

        // Creare l'area di pesca
        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa, mazzoOro);

        // Mostrare lo stato dell'area di pesca
        areaDiPesca.mostraAreaDiPesca();
    }
}
