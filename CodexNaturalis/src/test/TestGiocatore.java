package test;

import Modello_giocatore.Giocatore;
import cardsModel.MazzoCarte;
import cardsModel.Carta;
import cardsModel.CartaIniziale;

public class TestGiocatore {
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

        // Crea un giocatore con i mazzi caricati
        Giocatore giocatore = new Giocatore("Mario", mazzoIniziale, mazzoRisorsa, mazzoOro);
        
        // Stampa le informazioni del giocatore
        giocatore.stampaInfoGiocatore();

        // Gioca una carta
        Carta cartaDaGiocare = giocatore.getManoGiocatore().getCarte().get(0);  // Prendi la prima carta dalla mano
        giocatore.giocaCarta(cartaDaGiocare, 1, 1);  // Posiziona la carta nella posizione (1, 1)

        // Stampa le informazioni del giocatore dopo aver giocato la carta
        giocatore.stampaInfoGiocatore();
    }
}
