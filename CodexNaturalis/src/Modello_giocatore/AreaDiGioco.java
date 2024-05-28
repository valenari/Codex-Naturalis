package Modello_giocatore;

import cardsModel.Carta;
import cardsModel.CartaIniziale;

import java.util.ArrayList;
import java.util.List;

public class AreaDiGioco {
    private Carta[][] griglia;
    private int dimensione;

    public AreaDiGioco(CartaIniziale cartaIniziale) {
        this.dimensione = 3;
        this.griglia = new Carta[dimensione][dimensione];
        griglia[1][1] = cartaIniziale;
    }

    public void posizionaCarta(Carta carta, int posizione) {
        int count = 1;
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (griglia[i][j] == null && ((i + j) % 2 == 0) && (i + j != 0) && (i != dimensione - 1 || j != dimensione - 1)) {
                    if (count == posizione) {
                        griglia[i][j] = carta;
                        espandiGrigliaSeNecessario();
                        return;
                    }
                    count++;
                }
            }
        }
    }

    private void espandiGrigliaSeNecessario() {
        boolean espandi = false;
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (griglia[i][j] != null) {
                    if (i == 0 || i == dimensione - 1 || j == 0 || j == dimensione - 1) {
                        espandi = true;
                        break;
                    }
                }
            }
            if (espandi) break;
        }

        if (espandi) {
            dimensione += 2;
            Carta[][] nuovaGriglia = new Carta[dimensione][dimensione];
            for (int i = 0; i < dimensione - 2; i++) {
                for (int j = 0; j < dimensione - 2; j++) {
                    nuovaGriglia[i + 1][j + 1] = griglia[i][j];
                }
            }
            griglia = nuovaGriglia;
        }
    }

    public void visualizzaGriglia() {
        char[][] grigliaVisualizzazione = new char[dimensione * 7][dimensione * 28];
        for (int i = 0; i < dimensione * 7; i++) {
            for (int j = 0; j < dimensione * 28; j++) {
                grigliaVisualizzazione[i][j] = ' ';
            }
        }

        int count = 1;
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (griglia[i][j] != null) {
                    String[] cartaRighe = griglia[i][j].toString().split("\n");
                    for (int r = 0; r < 7; r++) {
                        for (int c = 0; c < 28; c++) {
                            if (r < cartaRighe.length && c < cartaRighe[r].length()) {
                                grigliaVisualizzazione[i * 7 + r][j * 28 + c] = cartaRighe[r].charAt(c);
                            }
                        }
                    }
                } else if (isDiagonallyAdjacent(i, j)) {
                    String[] cellaVuota = {
                            "----------------------------",
                            "[                          ]",
                            "[                          ]",
                            "[            N             ]",
                            "[                          ]",
                            "[                          ]",
                            "----------------------------"
                    };
                    for (int r = 0; r < 7; r++) {
                        for (int c = 0; c < 28; c++) {
                            if (r == 3 && c == 13) {
                                grigliaVisualizzazione[i * 7 + r][j * 28 + c] = Character.forDigit(count, 10);
                            } else {
                                grigliaVisualizzazione[i * 7 + r][j * 28 + c] = cellaVuota[r].charAt(c);
                            }
                        }
                    }
                    count++;
                }
            }
        }

        for (char[] chars : grigliaVisualizzazione) {
            System.out.println(new String(chars));
        }
    }

    private boolean isDiagonallyAdjacent(int i, int j) {
        int[][] directions = {
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int[] direction : directions) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];
            if (newRow >= 0 && newRow < dimensione && newCol >= 0 && newCol < dimensione && griglia[newRow][newCol] != null) {
                return true;
            }
        }
        return false;
    }
}
