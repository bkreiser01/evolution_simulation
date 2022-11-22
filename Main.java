package evolution_simulation;

public class Main {
	public static void main(String args[]) {
        Map world = new Map(50,50);
        Animal[] animals = new Animal[100];

        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Animal();
        }


        world.populate(animals);
        
        System.out.println("START");
        System.out.println(world);
        
        world.update(99);
        
        System.out.println(world);
        System.out.println("END");
    }
}
