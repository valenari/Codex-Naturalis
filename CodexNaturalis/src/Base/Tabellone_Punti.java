package Base;

import Base.PedinaC;

public class Tabellone_Punti {
	// Gioctore
		private int GioctoreNumber; 
		//Points
	    private int NumPuntiUnGicotore;  // Numero di punti per un giocatore
	    
		//public enum Pedina {ROSSO, BLU, VERDE, GIALLO, NERO}; //public Pedina GioctorePedina;
		 
	    //--------------------------------------------------------------------------------------------------//
	    public int getGioctorePunti(int numDelleGioctore) {
	        this.GioctoreNumber = numDelleGioctore;
	         this.NumPuntiUnGicotore = Gioctore.getGioctorePunti(numDelleGioctore); // change in feature
	    }
	    
	    // Prendi il numero di giocatore e ritorna il colore di Pedina
	    public PedinaC getGioctorePedina() {
	        switch (GioctoreNumber) {
	            case 1: return PedinaC.BLU;
	            case 2: return PedinaC.ROSSO;
	            case 3: return PedinaC.VERDE;
	            case 4: return PedinaC.GIALLO;
	            default: return PedinaC.NERO;
	        }
	    }

	    // get Possession(X, Y) for Pedina for every player
	    public void setP_X(int NumPuntiUnGicotore) {
	        switch (NumPuntiUnGicotore) {
	            case 0: getGioctorePedina().setX(1); break; //Punto 0
	            case 1: getGioctorePedina().setX(1); break; //Punto 1
	            case 2: getGioctorePedina().setX(1); break; //....
	            case 3: getGioctorePedina().setX(1); break; //Punto 29
	            default: getGioctorePedina().setX(0); break;
	        }
	    }
	    
	    public void setP_Y(int NumPuntiUnGicotore) {
	        switch (NumPuntiUnGicotore) {
	        case 0: getGioctorePedina().setY(1); break; //Punto 0
	        case 1: getGioctorePedina().setY(1); break; //Punto 1
	        case 2: getGioctorePedina().setY(1); break; //....
	        case 3: getGioctorePedina().setY(1); break; //Punto 29
	        default: getGioctorePedina().setY(0); break;
	        }
	    }
}
