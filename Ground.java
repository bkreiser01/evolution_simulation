package evolution_simulation;

public class Ground extends Tileable {
	boolean grass;
	int growth_period;
	int growth_time;
	
	
	// Constructors
	public Ground(Map m,int i) {
		super(m);
		grass = true;
		growth_time = 0;
		growth_period = i;
	}
	
	public Ground(Map m) {
		super(m);
		grass = false;
		growth_time = 0;
		growth_period = 0;
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
	
	public void update() {
		if (!grass && growth_period != 0) { // if the ground has grass
			if (growth_time == growth_period) {
				grass = true;
			} else {
				growth_time++;
			}
		}
	}
}
