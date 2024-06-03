package test;

import cardsModel.CartaOro;
import java.util.Arrays;
import java.util.List;

public class TestCartaOro {

    public static void main(String[] args) {
        // Creazione di un oggetto CartaOro con attributi specifici
        List<String> risorseRichieste = Arrays.asList("Vegetale", "Animale");
        CartaOro cartaOro = new CartaOro(1, "Visibile - Nascosto - Visibile - Nascosto", "Fungo", 3, "Inchiostro", risorseRichieste);

        // Verifica della corretta inizializzazione
        assert cartaOro.getIdCarta() == 1 : "Errore nell'inizializzazione dell'ID della carta";
        assert cartaOro.getTipoCarta().equals("Oro") : "Errore nell'inizializzazione del tipo di carta";
        assert cartaOro.getFronte().equals("Visibile - Nascosto - Visibile - Nascosto") : "Errore nell'inizializzazione del fronte della carta";
        assert cartaOro.getRetro().equals("Visibile - Visibile - Visibile - Visibile - Fungo") : "Errore nell'inizializzazione del retro della carta";
        assert cartaOro.getPunti() == 3 : "Errore nell'inizializzazione dei punti";
        assert cartaOro.getCriterioPunti().equals("Oggetto") : "Errore nell'inizializzazione del criterio punti";
        assert cartaOro.getRisorseRichieste().equals(risorseRichieste) : "Errore nell'inizializzazione delle risorse richieste";

        // Verifica dei metodi getRisorseRichieste e getTipoRisorsa
        assert cartaOro.getRisorseRichieste().equals(risorseRichieste) : "Errore nel metodo getRisorseRichieste";
        assert cartaOro.getTipoCarta().equals("Fungo") : "Errore nel metodo getTipoRisorsa";

        // Verifica dei metodi toString e toStringRetro
        System.out.println("Verifica del metodo toString:");
        System.out.println(cartaOro.toString());
        System.out.println("Verifica del metodo toStringRetro:");
        System.out.println(cartaOro.toStringRetro());

        System.out.println("Tutti i test sono passati con successo per la carta con ID: " + cartaOro.getIdCarta());
    }
}
