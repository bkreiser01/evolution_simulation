package evolution_simulation;

public class Tileable {
	private Tile currentTile;
	private Map world;
	
	
	public Tileable(Map w) {
		world = w;
		this.setTile(null);
	}

	public Tile getTile() {
    	return currentTile;
    }
	
	public Map getMap() {
		return world;
	}
	
	public void setTile(Tile t) {
		currentTile = t;
	}
	
	public Map setMap(Map m) {
		return world;
	}
}
