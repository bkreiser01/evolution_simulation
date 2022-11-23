package evolution_simulation;

public class Grass extends Tileable{
	private int growth_time;
	private int nutrition;
	private boolean hasGrass;
	
	public Grass(Map m, int i, int n) {
		super(m);
		growth_time = i;
		nutrition = n;
		hasGrass = false;
	}
	
	public int eat() {
		return nutrition;
	}
	
	public void update() {
		
	}
}
