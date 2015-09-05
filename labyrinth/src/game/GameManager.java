package game;

import infrastructure.Labyrinth;

import java.util.Scanner;

import creatures.Player;
import view.AutomaDrawer;
import view.Console;

public class GameManager {
	public volatile static boolean running = true;
	
	public static void main(String[] args) {
		
		Console.printWelcomeMsg();
		Console.printGameInstructions();
		Console.printGameStartInstructions();

		Player player = null;
		
		int level = 1;
		
		String read;
		Scanner scanIn = new Scanner(System.in);
		Game game = new Game(false, false, false);
		
			
		while (!Levels.isLast(level)) {
			running = true;
			Labyrinth lab = null;
			try {
				lab = Levels.genLabyrinth(level+2);
				player = new Player("F", lab.getEntrance(), 3);
				lab.setPlayer(player);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			Console console = new Console(lab);
			
			AutomaDrawer drawer = new AutomaDrawer(console,player);
			Thread creatureDrawer = new Thread(drawer);
			creatureDrawer.start();
			
			if(Levels.levelChanged){
				Levels.levelChanged = false;
				Console.printLevel(level);
				console.draw();
				console.printMoveMsg();
			}
			
			while (true) {
				
				System.out.println();
				read = scanIn.nextLine();
				
				if (game.isOn() && player.getLife() > 0) {
					if(player.getLife() == 0){
						Console.printGameOver();
						return;
					}					
					if ((read.toLowerCase().startsWith("l"))){
						System.out.println("Player has Life="+player.getLife());
					}
					if ((read.toLowerCase().startsWith("w")
							| read.toLowerCase().startsWith("z")
							| read.toLowerCase().startsWith("s") | read
							.toLowerCase().startsWith("a"))) {
						try {
							if (lab.move(player,Console.adaptDirection((read.charAt(0))))) {
								console.draw();
								console.printMoveMsg();
							} else {
								console.draw();
								console.printLevelFinishedMsg(level);
								level = Levels.next(level);
								Levels.levelChanged = true;
								
								if(creatureDrawer!=null){
									creatureDrawer.interrupt();
									drawer.stop = true;
									Levels.animators.clear();
								}
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
