package Modello_giocatore;

import java.util.ArrayList;
import java.util.List;

import cardsModel.Carta;
import cardsModel.CartaIniziale;

public class AreaDiGioco {
    private Carta[][] griglia;
    private int dimensione;

    public AreaDiGioco(CartaIniziale cartaIniziale) {
        this.dimensione = 3;
        this.griglia = new Carta[dimensione][dimensione];
        griglia[dimensione / 2][dimensione / 2] = cartaIniziale;
    }

    public void posizionaCarta(Carta carta, int posizione) {
        int count = 1;
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (griglia[i][j] == null && isDiagonallyAdjacentAndValid(i, j)) {
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
                } else if (isDiagonallyAdjacentAndValid(i, j)) {
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
                            if (r == 3 && c >= 13 && c < 13 + Integer.toString(count).length()) {
                                grigliaVisualizzazione[i * 7 + r][j * 28 + c] = Integer.toString(count).charAt(c - 13);
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

    private boolean isDiagonallyAdjacentAndValid(int i, int j) {
        int[][] directions = {
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };
        for (int[] direction : directions) {
            int newRow = i + direction[0];
            int newCol = j + direction[1];
            if (newRow >= 0 && newRow < dimensione && newCol >= 0 && newCol < dimensione && griglia[newRow][newCol] != null) {
                if (!isAngoloNascosto(griglia[newRow][newCol], direction)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAngoloNascosto(Carta carta, int[] direction) {
        String angolo = "";
        if (direction[0] == -1 && direction[1] == -1) {
            angolo = carta.getAngolo(3);
        } else if (direction[0] == -1 && direction[1] == 1) {
            angolo = carta.getAngolo(2);
        } else if (direction[0] == 1 && direction[1] == -1) {
            angolo = carta.getAngolo(1);
        } else if (direction[0] == 1 && direction[1] == 1) {
            angolo = carta.getAngolo(0);
        }
        return angolo.equals("Nascosto");
    }
    
    public List<Carta> getCarteVisibili() {
        List<Carta> carteVisibili = new ArrayList<>();
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                if (griglia[i][j] != null) {
                    carteVisibili.add(griglia[i][j]);
                }
            }
        }
        return carteVisibili;
    }

	public Carta[][] getGriglia() {
		return griglia;
	}
}
