package utils;

public class Utils {

	/**
	 * Generates a random number between 0 and max-1
	 * @param max
	 * @return rand
	 */
	public static int generateRandomNumber(int max) {
		return (int) (Math.random() * (max));
	}
}
