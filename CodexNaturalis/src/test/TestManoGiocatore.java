package test;

import cardsModel.CartaRisorsa;
import modelPlayer.ManoGiocatore;

public class TestManoGiocatore {

    public static void main(String[] args) {
        ManoGiocatore mano = new ManoGiocatore();

        // Creazione di alcune carte per il test
        CartaRisorsa carta1 = new CartaRisorsa(1, "Visibile - Nascosto - Visibile - Nascosto", "Vegetale", 2);
        CartaRisorsa carta2 = new CartaRisorsa(2, "Nascosto - Visibile - Nascosto - Visibile", "Fungo", 3);
        CartaRisorsa carta3 = new CartaRisorsa(3, "Visibile - Visibile - Nascosto - Nascosto", "Animale", 1);
        CartaRisorsa carta4 = new CartaRisorsa(4, "Nascosto - Nascosto - Visibile - Visibile", "Insetto", 4);

        // Aggiunta di carte alla mano e verifica del limite massimo di 3 carte
        mano.aggiungiCarta(carta1);
        mano.aggiungiCarta(carta2);
        mano.aggiungiCarta(carta3);
        mano.aggiungiCarta(carta4); // Questa carta non dovrebbe essere aggiunta

        // Verifica che la mano contenga solo 3 carte
        System.out.println("Numero di carte in mano: " + mano.getNumeroCarte());
        assert mano.getNumeroCarte() == 3 : "Errore: La mano dovrebbe contenere solo 3 carte";

        // Verifica del contenuto della mano
        assert mano.getCarta(0).equals(carta1) : "Errore: La prima carta dovrebbe essere carta1";
        assert mano.getCarta(1).equals(carta2) : "Errore: La seconda carta dovrebbe essere carta2";
        assert mano.getCarta(2).equals(carta3) : "Errore: La terza carta dovrebbe essere carta3";

        // Rimozione di una carta dalla mano e verifica della corretta rimozione
        CartaRisorsa cartaRimossa = (CartaRisorsa) mano.rimuoviCarta(1);
        assert cartaRimossa.equals(carta2) : "Errore: La carta rimossa dovrebbe essere carta2";

        // Verifica che la mano contenga solo 2 carte
        System.out.println("Numero di carte in mano dopo la rimozione: " + mano.getNumeroCarte());
        assert mano.getNumeroCarte() == 2 : "Errore: La mano dovrebbe contenere 2 carte";

        // Verifica del metodo stampaMano
        System.out.println("Stampa della mano:");
        mano.stampaMano();

        System.out.println("Tutti i test sono passati con successo.");
    }
}
