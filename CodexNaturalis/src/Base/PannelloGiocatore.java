package Base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PannelloGiocatore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PannelloGiocatore frame = new PannelloGiocatore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public PannelloGiocatore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(100, 100, 800, 500));
		
		//RisorsaFungo1 Prova = new RisorsaFungo1();	
		//Prova.setBounds(10, 10, 100, 360);
		//contentPane.add(Prova);
		
		setContentPane(contentPane);
	}

}
