package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Modello_giocatore.Giocatore;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.util.List;

public class SchermataDiGioco extends JFrame {

    private JPanel contentPane;
    private JLabel lblInfoGiocatore;
    private JLabel lblPunteggio;
    private JLabel lblTurno;
    private List<Giocatore> giocatori;

    public SchermataDiGioco(List<Giocatore> giocatori) {
        setTitle("Codex Naturalis - Schermata di Gioco");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 1, 0, 0));

        // Visualizza le informazioni del giocatore corrente
        lblInfoGiocatore = new JLabel();
        contentPane.add(lblInfoGiocatore);

        // Visualizza il punteggio del giocatore corrente
        lblPunteggio = new JLabel();
        contentPane.add(lblPunteggio);

        // Visualizza il turno di gioco corrente
        lblTurno = new JLabel();
        contentPane.add(lblTurno);
        
        // Aggiorna le informazioni dei giocatori
        aggiornaInformazioniGiocatori(giocatori);
    }

    // Metodo per aggiornare le informazioni dei giocatori nella schermata di gioco
    public void aggiornaInformazioniGiocatori(List<Giocatore> giocatori) {
        // Visualizza le informazioni del giocatore corrente
        Giocatore giocatoreCorrente = giocatori.get(0); // Assumiamo che il primo giocatore sia il giocatore corrente
        lblInfoGiocatore.setText("Giocatore Corrente: " + giocatoreCorrente.getNome());

        // Visualizza il punteggio del giocatore corrente
        lblPunteggio.setText("Punteggio: " + giocatoreCorrente.getPunti());

        // Visualizza il turno di gioco corrente
        // Aggiungi qui la logica per visualizzare il turno di gioco corrente, se necessario
    }

    // Metodo per mostrare un messaggio di fine partita
    public void mostraMessaggioFinePartita(String messaggio) {
        JOptionPane.showMessageDialog(this, messaggio, "Fine Partita", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
