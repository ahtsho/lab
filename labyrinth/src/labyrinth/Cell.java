package labyrinth;

public class Cell {

	char nome;
	boolean pareteNord;
	boolean pareteSud;
	boolean pareteOvest;
	boolean pareteEst;
	int row, col;
	
	Cell (boolean nord, boolean sud, boolean ovest, boolean est, char n){
		pareteNord = nord;
		pareteSud = sud;
		pareteOvest = ovest;
		pareteEst = est;
		nome = n;
	}
	
	public void draw(){
		if(pareteEst==true){
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
		
		if(pareteNord==true){
			System.out.print("-----");	
		} else {
			System.out.print("     ");
		}
		if(pareteOvest==true){
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
		if(pareteOvest==true){
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
		
		if(pareteSud==true){
			System.out.print("_____");	
		} else {
			System.out.print("     ");
		}
		if(pareteOvest==true){
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
	}
	
	
}
