package evolution_simulation;

public class Map {
	private int height;
    private int width;
    private Tile[][] ground;
    
    private Animal[] animals;

    public Map(int h, int w) {
        height = h;
        width = w;
        ground = new Tile[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	int[] tmp = {i, j};
                ground[i][j] = new Tile(tmp, new Ground());
            }
        }
    }
    
    // Getters 
    public Tile getTile(int x, int y) {
    	try {
    		return ground[x][y];
    	} catch (Exception E) {
    		return null;
    	}
    }
    
    public Animal[] getAnimals() {
    	return animals;
    }
    
    
    // Methods
    public void populate(Animal[] arr) {
        for (Animal a : arr) {
            for (int i = 0; i < width * height; i++) {
                int rand_x = (int)(Math.random()*(height));
                int rand_y = (int)(Math.random()*(width));
                
                if (ground[rand_x][rand_y].getType() == ' ') {
                    ground[rand_x][rand_y].setObj(a);
                    a.setTile(ground[rand_x][rand_y]);
                    break;
                }
            }
        }
        
        animals = arr;
    }
    
    public Tile swapTiles(Tile a, Tile b) {
    	int[] a_c = a.getCoords();
    	int[] b_c = b.getCoords();
   
    	ground[a_c[0]][a_c[1]].setCoords(b_c);
    	ground[b_c[0]][b_c[1]].setCoords(a_c);
    	
    	Tile tmp = ground[a_c[0]][a_c[1]];
    	ground[a_c[0]][a_c[1]] = ground[b_c[0]][b_c[1]];
    	ground[b_c[0]][b_c[1]] = tmp;
    

    	return ground[b_c[0]][b_c[1]];
    }
    
    public void update(int count) {
    	for (int i = 0; i < count; i++) {
    		// Update animals
        	for (Animal a : animals) {
        		if (!a.isDead()) {
        			a.update(this);
        		}
        	}
        	
        	// Clean up the dead
        	for (Animal a : animals) {
        		if (a.isDead()) {
        			a.getTile().setObj(new Ground());
        		}
        	}
        }
    }
    public String toString() {
        StringBuilder r_str = new StringBuilder();
        
        if (height < 10 && width < 10) {
        	r_str.append("  ");
            for (int i = 0; i < width; i++) {
                r_str.append(i);
            }
            
            r_str.append("\n |");
            for (int i = 0; i < width; i++) {
                r_str.append('=');
            }
            r_str.append("|\n");

            for (int i = 0; i < height; i++) {
                r_str.append(i + "|");
                for (int j = 0; j < width; j++) {
                	r_str.append(ground[i][j].getType());
                }
                r_str.append("|\n");
            }

            r_str.append(" |");
            for (int j = 0; j < width; j++) {
                r_str.append('=');
            }
            r_str.append("|\n");
        } else {
            
            r_str.append("|");
            for (int i = 0; i < width; i++) {
                r_str.append('=');
            }
            r_str.append("|\n");

            for (int i = 0; i < height; i++) {
                r_str.append("|");
                for (int j = 0; j < width; j++) {
                	r_str.append(ground[i][j].getType());
                }
                r_str.append("|\n");
            }

            r_str.append("|");
            for (int j = 0; j < width; j++) {
                r_str.append('=');
            }
            r_str.append("|\n");
        }
        
        int animal_count = 0;
        for (Animal a : animals) {
        	if (!a.isDead()) {
        		animal_count++;
        	}
    	}
        
        
        r_str.append("Alive Animal: " + animal_count + '\n');

        return r_str.toString();
    }
}
