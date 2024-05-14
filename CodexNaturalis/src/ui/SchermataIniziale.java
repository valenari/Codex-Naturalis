package ui;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SchermataIniziale extends JFrame {

    private JPanel contentPane;
    private boolean partitaIniziata;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SchermataIniziale frame = new SchermataIniziale();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public SchermataIniziale() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Bottone per avviare la partita
        JButton btnAvviaPartita = new JButton("Avvia Partita");
        btnAvviaPartita.addActionListener(e -> avviaPartita());
        btnAvviaPartita.setBounds(320, 200, 150, 50); // Imposta le dimensioni e la posizione del bottone
        contentPane.add(btnAvviaPartita);

        // Inizializza il flag della partita come false
        partitaIniziata = false;
    }

    // Metodo per segnalare se la partita è stata avviata
    public boolean isPartitaIniziata() {
        return partitaIniziata;
    }

    // Metodo per avviare la partita
    public void avviaPartita() {
        partitaIniziata = true;
    }
}
