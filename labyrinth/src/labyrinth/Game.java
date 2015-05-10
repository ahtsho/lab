package labyrinth;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {

		System.out.println("Welcome to the MAZE!!!");
		System.out.println("Press N to move North, S to move south, W to move West, E to move Est");
		System.out.println("Press Q to quit the game.");

		Cell first = new Cell(true, true, false, false, 'A');
		Cell c2 = new Cell(true, false, false, true, 'B');
		Cell c3 = new Cell(true, false, true, true, 'C');
		Cell last = new Cell(false, false, true, true, 'D');
		Player p = new Player();
		p.name = "F";
		p.position = first;

		ArrayList<Cell> labs = new ArrayList<Cell>();
		labs.add(first);
		labs.add(c2);
		labs.add(c3);
		labs.add(last);

		Labyrinth labirintoSemplice = new Labyrinth(labs, first, last, p);
		// labirintoSemplice.showCoords();
		labirintoSemplice.draw();
		String read;
		Scanner scanIn = new Scanner(System.in);
		
		while (true) {
			System.out.println("Move: ");
			read = scanIn.nextLine();
			if (read.startsWith("N") | read.startsWith("S")
					| read.startsWith("W") | read.startsWith("E")) {
				
				if(labirintoSemplice.move(p, read.charAt(0))){
					labirintoSemplice.draw();
				} else {
					labirintoSemplice.draw();
					System.out.println("You won!");
					break;
				}
			} else if (read.startsWith("Q")){
				System.out.println("Bye Bye!!! Thanks for playing :) ");
				break;
			} else {
				System.out.println("Press N to move North, S to move south, W to move West, E to move Est");
			}
		}
	}

}
