package util;

import cardsModel.Carta;
import cardsModel.CartaIniziale;

import java.util.ArrayList;
import java.util.List;

public class StampaCarta {

    public static void stampaOrizzontalmente(CartaIniziale carta) {
        String[] fronte = carta.toString().split("\n");
        String[] retro = carta.toStringRetro().split("\n");
        int maxLines = Math.max(fronte.length, retro.length);

        System.out.println("Fronte:\t\t\t\tRetro:");
        for (int i = 0; i < maxLines; i++) {
            if (i < fronte.length) {
                System.out.print(fronte[i]);
            } else {
                System.out.print(" ".repeat(28));
            }
            System.out.print("\t");
            if (i < retro.length) {
                System.out.println(retro[i]);
            } else {
                System.out.println();
            }
        }
    }

    public static void stampaCarteOrizzontalmente(List<? extends Carta> carte) {
        List<String[]> carteStringhe = new ArrayList<>();
        int maxLines = 0;

        for (Carta carta : carte) {
            String[] righe = carta.toString().split("\n");
            carteStringhe.add(righe);
            maxLines = Math.max(maxLines, righe.length);
        }

        for (int line = 0; line < maxLines; line++) {
            for (String[] righe : carteStringhe) {
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

    public static void stampaAreaDiPesca(List<? extends Carta> carteVisibili, Carta cartaCoperta, boolean isRisorsa) {
        List<String[]> carteStringhe = new ArrayList<>();
        int maxLines = 0;

        if (cartaCoperta != null) {
            carteStringhe.add(aggiungiNumeroAStringa(cartaCoperta.toStringRetro().split("\n"), isRisorsa ? 1 : 4));
            maxLines = Math.max(maxLines, carteStringhe.get(0).length);
        }

        for (int i = 0; i < carteVisibili.size(); i++) {
            String[] righe = aggiungiNumeroAStringa(carteVisibili.get(i).toString().split("\n"), isRisorsa ? i + 2 : i + 5);
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

    private static String[] aggiungiNumeroAStringa(String[] righe, int numero) {
        String numeroStringa = "{" + numero + "}";
        int centro = righe[righe.length - 1].length() / 2;
        int start = centro - numeroStringa.length() / 2;
        StringBuilder sb = new StringBuilder(righe[righe.length - 1]);
        sb.replace(start, start + numeroStringa.length(), numeroStringa);
        righe[righe.length - 1] = sb.toString();
        return righe;
    }
}
