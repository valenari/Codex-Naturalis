package modelObiettivi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaricatoreObiettivi {

    public List<Obiettivo> caricaObiettiviDisposizione(String filename) {
        List<Obiettivo> obiettivi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Salta le linee vuote

                if (line.startsWith("Obbiettivo")) {
                    String descrizione = line;
                    line = br.readLine();
                    while (line != null && line.trim().isEmpty()) line = br.readLine(); // Salta linee vuote

                    List<String> sequenza = new ArrayList<>();
                    List<int[]> posizioni = new ArrayList<>();

                    if (line != null && line.startsWith("Sequenza:")) {
                        String[] parti = line.substring(9).split("-");
                        for (String parte : parti) {
                            sequenza.add(parte.trim());
                        }
                    }

                    line = br.readLine();
                    while (line != null && line.trim().isEmpty()) line = br.readLine(); // Salta linee vuote

                    if (line != null && line.startsWith("Posizioni:")) {
                        String[] parti = line.substring(10).split(" ");
                        for (String parte : parti) {
                            parte = parte.replace("[", "").replace("]", "");
                            if (!parte.isEmpty()) {
                                String[] coord = parte.split(",");
                                if (coord.length == 2) {
                                    try {
                                        int[] pos = {Integer.parseInt(coord[0].trim()), Integer.parseInt(coord[1].trim())};
                                        posizioni.add(pos);
                                    } catch (NumberFormatException e) {
                                        System.err.println("Errore nel parsing delle coordinate: " + parte);
                                    }
                                }
                            }
                        }
                    }

                    line = br.readLine();
                    while (line != null && line.trim().isEmpty()) line = br.readLine(); // Salta linee vuote

                    if (line != null) {
                        try {
                            int punti = Integer.parseInt(line.split(":")[1].trim());
                            obiettivi.add(new ObiettivoDisposizione(descrizione, punti, sequenza, posizioni));
                        } catch (NumberFormatException e) {
                            System.err.println("Errore nel parsing dei punti: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obiettivi;
    }

    public List<Obiettivo> caricaObiettiviRisorse(String filename) {
        List<Obiettivo> obiettivi = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Salta le linee vuote

                if (line.startsWith("Obbiettivo")) {
                    String descrizione = line;
                    line = br.readLine();
                    while (line != null && line.trim().isEmpty()) line = br.readLine(); // Salta linee vuote

                    List<String> tipiRisorse = new ArrayList<>();
                    List<Integer> quantita = new ArrayList<>();

                    if (line != null && line.startsWith("Quantità:")) {
                        String[] quantitaParti = line.substring(9).split(" ");
                        for (String q : quantitaParti) {
                            if (!q.trim().isEmpty()) {
                                try {
                                    quantita.add(Integer.parseInt(q.trim()));
                                } catch (NumberFormatException e) {
                                    System.err.println("Errore nel parsing delle quantità: " + q);
                                }
                            }
                        }
                    }

                    line = br.readLine();
                    while (line != null && line.trim().isEmpty()) line = br.readLine(); // Salta linee vuote

                    if (line != null && line.startsWith("Tipo:")) {
                        String[] tipiParti = line.substring(5).split(" ");
                        for (String tipo : tipiParti) {
                            if (!tipo.trim().isEmpty()) {
                                tipiRisorse.add(tipo.trim());
                            }
                        }
                    }

                    line = br.readLine();
                    while (line != null && line.trim().isEmpty()) line = br.readLine(); // Salta linee vuote

                    if (line != null) {
                        try {
                            int punti = Integer.parseInt(line.split(":")[1].trim());
                            obiettivi.add(new ObiettivoRisorse(descrizione, punti, tipiRisorse, quantita));
                        } catch (NumberFormatException e) {
                            System.err.println("Errore nel parsing dei punti: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obiettivi;
    }
}
