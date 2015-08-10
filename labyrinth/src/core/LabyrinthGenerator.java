package core;

import java.util.ArrayList;

import utils.Utils;

public class LabyrinthGenerator {

	private Labyrinth genLabyrinth;
	private ArrayList<Cell> path;
	private ArrayList<Cell> subPath;

	public ArrayList<Cell> getSubPath() {
		return subPath;
	}

	public void setSubPath(ArrayList<Cell> subPath) {
		this.subPath = subPath;
	}

	public Labyrinth getLabyrinth() {
		return genLabyrinth;
	}

	public ArrayList<Cell> getPath() {
		return path;
	}

	public Labyrinth generateLabyrinth(int n) throws Exception {
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
		Cell entrance = chooseEntrance();
		path.add(entrance);

		Cell cell = dig(entrance,Labyrinth.getOppositeDirection(entrance.getFirstOpenWallNSWE()),path);
		path.add(cell);

		while (cell != null) {
			char d = chooseDirection();
			cell = dig(cell, d,path);
			if (cell != null) {
				path.add(cell);
			}
		}
		genLabyrinth.setExit(path.get(path.size()-1));
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
		Cell nextCell = genLabyrinth.getCellForDirection(cell, direction);
		boolean dirN = false;
		boolean dirS = false;
		boolean dirW = false;
		boolean dirE = false;
		if (nextCell != null) {
			while (pathContainsCell(nextCell, aPath) & !(dirN & dirS & dirW & dirE)) {
				direction = chooseDirection();
				if(direction==Cell.NORTH){
					dirN = true;
				}
				if(direction==Cell.SOUTH){
					dirS = true;
				}
				if(direction==Cell.WEST){
					dirW = true;
				}
				if(direction==Cell.EAST){
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
		int random = Utils.generateRandomNumber(4);
		char direction = Labyrinth.getDirectionFromNumber(random);
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
		subPath = new ArrayList<Cell>();
		for(int i = 0; i < path.size(); i=i+5){
		// create tunnel without exit
			Cell cell = path.get(i);
			subPath.add(cell);
			
			while (cell!= null) {
				if(!genLabyrinth.isInLabyrinthWall(cell)){
					char d = chooseDirection();
					cell = dig(cell, d, subPath);
					if(cell==null){
						break;
					}	
					subPath.add(cell);
				} else {
					break;
				}
			}
		}
		return genLabyrinth;
	}

}
