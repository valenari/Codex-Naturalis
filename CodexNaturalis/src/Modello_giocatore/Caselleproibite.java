package Modello_giocatore;

public enum Caselleproibite {
    NULL("null"), EMPTY("empty");
    
    private String casella;
    
    private Caselleproibite(String casella) {
        this.casella = casella;
    }

    @Override
    public String toString() {
        return casella;
    }
}
