package view;

import core.Cell;
import core.Labyrinth;

public class Console {
	
	public static String HORIZONTAL_WALL = "=====";
	public static String NO_HORIZONTAL_WALL = "-.-.-";
	public static String HORIZONTAL_SPACE = "-----";
	
	public static String CORNER = "+";
	public static String NO_CORNER = "*";
	
	public static String VERTICAL_WALL = "|";
	public static String NO_VERTICAL_WALL = "x";	
	
	private Labyrinth lab;
	
	public Console(Labyrinth l){
		lab = l;
	}
	
	public void draw() {
		for (int row = 0; row < lab.getLabyrinthDimension() - 1; row++) {
			drawFirstThird(row);
			drawSecondThird(row);
		}
		drawFirstThird(lab.getLabyrinthDimension() - 1);
		drawSecondThird(lab.getLabyrinthDimension() - 1);
		drawThirdThird(lab.getLabyrinthDimension() - 1);
	}

	private void drawFirstThird(int r) {
		int f = 0;
		Cell c = null;
		Cell succ = null;
		for (int col = 0; col < lab.getLabyrinthDimension() - 1; col++) {

			f = (int) (r * lab.getLabyrinthDimension() + col);
			c = lab.getCells().get(f);
			if (f < lab.getCells().size() - 1) {
				succ = lab.getCells().get(f);
			}

			// parti sopra 1/3
			if (c.isWest()) {
				System.out.print(Console.CORNER);
			} else {
				System.out.print(Console.NO_CORNER);
			}
			if (c.isNorth()) {
				System.out.print(Console.HORIZONTAL_WALL);
			} else {
				System.out.print(Console.NO_HORIZONTAL_WALL);
			}
		}
		f = (int) (r * lab.getLabyrinthDimension()+ lab.getLabyrinthDimension() - 1);
		c = lab.getCells().get(f);
		if (c.isWest()) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
		if (c.isNorth()) {
			System.out.print(Console.HORIZONTAL_WALL);
		} else {
			System.out.print(Console.NO_HORIZONTAL_WALL);
		}
		if (c.isEast()) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
		System.out.println();

	}

	private void drawSecondThird(int r) {
		int f = 0;
		Cell c = null;
		for (int col = 0; col < lab.getLabyrinthDimension() - 1; col++) {

			f = (int) (r * lab.getLabyrinthDimension() + col);
			c = lab.getCells().get(f);
			// parti in mezzo 2/3
			if (c.isWest()) {
				if (lab.getPlayer().getPosition().equals(c)) {
					System.out.print(Console.VERTICAL_WALL + "  " + lab.getPlayer().getName()
							+ "  ");
				} else {
					System.out.print(Console.VERTICAL_WALL
							+ Console.HORIZONTAL_SPACE);
				}
			} else {
				if (lab.getPlayer().getPosition().equals(c)) {
					System.out.print(Console.NO_VERTICAL_WALL + "  "
							+ lab.getPlayer().getName() + "  ");
				} else {
					System.out.print(Console.NO_VERTICAL_WALL
							+ Console.HORIZONTAL_SPACE);
				}
			}

		}
		f = (int) (r * lab.getLabyrinthDimension() + lab.getLabyrinthDimension() - 1);
		c = lab.getCells().get(f);
		if (c.isWest()) {
			if (lab.getPlayer().getPosition().equals(c)) {
				System.out.print(Console.VERTICAL_WALL + "  " + lab.getPlayer().getName()
						+ "  ");
			} else {
				System.out.print(Console.VERTICAL_WALL
						+ Console.HORIZONTAL_SPACE);
			}
		} else {
			if (lab.getPlayer().getPosition().equals(c)) {
				System.out.print(Console.NO_VERTICAL_WALL + "  " + lab.getPlayer().getName()
						+ "  ");
			} else {
				System.out.print(Console.NO_VERTICAL_WALL
						+ Console.HORIZONTAL_SPACE);
			}
		}

		if (c.isEast()) {
			System.out.print(Console.VERTICAL_WALL);
		} else {
			System.out.print(Console.NO_VERTICAL_WALL);
		}
		System.out.println();
	}
	
	private void drawThirdThird(int r) {
		int f = 0;
		Cell c = null;

		for (int col = 0; col < lab.getLabyrinthDimension() - 1; col++) {
			Cell succ = null;
			f = (int) (r * lab.getLabyrinthDimension() + col);
			c = lab.getCells().get(f);

			if (f < lab.getCells().size() - 1) {
				succ = lab.getCells().get(f);
			}

			if (c.isWest()) {
				System.out.print(Console.CORNER);
			} else {
				System.out.print(Console.NO_CORNER);
			}
			if (c.isSouth()) {
				System.out.print(Console.HORIZONTAL_WALL);
			} else {
				System.out.print(Console.NO_HORIZONTAL_WALL);
			}
		}
		f = (int) (r * lab.getLabyrinthDimension() + lab.getLabyrinthDimension() - 1);
		c = lab.getCells().get(f);
		if (c.isWest()) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
		if (c.isSouth()) {
			System.out.print(Console.HORIZONTAL_WALL);
		} else {
			System.out.print(Console.NO_HORIZONTAL_WALL);
		}
		if (c.isEast()) {
			System.out.print(Console.CORNER);
		} else {
			System.out.print(Console.NO_CORNER);
		}
	}

	
}
