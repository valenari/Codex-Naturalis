package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Modello_giocatore.AreaDiGioco;
import Modello_giocatore.Giocatore;
import cardsModel.MazzoCarte;

public class InizioGioco {
	public static void main(String[] args) {
        
/*		CREIAMO I MAZZI GIA' DA ORA (?)
 * 		//Creazione dei diversi mazzi
    	MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa");
        MazzoCarte mazzoOro = new MazzoCarte("Oro");

        //Caricamento delle carte dai file nei mazzi
        mazzoIniziale.caricaCarteDaFile("src/fileCarte/CarteIniziali.txt");
        mazzoRisorsa.caricaCarteDaFile("src/fileCarte/CarteRisorsa.txt");
        mazzoOro.caricaCarteDaFile("src/fileCarte/CarteOro.txt");

        //Mescola i mazzi
        mazzoIniziale.mescolaMazzo();
        mazzoRisorsa.mescolaMazzo();
        mazzoOro.mescolaMazzo();
*/
		
		// Creazione dei giocatori
        List<String> nomiGiocatori = new ArrayList<>();
        
        //List<Giocatore> giocatori = new ArrayList<>();
        
        int g;
        Scanner sc=new Scanner(System.in);
        do{
        	System.out.println("Inserisci il numero di giocatori (almeno 2 e massimo 4)");
            g=sc.nextInt();
            if(g<2 || g>4)System.out.println("ERRORE: il numero di giocatori non è valido");
        }while(g<2 || g>4);
        for(int i=0; i<g; i++) {
        	System.out.println("Inserisci il nome del Giocatore "+(i+1)+":");
        	String n=sc.next();
        	nomiGiocatori.add(n);
        }
        // Creazione dell'area di gioco
        AreaDiGioco area1 = new AreaDiGioco();
	}
}
