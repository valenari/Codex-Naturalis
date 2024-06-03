package test;

import modelObiettivi.ObiettivoDisposizione;
import modelPlayer.AreaDiGioco;
import modelPlayer.Contatori;
import cardsModel.CartaIniziale;
import cardsModel.CartaRisorsa;
import cardsModel.Carta;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestObiettivoDisposizione {

    public static void main(String[] args) {
        // Creazione di un obiettivo di disposizione
        List<String> sequenza = Arrays.asList("Fungo", "Fungo", "Fungo");
        List<int[]> posizioni = Arrays.asList(new int[]{0, 0}, new int[]{1, 1}, new int[]{2, 2});
        ObiettivoDisposizione obiettivo = new ObiettivoDisposizione("Disposizione diagonale di tre funghi", 2, sequenza, posizioni);

        // Verifica della corretta inizializzazione
        System.out.println("Obiettivo Disposizione:");
        System.out.println("Descrizione: " + obiettivo.getDescrizione());
        System.out.println("Punti: " + obiettivo.getPunti());
        System.out.println("Sequenza: " + sequenza);
        System.out.println("Posizioni: " + Arrays.deepToString(posizioni.toArray()));

        // Creazione della griglia di gioco
        Contatori contatori = new Contatori();
        CartaIniziale cartaIniziale = new CartaIniziale(1, "Fronte1 - Fronte2 - Fronte3 - Fronte4", Arrays.asList("Fungo", "Fungo"), "Retro1 - Retro2 - Retro3 - Retro4");
        AreaDiGioco areaDiGioco = new AreaDiGioco(cartaIniziale, contatori);

        // Aggiunta di carte risorsa alla griglia per soddisfare l'obiettivo
        Scanner scanner = new Scanner(System.in);
        List<String> tipiCarte = Arrays.asList("Fungo", "Fungo", "Fungo", "Fungo", "Fungo"); // Lista di carte per simulare il test

        for (String tipo : tipiCarte) {
            CartaRisorsa cartaRisorsa = new CartaRisorsa(2, "Fronte1 - Fronte2 - Fronte3 - Fronte4", tipo, 0);
            boolean cartaPosizionata = false;

            while (!cartaPosizionata) {
                System.out.println("Scegli una posizione per la carta di tipo " + tipo + ":");
                areaDiGioco.visualizzaGriglia();
                System.out.print("Inserisci la posizione disponibile: ");
                int posizione = scanner.nextInt();
                cartaPosizionata = areaDiGioco.posizionaCarta(cartaRisorsa, posizione, true);

                if (!cartaPosizionata) {
                    System.out.println("Posizione non valida. Riprova.");
                }
            }
        }

        // Stampa di debug: griglia e carte
        System.out.println("Debug: Griglia di gioco dopo aggiunta carte:");
        areaDiGioco.visualizzaGriglia();
        stampaCarteInGriglia(areaDiGioco);

        // Verifica dell'obiettivo con la configurazione della griglia
        int punteggioTotale = verificaObiettivo(obiettivo, areaDiGioco, contatori);
        System.out.println("Punteggio totale ottenuto: " + punteggioTotale); // dovrebbe essere 2

        scanner.close();
    }

    private static int verificaObiettivo(ObiettivoDisposizione obiettivo, AreaDiGioco areaDiGioco, Contatori contatori) {
        int punteggioTotale = 0;
        if (obiettivo.verificaObiettivo(areaDiGioco, contatori)) {
            punteggioTotale += obiettivo.getPunti();
        }
        return punteggioTotale;
    }

    private static void stampaCarteInGriglia(AreaDiGioco areaDiGioco) {
        Carta[][] griglia = areaDiGioco.getGriglia();
        System.out.println("Stato attuale della griglia:");
        for (int i = 0; i < griglia.length; i++) {
            for (int j = 0; j < griglia[i].length; j++) {
                Carta carta = griglia[i][j];
                if (carta != null) {
                    System.out.println("Posizione (" + i + "," + j + "): " + carta.getTipoCarta() + " - " + ((carta instanceof CartaRisorsa) ? ((CartaRisorsa) carta).getTipoRegno() : ((carta instanceof CartaIniziale) ? "Carta Iniziale" : "Altro")));
                } else {
                    System.out.println("Posizione (" + i + "," + j + "): vuoto");
                }
            }
        }
    }
}
