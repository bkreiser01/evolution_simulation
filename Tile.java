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
    
    public String getType(){
    	// i do not know why i need a switch case here, but it doesn't work otherwise so..
        switch (((Object)obj).getClass().getSimpleName()) {
        	case "Rabbit":
        		return "Rabbit";
        	case "Animal":
                return "Animal";
            case "Ground":
            	return "Ground";
            case "Grass":
            	return "Grass";
            default:
                return "None";
        }
    }
    
    // Setters
    public void setCoords(int[] arr) {
    	cords = arr;
    }
    
    public void setObj(Object o) {
    	obj = o;
    }
    
    public String toString() {
    	return "[TILE]\n"
    		 + "Type: " + this.getType() + "\n"
    		 + "Coords: " + cords[1] + ", " + cords[0] + "\n"
    		 + "------\n";
    }
}