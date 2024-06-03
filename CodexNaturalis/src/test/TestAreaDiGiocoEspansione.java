package test;

import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;
import cardsModel.Carta;
import cardsModel.CartaIniziale;
import cardsModel.CartaRisorsa;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestAreaDiGiocoEspansione {

    public static void main(String[] args) {
        // Creazione della griglia di gioco
        Contatori contatori = new Contatori();
        CartaIniziale cartaIniziale = new CartaIniziale(1, "Vegetale - Animale - Insetto - Fungo", Arrays.asList("Fungo", "Fungo"), "Animale - Vegetale - Fungo - Insetto");
        AreaDiGioco areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);

        // Aggiunta di carte risorsa alla griglia per testare l'espansione
        List<CartaRisorsa> carteDaAggiungere = Arrays.asList(
            new CartaRisorsa(2, "Vegetale - Vegetale - Vegetale - Vegetale", "Vegetale", 0),
            new CartaRisorsa(3, "Fungo - Fungo - Fungo - Fungo", "Fungo", 0),
            new CartaRisorsa(4, "Animale - Animale - Animale - Animale", "Animale", 0),
            new CartaRisorsa(5, "Insetto - Insetto - Insetto - Insetto", "Insetto", 0),
            new CartaRisorsa(6, "Vegetale - Vegetale - Vegetale - Vegetale", "Vegetale", 0),
            new CartaRisorsa(7, "Fungo - Fungo - Fungo - Fungo", "Fungo", 0)
        );

        Scanner scanner = new Scanner(System.in);

        // Simulazione del posizionamento delle carte
        for (CartaRisorsa carta : carteDaAggiungere) {
            boolean cartaPosizionata = false;

            while (!cartaPosizionata) {
                // Stampa dello stato attuale della griglia con le posizioni disponibili
                System.out.println("Stato attuale della griglia:");
                areaDiGioco.visualizzaGriglia();
                stampaCoordinate(areaDiGioco);

                // Simulazione della scelta della posizione
                System.out.println("Inserisci la posizione disponibile per la carta " + carta.getTipoRegno() + ":");
                int posizioneDisponibile = scanner.nextInt();
                cartaPosizionata = areaDiGioco.posizionaCarta(carta, posizioneDisponibile, true);
            }

            // Stampa della griglia dopo ogni posizionamento
            System.out.println("Stato della griglia dopo il posizionamento della carta " + carta.getTipoRegno() + ":");
            areaDiGioco.visualizzaGriglia();
            stampaCoordinate(areaDiGioco);
        }

        scanner.close();
    }

    private static void stampaCoordinate(AreaDiGioco areaDiGioco) {
        Carta[][] griglia = areaDiGioco.getGriglia();
        System.out.println("Coordinate delle carte nella griglia:");
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                if (griglia[i][j] != null) {
                    String tipoRegno = griglia[i][j] instanceof CartaRisorsa ? ((CartaRisorsa) griglia[i][j]).getTipoRegno() : "Iniziale";
                    System.out.printf("Posizione (%d,%d): %s - %s\n", i, j, griglia[i][j].getTipoCarta(), tipoRegno);
                } else {
                    System.out.printf("Posizione (%d,%d): vuoto\n", i, j);
                }
            }
        }
    }
}
