package test;

import infrastructure.Cell;
import infrastructure.Labyrinth;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Utils;
import view.Console;
import creatures.Animator;
import creatures.Guard;
import creatures.Player;

public class LifeManagerTest {

	public static void main(String[] args){
		guardVSplayer();
	}
	
	public static void guardVSplayer(){
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

	

		Guard guard1 = new Guard("G", a, 1,1.0f);
//		ArrayList<Player> guards = new ArrayList<Player>();
//		guards.add(guard1);
//		
//		ArrayList<Cell> path = new ArrayList<Cell>();
//		
//		path.add(a);
//		path.add(b);
//		path.add(e);
//		path.add(h);
//		path.add(i);
//		Utils.addInverse(path);
//		
//		Animator anima = new Animator(guard1, l, path, 1000);
//		new Thread(anima).start();
		
//		Guard guard2 = new Guard("R", g, 1, 1.0f);
//		guards.add(guard2);
//		l.setGuards(guards);
//		ArrayList<Cell> path2 = new ArrayList<Cell>();
//		/*
//		 * abc
//		 * def
//		 * ghi
//		 */
//		path2.add(g);
//		path2.add(d);
//		path2.add(a);
//		Utils.addInverse(path2);
//		
		
		
		String read;
		Scanner scanIn = new Scanner(System.in);
		Player p = new Player(3);
		p.setName("F");
		p.setPosition(l.getEntrance());
		l.setPlayer(p);
		Console console = new Console(l);
		console.draw();
		while (true) {
			read = scanIn.nextLine();
			if ((read.toLowerCase().startsWith("n")
					| read.toLowerCase().startsWith("s")
					| read.toLowerCase().startsWith("w") | read.toLowerCase()
					.startsWith("e"))) {
				try {
					l.move(p, Console.adaptDirection((read.charAt(0))));
					console.draw();

				} catch (Exception err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				}
			}
		}

	}

	
}
