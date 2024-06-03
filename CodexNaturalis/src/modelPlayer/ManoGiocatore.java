package modelPlayer;

import cardsModel.Carta;

import java.util.ArrayList;
import java.util.List;

public class ManoGiocatore {
    private List<Carta> carteInMano;

    public ManoGiocatore() {
        this.carteInMano = new ArrayList<>();
    }

    public void aggiungiCarta(Carta carta) {
    	//System.out.println("Aggiungendo carta: \n" + carta); Riga per debug
        if (carteInMano.size() < 3) {
            carteInMano.add(carta);
        } else {
            System.out.println("Non puoi aggiungere più di tre carte nella mano.");
        }
    }

    public Carta rimuoviCarta(int indice) {
        if (indice >= 0 && indice < carteInMano.size()) {
            return carteInMano.remove(indice);
        }
        return null;
    }

    public void stampaMano() {
        List<String[]> carteStringhe = new ArrayList<>();
        int maxLines = 0;

        for (int i = 0; i < carteInMano.size(); i++) {
            String[] righe = aggiungiNumeroAStringa(carteInMano.get(i).toString().split("\n"), i + 1);
            carteStringhe.add(righe);
            maxLines = Math.max(maxLines, righe.length);
        }

        for (int line = 0; line < maxLines; line++) {
            for (int i = 0; i < carteStringhe.size(); i++) {
                String[] righe = carteStringhe.get(i);
                if (line < righe.length) {
                    System.out.print(righe[line]);
                } else {
                    System.out.print(" ".repeat(28));
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    private String[] aggiungiNumeroAStringa(String[] righe, int numero) {
        String numeroStringa = "{" + numero + "}";
        int centro = righe[righe.length - 1].length() / 2;
        int start = centro - numeroStringa.length() / 2;
        StringBuilder sb = new StringBuilder(righe[righe.length - 1]);
        sb.replace(start, start + numeroStringa.length(), numeroStringa);
        righe[righe.length - 1] = sb.toString();
        return righe;
    }

    public Carta getCarta(int indice) {
        return carteInMano.get(indice);
    }

    public int getNumeroCarte() {
        return carteInMano.size();
    }
}
