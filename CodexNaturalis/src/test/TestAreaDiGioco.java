package test;

import Modello_giocatore.AreaDiGioco;
import cardsModel.MazzoCarte;
import cardsModel.CartaIniziale;
import cardsModel.CartaRisorsa;
import cardsModel.CartaOro;

public class TestAreaDiGioco {
    public static void main(String[] args) {
        // Carica i mazzi dalle carte
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale");
        mazzoIniziale.caricaCarteDaFile("src/fileCarte/CarteIniziali.txt");
        mazzoIniziale.mescolaMazzo();

        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        mazzoRisorsa.caricaCarteDaFile("src/fileCarte/CarteRisorsa.txt");
        mazzoRisorsa.mescolaMazzo();

        MazzoCarte mazzoOro = new MazzoCarte("Oro");
        mazzoOro.caricaCarteDaFile("src/fileCarte/CarteOro.txt");
        mazzoOro.mescolaMazzo();

        // Crea l'area di gioco con una carta iniziale
        CartaIniziale cartaIniziale = (CartaIniziale) mazzoIniziale.pescaCarta();
        AreaDiGioco areaDiGioco = new AreaDiGioco(cartaIniziale);

        // Mostra la griglia iniziale
        System.out.println("Griglia iniziale con la carta iniziale al centro:");
        areaDiGioco.visualizzaGriglia();

        // Gioca una carta risorsa e una carta oro
        CartaRisorsa cartaRisorsa = (CartaRisorsa) mazzoRisorsa.pescaCarta();
        CartaOro cartaOro = (CartaOro) mazzoOro.pescaCarta();

        // Posiziona le carte sulla griglia
        areaDiGioco.giocaCarta(cartaRisorsa, -1, -1);  // Posiziona una carta in alto a sinistra rispetto al centro
        areaDiGioco.giocaCarta(cartaOro, 1, 1);        // Posiziona una carta in basso a destra rispetto al centro

        // Mostra la griglia aggiornata
        System.out.println("\nGriglia aggiornata dopo aver giocato due carte:");
        areaDiGioco.visualizzaGriglia();
    }
}
