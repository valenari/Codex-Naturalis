package modelTavolo;

public class Tavolo {
    private TracciatoSegnapunti tracciatoSegnapunti;
    private AreaDiPesca areaDiPesca;
    private ObiettiviComuni obiettiviComuni;

    // Costruttore della classe Tavolo
    public Tavolo(TracciatoSegnapunti tracciatoSegnapunti, AreaDiPesca areaDiPesca, ObiettiviComuni obiettiviComuni) {
        this.tracciatoSegnapunti = tracciatoSegnapunti;
        this.areaDiPesca = areaDiPesca;
        this.obiettiviComuni = obiettiviComuni;
    }

    // Getter per il tracciato segnapunti
    public TracciatoSegnapunti getTracciatoSegnapunti() {
        return tracciatoSegnapunti;
    }

    // Getter per l'area di pesca
    public AreaDiPesca getAreaDiPesca() {
        return areaDiPesca;
    }

    // Getter per gli obiettivi comuni
    public ObiettiviComuni getObiettiviComuni() {
        return obiettiviComuni;
    }

    // Metodo per aggiornare il punteggio di un giocatore
    public void aggiornaSegnapunti(String giocatore, int punti) {
        tracciatoSegnapunti.aggiornaPunti(giocatore, punti);
    }
}


