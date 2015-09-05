package test;

import infrastructure.Cell;
import infrastructure.Labyrinth;

import java.util.ArrayList;

import utils.Utils;
import view.AutomaDrawer;
import view.Console;
import creatures.Animator;
import creatures.Creature;
import creatures.Guard;
import creatures.Player;

public class AutomaDrawerTest {
	public static void main(String[] args) {
		twoGuards();
	}
	
	private static void twoGuards() {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		// n s w e
		Cell a = new Cell(true, false, true, false, "a");
		Cell b = new Cell(true, false, false, false, "b");
		Cell c = new Cell(true, false, false, true, "c");

		Cell d = new Cell(false, false, true, true, "d");
		Cell e = new Cell(false, false, true, true, "e");
		Cell f = new Cell(false, false, true, true, "f");

		Cell g = new Cell(false, true, true, false, "g");
		Cell h = new Cell(false, true, false, false, "h");
		Cell i = new Cell(false, true, false, true, "i");
		cs.add(a);
		cs.add(b);
		cs.add(c);
		cs.add(d);
		cs.add(e);
		cs.add(f);
		cs.add(g);
		cs.add(h);
		cs.add(i);
		Labyrinth l = new Labyrinth(cs, b, g);

		Player p = new Player();
		p.setName("F");
		p.setPosition(c);
		l.setPlayer(p);

		Guard guard1 = new Guard("G", a, 1,1.0f);
		ArrayList<Creature> guards = new ArrayList<Creature>();
		guards.add(guard1);
		
		ArrayList<Cell> path = new ArrayList<Cell>();
		
		path.add(a);
		path.add(b);
		path.add(e);
		path.add(h);
		path.add(i);
		Utils.addInverse(path);
		
		Animator anima = new Animator(guard1, l, path, 1000);
		new Thread(anima).start();
		
		Guard guard2 = new Guard("R", g, 1, 1.0f);
		guards.add(guard2);
		l.setCreatures(guards);
		ArrayList<Cell> path2 = new ArrayList<Cell>();
		
		path2.add(g);
		path2.add(d);
		path2.add(a);
		Utils.addInverse(path2);
		
		Animator anima2 = new Animator(guard2, l, path2, 1500);
		new Thread(anima2).start();

		Console console = new Console(l);
		
		
		Player player = new Player("P",l.getEntrance(),3);
		
		AutomaDrawer drawer = new AutomaDrawer(console,player);
		Thread creatureDrawer = new Thread(drawer);
		creatureDrawer.start();
		
		console.draw();
//		while (true) {
//			
//		}

	}

	
	
}
