package test;

import modelObiettivi.CaricatoreObiettivi;
import modelObiettivi.Obiettivo;
import java.util.List;

public class TestCaricatoreObiettivi {
    public static void main(String[] args) {
        CaricatoreObiettivi caricatore = new CaricatoreObiettivi();

        // Test per obiettivi di disposizione
        System.out.println("Test obiettivi di disposizione:");
        List<Obiettivo> obiettiviDisposizione = caricatore.caricaObiettiviDisposizione("src/fileCarte/ObiettiviDisposizione.txt");
        for (Obiettivo obiettivo : obiettiviDisposizione) {
            System.out.println(obiettivo);
        }

        // Test per obiettivi di risorse
        System.out.println("Test obiettivi di risorse:");
        List<Obiettivo> obiettiviRisorse = caricatore.caricaObiettiviRisorse("src/fileCarte/ObiettiviRisorse.txt");
        for (Obiettivo obiettivo : obiettiviRisorse) {
            System.out.println(obiettivo);
        }
    }
}
