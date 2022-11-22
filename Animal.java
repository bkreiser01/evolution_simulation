package evolution_simulation;

public class Animal extends Tileable {
	private char type;
	
	private int hunger; // max 100, min 0
	private int m_factor; // metabolism factor 1-5
	private boolean dead;
	
	
	// Constructors
    public Animal() {
    	super();
        this.setType('A');
        hunger = 100;
        dead = false;
        m_factor = (int)(Math.random()*(5))+1;
    }

    // Getters
    public char getType(){
        return type;
    }

    
    public boolean isDead() {
    	return dead;
    }

    // Setters
    public void setType(char c) {
        type = c;
    }
    
    // Methods
	private int moveD(Map world, int d) {
    	int[] c = this.getTile().getCoords();
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
    	
    	
    	if (nextTile == null) {
    		return -1;
    	}
    	if (nextTile.getType() == 'G') {
    		//System.out.println("YUMMY FOOD");
    		hunger += ((Ground)(nextTile.getObj())).eat();
    		this.setTile(world.swapTiles(this.getTile(), nextTile));
    		return 0;
    	}
    	
    	this.setTile(world.swapTiles(this.getTile(), nextTile));
		hunger -= m_factor;
		return 0;
    	
    	
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
    
    public String toString() {
    	return "[Animal]\n"
    		 + "Type: " + type + '\n'
    		 + "Hunger: " + hunger + '\n'
    		 + "M_factor: " + m_factor + '\n'
    		 + "------";
    }
}
