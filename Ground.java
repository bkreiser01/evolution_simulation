package evolution_simulation;

public class Ground extends Tileable {
	boolean grass;
	int growth_time;
	
	
	// Constructors
	public Ground(int i) {
		grass = true;
		growth_time = i;
	}
	
	public Ground() {
		grass = false;
	}
	
	// Getters
	public boolean hasGrass() {
		return grass;
	}
	
	// Methods
	public int eat() {
		if (grass) {
			grass = false;
			return 20;
		}
		return 0;
	}
}
