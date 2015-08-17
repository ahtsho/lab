package test;

import java.util.ArrayList;
import java.util.Scanner;

import view.Console;
import core.Animator;
import core.Cell;
import core.Guard;
import core.Labyrinth;
import core.Player;

public class GuardAnimationTest {
	public static void main(String[] args){
		ArrayList<Cell> cs = new ArrayList<Cell>();
		Cell a = new Cell(false, false, false, false, "a");
		Cell b = new Cell(false, false, false, false, "b");
		Cell c = new Cell(false, false, false, false, "c");
		
		Cell d = new Cell(false, false, false, false, "d");
		Cell e = new Cell(false, false, false, false, "e");
		Cell f = new Cell(false, false, false, false, "f");
		
		Cell g = new Cell(false, false, false, false, "g");
		Cell h = new Cell(false, false, false, false, "h");
		Cell i = new Cell(false, false, false, false, "i");
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
		
		String read;
		Scanner scanIn = new Scanner(System.in);
		Player p = new Player();
		p.setName("F");
		p.setPosition(c);
		l.setPlayer(p);
//		
		Guard guard = new Guard("G",d,1);
		l.setGuard(guard);
		ArrayList<Cell> path = new ArrayList<Cell>();
		path.add(g);
		path.add(d);
		path.add(a);
		Animator anima = new Animator(guard, l,path,1000);
		 new Thread(anima).start();
		
		Console console = new Console(l);
		console.draw();
		while (true) {
				console.draw();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
		}

	}
}
