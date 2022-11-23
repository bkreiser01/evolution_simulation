package evolution_simulation;

public class Animal extends Tileable {
	// Attributes (these dont change between generations
	private boolean dead; 			// either dead or alive
	private boolean prego;			// prego or no prego
	private boolean herbavore;		// eats only veggies
	private char type;				// type of animal
	private int hunger; 			// max 100, min 0 - starts at 100
	private int reproductive_urge; 	// max 100, min 0 - starts at 0
	
	// Genetics
	private int m_factor; 			// metabolism factor 1-5
	private int prego_term;         // length of pregnancy
	private int gender; 			// 0 for male, 1 for female
	
	// Constructors
    public Animal(Map w) {
    	super(w);
        this.setType('A');
        this.setHunger(100);
        this.setGender((int)(Math.random()*(2)));
        if (this.getGender() != 0) {
        	this.setPTerm(10);
        }
        this.setMFactor((int)(Math.random()*(5))+1);
        this.setReproductiveUrge(0);
        this.setPrego(false);
        this.setDeath(false);
        this.setHerbavore(true);
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
    
    public int getPTerm() {
    	return prego_term;
    } 
    
    public boolean isDead() {
    	return dead;
    }
    
    public boolean isPrego() {
    	return prego;
    }
    
    public boolean isHerbavore() {
    	return herbavore;
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
    
    public void setPTerm(int i) {
    	prego_term = i;
    }
    
    private void setPrego(boolean b) {
    	prego = b;
    }
    
    private void setDeath(boolean b) {
    	dead = b;
    }
    
    public void setHerbavore(boolean b) {
    	herbavore = b;
    }
    
    // Methods
	private int moveD(int d) {
    	int[] c = this.getTile().getCoords();
    	Tile nextTile;
    	
    	switch (d) {
    		case 0: // north
    			nextTile = this.getMap().getTile(c[0]-1, c[1]);
    			break;
    		case 1: // north-east
    			nextTile = this.getMap().getTile(c[0]-1, c[1]+1);
    			break;
    		case 2: // east
    			nextTile = this.getMap().getTile(c[0], c[1]+1);
    			break;
    		case 3: // south-east
    			nextTile = this.getMap().getTile(c[0]+1, c[1]+1);
    			break;
    		case 4:  // south
    			nextTile = this.getMap().getTile(c[0]+1, c[1]);
    			break;
    		case 5: // south-west
    			nextTile = this.getMap().getTile(c[0]+1, c[1]-1);
    			break;
    		case 6: // west
    			nextTile = this.getMap().getTile(c[0], c[1]-1);
    			break;
    		case 7: // north-west
    			nextTile = this.getMap().getTile(c[0]-1, c[1]-1);
    			break;
    		default:
    			nextTile = null;
    			break;
    	}
    	
    	
    	if (nextTile == null) {
    		return -1;
    	}
    	
    	
    	if (nextTile.getType() != "Ground " && nextTile.getType() != this.getTile().getType()) {
    		if (this.isHerbavore() && nextTile.getType() == "Grass") {
    			hunger += ((Grass)(nextTile.getObj())).eat();
    			return 0;
    		}
    		this.setTile(this.getMap().swapTiles(this.getTile(), nextTile));
    	} else if (nextTile.getType() == "Animal" && nextTile.getType() == this.getTile().getType()) {
    		Animal potential_mate = (Animal)(nextTile.getObj());
    		
    		if (potential_mate.getGender() != this.getGender()
    				&& potential_mate.getReproductiveUrge() > potential_mate.getHunger()
    				&& reproductive_urge > hunger) {
    			if (gender != 0) {
    				prego = true;
    			} else {
    				potential_mate.setPrego(true);
    			}
    			reproductive_urge = 0;
    			potential_mate.setReproductiveUrge(0);
    		}
    	}
    	
    	this.setTile(this.getMap().swapTiles(this.getTile(), nextTile));
		hunger -= m_factor;
		return 0;
    	
    	
    }
	
	private void birth() {
		Tile[] s_tiles = this.getMap().surroundingTiles(this.getTile().getCoords(), 1);
		
		for (Tile t : s_tiles) {
			if (t.getType() == "Grass") {
				t.setObj(new Rabbit(this.getMap()));
				break;
			}
		}
    }
	
    public void kill() {
    	this.setDeath(true);
    }
    
    public void update() {
    	int rand_d = 0;
    	int moved = -1;
    	
    	while (moved == -1) {
    		rand_d = (int)(Math.random()*(8));
    		moved = this.moveD(rand_d);
    	}

    	if (hunger <= 0) {
    		this.kill();
    	}
    	
    	if (!this.isPrego()) {
        	reproductive_urge += (int)(Math.random()*(5));
    	}
    	
    	if (this.isPrego()) {
    		if (this.getPTerm() == 0) {
    			this.birth();
    			this.setPrego(false);
    			this.setPTerm(10);
    		} else {
    			this.setPTerm(this.getPTerm()-1);
    		}
    	}
    }
    
    public String toString() {
    	return "[Animal]\n"
    		 + "Type: " + type + '\n'
    		 + "Gender: " + gender + '\n'
    		 + "Hunger: " + hunger + '\n'
    		 + "M_factor: " + m_factor + '\n'
    		 + "Reproductive Urge: " + reproductive_urge + '\n'
    		 + "isPrego: " + prego + '\n'
    		 + "isDead: " + dead + '\n'
    		 + "------";
    }
}
