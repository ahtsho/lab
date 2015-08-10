package core;

import java.util.Scanner;

import view.Console;

public class GameManager {

	public static void main(String[] args) {

		Console.printWelcomeMsg();
		Console.printGameInstructions();
		Console.printGameStartInstructions();

		Player p = new Player(1);
		p.name = "F";
		
		int level = 1;
		
		// console.draw();
		String read;
		Scanner scanIn = new Scanner(System.in);
		Game game = new Game(false, false, false);

		while (!Levels.isLast(level)) {
			Labyrinth lab = null;
			try {
				lab = Levels.genLabyrinth(level+2, p);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			Console console = new Console(lab);
			
			if(Levels.levelChanged){
				Levels.levelChanged = false;
				Console.printLevel(level);
				console.draw();
				console.printMoveMsg();
			}
			
			while (true) {
				System.out.println();
				read = scanIn.nextLine();
				
				if (game.isOn()) {
					if(p.getLife() == 0){
						System.out.println("GAME OVER");
						return;
					}
					if ((read.toLowerCase().startsWith("l"))){
						System.out.println("Player has Life="+p.getLife()+" damage="+p.getDamage());
					}
					if ((read.toLowerCase().startsWith("n")
							| read.toLowerCase().startsWith("s")
							| read.toLowerCase().startsWith("w") | read
							.toLowerCase().startsWith("e"))) {
						try {
							if (lab.move(p,Console.adaptDirection((read.charAt(0))))) {
								console.draw();
								console.printMoveMsg();
							} else {
								console.draw();
								console.printLevelFinishedMsg(level);
								level = Levels.next(level);
								Levels.levelChanged = true;
								break;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (read.toLowerCase().startsWith("q")) {
						game.end();
						console.printGameTerminatedByUserMsg();
						break;
					} else {
						Console.printGameInstructions();
					}
				} else if (read.toLowerCase().startsWith("y") && !game.isOn() ) {
					game.start();
					Levels.levelChanged = false;
					Console.printLevel(level);
					console.draw();
					console.printMoveMsg();
				} else {
					Console.printGameStartInstructions();
				}
			}
		}
		Console.printGameWonMsg();
		
	}

}
