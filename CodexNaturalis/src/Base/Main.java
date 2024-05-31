package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Modello_giocatore.AreaDiGioco;
import cardsModel.MazzoCarte;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("\n\t- 🍄 - ☘️ - CODEX NATURALIS - 🐺 - 🦋 -");
		System.out.println("\t                 📜  𓆰  🧪\n\n");
		
		System.out.println("Premere qualsiasi tasto per giocare, o premere 0 per uscire");
		Scanner sc = new Scanner(System.in);
		String risposta = sc.nextLine();
		if (risposta.equalsIgnoreCase("0"))System.exit(0);
		System.out.println("pappa");
		sc.close();
		inizializzaMazzi();
		
		
		/*Turno turno = new Turno(giocatori);
		  
		  while (!turno.isPartitaTerminata()) {
            Giocatore giocatoreCorrente = turno.getGiocatoreCorrente();
            System.out.println("È il turno di: " + giocatoreCorrente.getNome());
            
             turno.controllaPunteggio();
            if (!turno.isPartitaTerminata()) {
                turno.prossimoTurno();
               }	
		 */
	}
	
	public static void inizializzaMazzi() {
		//Creazione dei diversi mazzi
		MazzoCarte mazzoIniziale = new MazzoCarte("Iniziale","src/fileCarte/CarteIniziali.txt");
        MazzoCarte mazzoRisorsa = new MazzoCarte("Risorsa","src/fileCarte/CarteRisorsa.txt");
        MazzoCarte mazzoOro = new MazzoCarte("Oro","src/fileCarte/CarteOro.txt");
        //Qui mazzo obbiettivi
	}
	
	public static void creazioneGiocatori() {
		// Creazione dei Giocatori
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
        sc.close();
	}
	public static void creazioneAreeGioco() {
		// Creazione dell'Area di gioco
        AreaDiGioco area1 = new AreaDiGioco();
	}

}
