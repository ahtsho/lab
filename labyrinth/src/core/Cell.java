package core;

public class Cell {

	char nome;
	boolean north;
	boolean south;
	boolean west;
	boolean pareteEst;
	int row, col;
	
	Cell (boolean nord, boolean sud, boolean ovest, boolean est, char n){
		north = nord;
		south = sud;
		west = ovest;
		pareteEst = est;
		nome = n;
	}
	
	public void draw(){
		if(pareteEst==true){
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
		
		if(pareteEst==true){
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
		
		if(pareteEst==true){
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
	
	
}
