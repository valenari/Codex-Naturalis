package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaIniziale;

public class AreaDiGioco {
    private Carta[][] griglia;
    private int size = 22;
    private int offset;

    public AreaDiGioco(CartaIniziale cartaIniziale) {
        this.griglia = new Carta[size][size];
        this.offset = size / 2;
        griglia[offset][offset] = cartaIniziale;
    }

    public void giocaCarta(Carta carta, int x, int y) {
        griglia[offset + x][offset + y] = carta;
    }

    public void visualizzaGriglia() {
        char[][] grigliaVisualizzazione = new char[size * 7][size * 28];
        for (int i = 0; i < size * 7; i++) {
            for (int j = 0; j < size * 28; j++) {
                grigliaVisualizzazione[i][j] = ' ';
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (griglia[i][j] != null) {
                    String[] cartaRighe = griglia[i][j].toString().split("\n");
                    for (int r = 0; r < cartaRighe.length; r++) {
                        for (int c = 0; c < cartaRighe[r].length(); c++) {
                            grigliaVisualizzazione[i * 7 + r][j * 28 + c] = cartaRighe[r].charAt(c);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < size * 7; i++) {
            for (int j = 0; j < size * 28; j++) {
                System.out.print(grigliaVisualizzazione[i][j]);
            }
            System.out.println();
        }
    }
}
