package game;

import infrastructure.Labyrinth;

import java.util.ArrayList;
import java.util.Scanner;

import creatures.Animator;
import creatures.Player;
import view.AutomaDrawer;
import view.Console;

public class GameManager {
//	public volatile static boolean running = true;
	
	public static void main(String[] args) {
		
		Console.printWelcomeMsg();
		Console.printGameInstructions();
		Console.printGameStartInstructions();

		Player player = new Player("F", 3);
		
		String read;
		Scanner scanIn = new Scanner(System.in);
		Game game = new Game(false, false, false);
		
			
		while (!Level.isLast()) {
//			running = true;
			Labyrinth lab = null;
			try {
				lab = Level.genLabyrinth();
				player.setPosition(lab.getEntrance());
				lab.setPlayer(player);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			Console console = new Console(lab);
			ArrayList<Thread> startedDrawers = new ArrayList<Thread>(); 
			for(Animator anima:Level.animators){
				AutomaDrawer drawer = new AutomaDrawer(console,player, anima);
				Thread creatureDrawer = new Thread(drawer);
				creatureDrawer.start();
				startedDrawers.add(creatureDrawer);
			}
			
			if(Level.levelChanged){
				Level.levelChanged = false;
				Console.printLevel(Level.currentLevel-2);
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
								System.out.println(lab.getPlayer().getPosition().getTools().size());
								console.printMoveMsg();
							} else {
								console.draw();
								console.printLevelFinishedMsg(Level.currentLevel-2);
								Level.next();
								Level.levelChanged = true;
								
								AutomaDrawer.stop = true;
								if(!startedDrawers.isEmpty()){
									for(Thread t: startedDrawers){
										t.interrupt();
									}
								}
								Level.animators.clear();
								
								break;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else if (read.toLowerCase().startsWith("q")) {
//						game.end();
						console.printGameTerminatedByUserMsg();
						break;
					} else {
						Console.printGameInstructions();
					}
				} else if (read.toLowerCase().startsWith("y") && !game.isOn() ) {
					game.start();
					Level.levelChanged = false;
					Console.printLevel(Level.currentLevel-2);
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
