package test;

import modelObiettivi.MazzoObiettivi;
import modelObiettivi.Obiettivo;

public class TestMazzoObiettivi {
    public static void main(String[] args) {
        // Creazione del mazzo di obiettivi
        MazzoObiettivi mazzo = new MazzoObiettivi("src/fileCarte/ObiettiviDisposizione.txt", "src/fileCarte/ObiettiviRisorse.txt");

        // Verifica della corretta inizializzazione
        System.out.println("Mazzo di obiettivi inizializzato:");
        mazzo.mostraMazzo();

        // Pesca di obiettivi e verifica dell'aggiornamento del mazzo
        System.out.println("\nPesca di obiettivi:");
        while (!mazzo.isMazzoVuoto()) {
            Obiettivo obiettivo = mazzo.pescaObiettivo();
            System.out.println("Obiettivo pescato: " + obiettivo);
        }

        // Verifica che il mazzo sia vuoto
        System.out.println("\nIl mazzo è vuoto: " + mazzo.isMazzoVuoto());
    }
}
