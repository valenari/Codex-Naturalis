package Modello_giocatore;

import cardsModel.Carta;

public class AreaDiGioco {
	
	Carta [][] base = new Carta[22][22];
	public AreaDiGioco() {
		
//mancano i metodi per mancanza di angoli		
		
		for(int i=0; i<22; i++) {
			for(int j=0; j<22; j++) {
				if(j==0 && i==0) {
					base[i][j].setCaselle(Caselleproibite.EMPTY);
				}else if((base[i][j-1].getCaselle()==Caselleproibite.NULL) && (base[i-1][j].getCaselle()==Caselleproibite.NULL)) {
					base[i][j].setCaselle(Caselleproibite.EMPTY);
				}else {
					base[i][j].setCaselle(Caselleproibite.NULL);
				}
			}
		}
	}

}
