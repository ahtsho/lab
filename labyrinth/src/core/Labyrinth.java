package core;

import java.util.ArrayList;

public class Labyrinth {

	private ArrayList<Cell> cells;
	private Cell entrance;
	private Cell exit;
	private int dimension;
	private Player player;
	private ArrayList<Cell> labyrinthWall;
	private Guard guard;

	public Labyrinth(ArrayList<Cell> cs, Cell c1, Cell c2) {
		cells = cs;
		entrance = c1;
		exit = c2;
		dimension = (int) Math.sqrt(cells.size());
		disposeCells();
	}

	public Labyrinth(ArrayList<Cell> cs, Cell c1, Cell c2, Player p) {
		cells = cs;
		entrance = c1;
		exit = c2;
		dimension = (int) Math.sqrt(cells.size());
		player = p;
		disposeCells();
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public boolean isEntrance(Cell c) {
		if (c == entrance)
			return true;
		return false;
	}

	public boolean isExit(Cell c) {
		if (c == exit)
			return true;
		return false;
	}

	public Cell getEntrance() {
		return entrance;
	}

	public Guard getGuard() {
		return this.guard;
	}

	public void setGuard(Guard aguard) {
		guard = aguard;
	}

	/**
	 * Sets a cell as the entrance of the labyrinth and breaks it's external
	 * wall
	 * 
	 * @param entrance
	 */
	public void setEntrance(Cell entrance) {
		this.entrance = entrance;
		breakEntranceExternalWall();
	}

	public Cell getExit() {
		return exit;
	}

	public void setExit(Cell exit) {
		this.exit = exit;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getLabyrinthDimension() {
		return this.dimension;
	}

	private int getIndexFromCoords(int r, int c) {
		return (int) (r * dimension + c);
	}

	private Cell getCell(int row, int col) {
		return cells.get(getIndexFromCoords(row, col));
	}

	/***
	 * Returns an array list of Cells that are in the external border of the
	 * labyrinth: i.e. that have one of their coordinates equal to a limit value
	 * 0 or n-1
	 * 
	 * @return labyrinthWall
	 */
	public ArrayList<Cell> getLabyrinthWall() {
		// labyrinth wall already calculated once
		if (labyrinthWall != null && labyrinthWall.size() == dimension * 4 - 4) {
			return labyrinthWall;
		}
		// calculate wall for the first time
		labyrinthWall = new ArrayList<Cell>();
		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).getRow() == 0
					|| cells.get(i).getRow() == dimension - 1
					|| cells.get(i).getCol() == 0
					|| cells.get(i).getCol() == dimension - 1) {
				labyrinthWall.add(cells.get(i));
			}
		}
		return labyrinthWall;
	}

	/*
	 * If cells have not been assigned coordinates, it assignes it to them
	 * Example of 3 x 3 matrix built from an array of 9 elements dim row col
	 * index = (row * dim + col) 3 0 0 0 0 1 1 0 2 2 1 0 3 1 1 4 1 2 5 2 0 6 2 1
	 * 7 2 2 8
	 */
	private void disposeCells() {
		if (!(cells.get(1).getRow() == 0 && cells.get(1).getCol() == 1)) {
			for (int row = 0; row < dimension; row++) {
				for (int col = 0; col < dimension; col++) {
					cells.get((int) (row * dimension + col)).setRow(row);
					cells.get((int) (row * dimension + col)).setCol(col);
				}
			}
		}
	}

	private Cell getNorth(Cell position) {
		Cell destination = null;
		if (position.getRow() > 0) {
			destination = cells.get(getIndexFromCoords(position.getRow() - 1,
					position.getCol()));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getSouth(Cell position) {
		Cell destination = null;
		if (position.getRow() < dimension) {
			destination = cells.get(getIndexFromCoords(position.getRow() + 1,
					position.getCol()));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getEst(Cell position) {
		Cell destination = null;
		if (position.getCol() < dimension) {
			destination = cells.get(getIndexFromCoords(position.getRow(),
					position.getCol() + 1));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getWest(Cell position) {
		Cell destination = null;
		if (position.getCol() > 0) {
			destination = cells.get(getIndexFromCoords(position.getRow(),
					position.getCol() - 1));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	/*
	 * Breaks the external wall of the entrance
	 */
	private void breakEntranceExternalWall() {
		if (entrance != null) {
			if (entrance.getRow() == 0) {
				entrance.setNorth(false);
			} else if (entrance.getCol() == 0) {
				entrance.setWest(false);
			} else if (entrance.getRow() == dimension - 1) {
				entrance.setSouth(false);
			} else if (entrance.getCol() == dimension - 1) {
				entrance.setEast(false);
			}
		}
	}

	/***
	 * Given a cell and a direnction returns the next cell in that direction
	 * 
	 * @param cell
	 * @param direnction
	 * @return nextCell if found, null otherwise
	 */
	public Cell getCellForDirection(Cell cell, char direnction) {
		Cell nextcell = null;
		if (cell != null) {
//			System.out.println("getCellForDirection cell" + cell.getName()
//					+ " dir=" + direnction);
			if (direnction == Cell.NORTH && cell.getRow() > 0) {
				nextcell = getCell(cell.getRow() - 1, cell.getCol());
			} else if (direnction == Cell.SOUTH
					&& cell.getRow() < dimension - 1) {
				nextcell = getCell(cell.getRow() + 1, cell.getCol());
			} else if (direnction == Cell.WEST && cell.getCol() > 0) {
				nextcell = getCell(cell.getRow(), cell.getCol() - 1);
			} else if (direnction == Cell.EAST && cell.getCol() < dimension - 1) {
				nextcell = getCell(cell.getRow(), cell.getCol() + 1);
			}
//			if (nextcell != null)
//				System.out.println("next cell=" + nextcell.getName());
		}
		return nextcell;
	}

	/**
	 * maps numbers in the set {0,1,2,3} to {N,S;W,E}
	 * 
	 * @param direction
	 *            = one of {0,1,2,3}
	 * @return one of {N,S;W,E}
	 * @throws Exception
	 *             wrong direction
	 */
	public static char getDirectionFromNumber(int direction) throws Exception {
		// System.out.println("CH 6: getDirectionFromNumber");
		if (direction == 0)
			return Cell.NORTH;
		else if (direction == 1)
			return Cell.SOUTH;
		else if (direction == 2)
			return Cell.WEST;
		else if (direction == 3)
			return Cell.EAST;
		throw new Exception("Wrong direnction " + direction);
	}

	/**
	 * returns the opposite direction of a given direction
	 * 
	 * @param direction
	 * @return S for N, N for S, W for E, E for W, empty char otherwise
	 */
	public static char getOppositeDirection(char direction) throws Exception {
		if (direction == Cell.NORTH) {
			return Cell.SOUTH;
		} else if (direction == Cell.SOUTH) {
			return Cell.NORTH;
		} else if (direction == Cell.WEST) {
			return Cell.EAST;
		} else if (direction == Cell.EAST) {
			return Cell.WEST;
		}
		throw new Exception("Non Existing direction");
	}

	/*
	 * Given a cell to go to,
	 */
	public boolean moveTo(Cell destination) {
		if (destination != null) {
//			System.out.println("move to destination=" + destination);
			player.position = destination;
//			System.out.println(player.getName()+"moved to ("+player.position.getRow()+","+player.position.getCol()+")");
			if (destination == exit) {
//				System.out.println("ups exited");
				return false;
			}
		}

		return true;
	}

	public boolean moveTo(Player p,Cell destination) {
		if (destination != null) {
			if(areConnected(p.getPosition(),destination)){
				p.position = destination;
				return true;	
			}
		}
		return false;
	}
	
	

	private boolean areConnected(Cell cell1, Cell cell2) {
		if(cell1.getRow()==cell2.getRow()){
			if(Math.abs(cell1.getCol()-cell2.getCol())==1){ // they are next to each other 
				if(cell2.getCol() > cell1.getCol()){ // get the order
					if(!cell1.isEast() && !cell2.isWest()){
						return true;
					} else {
						return false;
					}
				} else {
					if(!cell2.isEast() && !cell1.isWest()){
						return true;
					} else {
						return false;
					}
				}
				
			}
		} else if(cell1.getCol()==cell2.getCol()){
			if(Math.abs(cell1.getRow()-cell2.getRow())==1){ // they are on top of each other 
				if(cell2.getRow() > cell1.getRow()){ // get the order
					if(!cell1.isSouth() && !cell2.isNorth()){
						return true;
					} else {
						return false;
					}
				} else {
					if(!cell2.isSouth() && !cell1.isNorth()){
						return true;
					} else {
						return false;
					}
				}
				
			}
		} 
		return false;
	}

	
	public boolean move(Player player, char direction) throws Exception {
		if (player.getPosition().getWallForDirection(direction)) {
//			System.out.println("ouch");
			player.damage((float) 0.1);
		} else {

			return moveTo(getCellForDirection(player.position, direction));
		}
//		System.out.println("move->true");
		return true;
	}

	public boolean isInLabyrinthWall(Cell cell) {
		for (Cell wallCell : labyrinthWall) {
			if (wallCell.equals(cell))
				return true;
		}
		return false;
	}

}
