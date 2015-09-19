package test;

import infrastructure.Cell;
import infrastructure.Labyrinth;

import java.util.ArrayList;

import creatures.Player;
import view.Console;

public class LabyrinthTest {

	static Labyrinth l;
	public static void main(String[] args) {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		Cell a = new Cell(false, false, false, false, "a");
		Cell b = new Cell(true, true, true, true, "b");
		Cell c = new Cell(true, true, true, true, "c");
		Cell d = new Cell(true, true, true, true, "d");
		Cell e = new Cell(true, true, true, true, "e");
		Cell f = new Cell(true, true, true, true, "f");
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
		Player p = new Player("A", 1);
		l = new Labyrinth(cs, b, g);
		l.setExit(a);
		l.setPlayer(p);
		testExitCellWall();
//		testGetLabyrinthWall(l);
	}
	
	public static void testExitCellWall(){
		Console c = new Console(l);
		c.draw();
		
		System.out.println(l.getExitCellWall());
	}

	public static void testGetLabyrinthWall(Labyrinth l) {
		System.out.println("-BEGIN------------getLabyrinthWall Test-------------");
		ArrayList<Cell> walls = l.getLabyrinthWall();
		if (walls.size() == l.getDimension() * 4 - 4) {
			System.out.println("Correct number of cells = " + (l.getDimension() * 4 - 4));
		} else {
			System.out.println("Wrong number of cells");
		}

		System.out.println("EXPECTED: {a,b,c,d,f,g,h,i}");
		System.out.print("FOUND   : {");
		for (int i = 0; i < walls.size() - 1; i++) {
			System.out.print(walls.get(i).getName() + ",");
		}
		System.out.println(walls.get(walls.size() - 1).getName() + "}");
		System.out.println("-END------------getLabyrinthWall Test-------------");
	}
}
