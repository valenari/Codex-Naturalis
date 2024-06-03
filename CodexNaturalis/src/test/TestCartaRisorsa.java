package test;

import cardsModel.CartaRisorsa;

public class TestCartaRisorsa {

    public static void main(String[] args) {
        // Creazione di un oggetto CartaRisorsa con attributi specifici
        CartaRisorsa cartaRisorsa = new CartaRisorsa(1, "Visibile - Nascosto - Visibile - Nascosto", "Vegetale", 2);

        // Verifica della corretta inizializzazione
        assert cartaRisorsa.getIdCarta() == 1 : "Errore nell'inizializzazione dell'ID della carta";
        assert cartaRisorsa.getTipoCarta().equals("Risorsa") : "Errore nell'inizializzazione del tipo di carta";
        assert cartaRisorsa.getFronte().equals("Visibile - Nascosto - Visibile - Nascosto") : "Errore nell'inizializzazione del fronte della carta";
        assert cartaRisorsa.getRetro().equals("Visibile - Visibile - Visibile - Visibile - Vegetale") : "Errore nell'inizializzazione del retro della carta";
        assert cartaRisorsa.getTipoCarta().equals("Vegetale") : "Errore nell'inizializzazione del tipo di risorsa";
        assert cartaRisorsa.getPunti() == 2 : "Errore nell'inizializzazione dei punti";

        // Verifica del metodo getTipoRisorsa
        assert cartaRisorsa.getTipoCarta().equals("Vegetale") : "Errore nel metodo getTipoRisorsa";

        // Verifica dei metodi toString e toStringRetro
        System.out.println("Verifica del metodo toString:");
        System.out.println(cartaRisorsa.toString());
        System.out.println("Verifica del metodo toStringRetro:");
        System.out.println(cartaRisorsa.toStringRetro());

        System.out.println("Tutti i test sono passati con successo per la carta con ID: " + cartaRisorsa.getIdCarta());
    }
}
