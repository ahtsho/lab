package core;

import java.util.ArrayList;

import view.Console;

public class Labyrinth {
	ArrayList<Cell> cells;

	Cell entrance;
	Cell exit;
	int dimension;
	Player player;

	Labyrinth(ArrayList<Cell> cs, Cell c1, Cell c2, Player p) {
		cells = cs;
		entrance = c1;
		exit = c2;
		dimension = (int) Math.sqrt(cells.size());
		player = p;
		disposeCells();
	}

	
	public ArrayList<Cell> getCells() {
		return cells;
	}


	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
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
				cells.get((int) (row * dimension + col)).row = row;
				cells.get((int) (row * dimension + col)).col = col;
			}
		}
	}

	public void showCoords() {
		for (int i = 0; i < cells.size(); i++) {
			System.out.println(cells.get(i).row + "," + cells.get(i).col);
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
			c = cells.get(f);

			if (f < cells.size() - 1) {
				succ = cells.get(f);
			}

			if (c.west) {
				System.out.print(Console.CORNER);
			} else {
				System.out.print(Console.NO_CORNER);
			}
			if (c.south) {
				System.out.print(Console.HORIZONTAL_WALL);
			} else {
				System.out.print(Console.NO_HORIZONTAL_WALL);
			}
		}
		f = (int) (r * dimension + dimension - 1);
		c = cells.get(f);
		if (c.west) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
		if (c.south) {
			System.out.print(Console.HORIZONTAL_WALL);
		} else {
			System.out.print(Console.NO_HORIZONTAL_WALL);
		}
		if (c.east) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
	}

	private void drawSecondThird(int r) {
		int f = 0;
		Cell c = null;
		for (int col = 0; col < dimension - 1; col++) {

			f = (int) (r * dimension + col);
			c = cells.get(f);
			// parti in mezzo 2/3
			if (c.west) {
				if (player.position.equals(c)) {
					System.out.print(Console.VERTICAL_WALL + "  " + player.name
							+ "  ");
				} else {
					System.out.print(Console.VERTICAL_WALL
							+ Console.HORIZONTAL_SPACE);
				}
			} else {
				if (player.position.equals(c)) {
					System.out.print(Console.NO_VERTICAL_WALL + "  "
							+ player.name + "  ");
				} else {
					System.out.print(Console.NO_VERTICAL_WALL
							+ Console.HORIZONTAL_SPACE);
				}
			}

		}
		f = (int) (r * dimension + dimension - 1);
		c = cells.get(f);
		if (c.west) {
			if (player.position.equals(c)) {
				System.out.print(Console.VERTICAL_WALL + "  " + player.name
						+ "  ");
			} else {
				System.out.print(Console.VERTICAL_WALL
						+ Console.HORIZONTAL_SPACE);
			}
		} else {
			if (player.position.equals(c)) {
				System.out.print(Console.NO_VERTICAL_WALL + "  " + player.name
						+ "  ");
			} else {
				System.out.print(Console.NO_VERTICAL_WALL
						+ Console.HORIZONTAL_SPACE);
			}
		}

		if (c.east) {
			System.out.print(Console.VERTICAL_WALL);
		} else {
			System.out.print(Console.NO_VERTICAL_WALL);
		}
		System.out.println();
	}

	private void drawFirstThird(int r) {
		int f = 0;
		Cell c = null;
		Cell succ = null;
		for (int col = 0; col < dimension - 1; col++) {

			f = (int) (r * dimension + col);
			c = cells.get(f);
			if (f < cells.size() - 1) {
				succ = cells.get(f);
			}

			// parti sopra 1/3
			if (c.west) {
				System.out.print(Console.CORNER);
			} else {
				System.out.print(Console.NO_CORNER);
			}
			if (c.north) {
				System.out.print(Console.HORIZONTAL_WALL);
			} else {
				System.out.print(Console.NO_HORIZONTAL_WALL);
			}
		}
		f = (int) (r * dimension + dimension - 1);
		c = cells.get(f);
		if (c.west) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
		if (c.north) {
			System.out.print(Console.HORIZONTAL_WALL);
		} else {
			System.out.print(Console.NO_HORIZONTAL_WALL);
		}
		if (c.east) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
		System.out.println();

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
		if (direnction == 'N') {
			if (player.position.north) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getNorth(player.position));
			}
		} else if (direnction == 'S') {
			if (player.position.south) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getSouth(player.position));
			}
		} else if (direnction == 'E') {
			if (player.position.east) {
				System.out.println("Ouch!!!");
			} else {
				return endGame(getEst(player.position));
			}
		} else if (direnction == 'W') {
			if (player.position.west) {
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
			destination = cells.get(getIndexFromCoords(position.row - 1,
					position.col));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getSouth(Cell position) {
		Cell destination = null;
		if (position.row < dimension) {
			destination = cells.get(getIndexFromCoords(position.row + 1,
					position.col));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getEst(Cell position) {
		Cell destination = null;
		if (position.col < dimension) {
			destination = cells.get(getIndexFromCoords(position.row,
					position.col + 1));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}

	private Cell getWest(Cell position) {
		Cell destination = null;
		if (position.col > 0) {
			destination = cells.get(getIndexFromCoords(position.row,
					position.col - 1));
		} else {
			System.out.println("Can't go this way");
		}
		return destination;
	}
}
