package evolution_simulation;

public class Animal extends Tileable {
	private char type;				// type of animal
	private int hunger; 			// max 100, min 0 - starts at 100
	private int m_factor; 			// metabolism factor 1-5
	private int gender; 			// 0 for male, 1 for female
	private int reproductive_urge; 	// max 100, min 0 - starts at 0
	private boolean dead; 			// either dead or alive
	
	
	// Constructors
    public Animal() {
    	super();
        this.setType('A');
        this.setHunger(100);
        this.setGender((int)(Math.random()*(2)));
        this.setMFactor((int)(Math.random()*(5))+1);
        this.setReproductiveUrge(0);
        dead = false;
    }

    // Getters
    public char getType() {
        return type;
    }

    public int getHunger() {
    	return hunger;
    }
    
    public int getMFactor() {
    	return m_factor;
    }

    public int getGender() {
    	return gender;
    }
    
    public int getReproductiveUrge() {
    	return reproductive_urge;
    }
    
    public boolean isDead() {
    	return dead;
    }
    
    // Setters
    public void setType(char c) {
        type = c;
    }
    
    private void setHunger(int i) {
    	hunger = i;
    }
    
    private void setGender(int i) {
    	gender = i;
    }
    
    private void setMFactor(int i) {
    	m_factor = i;
    }
    
    private void setReproductiveUrge(int i) {
    	reproductive_urge = i;
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
    	
    	if (nextTile.getType() != ' ' && nextTile.getType() != this.getTile().getType()) {
    		if (nextTile.getType() == 'G' ) {
    			hunger += ((Ground)(nextTile.getObj())).eat();
    		}
    		this.setTile(world.swapTiles(this.getTile(), nextTile));
    		return 0;
    	}
    	
    	if (nextTile.getType() != ' ' && nextTile.getType() == this.getTile().getType()) {
    		Animal potential_mate = (Animal)(nextTile.getObj());
    		
    		if (potential_mate.getGender() != this.getGender() && reproductive_urge > hunger) {
    			System.out.println("MATE FOUND :)");
    			reproductive_urge = 0;
    		}
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
    	
    	reproductive_urge += (int)(Math.random()*(5));
    }
    
    public String toString() {
    	return "[Animal]\n"
    		 + "Type: " + type + '\n'
    		 + "Gender: " + gender + '\n'
    		 + "Hunger: " + hunger + '\n'
    		 + "M_factor: " + m_factor + '\n'
    		 + "isDead: " + dead + '\n'
    		 + "------";
    }
}
