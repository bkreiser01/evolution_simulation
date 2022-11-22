package evolution_simulation;

public class Main {
	private static final int map_x = 50;		//Map Height
	private static final int map_y = 50;		//Map Width
	
	private static final int animal_c = 100; 	//Animal Count
	
	private static final int plants_c = 200; 	//Plants Count
	private static final int plants_g = 10;		//Plants Growth Period
	
	
	public static void main(String args[]) {
        Map world = new Map(map_x,map_y);
        Animal[] animals = new Animal[animal_c];
        Ground[] plants = new Ground[plants_c];

        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Animal();
        }

        for (int i = 0; i < plants.length; i++) {
        	plants[i] = new Ground(plants_g);
        }

        world.populate(animals);
        world.plant(plants);
        
        System.out.println("START");
        System.out.println(world);
        
        world.update(1000);
        
        System.out.println(world);
        System.out.println("END");
    }
}
