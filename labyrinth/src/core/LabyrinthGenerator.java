package core;

import java.util.ArrayList;

import utils.Utils;

public class LabyrinthGenerator {

	private Labyrinth genLabyrinth;
	private ArrayList<Cell> path;

	public Labyrinth getLabyrinth() {
		return genLabyrinth;
	}

	public ArrayList<Cell> getPath() {
		return path;
	}

	public Labyrinth generateLabyrinth(int n) throws Exception {
//		System.out.println("CH2: generateLabyrinth");
		generateFull(n);
		createTunnel();
		return genLabyrinth;
	}

	/*
	 * Generate an (n x n) Labyrinth
	 * 
	 * @param n
	 */
	public void generateFull(int n) {
//		System.out.println("CH 3: generateFull");
		ArrayList<Cell> labs = new ArrayList<Cell>();
		path = new ArrayList<Cell>();
		for (int i = 0; i < n * n; i++) {
			labs.add(new Cell(true, true, true, true, i % 10 + ""));
		}
		genLabyrinth = new Labyrinth(labs, labs.get(0), labs.get(n * n - 1));
	}

	/***
	 * Chooses an entrance, moves one step in the opposite direction of the
	 * entrance, then untill it comes out of the labyrinth it keeps on moving
	 * random directions.
	 * 
	 * @throws Exception
	 *             Non exixting direction
	 */
	public void createTunnel() throws Exception {
//		System.out.println("CH 4:createTunnel");
		Cell entrance = chooseEntrance();
		path.add(entrance);

		Cell cell = dig(entrance,Labyrinth.getOppositeDirection(entrance.getFirstOpenWallNSWE()),path);
		path.add(cell);

		while (cell != null) {
//			System.out.println("create tunnel can still go on");
			char d = chooseDirection();
			cell = dig(cell, d,path);
			if (cell != null) {
				path.add(cell);
			}
		}
	}

	/**
	 * Given a cell, obtains from the labyrinth the next cell in the given
	 * direction. If there is a cell to be reached, which has not been visited
	 * yet it moves there by breaking the walls of the current cell and the
	 * adjacent wall of the next cell. If there is a cell to be reached, but has
	 * already been visited, keeps on choosing a new direction untill either it
	 * finds a new cell or exits the labyrinth
	 * 
	 * @param cell
	 * @param direction
	 * @return nextCell
	 * @throws Exception
	 *             non exixting direction
	 */
	public Cell dig(Cell cell, char direction, ArrayList<Cell> aPath) throws Exception {
//		System.out.println("CH 7: dig ");
		Cell nextCell = genLabyrinth.getCellForDirection(cell, direction);
		boolean dirN = false;
		boolean dirS = false;
		boolean dirW = false;
		boolean dirE = false;
		if (nextCell != null) {
			while (pathContainsCell(nextCell, aPath) & !(dirN & dirS & dirW & dirE)) {
//				System.out.println("pathContainsCell already");
				direction = chooseDirection();
				if(direction==Labyrinth.NORTH){
					dirN = true;
				}
				if(direction==Labyrinth.SOUTH){
					dirS = true;
				}
				if(direction==Labyrinth.WEST){
					dirW = true;
				}
				if(direction==Labyrinth.EAST){
					dirE = true;
				}
				nextCell = genLabyrinth.getCellForDirection(cell,direction);
				if (nextCell == null) {
					break;
				}
			}
		}
		cell.breakWall(direction);
		// astuzia della Feffi
		if (nextCell != null) {
			nextCell.breakWall(Labyrinth.getOppositeDirection(direction));
		}
		return nextCell;
	}
//	
//	public Cell digFree(Cell cell, char direction) throws Exception {
////		System.out.println("CH 7: dig ");
//		Cell nextCell = genLabyrinth.getCellForDirection(cell, direction);
//		if(genLabyrinth.getLabyrinthWall().contains(cell)){
//			return null;
//		}
//		cell.breakWall(direction);
//		// astuzia della Feffi
//		if (nextCell != null) {
//			nextCell.breakWall(Labyrinth.getOppositeDirection(direction));
//		}
//		return nextCell;
//	}

	/**
	 * checks if cell is already in the path
	 * @param cell
	 * @return true if contained, false otherwise
	 */
	public boolean pathContainsCell(Cell cell, ArrayList<Cell> aPath) {
		for (int i = 0; i < aPath.size(); i++) {
			if (cell.equals(aPath.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Generates a random number from the set {0,1,2,3} and returns
	 * corresponding direction.
	 * 
	 * @return one of {N,S,W,E}
	 * @throws Exception
	 */
	public char chooseDirection() throws Exception {
//		System.out.println("CH 5:chooseDirection");
		int random = Utils.generateRandomNumber(4);
//		System.out.println("random = "+random);
		char direction = Labyrinth.getDirectionFromNumber(random);
//		System.out.println("direction = "+direction);
		return direction;
	}

	/*
	 * requests an array of cells that are on the perimeter of the labyrinth and
	 * chooses randomly one from those to be the entrance.
	 * 
	 * @return entrance cell
	 */
	public Cell chooseEntrance() {
		ArrayList<Cell> borderCells = genLabyrinth.getLabyrinthWall();
		Cell entrance = borderCells.get(Utils.generateRandomNumber(genLabyrinth.getLabyrinthWall().size() - 1));
		genLabyrinth.setEntrance(entrance);
		return entrance;
	}

	public Labyrinth generateDeadEndTunnels() throws Exception {
		
		//loop over path, every 3
		for(int i = 0; i < path.size(); i=i+5){
			ArrayList<Cell> subPath = new ArrayList<Cell>();
		// create tunnel without exit
			Cell cell = path.get(i);
			subPath.add(cell);
			int length = 0;
			while (cell!= null && length < 15) {
				length++;
				char d = chooseDirection();
				cell = dig(cell, d, subPath);
				subPath.add(cell);
			}
		}
		return genLabyrinth;
	}

}
