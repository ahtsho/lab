package core;

import java.util.ArrayList;

public class Labyrinth {
	


	public static final char NORTH = 'N';
	public static final char SOUTH = 'S';
	public static final char WEST = 'W';
	public static final char EAST = 'E';
	ArrayList<Cell> cells;

	Cell entrance;
	Cell exit;
	int dimension;
	Player player;

	Labyrinth(ArrayList<Cell> cs, Cell c1, Cell c2) {
		cells = cs;
		entrance = c1;
		exit = c2;
		dimension = (int) Math.sqrt(cells.size());
		disposeCells();
	}
	
	
	Labyrinth(ArrayList<Cell> cs, Cell c1, Cell c2, Player p) {
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

	public ArrayList<Cell> getLabyrinthWall() {
		ArrayList<Cell> labyrinthWall = new ArrayList<Cell>();
		for (int i = 0; i < cells.size(); i++) {
			if(cells.get(i).getRow()==0 || cells.get(i).getRow()==dimension-1 ||
					cells.get(i).getCol()==0 || cells.get(i).getCol()==dimension-1){
				labyrinthWall.add(cells.get(i));
			}
		}
		return labyrinthWall;
	}
	
	public boolean isEntrance(Cell c){
		if (c==entrance) return true;
		return false;
	}

	public boolean isExit(Cell c){
		if (c==exit) return true;
		return false;
	}
	
	public Cell getEntrance() {
		return entrance;
	}


	public void setEntrance(Cell entrance) {
		this.entrance = entrance;
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

	private void disposeCells() {
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				cells.get((int) (row * dimension + col)).setRow(row);
				cells.get((int) (row * dimension + col)).setCol(col);
			}
		}
	}

	
	

	
	private boolean endGame(Cell destination) {
		if (destination != null) {
			player.position = destination;
		}
		if (destination == exit) {
			return false;
		}
		return true;
	}

	public boolean move(Player player, char direnction) {
		if (direnction == NORTH) {
			if (player.position.isNorth()) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getNorth(player.position));
			}
		} else if (direnction == SOUTH) {
			if (player.position.isSouth()) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getSouth(player.position));
			}
		} else if (direnction == EAST) {
			if (player.position.isEast()) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getEst(player.position));
			}
		} else if (direnction == WEST) {
			if (player.position.isWest()) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getWest(player.position));
			}
		}
		return true;
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


	public void breakExternalWall(Cell entrance) {
		if(entrance.getRow() == 0){
			entrance.setNorth(false);
		} else if (entrance.getCol() == 0){
			entrance.setWest(false);
		}else if (entrance.getRow() == dimension-1){
			entrance.setSouth(false);
		}else if (entrance.getCol() == dimension-1){
			entrance.setEast(false);
		}
	}


	public Cell getCellForDirection(Cell cell, char direnction) {
		Cell nextcell = null;
		if(direnction == Labyrinth.NORTH && cell.getRow() > 0){
			nextcell = getCell(cell.getRow()-1,cell.getCol());
			System.out.println("gcfd"+nextcell.getRow()+","+nextcell.getCol());

		}else if(direnction == Labyrinth.SOUTH && cell.getRow() < dimension-1){
			nextcell = getCell(cell.getRow()+1,cell.getCol());
			System.out.println("gcfd"+nextcell.getRow()+","+nextcell.getCol());
		}else if(direnction == Labyrinth.WEST && cell.getCol() > 0){
			nextcell = getCell(cell.getRow(),cell.getCol()-1);
			System.out.println("gcfd"+nextcell.getRow()+","+nextcell.getCol());
		}else if(direnction == Labyrinth.EAST && cell.getCol() < dimension-1){
			nextcell = getCell(cell.getRow(),cell.getCol()+1);
			System.out.println("gcfd"+nextcell.getRow()+","+nextcell.getCol());
		}else {
			System.out.println("gcfd returned null");
		}
		
		return nextcell;
	}


	private Cell getCell(int row, int col) {
		return cells.get(getIndexFromCoords(row, col));
	}


	public static char getOppositeDirection(char direction) {
		if(direction == NORTH){
			return SOUTH;
		} else if(direction == SOUTH){
			return NORTH;
		} else if(direction == WEST){
			return EAST;
		} else if(direction == EAST){
			return WEST;
		}
		return ' ';
	}
}
