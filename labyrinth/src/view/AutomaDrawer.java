package view;

import game.Levels;

public class AutomaDrawer implements Runnable {
	public volatile boolean stop = false; 
	private Console c;
	
	public AutomaDrawer(Console aConsole){
		c = aConsole;
	}
	
	@Override
	public void run() {
		while (!stop) {
			if (!Levels.animators.isEmpty()) {
				if (Levels.animators.get(0).moved) {
					c.draw();
					System.out.println("thread " + this.hashCode());

					Levels.animators.get(0).moved = false;
				}
				// for(int a = 0; a < Levels.animators.size(); a++){
				// if(Levels.animators.get(a).moved){
				// console.draw();
				// Levels.animators.get(a).moved = false;
				// }
				// }
			}
		}

	}

}
