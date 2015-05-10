package labyrinth;

import java.util.ArrayList;

public class Labyrinth {
	ArrayList<Cell> celle;
	Cell entrance;
	Cell exit;
	int dimension;
	Player player;

	Labyrinth(ArrayList<Cell> cs, Cell c1, Cell c2, Player p) {
		celle = cs;
		entrance = c1;
		exit = c2;
		dimension = (int) Math.sqrt(celle.size());
		player = p;
		disposeCells();
	}

	private int getIndexFromCoords(int r, int c) {
		return (int) (r * dimension + c);
	}

	private void disposeCells() {
		for (int row = 0; row < dimension; row++) {
			for (int col = 0; col < dimension; col++) {
				celle.get((int) (row * dimension + col)).row = row;
				celle.get((int) (row * dimension + col)).col = col;
			}
		}
	}

	public void showCoords() {
		for (int i = 0; i < celle.size(); i++) {
			System.out.println(celle.get(i).row + "," + celle.get(i).col);
		}
	}

	public void draw() {
		for (int row = 0; row < dimension - 1; row++) {
			drawFirstThird(row);
			drawSecondThird(row);
		}
		drawFirstThird(dimension - 1);
		drawSecondThird(dimension - 1);
		drawThirdThird(dimension - 1);
	}

	private void drawThirdThird(int r) {
		int f = 0;
		Cell c = null;

		for (int col = 0; col < dimension - 1; col++) {
			Cell succ = null;
			f = (int) (r * dimension + col);
			c = celle.get(f);

			if (f < celle.size() - 1) {
				succ = celle.get(f);
			}

			if (c.pareteOvest) {
				System.out.print("+");
			} else {
				System.out.print(" ");
			}
			if (c.pareteSud) {
				System.out.print("     ");
			} else {
				System.out.print("     ");
			}
		}
		f = (int) (r * dimension + dimension - 1);
		c = celle.get(f);
		if (c.pareteOvest) {
			System.out.print("+");
		} else {
			System.out.print(" ");
		}
		if (c.pareteSud) {
			System.out.print("     ");
		} else {
			System.out.print("     ");
		}
		if (c.pareteEst) {
			System.out.println("+");
		} else {
			System.out.print("");
		}
	}

	private void drawSecondThird(int r) {
		int f = 0;
		Cell c = null;
		for (int col = 0; col < dimension - 1; col++) {

			f = (int) (r * dimension + col);
			c = celle.get(f);
			// parti in mezzo 2/3
			if (c.pareteOvest) {
				if (player.position.equals(c)) {
					System.out.print("|  " + player.name + "  ");
				} else {
					System.out.print("|     ");
				}
			} else {
				if (player.position.equals(c)) {
					System.out.print("   " + player.name + "  ");
				} else {
					System.out.print("      ");
				}
			}

		}
		f = (int) (r * dimension + dimension - 1);
		c = celle.get(f);
		if (c.pareteOvest) {
			if (player.position.equals(c)) {
				System.out.print("|  " + player.name + "  ");
			} else {
				System.out.print("|     ");
			}
		} else {
			if (player.position.equals(c)) {
				System.out.print("   " + player.name + "  ");
			} else {
				System.out.print("      ");
			}
		}

		if (c.pareteEst) {
			System.out.print("|");
		} else {
			System.out.print("");
		}
		System.out.println();
	}

	private void drawFirstThird(int r) {
		int f = 0;
		Cell c = null;
		Cell succ = null;
		for (int col = 0; col < dimension - 1; col++) {

			f = (int) (r * dimension + col);
			c = celle.get(f);
			if (f < celle.size() - 1) {
				succ = celle.get(f);
			}

			// parti sopra 1/3
			if (c.pareteOvest) {
				System.out.print("+");
			} else {
				System.out.print(" ");
			}
			if (c.pareteNord) {
				System.out.print("-----");
			} else {
				System.out.print("     ");
			}
		}
		f = (int) (r * dimension + dimension - 1);
		c = celle.get(f);
		if (c.pareteOvest) {
			System.out.print("+");
		} else {
			System.out.print("-");
		}
		if (c.pareteNord) {
			System.out.print("-----");
		} else {
			System.out.print("     ");
		}
		if (c.pareteEst) {
			System.out.print("+");
		} else {
			System.out.print("");
		}
		System.out.println();

	}

	private boolean endGame(Cell destination) {
		if(destination!=null){
			player.position = destination;
		}
		if (destination == exit) {
			return false;
		}
		return true;
	}


	public boolean move(Player player, char direnction) {
		if (direnction == 'N') {
			if (player.position.pareteNord) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getNorth(player.position));
			}
		} else if (direnction == 'S') {
			if (player.position.pareteSud) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getSouth(player.position));
			}
		} else if (direnction == 'E') {
			if (player.position.pareteEst) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getEst(player.position));
			}
		} else if (direnction == 'W') {
			if (player.position.pareteOvest) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getWest(player.position));
			}
		}
		return true;
	}

	private Cell getNorth(Cell position) {
		Cell destination = null;
		if (position.row > 0) {
			destination = celle.get(getIndexFromCoords(position.row - 1,position.col));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getSouth(Cell position) {
		Cell destination = null;
		if (position.row < dimension) {
			destination = celle.get(getIndexFromCoords(position.row + 1, position.col));
		}else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getEst(Cell position) {
		Cell destination = null;
		if (position.col < dimension) {
			destination =celle.get(getIndexFromCoords(position.row, position.col + 1)); 
		}else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getWest(Cell position) {
		Cell destination = null;
		if (position.col > 0) {
			destination = celle.get(getIndexFromCoords(position.row, position.col - 1));
		}else {
			System.out.println("Can't go this way");
		}
		return destination;
	}
}
