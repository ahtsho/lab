package core;

import java.util.Scanner;

import view.Console;

public class Game {

	public static void main(String[] args) {
		Console.printWelcomeMsg();
		Console.printGameInstructions();
		
		
		Player p = new Player();
		p.name = "F";
		
		int level = 2;
		Labyrinth lab = Levels.getLabyrinth(level, p);
		Console console = new Console(lab);
//		console.draw();
		String read;
		Scanner scanIn = new Scanner(System.in);

		while (true) {
			System.out.println();
			System.out.println("Move: ");
			read = scanIn.nextLine();
			if (read.startsWith("N") | read.startsWith("S")
					| read.startsWith("W") | read.startsWith("E")) {

				if (lab.move(p, read.charAt(0))) {
					console.draw();
				} else {
					console.draw();
					System.out.println();
					System.out.println("You won!");
					break;
				}
			} else if (read.startsWith("Q")) {
				System.out.println("Bye Bye!!! Thanks for playing :) ");
				break;
			}else if(read.startsWith("R")){
				console.draw();
			} else {
				Console.printGameInstructions();
			}
		}
	}

}
