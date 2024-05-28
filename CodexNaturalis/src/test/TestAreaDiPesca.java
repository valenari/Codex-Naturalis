package test;

import cardsModel.*;
import modelTavolo.AreaDiPesca;

public class TestAreaDiPesca {
    public static void main(String[] args) {
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        mazzoRisorsa.caricaCarteDaFile("src/fileCarte/CarteRisorsa.txt");

        MazzoCarte mazzoOro = new MazzoCarte("Oro");
        mazzoOro.caricaCarteDaFile("src/fileCarte/CarteOro.txt");

        AreaDiPesca areaDiPesca = new AreaDiPesca(mazzoRisorsa.getCarte(), mazzoOro.getCarte());
        areaDiPesca.mostraAreaDiPesca();
    }
}
