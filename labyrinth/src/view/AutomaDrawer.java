package view;

import model.creatures.Animator;
import model.creatures.Creature;
import model.creatures.Player;
import model.game.Level;

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
