package utils;

import java.util.ArrayList;

public class Utils {

	/**
	 * Generates a random number between 0 and max-1
	 * @param max
	 * @return rand
	 */
	public static int generateRandomNumber(int max) {
		return (int) (Math.random() * (max));
	}
	

	public static void addInverse(ArrayList array) {
		for(int i = array.size()-2; i>0; i--){
			array.add(array.get(i));
		}
	}
}
