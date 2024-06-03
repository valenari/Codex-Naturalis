package test;

import cardsModel.CartaIniziale;
import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;

import java.util.Arrays;
import java.util.List;

public class TestContatori {

    public static void main(String[] args) {
        // Inizializzazione dei contatori
        Contatori contatori = new Contatori();

        // Verifica dei valori iniziali
        System.out.println("Valori iniziali dei contatori:");
        contatori.mostraContatori();

        // Creazione di una carta iniziale e area di gioco
        CartaIniziale cartaIniziale = new CartaIniziale(1, "Visibile - Nascosto - Visibile - Nascosto", List.of("Vegetale", "Fungo"), "Visibile - Visibile - Visibile - Visibile");
        AreaDiGioco areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);

        // Aggiunta di carte risorsa e oro
        CartaRisorsa cartaRisorsa1 = new CartaRisorsa(2, "Visibile - Nascosto - Vegetale - Nascosto", "Vegetale", 2);
        CartaRisorsa cartaRisorsa2 = new CartaRisorsa(3, "Visibile - Nascosto - Fungo - Nascosto", "Fungo", 3);
        CartaOro cartaOro = new CartaOro(4, "Visibile - Nascosto - Animale - Nascosto", "Animale", 5, "Angoli", Arrays.asList("Vegetale", "Fungo"));

        // Posizionamento delle carte nell'area di gioco
        areaDiGioco.posizionaCarta(cartaRisorsa1, 1, true);
        areaDiGioco.posizionaCarta(cartaRisorsa2, 2, true);
        areaDiGioco.posizionaCarta(cartaOro, 3, true);

        // Aggiornamento dei contatori
        contatori.aggiornaContatori(areaDiGioco);

        // Verifica dei valori aggiornati dei contatori
        System.out.println("\nValori aggiornati dei contatori:");
        contatori.mostraContatori();

        // Verifica del metodo decrementaContatore
        contatori.decrementaContatore("Vegetale");
        System.out.println("\nValori dei contatori dopo decremento Vegetale:");
        contatori.mostraContatori();

        // Verifica del metodo verificaRisorse
        List<String> risorseRichieste = Arrays.asList("Vegetale", "Fungo");
        boolean risorseVerificate = contatori.verificaRisorse(risorseRichieste);
        System.out.println("\nVerifica risorse richieste (Vegetale, Fungo): " + risorseVerificate);

        risorseRichieste = Arrays.asList("Vegetale", "Animale");
        risorseVerificate = contatori.verificaRisorse(risorseRichieste);
        System.out.println("Verifica risorse richieste (Vegetale, Animale): " + risorseVerificate);

        System.out.println("Tutti i test sono passati con successo.");
    }
}
