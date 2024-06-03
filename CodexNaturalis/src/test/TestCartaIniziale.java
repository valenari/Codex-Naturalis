package test;

import cardsModel.CartaIniziale;
import java.util.Arrays;
import java.util.List;

public class TestCartaIniziale {

    public static void main(String[] args) {
        // Creazione di un oggetto CartaIniziale con attributi specifici
        List<String> centrale = Arrays.asList("Vegetale", "Animale");
        CartaIniziale cartaIniziale = new CartaIniziale(1, "Visibile - Nascosto - Visibile - Nascosto", centrale, "Visibile - Visibile - Visibile - Visibile - Fungo");

        // Verifica della corretta inizializzazione
        assert cartaIniziale.getIdCarta() == 1 : "Errore nell'inizializzazione dell'ID della carta";
        assert cartaIniziale.getTipoCarta().equals("Iniziale") : "Errore nell'inizializzazione del tipo di carta";
        assert cartaIniziale.getFronte().equals("Visibile - Nascosto - Visibile - Nascosto") : "Errore nell'inizializzazione del fronte della carta";
        assert cartaIniziale.getRetro().equals("Visibile - Visibile - Visibile - Visibile - Fungo") : "Errore nell'inizializzazione del retro della carta";
        assert cartaIniziale.getCentrale().equals(centrale) : "Errore nell'inizializzazione delle risorse centrali";

        // Verifica dei metodi getCentrale e getTipoRisorsa
        assert cartaIniziale.getCentrale().equals(centrale) : "Errore nel metodo getCentrale";
        assert cartaIniziale.getTipoCarta().equals("Fungo") : "Errore nel metodo getTipoRisorsa";

        // Verifica dei metodi toString e toStringRetro
        System.out.println("Verifica del metodo toString:");
        System.out.println(cartaIniziale.toString());
        System.out.println("Verifica del metodo toStringRetro:");
        System.out.println(cartaIniziale.toStringRetro());

        System.out.println("Tutti i test sono passati con successo!");
    }
}
