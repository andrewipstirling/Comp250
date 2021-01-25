package assignment3;

import RuntimeTester.*;
import java.lang.Math;
import java.util.ArrayList;


public class SpeedTester {

	public static void main(String[] args) {
		Visualizer.launch(SpeedTester.class);
	}
	@benchmark(name = "getVetSchedule() Test")
	public static long shelterTest(long input) {
		ArrayList<Integer> ages = new ArrayList<Integer>();
		ArrayList<Integer> days = new ArrayList<Integer>();
		ArrayList<Dog> dogs = new ArrayList<Dog>();
		Dog R = new Dog("Rex", 8, 100, 5, 50.0);
		DogShelter s = new DogShelter(R);
		
		int range = (200 - 1)+ 1;
		for(long i = 0; i < input; i++) {
			int rando = (int)((Math.random() * range) + 1);
			int randDays = (int)((Math.random() * range) + 1);
			if(!(ages.contains(rando)) && !(days.contains(randDays))) {
				ages.add(rando);
				days.add(randDays);
				s.shelter(new Dog("Name "+i, rando, randDays, 13, 55.0));
				
			}
			
		}
		long startTime = System.nanoTime();       //This indicates when the timer on the method starts
	    s.getVetSchedule();
	    long endTime = System.nanoTime();         //This indicates where the timer on the method ends
	    return endTime - startTime;
	}
}
