package test;

import Modello_giocatore.Giocatore;
import cardsModel.MazzoCarte;

import java.util.Scanner;

public class TestGiocatore {
    public static void main(String[] args) {
        MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        MazzoCarte mazzoOro = new MazzoCarte("Oro");

        mazzoIniziale.caricaCarteDaFile("src/fileCarte/CarteIniziali.txt");
        mazzoRisorsa.caricaCarteDaFile("src/fileCarte/CarteRisorsa.txt");
        mazzoOro.caricaCarteDaFile("src/fileCarte/CarteOro.txt");

        mazzoIniziale.mescolaMazzo();
        mazzoRisorsa.mescolaMazzo();
        mazzoOro.mescolaMazzo();

        Giocatore giocatore = new Giocatore("Giocatore1", mazzoIniziale, mazzoRisorsa, mazzoOro);

        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("Area di Gioco:");
            giocatore.mostraAreaDiGioco();

            System.out.println("\nMano Giocatore:");
            giocatore.mostraMano();

            System.out.println("\nScegli una carta da giocare (1-3):");
            int cartaDaGiocare = scanner.nextInt() - 1;

            System.out.println("Scegli la posizione (numero casella disponibile):");
            int indicePosizioneVuota = scanner.nextInt();

            giocatore.giocaCarta(giocatore.getCartaDallaMano(cartaDaGiocare), indicePosizioneVuota);

            System.out.println("\nArea di Pesca:");
            giocatore.mostraAreaDiPesca();

            System.out.println("\nScegli una carta da pescare (1-6):");
            int cartaDaPescare = scanner.nextInt();

            giocatore.pescaCarta(cartaDaPescare);

            System.out.println("\nMano Giocatore aggiornata:");
            giocatore.mostraMano();

            System.out.println("\nVuoi continuare? (s/n):");
            String risposta = scanner.next();
            continua = risposta.equalsIgnoreCase("s");
        }

        scanner.close();
    }
}
