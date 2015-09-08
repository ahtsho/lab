package view;

import creatures.Animator;
import creatures.Creature;
import creatures.Player;
import game.Level;

public class AutomaDrawer implements Runnable {
	public volatile static boolean stop;
	private Player player;
	private Console console;
	private Animator animator;

	public AutomaDrawer(Console aConsole, Player p, Animator anima) {
		console = aConsole;
		player = p;
		animator = anima;
		stop = false;
	}

	@Override
	public void run() {
		while (!stop) {
			if (player.getLife() > 0) {
				if (animator != null && animator.moved) {
					console.draw();
//					System.out.println("Th:"+animator.getCreature().getName());
					animator.moved = false;
				}
			} else {
				stop = true;
				Console.printGameOver();
				return;
			}
		}
		animator.stop = true;
	}

}
