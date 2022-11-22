package evolution_simulation;

public class Map {
	private int height;
    private int width;
    private int cycle;
    private Tile[][] ground;
    
    private Animal[] animals;
    private Ground[] plants;

    public Map(int h, int w) {
    	cycle = 0;
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
    
    public void plant(Ground[] arr) {
    	for (Ground g : arr) {
            for (int i = 0; i < width * height; i++) {
                int rand_x = (int)(Math.random()*(height));
                int rand_y = (int)(Math.random()*(width));
                
                if (ground[rand_x][rand_y].getType() == ' ') {
                    ground[rand_x][rand_y].setObj(g);
                    g.setTile(ground[rand_x][rand_y]);
                    break;
                }
            }
        }
    	
    	plants = arr;
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
        	
        	for (Ground p : plants) {
        		p.update();
        	}
        	
        	// Clean up the dead
        	for (Animal a : animals) {
        		if (a.isDead()) {
        			a.getTile().setObj(new Ground());
        		}
        	}
        	cycle++;
        }
    }
    
    public String observe() {
    	StringBuilder r_str = new StringBuilder();
    	int animals_alive = 0;
    	int grass_count = 0;
    	
    	
    	for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (ground[i][j].getType()) {
                	case 'A':
                		animals_alive++;
                		break;
                	case 'G':
                		grass_count++;
                		break;
                }
            	
            }
        }
    	r_str.append("Cycle: " + cycle + '\n');
    	r_str.append("Animals Alive: " + animals_alive + '\n');
    	r_str.append("Grass Count: " + grass_count);
    	
    	return r_str.toString();
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
        
        r_str.append(this.observe());

        return r_str.toString();
    }
}
