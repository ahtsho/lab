package core;

import java.util.ArrayList;

import utils.Utils;

public class Animator implements Runnable {
	private Player p;
	private Labyrinth l;
	private int sleepTime;
	private ArrayList<Cell> path = new ArrayList<Cell>();
	public boolean moved = false;
	public Animator(Player p, Labyrinth l,ArrayList<Cell> aPath, int time) {
		this.p = p;
		this.l = l;
		this.sleepTime = time;
		path=aPath;
	}

	
	@Override
	public void run() {
		Cell destination = null;
		while (true) {
			int i = 0;
			try {
				for (; i < path.size();i++){
					destination = path.get(i);
					moved=l.moveTo(p,destination);
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		// this.setPosition(lab.getNext(position));
	}
}
