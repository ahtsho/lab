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

	private static boolean breakWall(Cell cell, char chooseDirenction) {
		
		return false;
	}

	private static char chooseDirenction() {
		// TODO Auto-generated method stub
		return ' ';
	}

	private static Cell chooseEntrance() {
		ArrayList<Cell> borderCells = genLabyrinth.getLabyrinthWall();
		int randomNumber = generateRandomNumber(borderCells.size()-1);
		Cell entrance = borderCells.get(randomNumber);
		genLabyrinth.setEntrance(entrance);
		genLabyrinth.breakExternalWall(entrance);
		return entrance;
	}

	public static int generateRandomNumber(int i) {
		return (int) ((System.nanoTime() + Math.pow(System.nanoTime(), 2) )  % i);
//		return (int) (Math.random() *i);
	}


	
	
}
