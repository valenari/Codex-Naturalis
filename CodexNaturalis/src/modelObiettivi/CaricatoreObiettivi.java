package modelObiettivi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaricatoreObiettivi {

    public static List<Obiettivo> caricaObiettiviDisposizione(String filename) {
        List<Obiettivo> obiettivi = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Obbiettivo")) {
                    String descrizione = linea;
                    List<String> sequenza = List.of(br.readLine().split(": ")[1].split("-"));
                    List<int[]> posizioni = new ArrayList<>();
                    for (String pos : br.readLine().split(": ")[1].split(" ")) {
                        String[] coord = pos.replace("[", "").replace("]", "").split(",");
                        posizioni.add(new int[]{Integer.parseInt(coord[0]), Integer.parseInt(coord[1])});
                    }
                    int punti = Integer.parseInt(br.readLine().split(": ")[1]);
                    obiettivi.add(new ObiettivoDisposizione(descrizione, punti, sequenza, posizioni));
                }
                // Salta la linea vuota tra gli obiettivi
                br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return obiettivi;
    }

    public static List<Obiettivo> caricaObiettiviRisorse(String filename) {
        List<Obiettivo> obiettivi = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Obbiettivo")) {
                    String descrizione = linea;
                    List<Integer> quantita = new ArrayList<>();
                    for (String q : br.readLine().split(": ")[1].split(" ")) {
                        quantita.add(Integer.parseInt(q));
                    }
                    List<String> tipiRisorse = List.of(br.readLine().split(": ")[1].split(" "));
                    int punti = Integer.parseInt(br.readLine().split(": ")[1]);
                    obiettivi.add(new ObiettivoRisorse(descrizione, punti, tipiRisorse, quantita));
                }
                // Salta la linea vuota tra gli obiettivi
                br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return obiettivi;
    }
}
