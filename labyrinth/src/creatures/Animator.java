package creatures;

import game.Game;
import infrastructure.Cell;
import infrastructure.Labyrinth;

import java.util.ArrayList;


public class Animator implements Runnable {
	private Creature creature;
	private Labyrinth l;
	private int sleepTime;
	private ArrayList<Cell> path = new ArrayList<Cell>();
	public volatile boolean moved = false;
	private Game game;
	
	public Animator(Creature p, Labyrinth l,ArrayList<Cell> aPath, int sleepMilli) {
		this.creature = p;
		this.l = l;
		this.sleepTime = sleepMilli;
		path=aPath;
	}
	public void setGame(Game g){
		game = g;
	}
	@Override
	public void run() {
		Cell destination = null;
		while (creature.getLife()>0) {		
			try {
				for (int i = 0; i < path.size();i++){
					destination = path.get(i);
					moved=l.moveTo(creature,destination);
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
