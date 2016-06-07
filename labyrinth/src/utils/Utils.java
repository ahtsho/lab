package utils;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

	private static Random randomGenerator=null;
	/**
	 * Generates a random number between 0 and max-1
	 * @param max
	 * @return rand
	 */
	
	public static int generateRandomNumber(int max) {
		if(randomGenerator==null){
			randomGenerator = new Random();
		}
		return randomGenerator.nextInt(max);
	}
	

	public static void addInverse(ArrayList array) {
		for(int i = array.size()-2; i>0; i--){
			array.add(array.get(i));
		}
	}
}
