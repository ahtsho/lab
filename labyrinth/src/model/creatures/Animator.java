package model.creatures;

import java.util.ArrayList;

import model.infrastructure.Cell;
import model.infrastructure.Labyrinth;

/**
 * Given a creature, a labyrinth a path and a sleep time in millseconds, the
 * animator, will move the creature while it's alive, on the path, back and
 * forth
 * 
 * @author at
 */
public class Animator implements Runnable {
	public boolean stop = false;
	private Creature creature;
	private Labyrinth l;
	private int sleepTime;
	private ArrayList<Cell> path = new ArrayList<Cell>();
	public volatile boolean moved = false;

	public Animator(Creature p, Labyrinth l, ArrayList<Cell> aPath,
			int sleepMilli) {
		this.creature = p;
		this.l = l;
		this.sleepTime = sleepMilli;
		path = aPath;
	}

	public Creature getCreature() {
		return creature;
	}

	@Override
	public void run() {
		Cell destination = null;
		while (creature.getLife() > 0 && !stop) {
			for (int i = 0; i < path.size(); i++) {
				destination = path.get(i);
				moved = l.moveTo(creature, destination);
//				System.out.println("Th:"+this.hashCode());
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}
	}
}
