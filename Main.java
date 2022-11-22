package evolution_simulation;

public class Main {
	public static void main(String args[]) {
        Map world = new Map(50,50);
        Animal[] animals = new Animal[100];
        Ground[] plants = new Ground[50];

        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Animal();
        }

        for (int i = 0; i < plants.length; i++) {
        	plants[i] = new Ground(20);
        }

        world.populate(animals);
        world.plant(plants);
        
        System.out.println("START");
        System.out.println(world);
        
        world.update(100);
        
        System.out.println(world);
        System.out.println("END");
    }
}
