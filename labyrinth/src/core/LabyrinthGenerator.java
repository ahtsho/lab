package core;

import java.util.ArrayList;

public class LabyrinthGenerator {
	private static Labyrinth genLabyrinth;
	public static ArrayList<Cell> path = new ArrayList<Cell>();
	
	
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
		path.add(cell);
		cell = explore(cell, Labyrinth.getOppositeDirection(cell.getOpenWall()));
		path.add(cell);
//		cell = explore(cell, chooseDirenction(cell));
		
		while(cell!=null){
			cell = explore(cell, chooseDirection());
			if(cell!=null){
				path.add(cell);
			}
		}
	}

	

	

	private static Cell explore(Cell cell, char direction) {
		Cell nextCell = genLabyrinth.getCellForDirection(cell, direction);
		if(nextCell != null){
			while(pathContainsCell(nextCell)){
				direction = chooseDirection();
				nextCell = genLabyrinth.getCellForDirection(cell, direction);
				if(nextCell == null){
					break;
				}
			}
		}
		cell.breakWall(direction);
		// astuzia della Feffi
		if(nextCell != null){
			nextCell.breakWall(Labyrinth.getOppositeDirection(direction));
		}
		return nextCell;
	}

	private static boolean pathContainsCell(Cell cell) {
		for(int i = 0; i < path.size(); i++){
			if(path.get(i).row==cell.row && path.get(i).col==cell.col){
				return true;
			}
		}
		return false;
	}

	public static char chooseDirection() {
		return getDirectionFromNumber(generateRandomNumber(4));
	}

	private static char getDirectionFromNumber(int direction){
		if(direction == 0){
			return Labyrinth.NORTH;
		} else if (direction == 1){
			return Labyrinth.SOUTH;
		} else if (direction == 2){
			return Labyrinth.WEST;
		} else if(direction == 3){
			return Labyrinth.EAST;
		}
		System.out.println("Wrong direnction "+direction);
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
		   return (int)(Math.random() * (max));
	}

	public static void drawPath() {
		System.out.println();
		for (int i = 0; i < path.size(); i++) {
			Cell c = path.get(i);
			System.out.println("("+c.getRow()+","+c.getCol()+")");
		}
	}

	

	
	
}
