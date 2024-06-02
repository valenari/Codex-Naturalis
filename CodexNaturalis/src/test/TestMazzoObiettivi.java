package test;

import modelObiettivi.MazzoObiettivi;
import modelObiettivi.Obiettivo;

import java.util.List;

public class TestMazzoObiettivi {
    public static void main(String[] args) {
        String filenameDisposizione = "src/fileCarte/ObiettiviDisposizione.txt";
        String filenameRisorse = "src/fileCarte/ObiettiviRisorse.txt";

        MazzoObiettivi mazzoObiettivi = new MazzoObiettivi(filenameDisposizione, filenameRisorse);

        System.out.println("Obiettivi:");
        while (!mazzoObiettivi.isVuoto()) {
            Obiettivo obiettivo = mazzoObiettivi.pescaObiettivo();
            System.out.println(obiettivo);
        }
    }
}
