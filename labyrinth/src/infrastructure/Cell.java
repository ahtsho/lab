package infrastructure;

import java.util.concurrent.CopyOnWriteArrayList;

import tools.Tool;
import creatures.Creature;

public class Cell {

	public static final char WEST = 'W';
	public static final char EAST = 'E';
	public static final char SOUTH = 'S';
	public static final char NORTH = 'N';
	
	private String name;
	private boolean north;
	private boolean south;
	private boolean west;
	private boolean east;
	private int row, col;

	private CopyOnWriteArrayList<Creature> hosts;
	private CopyOnWriteArrayList<Tool> tools;
	
	public synchronized CopyOnWriteArrayList<Tool> getTools() {
		return tools;
	}
	public synchronized void setTools(CopyOnWriteArrayList<Tool> tools) {
		this.tools = tools;
	}
	public synchronized void setHosts(CopyOnWriteArrayList<Creature> players){
		hosts=players;
	}
	public synchronized CopyOnWriteArrayList<Creature> getHosts(){
		return hosts;
	}
	
	public Cell(boolean N, boolean S, boolean W, boolean E, String string) {
		hosts = new CopyOnWriteArrayList<Creature>();
		tools = new CopyOnWriteArrayList<Tool>();
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
	 * @param direction
	 *            = N, S, W, E
	 * @return true if succeded, flase if failed
	 */
	public boolean breakWall(char direction) {
		if (direction == Cell.NORTH) {
			this.setNorth(false);
			return true;
		}
		if (direction == Cell.WEST) {
			this.setWest(false);
			return true;
		}
		if (direction == Cell.SOUTH) {
			this.setSouth(false);
			return true;
		}
		if (direction == Cell.EAST) {
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
			return Cell.NORTH;
		if (!this.south)
			return Cell.SOUTH;
		if (!this.west)
			return Cell.WEST;
		if (!this.east)
			return Cell.EAST;
		throw new Exception("No open walls");
	}

	public char getOpenWallNSWEExcept(char wall) throws Exception {
		if (!this.north && wall!=Cell.NORTH)
			return Cell.NORTH;
		if (!this.south && wall!=Cell.SOUTH)
			return Cell.SOUTH;
		if (!this.west && wall!=Cell.WEST)
			return Cell.WEST;
		if (!this.east && wall!=Cell.EAST)
			return Cell.EAST;
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
	
	public boolean getWallForDirection(char direction) throws Exception{
		if (direction == Cell.NORTH) {
			if (this.isNorth()) {
				return true;
			} else {
				return false;
			}
		}else if (direction == Cell.SOUTH) {
			if (this.isSouth()) {
				return true;
			} else {
				return false;
			}
		}else if (direction == Cell.WEST) {
			if (this.isWest()) {
				return true;
			} else {
				return false;
			}
		} else if (direction == Cell.EAST) {
			if (this.isEast()) {
				return true;
			} else {
				return false;
			}
		}
		throw new Exception ("No such direction");
	}
	public synchronized void addHost(Creature creature) {
		if(!hosts.contains(creature)){
			hosts.add(creature);
		} 
	}
	
	public synchronized void removeHost(Creature creature) {
		if(hosts.contains(creature)){
			hosts.remove(creature);
		}
	}
	
	public synchronized void removeTools() {
		tools.clear();
		
	}
}
