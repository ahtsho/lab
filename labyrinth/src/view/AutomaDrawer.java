package view;

import creatures.Player;
import game.Levels;

public class AutomaDrawer implements Runnable {
	public volatile boolean stop = false;
	private Player player;
	private Console console;

	public AutomaDrawer(Console aConsole, Player p) {
		console = aConsole;
		player = p;
	}

	@Override
	public void run() {
		while (!stop) {
			if (!Levels.animators.isEmpty()) {
				if (player.getLife() > 0) {
					if (Levels.animators.get(0).moved) {
						console.draw();
						// System.out.println("thread " + this.hashCode());

						Levels.animators.get(0).moved = false;
					} else {
						
					}
					// for(int a = 0; a < Levels.animators.size(); a++){
					// if(Levels.animators.get(a).moved){
					// console.draw();
					// Levels.animators.get(a).moved = false;
					// }
					// }
				} else {
					Levels.animators.clear();
					stop = true;
					Console.printGameOver();
					return;
				}
			}
		}

	}

}
