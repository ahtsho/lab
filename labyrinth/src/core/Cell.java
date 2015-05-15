package core;

public class Cell {

	String name;
	boolean north;
	boolean south;
	boolean west;
	boolean east;
	int row, col;
	
	Cell (boolean N, boolean S, boolean W, boolean E, String string){
		north = N;
		south = S;
		west = W;
		east = E;
		name = string;
	}
	
	@Deprecated
	public void draw(){
		if(east==true){
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
		
		if(north==true){
			System.out.print("-----");	
		} else {
			System.out.print("     ");
		}
		if(west==true){
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
		System.out.println();
		
		if(east==true){
			System.out.print("|     ");
		} else {
			System.out.print("      ");
		}
		if(west==true){
			System.out.print("|");
		} else {
			System.out.print(" ");
		}
		System.out.println();
		
		if(east==true){
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
		
		if(south==true){
			System.out.print("_____");	
		} else {
			System.out.print("     ");
		}
		if(west==true){
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
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
	
	
}
