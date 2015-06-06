package core;

import java.util.ArrayList;

public class LabyrinthGenerator {
	private static Labyrinth genLabyrinth;
	public static ArrayList<Cell> path;
	
	
	public static Labyrinth generateFull(int n) {
		ArrayList<Cell> labs = new ArrayList<Cell>();
		path = new ArrayList<Cell>();
		for (int i = 0; i < n*n; i++) {
			labs.add(new Cell(true, true, true, true,  i%n+""));
		}
		genLabyrinth = new Labyrinth(labs, labs.get(0), labs.get(n*n-1));
		return genLabyrinth;
	}
	
	public static void createTunnel(Labyrinth labyrinth){
		Cell cell = chooseEntrance();
		System.out.println("ENTRANCE: "+cell.getRow()+","+cell.getCol());
		path.add(cell);
		System.out.println(cell.getRow()+","+cell.getCol()+" visited");
		cell = explore(cell, Labyrinth.getOppositeDirection(cell.getFirstOpenWallNSWE()));
		System.out.println("second cell "+cell.getRow()+","+cell.getCol());
		path.add(cell);
		System.out.println(cell.getRow()+","+cell.getCol()+" visited");

//		cell = explore(cell, chooseDirenction(cell));
		int count=0;
		while(cell!=null && count < labyrinth.getCells().size()){
			count++;
			char d = chooseDirection();
			System.out.println("choose dir "+count+" "+d);
			cell = explore(cell, d);
			if(cell!=null){
				path.add(cell);
				System.out.println(cell.getRow()+","+cell.getCol()+" visited");
			}
		}
	}

	

	

	private static Cell explore(Cell cell, char direction) {
		System.out.println("EXPLORE");
		Cell nextCell = genLabyrinth.getCellForDirection(cell, direction);
		if(nextCell != null){
			while(pathContainsCell(nextCell)){
				direction = chooseDirection();
				System.out.println("exp "+direction);
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
			if(path.get(i).getRow()==cell.getRow() && path.get(i).getCol()==cell.getCol()){
				System.out.println("Cell "+cell.getRow()+","+cell.getCol()+" found");
				return true;
			}
		}
		System.out.println("Cell "+cell.getRow()+","+cell.getCol()+" not found");
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
	
	private static Cell chooseEntrance() {
		ArrayList<Cell> borderCells = genLabyrinth.getLabyrinthWall();
		int randomNumber = generateRandomNumber(borderCells.size()-1);
		Cell entrance = borderCells.get(randomNumber);
		genLabyrinth.setEntrance(entrance);
		genLabyrinth.breakExternalWall();
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
