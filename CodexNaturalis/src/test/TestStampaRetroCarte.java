package test;

import cardsModel.CartaRisorsa;
import cardsModel.CartaOro;
import Modello_giocatore.Caselleproibite;
import java.util.ArrayList;
import java.util.List;

public class TestStampaRetroCarte {
    public static void main(String[] args) {
        // Creazione di alcune carte risorsa di test
        List<CartaRisorsa> carteRisorsa = new ArrayList<>();
        carteRisorsa.add(new CartaRisorsa(0, "Fungo - Visibile - Fungo - Nascosto", Caselleproibite.NULL, "Fungo", 0));
        carteRisorsa.add(new CartaRisorsa(1, "Vegetale - Nascosto - Visibile - Animale", Caselleproibite.NULL, "Vegetale", 1));
        
        // Creazione di alcune carte oro di test
        List<CartaOro> carteOro = new ArrayList<>();
        List<String> risorseRichieste = new ArrayList<>();
        risorseRichieste.add("Fungo");
        risorseRichieste.add("Fungo");
        risorseRichieste.add("Animale");
        carteOro.add(new CartaOro(40, "Nascosto - Visibile - Visibile - Piuma", Caselleproibite.NULL, "Fungo", 1, "Piuma", risorseRichieste));
        risorseRichieste = new ArrayList<>();
        risorseRichieste.add("Vegetale");
        risorseRichieste.add("Animale");
        risorseRichieste.add("Insetto");
        carteOro.add(new CartaOro(50, "Piuma - Visibile - Nascosto - Visibile", Caselleproibite.NULL, "Vegetale", 2, "Angoli", risorseRichieste));

        // Stampa delle carte risorsa
        System.out.println("Carte Risorsa:");
        for (CartaRisorsa carta : carteRisorsa) {
            carta.stampaCarta();
            carta.stampaRetro();
        }

        // Stampa delle carte oro
        System.out.println("\nCarte Oro:");
        for (CartaOro carta : carteOro) {
            carta.stampaCarta();
            carta.stampaRetro();
        }
    }
}
