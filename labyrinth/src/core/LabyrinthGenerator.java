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
		while(cell!=null){
			cell = explore(cell, chooseDirenction());
		}
	}

	

	private static Cell explore(Cell cell, char direnction) {
		cell.breakWall(direnction);
		return genLabyrinth.getCellForDirection(cell, direnction);
	}

	public static char chooseDirenction() {
		int direction = generateRandomNumber(4);
		if(direction == 1){
			return Labyrinth.NORTH;
		} else if (direction == 2){
			return Labyrinth.SOUTH;
		} else if (direction == 3){
			return Labyrinth.WEST;
		}
		return Labyrinth.EAST;
	}

	private static Cell chooseEntrance() {
		ArrayList<Cell> borderCells = genLabyrinth.getLabyrinthWall();
		int randomNumber = generateRandomNumber(borderCells.size()-1);
		Cell entrance = borderCells.get(randomNumber);
		genLabyrinth.setEntrance(entrance);
		genLabyrinth.breakExternalWall(entrance);
		return entrance;
	}

	public static int generateRandomNumber(int max) {
		   return (int)(Math.random() * (max+1));
	}

	

	
	
}
