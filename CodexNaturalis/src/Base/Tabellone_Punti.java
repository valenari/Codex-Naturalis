package Base;

import Base.PedinaC;

public class Tabellone_Punti {
	// Giocatore
		private int GiocatoreNumber; 
		//Points
	    private int NumPuntiUnGiocatore;  // Numero di punti per un giocatore
	    
		//public enum Pedina {ROSSO, BLU, VERDE, GIALLO, NERO}; //public Pedina GiocatorePedina;
		 
	    //--------------------------------------------------------------------------------------------------//
	    /*public int getGiocatorePunti(int numDelleGiocatore) {
	        this.GiocatoreNumber = numDelleGiocatore;
	        this.NumPuntiUnGiocatore = Giocatore.getGiocatorePunti(numDelleGiocatore); // change in feature
	    }*/
	    
	    // Prendi il numero di giocatore e ritorna il colore di Pedina
	    public PedinaC getGiocatorePedina() {
	        switch (GiocatoreNumber) {
	            case 1: return PedinaC.BLU;
	            case 2: return PedinaC.ROSSO;
	            case 3: return PedinaC.VERDE;
	            case 4: return PedinaC.GIALLO;
	            default: return PedinaC.NERO;
	        }
	    }

	    // get Possession(X, Y) for Pedina for every player
	    public void setP_X(int NumPuntiUnGiocatore) {
	        switch (NumPuntiUnGiocatore) {
	            case 0: getGiocatorePedina().setX(1); break; //Punto 0
	            case 1: getGiocatorePedina().setX(1); break; //Punto 1
	            case 2: getGiocatorePedina().setX(1); break; //....
	            case 3: getGiocatorePedina().setX(1); break; //Punto 29
	            default: getGiocatorePedina().setX(0); break;
	        }
	    }
	    
	    public void setP_Y(int NumPuntiUnGiocatore) {
	        switch (NumPuntiUnGiocatore) {
	        case 0: getGiocatorePedina().setY(1); break; //Punto 0
	        case 1: getGiocatorePedina().setY(1); break; //Punto 1
	        case 2: getGiocatorePedina().setY(1); break; //....
	        case 3: getGiocatorePedina().setY(1); break; //Punto 29
	        default: getGiocatorePedina().setY(0); break;
	        }
	    }
}
