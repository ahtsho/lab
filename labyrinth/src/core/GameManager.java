package core;

import java.util.Scanner;

import view.Console;

public class GameManager {

	public static void main(String[] args) {

		Console.printWelcomeMsg();
		Console.printGameInstructions();
		Console.printGameStartInstructions();

		Player p = new Player();
		p.name = "F";

		int level = 2;
		Labyrinth lab = Levels.getLabyrinth(level, p);
		Console console = new Console(lab);
		// console.draw();
		String read;
		Scanner scanIn = new Scanner(System.in);
		Game game = new Game(false, false);

		while (true) {
			System.out.println();
			read = scanIn.nextLine();

			if(game.isOn()){
				if ((read.toLowerCase().startsWith("n")
								| read.toLowerCase().startsWith("s")
								| read.toLowerCase().startsWith("w") | read
								.toLowerCase().startsWith("e"))) {
					if (lab.move(p, Console.adaptDirection((read.charAt(0))))) {
						console.draw();
					} else {
						console.draw();
						System.out.println();
						System.out.println("You won!");
						break;
					}
				} else if (read.toLowerCase().startsWith("q")) {
					game.end();
					System.out.println("Bye Bye!!! Thanks for playing :) ");
					break;
				} else {
					Console.printGameInstructions();
				}
			} else if(read.toLowerCase().startsWith("y") && !game.isOn()){
				game.start();
				System.out.println("Game started...");
				console.draw();
				System.out.println();
				System.out.println("Move:");
			}else {
				Console.printGameStartInstructions();
			}
		}
	}

}
