package test;

import modelObiettivi.ObiettivoRisorse;
import modelPlayer.Contatori;
import java.util.Arrays;
import java.util.List;

public class TestObiettivoRisorse {

    public static void main(String[] args) {
        // Creazione di un obiettivo di risorsa
        List<String> tipiRisorse = Arrays.asList("Fungo", "Fungo", "Fungo");
        List<Integer> quantita = Arrays.asList(1, 1, 1);
        ObiettivoRisorse obiettivo = new ObiettivoRisorse("Raccogli tre funghi", 2, tipiRisorse, quantita);

        // Verifica della corretta inizializzazione
        System.out.println("Obiettivo Risorsa:");
        System.out.println("Descrizione: " + obiettivo.getDescrizione());
        System.out.println("Punti: " + obiettivo.getPunti());
        System.out.println("Tipi Risorse: " + tipiRisorse);
        System.out.println("Quantità: " + quantita);

        // Creazione e aggiornamento dei contatori
        Contatori contatori = new Contatori();
        // Aggiunta di risorse ai contatori per la verifica
        incrementaContatore(contatori, "Fungo", 6); // Incrementa di 6 funghi

        // Mostra i contatori aggiornati
        contatori.mostraContatori();

        // Verifica dell'obiettivo con i contatori aggiornati
        int punteggioTotale = verificaObiettivo(obiettivo, contatori);
        System.out.println("Punteggio totale ottenuto: " + punteggioTotale); // dovrebbe essere 4

        // Mostra i contatori finali
        contatori.mostraContatori();
    }

    private static int verificaObiettivo(ObiettivoRisorse obiettivo, Contatori contatori) {
        int punteggioTotale = 0;
        // Creazione di una copia dei contatori per la verifica
        Contatori contatoriTemp = new Contatori();
        incrementaContatore(contatoriTemp, "Fungo", contatori.getContatore("Fungo"));

        while (obiettivo.verificaObiettivo(null, contatoriTemp)) {
            punteggioTotale += obiettivo.getPunti();
            // Decrementa i contatori temporanei per riflettere l'utilizzo delle risorse
            for (int i = 0; i < obiettivo.getTipiRisorse().size(); i++) {
                decrementaContatore(contatoriTemp, obiettivo.getTipiRisorse().get(i), obiettivo.getQuantita().get(i));
            }
        }
        return punteggioTotale;
    }

    private static void incrementaContatore(Contatori contatori, String elemento, int quantita) {
        for (int i = 0; i < quantita; i++) {
            contatori.incrementaContatore(elemento, quantita);
        }
    }

    private static void decrementaContatore(Contatori contatori, String elemento, int quantita) {
        for (int i = 0; i < quantita; i++) {
            contatori.decrementaContatore(elemento);
        }
    }
}
