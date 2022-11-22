package evolution_simulation;

public class Tile{
    private int[] cords;
    private Object obj;

    // Constructor
    public Tile(int[] arr, Object o) {
        this.setCoords(arr);
        this.setObj(o);
    }

    // Getters
    public int[] getCoords() {
    	return cords;
    }
    
    public Object getObj() {
    	return obj;
    }
    
    public char getType(){
        switch (((Object)obj).getClass().getSimpleName()) {
        	case "Rabbit":
        		return 'R';
        	case "Animal":
                return 'A';
            case "Ground":
            	if (((Ground)(obj)).hasGrass()) {
            		return 'G';
            	}
            	return ' ';
            default:
                return 'N';
        }
    }
    
    // Setters
    public void setCoords(int[] arr) {
    	cords = arr;
    }
    
    public void setObj(Object o) {
    	obj = o;
    }
    
    public void print() {
    	System.out.println(this);
    }
    
    public String toString() {
    	return "[TILE]\n"
    		 + "Type: " + this.getType() + "\n"
    		 + "Coords: " + cords[1] + ", " + cords[0] + "\n"
    		 + "------";
    }
}