package evolution_simulation;

public class Main {
	private static final int map_x = 50;			//Map Height
	private static final int map_y = 50;			//Map Width
	
	private static final int animal_c = 100; 			//Animal Count
	
	private static final int plants_c = 500; 		//Plants Count
	private static final int plant_nutrition = 50;
	private static final int plants_g = 10;			//Plants Growth Period
	
	private static final int iterations = 1000000; 	//World iterations
	
	
	public static void main(String args[]) {
        Map world = new Map(map_x,map_y);
        Animal[] animals = new Animal[animal_c];
        Grass[] plants = new Grass[plants_c];
        
        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Rabbit(world);
        }

        for (int i = 0; i < plants.length; i++) {
        	plants[i] = new Grass(world, plants_g, plant_nutrition);
        }

        world.populate(animals);
        world.plant(plants);
        
        //System.out.print(world);
        System.out.println(world.observe());
        world.update(iterations);
        //System.out.print(world);
        System.out.println(world.observe());
        
    }
}
