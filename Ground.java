package evolution_simulation;

public class Ground {
	char type;
	
	// Constructors
	public Ground(char c) {
		this.setGroundType(c);
	}
	
	// Setters
	public void setGroundType(char c) {
		type = c;
	}
	
	// Getters
	public char getGroundType() {
		return type;
	}
	
	// Methods
}
