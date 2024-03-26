package CarteRisorsa;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class RisorsaFungo1 extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public RisorsaFungo1() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 10, 240, 160);
		lblNewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CarteRisorsa/Scansioni Carte Risorsa/Carta Risorsa Fungo Retro.png")));
		add(lblNewLabel);

	}
}
