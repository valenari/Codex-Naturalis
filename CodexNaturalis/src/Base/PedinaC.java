package Base;

public enum PedinaC {
	ROSSO(0, 0),
    BLU(1, 1),
    VERDE(2, 2),
    GIALLO(3, 3),
    NERO(4, 4);

    private int x; 
    private int y; 
    
    // Constructor
    private PedinaC(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    //Setters
    public void setX(int x){
    	this.x = x;
    }
    
    public void setY(int y){
    	this.y = y;
    }
}
