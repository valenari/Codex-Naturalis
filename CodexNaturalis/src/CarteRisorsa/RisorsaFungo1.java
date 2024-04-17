package CarteRisorsa;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RisorsaFungo1 extends JPanel {

	private static final long serialVersionUID = 1L;

	public RisorsaFungo1() {
		
		setLayout(new FlowLayout(FlowLayout.RIGHT));

		
		JLabel lblNewLabel = new JLabel();
		//lblNewLabel.setBounds(10, 10, 240, 360);
		
		lblNewLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CarteRisorsa/Scansioni Carte Risorsa/Carta Risorsa Fungo Retro.png")));

	}
}
