package evolution_simulation;

public class Animal {
	private char type;
	private Tile currentTile;
	
	private int hunger; // max 100, min 0
	private boolean dead;
	
	
	// Constructors
    public Animal() {
        this.setType('A');
        hunger = 100;
        dead = false;
    }

    // Getters
    public char getType(){
        return type;
    }
    
    public Tile getTile() {
    	return currentTile;
    }
    
    public boolean isDead() {
    	return dead;
    }

    // Setters
    public void setType(char c) {
        type = c;
    }
    
    public void setTile(Tile t) {
    	currentTile = t;
    }
    
    public void eat(int i) {
    	if (hunger + i > 100 ) {
    		hunger = 100;
    	} else {
    		hunger += i;
    	}
    }
    
    // Methods
    private int moveD(Map world, int d) {
    	int[] c = currentTile.getCoords();
    	Tile nextTile;
    	
    	switch (d) {
    		case 0: // north
    			nextTile = world.getTile(c[0]-1, c[1]);
    			break;
    		case 1: // north-east
    			nextTile = world.getTile(c[0]-1, c[1]+1);
    			break;
    		case 2: // east
    			nextTile = world.getTile(c[0], c[1]+1);
    			break;
    		case 3: // south-east
    			nextTile = world.getTile(c[0]+1, c[1]+1);
    			break;
    		case 4:  // south
    			nextTile = world.getTile(c[0]+1, c[1]);
    			break;
    		case 5: // south-west
    			nextTile = world.getTile(c[0]+1, c[1]-1);
    			break;
    		case 6: // west
    			nextTile = world.getTile(c[0], c[1]-1);
    			break;
    		case 7: // north-west
    			nextTile = world.getTile(c[0]-1, c[1]-1);
    			break;
    		default:
    			nextTile = null;
    			break;
    	}
    	
    	if (nextTile != null) {
    		currentTile = world.swapTiles(currentTile, nextTile);
    		hunger--;
    		return 0;
    	}
    	return -1;
    }
    
    public void kill(Map world) {
    	dead = true;
    }
    
    public void update(Map world) {
    	int rand_d = 0;
    	int moved = -1;
    	
    	while (moved == -1) {
    		rand_d = (int)(Math.random()*(8));
    		moved = this.moveD(world, rand_d);
    	}

    	if (hunger <= 0) {
    		this.kill(world);
    	}
    }
}
