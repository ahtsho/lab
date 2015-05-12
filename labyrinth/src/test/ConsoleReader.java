package test;

import java.io.Console;

public class ConsoleReader {

	public static void main(String[] args) {
		Console cons;
		while (true) {
			if ((cons = System.console()) != null && cons.readLine() != null) {

				System.out.println(cons.readLine());
			}
		}
	}
}
