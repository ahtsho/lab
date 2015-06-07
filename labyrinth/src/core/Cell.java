package core;

public class Cell {

	String name;
	private boolean north;
	private boolean south;
	private boolean west;
	private boolean east;
	private int row, col;

	public Cell(boolean N, boolean S, boolean W, boolean E, String string) {
		north = N;
		south = S;
		west = W;
		east = E;
		name = string;
	}

	public boolean isNorth() {
		return north;
	}

	public void setNorth(boolean north) {
		this.north = north;
	}

	public boolean isSouth() {
		return south;
	}

	public void setSouth(boolean south) {
		this.south = south;
	}

	public boolean isWest() {
		return west;
	}

	public void setWest(boolean west) {
		this.west = west;
	}

	public boolean isEast() {
		return east;
	}

	public void setEast(boolean east) {
		this.east = east;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getName() {
		return name;
	}

	/***
	 * Sets the wall of a given direction to false
	 * 
	 * @param direnction
	 *            = N, S, W, E
	 * @return true if succeded, flase if failed
	 */
	public boolean breakWall(char direnction) {
		if (direnction == Labyrinth.NORTH) {
			this.setNorth(false);
			return true;
		}
		if (direnction == Labyrinth.WEST) {
			this.setWest(false);
			return true;
		}
		if (direnction == Labyrinth.SOUTH) {
			this.setSouth(false);
			return true;
		}
		if (direnction == Labyrinth.EAST) {
			this.setEast(false);
			return true;
		}
		return false;
	}

	/***
	 * Returns the first wall that is false checking them in the NSWE order
	 * 
	 * @return N, S, W, E if one is open, empty char if all walls are there.
	 */
	public char getFirstOpenWallNSWE() throws Exception {
		if (!this.north)
			return Labyrinth.NORTH;
		if (!this.south)
			return Labyrinth.SOUTH;
		if (!this.west)
			return Labyrinth.WEST;
		if (!this.east)
			return Labyrinth.EAST;
		throw new Exception("No open walls");
	}

	/**
	 * Compares the given cell with current cell's coordinates and name
	 * @param cell
	 * @return true if match, false otherwise
	 */
	public boolean equals(Cell c) {
		if (this.getRow() == c.getRow() 
				&& this.getCol() == c.getCol()
				&& this.getName() == c.getName())
			return true;
		return false;
	}

}
