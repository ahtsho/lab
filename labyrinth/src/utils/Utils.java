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
	
	public static void main(String[] args){
		for (int i = 1; i < 100; i++) {
			System.out.println("i="+i+", i^2="+(int)(Math.pow(i, 2))+", i*log(i)="+(int)(i*Math.log(i)));
		}
		
	}
}
