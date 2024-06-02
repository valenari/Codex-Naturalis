package cardsModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReaderUtil {

    public static List<CartaIniziale> leggiCarteIniziali(String filename) {
        List<CartaIniziale> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String fronte = null;
            List<String> centrale = null;
            String retro = null;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Id: ")) {
                    if (idCarta != -1) {
                        CartaIniziale carta = new CartaIniziale(idCarta, fronte, centrale, retro);
                        carte.add(carta);
                    }
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                } else if (linea.startsWith("Fronte: ")) {
                    fronte = linea.split(": ")[1];
                } else if (linea.startsWith("Centrale: ")) {
                    centrale = List.of(linea.split(": ")[1].split(" - "));
                } else if (linea.startsWith("Retro: ")) {
                    retro = linea.split(": ")[1];
                }
            }
            if (idCarta != -1) {
                CartaIniziale carta = new CartaIniziale(idCarta, fronte, centrale, retro);
                carte.add(carta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carte;
    }

    public static List<CartaOro> leggiCarteOro(String filename) {
        List<CartaOro> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String tipoRegno = null;
            String angoliFronte = null;
            int punti = 0;
            String criterioPunti = null;
            List<String> risorseRichieste = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("N° ORO: ")) {
                    if (idCarta != -1) {
                        CartaOro carta = new CartaOro(idCarta, angoliFronte, tipoRegno, punti, criterioPunti, risorseRichieste);
                        carte.add(carta);
                    }
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                    risorseRichieste = new ArrayList<>();
                } else if (linea.startsWith("Regno: ")) {
                    tipoRegno = linea.split(": ")[1];
                } else if (linea.startsWith("Angoli Fronte: ")) {
                    angoliFronte = linea.split(": ")[1];
                } else if (linea.startsWith("Risorse Richieste: ")) {
                    String[] risorse = linea.split(": ")[1].split(" - ");
                    Collections.addAll(risorseRichieste, risorse);
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                } else if (linea.startsWith("Criterio Punti: ")) {
                    criterioPunti = linea.split(": ")[1];
                }
            }
            if (idCarta != -1) {
                CartaOro carta = new CartaOro(idCarta, angoliFronte, tipoRegno, punti, criterioPunti, risorseRichieste);
                carte.add(carta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carte;
    }

    public static List<CartaRisorsa> leggiCarteRisorsa(String filename) {
        List<CartaRisorsa> carte = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linea;
            int idCarta = -1;
            String tipoRegno = null;
            String angoliFronte = null;
            int punti = 0;

            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("N° Risorsa: ")) {
                    if (idCarta != -1) {
                        CartaRisorsa carta = new CartaRisorsa(idCarta, angoliFronte, tipoRegno, punti);
                        carte.add(carta);
                    }
                    idCarta = Integer.parseInt(linea.split(": ")[1]);
                } else if (linea.startsWith("Regno: ")) {
                    tipoRegno = linea.split(": ")[1];
                } else if (linea.startsWith("Angoli Fronte: ")) {
                    angoliFronte = linea.split(": ")[1];
                } else if (linea.startsWith("Punti: ")) {
                    punti = Integer.parseInt(linea.split(": ")[1]);
                }
            }
            if (idCarta != -1) {
                CartaRisorsa carta = new CartaRisorsa(idCarta, angoliFronte, tipoRegno, punti);
                carte.add(carta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carte;
    }
}
