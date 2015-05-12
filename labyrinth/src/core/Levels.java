package core;

import java.util.ArrayList;

public class Levels {

	public static int MAX_LEVEL = 2;
	public static boolean levelChanged;
	
	public static Labyrinth getLabyrinth(int level, Player p) {
		if(level==1){
			Cell first = new Cell(true, true, false, false, 'A');
			Cell c2 = new Cell(true, false, false, true, 'B');
			Cell c3 = new Cell(true, false, true, true, 'C');
			Cell last = new Cell(false, false, true, true, 'D');
//			Player p = new Player();
//			p.name = "F";
			p.position = first;

			ArrayList<Cell> labs = new ArrayList<Cell>();
			labs.add(first);
			labs.add(c2);
			labs.add(c3);
			labs.add(last);

			return new Labyrinth(labs, first, last, p);
		} 
		if(level==2){
			Cell c1 = new Cell(true, false, true, false, 'B');
			Cell c2 = new Cell(true, true, false, false, 'C');
			Cell c3 = new Cell(true, false, false, true, 'C');
			Cell entrance = new Cell(false, false, false, true, 'A');
			Cell c5 = new Cell(true, true, true, false, 'C');
			Cell c6 = new Cell(false, false, false, true, 'C');
			Cell c7 = new Cell(false, true, true, false, 'C');
			Cell c8 = new Cell(true, true, false, true, 'C');
			Cell exit = new Cell(false, true, true, false, 'D');
//			Player p = new Player();
//			p.name = "F";
			p.position = entrance;

			ArrayList<Cell> labs = new ArrayList<Cell>();
			labs.add(c1);
			labs.add(c2);
			labs.add(c3);
			labs.add(entrance);
			labs.add(c5);
			labs.add(c6);
			labs.add(c7);
			labs.add(c8);
			labs.add(exit);

			return new Labyrinth(labs, entrance, exit, p);
		}
		
		return null;
	}

	public static boolean isLast(int level) {
		if(level>MAX_LEVEL) return true;
		return false;
	}

	public static int next(int currentLevel) {
		return (currentLevel + 1);
	}
	 
}
