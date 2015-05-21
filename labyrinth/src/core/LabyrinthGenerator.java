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
		cell = explore(cell, chooseDirenction(cell));
		
		while(cell!=null){
			cell = explore(cell, chooseDirenction());
		}
	}

	

	

	private static Cell explore(Cell cell, char direnction) {
		cell.breakWall(direnction);
		Cell nextCell = genLabyrinth.getCellForDirection(cell, direnction);
		if(nextCell != null){
			nextCell.breakWall(Labyrinth.getOppositeDirection(direnction));
		}
		return nextCell;
	}

	public static char chooseDirenction() {
		int direction = generateRandomNumber(4);
		return getDirectionFromNumber(direction);
	}

	private static char getDirectionFromNumber(int direction){
		if(direction == 1){
			return Labyrinth.NORTH;
		} else if (direction == 2){
			return Labyrinth.SOUTH;
		} else if (direction == 3){
			return Labyrinth.WEST;
		} else if(direction == 4){
			return Labyrinth.EAST;
		}
		return ' ';
	}
	private static char chooseDirenction(Cell cell) {
		char direction = getDirectionFromNumber(generateRandomNumber(4));
		char openWall = cell.getOpenWall();
		while(openWall == direction){
			direction = getDirectionFromNumber(generateRandomNumber(4));
		}
		return direction;
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
