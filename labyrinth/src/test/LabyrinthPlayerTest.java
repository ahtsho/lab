package test;

import game.Game;
import game.Level;
import infrastructure.Cell;
import infrastructure.Labyrinth;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import view.Console;
import creatures.Player;

public class LabyrinthPlayerTest {
	public static void main(String[] args) {
		CopyOnWriteArrayList<Cell> cs = new CopyOnWriteArrayList<Cell>();
		Cell a = new Cell(true, true, true, true, "a");
		Cell b = new Cell(true, false, true, true, "b");
		Cell c = new Cell(true, true, true, true, "c");
		Cell d = new Cell(true, true, true, true, "d");
		Cell e = new Cell(false, true, true, false, "e");
		Cell f = new Cell(true, true, false, false, "f");
		Cell g = new Cell(true, true, true, true, "g");
		Cell h = new Cell(true, true, true, true, "h");
		Cell i = new Cell(true, true, true, true, "i");
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
