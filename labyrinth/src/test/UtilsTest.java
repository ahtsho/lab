package test;

import java.util.ArrayList;

import utils.Utils;

public class UtilsTest {

	public static void main(String[] args) {
		//rndNTest();
		addInverseTest();
		
	}
	private static void addInverseTest(){
		ArrayList nums = new ArrayList();
		nums.add("a");
		nums.add("b");
		nums.add("c");
		nums.add("d");
		nums.add("e");
		nums.add("f");
		Utils.addInverse(nums);
		System.out.println(nums.toString());
	}
	private static void rndNTest() {
		for (int i = 0; i < 100; i++){
			System.out.println(Utils.generateRandomNumber(4));
		}
	}

	
}
