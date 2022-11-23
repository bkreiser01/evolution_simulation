package evolution_simulation;

public class Grass extends Tileable{
	private int growth_time;
	private int growth;
	private int nutrition;
	private boolean hasGrass;
	
	public Grass(Map m, int i, int n) {
		super(m);
		growth_time = i;
		growth = i;
		nutrition = n;
		hasGrass = true;
	}
	
	public int eat() {
		if (hasGrass) {
			hasGrass = false;
			growth = 0;
			return nutrition;
		}
		return 0;
	}
	
	public void update() {
		if (growth >= growth_time) {
			hasGrass = true;
		} else {
			growth++;
		}
	}
}
