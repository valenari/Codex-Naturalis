package modelTavolo;

import cardsModel.CartaOro;
import cardsModel.CartaRisorsa;
import cardsModel.Carta;

import java.util.ArrayList;
import java.util.List;

public class AreaDiPesca {
    private List<CartaRisorsa> carteRisorsaVisibili;
    private List<CartaOro> carteOroVisibili;
    private List<CartaRisorsa> mazzoRisorsaCoperto;
    private List<CartaOro> mazzoOroCoperto;

    public AreaDiPesca(List<Carta> carteRisorsa, List<Carta> carteOro) {
        this.mazzoRisorsaCoperto = new ArrayList<>();
        this.mazzoOroCoperto = new ArrayList<>();
        for (Carta carta : carteRisorsa) {
            this.mazzoRisorsaCoperto.add((CartaRisorsa) carta);
        }
        for (Carta carta : carteOro) {
            this.mazzoOroCoperto.add((CartaOro) carta);
        }
        this.carteRisorsaVisibili = new ArrayList<>();
        this.carteOroVisibili = new ArrayList<>();

        pescaCarteIniziali();
    }

    private void pescaCarteIniziali() {
        pescaCartaDalMazzoRisorsa();
        pescaCartaDalMazzoRisorsa();
        pescaCartaDalMazzoOro();
        pescaCartaDalMazzoOro();
    }

    private void pescaCartaDalMazzoRisorsa() {
        if (!mazzoRisorsaCoperto.isEmpty()) {
            carteRisorsaVisibili.add(mazzoRisorsaCoperto.remove(0));
        }
    }

    private void pescaCartaDalMazzoOro() {
        if (!mazzoOroCoperto.isEmpty()) {
            carteOroVisibili.add(mazzoOroCoperto.remove(0));
        }
    }

    public Carta pescaCarta(int indice) {
        Carta cartaPescata = null;
        if (indice == 0) {
            cartaPescata = mazzoRisorsaCoperto.remove(0);
            pescaCartaDalMazzoRisorsa();
        } else if (indice == 1 || indice == 2) {
            cartaPescata = carteRisorsaVisibili.remove(indice - 1);
            pescaCartaDalMazzoRisorsa();
        } else if (indice == 3) {
            cartaPescata = mazzoOroCoperto.remove(0);
            pescaCartaDalMazzoOro();
        } else if (indice == 4 || indice == 5) {
            cartaPescata = carteOroVisibili.remove(indice - 4);
            pescaCartaDalMazzoOro();
        }
        return cartaPescata;
    }

    public void mostraAreaDiPesca() {
        System.out.println("Carte Risorsa Visibili:");
        stampaCarteOrizzontalmente(carteRisorsaVisibili, mazzoRisorsaCoperto, true);

        System.out.println("\nCarte Oro Visibili:");
        stampaCarteOrizzontalmente(carteOroVisibili, mazzoOroCoperto, false);
    }

    private void stampaCarteOrizzontalmente(List<? extends Carta> carte, List<? extends Carta> mazzoCoperto, boolean isRisorsa) {
        List<String[]> carteStringhe = new ArrayList<>();
        int maxLines = 0;

        if (!mazzoCoperto.isEmpty()) {
            carteStringhe.add(aggiungiNumeroAStringa(mazzoCoperto.get(0).toStringRetro().split("\n"), isRisorsa ? 1 : 4));
            maxLines = Math.max(maxLines, carteStringhe.get(0).length);
        }

        for (int i = 0; i < carte.size(); i++) {
            String[] righe = aggiungiNumeroAStringa(carte.get(i).toString().split("\n"), isRisorsa ? i + 2 : i + 5);
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
}
