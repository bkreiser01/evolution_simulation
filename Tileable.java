package evolution_simulation;

public class Tileable {
	private Tile currentTile;
	
	public Tileable() {
		this.setTile(null);
	}

	public Tile getTile() {
    	return currentTile;
    }
	
	public void setTile(Tile t) {
		currentTile = t;
	}
}
