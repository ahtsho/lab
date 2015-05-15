package core;

import java.util.ArrayList;

public class LabyrinthGenerator {
	private static Labyrinth genLabyrinth;
	
	public static Labyrinth generateFull(int n) {
		ArrayList<Cell> labs = new ArrayList<Cell>();
		for (int i = 0; i < n*n; i++) {
			labs.add(new Cell(true, true, true, true,  i%n+""));
		}
		genLabyrinth = new Labyrinth(labs, labs.get(0), labs.get(n*n-1));
		return genLabyrinth;
	}
	
	public static void createTunnel(Labyrinth labyrinth){
		Cell cell = chooseEntrance();
		boolean out = false;
		while(out){
			out = breakWall(cell, chooseDirenction());
		}
	}

	private static boolean breakWall(Cell cell, Object chooseDirenction) {
		// TODO Auto-generated method stub
		return false;
	}

	private static Object chooseDirenction() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Cell chooseEntrance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
