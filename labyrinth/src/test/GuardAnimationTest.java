package test;

import java.util.ArrayList;

import view.Console;
import core.Animator;
import core.Cell;
import core.Guard;
import core.Labyrinth;
import core.Player;

public class GuardAnimationTest {
	public static void main(String[] args) {
		// moveGuardNS();
//		moveGuardEW();
//		moveGuardEWWithWall();
		moveGuardZigZag();
	}
	
	private static void moveGuardZigZag() {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		// n s w e
		Cell a = new Cell(true, false, true, false, "a");
		Cell b = new Cell(true, false, false, false, "b");
		Cell c = new Cell(true, false, false, true, "c");

		Cell d = new Cell(false, false, true, false, "d");
		Cell e = new Cell(false, false, false, true, "e");
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

		Guard guard = new Guard("G", a, 1);
		l.setGuard(guard);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(a);
		path.add(b);
		path.add(e);
		path.add(h);
		path.add(i);
		path.add(h);
		path.add(e);
		path.add(b);
		Animator anima = new Animator(guard, l, path, 1000);
		new Thread(anima).start();
		Console console = new Console(l);
		console.draw();
		while (true) {
			if (anima.moved) {
				console.draw();
				anima.moved = false;
			}
		}

	}


	private static void moveGuardEWWithWall() {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		// n s w e
		Cell a = new Cell(true, false, true, false, "a");
		Cell b = new Cell(true, false, false, false, "b");
		Cell c = new Cell(true, false, false, true, "c");

		Cell d = new Cell(false, false, true, false, "d");
		Cell e = new Cell(false, false, false, true, "e");
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

		Guard guard = new Guard("G", d, 1);
		l.setGuard(guard);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(d);
		path.add(e);
		path.add(f);
		path.add(e);
		Animator anima = new Animator(guard, l, path, 1000);
		new Thread(anima).start();
		Console console = new Console(l);
		console.draw();
		while (true) {
			if (anima.moved) {
				console.draw();
				anima.moved = false;
			}
		}

	}

	
	private static void moveGuardEW() {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		// n s w e
		Cell a = new Cell(true, false, true, false, "a");
		Cell b = new Cell(true, false, false, false, "b");
		Cell c = new Cell(true, false, false, true, "c");

		Cell d = new Cell(false, false, true, false, "d");
		Cell e = new Cell(false, false, false, false, "e");
		Cell f = new Cell(false, false, false, true, "f");

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

		Guard guard = new Guard("G", d, 1);
		l.setGuard(guard);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(d);
		path.add(e);
		path.add(f);
		path.add(e);
		Animator anima = new Animator(guard, l, path, 1000);
		new Thread(anima).start();
		Console console = new Console(l);
		console.draw();
		while (true) {
			if (anima.moved) {
				console.draw();
				anima.moved = false;
			}
		}

	}

	private static void moveGuardNS() {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		// n s w e
		Cell a = new Cell(true, false, true, false, "a");
		Cell b = new Cell(true, false, false, false, "b");
		Cell c = new Cell(true, false, false, true, "c");

		Cell d = new Cell(false, false, true, false, "d");
		Cell e = new Cell(false, false, false, false, "e");
		Cell f = new Cell(false, false, false, true, "f");

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

		Guard guard = new Guard("G", a, 1);
		l.setGuard(guard);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(a);
		path.add(d);
		path.add(g);
		path.add(d);
		Animator anima = new Animator(guard, l, path, 1000);
		new Thread(anima).start();
		Console console = new Console(l);
		console.draw();
		while (true) {
			if (anima.moved) {
				console.draw();
				anima.moved = false;
			}
		}
	}
}
