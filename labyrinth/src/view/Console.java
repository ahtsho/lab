package view;

import java.util.ArrayList;

import core.Cell;
import core.Labyrinth;

public class Console {

	

	public static String HORIZONTAL_WALL = "=====";
	public static String NO_HORIZONTAL_WALL = "     ";
	public static String HORIZONTAL_SPACE = "     ";
	public static String TWO_HORIZONTAL_SPACES = "  ";

	public static String CORNER = "+";
	public static String NO_CORNER = ".";

	public static final String ENTRANCE = "@";
	public static final String EXIT = "¤";

	public static String VERTICAL_WALL = "|";
	public static String NO_VERTICAL_WALL = " ";

	private Labyrinth lab;

	public Console(Labyrinth l) {
		lab = l;
	}

	public void draw() {
		System.out.println("Player has Life="+lab.getPlayer().getLife()+" damage="+lab.getPlayer().getDamage());
		for (int row = 0; row < lab.getLabyrinthDimension() - 1; row++) {
			drawFirstThird(row);
			drawSecondThird(row);
//			drawSecondThirdWithCoords(row);
//			drawSecondThirdWithCellName(row);
		}
		drawFirstThird(lab.getLabyrinthDimension() - 1);
		drawSecondThird(lab.getLabyrinthDimension() - 1);
//		drawSecondThirdWithCoords(lab.getLabyrinthDimension() - 1);
//		drawSecondThirdWithCellName(lab.getLabyrinthDimension() - 1);
		drawThirdThird(lab.getLabyrinthDimension() - 1);
	}

	private void drawFirstThird(int r) {
		int f = 0;
		Cell c = null;
		for (int col = 0; col < lab.getLabyrinthDimension() - 1; col++) {

			f = (int) (r * lab.getLabyrinthDimension() + col);
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
		}
		f = (int) (r * lab.getLabyrinthDimension()
				+ lab.getLabyrinthDimension() - 1);
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
			if (c.isWest()) {
				System.out.print(Console.VERTICAL_WALL +TWO_HORIZONTAL_SPACES);
				if (lab.getPlayer().getPosition().equals(c) && lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
					System.out.print("*");
				}else if (lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
					System.out.print(lab.getGuard().getName());
				}else if (lab.getPlayer().getPosition().equals(c)) {
					System.out.print(lab.getPlayer().getName());
				} else{
					System.out.print(" ");
				}
				System.out.print(Console.TWO_HORIZONTAL_SPACES);
			} else {
				System.out.print(Console.NO_VERTICAL_WALL +TWO_HORIZONTAL_SPACES);
				if (lab.getPlayer().getPosition().equals(c) && lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
					System.out.print("*");
				}else if (lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
					System.out.print(lab.getGuard().getName());
				}else if (lab.getPlayer().getPosition().equals(c)) {
					System.out.print(lab.getPlayer().getName());
				} else{
					System.out.print(" ");
				}
				System.out.print(Console.TWO_HORIZONTAL_SPACES);
				
			}

		}
		f = (int) (r * lab.getLabyrinthDimension()
				+ lab.getLabyrinthDimension() - 1);
		c = lab.getCells().get(f);
		if (c.isWest()) {
			System.out.print(Console.VERTICAL_WALL +TWO_HORIZONTAL_SPACES);
			if (lab.getPlayer().getPosition().equals(c) && lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
				System.out.print("*");
			}else if (lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
				System.out.print(lab.getGuard().getName());
			}else if (lab.getPlayer().getPosition().equals(c)) {
				System.out.print(lab.getPlayer().getName());
			} else{
				System.out.print(" ");
			}
			System.out.print(Console.TWO_HORIZONTAL_SPACES);
			
		} else {
			System.out.print(Console.NO_VERTICAL_WALL +TWO_HORIZONTAL_SPACES);
			if (lab.getPlayer().getPosition().equals(c) && lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
				System.out.print("*");
			}else if (lab.getGuard()!= null && lab.getGuard().getPosition().equals(c)) {
				System.out.print(lab.getGuard().getName());
			}else if (lab.getPlayer().getPosition().equals(c)) {
				System.out.print(lab.getPlayer().getName());
			} else{
				System.out.print(" ");
			}
			System.out.print(Console.TWO_HORIZONTAL_SPACES);
			
//			if (lab.getPlayer().getPosition().equals(c)) {
//					System.out.print(Console.NO_VERTICAL_WALL + "  "
//							+ lab.getPlayer().getName() + "  ");
//			} if (lab.getGuard().getPosition().equals(c)) {
//				System.out.print(Console.NO_VERTICAL_WALL + "  "
//						+ lab.getGuard().getName() + "  ");
//			} else {
//					System.out.print(Console.NO_VERTICAL_WALL
//							+ Console.HORIZONTAL_SPACE);
//			}
			
		}

		if (c.isEast()) {
			System.out.print(Console.VERTICAL_WALL);
		} else {
			System.out.print(Console.NO_VERTICAL_WALL);
		}
		System.out.println();
	}

	private void drawSecondThirdWithCoords(int r) {
		int f = 0;
		Cell c = null;
		String coords = "";
		for (int col = 0; col < lab.getLabyrinthDimension() - 1; col++) {

			f = (int) (r * lab.getLabyrinthDimension() + col);
			c = lab.getCells().get(f);
			coords = " "+ c.getRow()+","+c.getCol() + " ";
			if (c.isWest()) {
				System.out.print(Console.VERTICAL_WALL + coords);
			} else {
				System.out.print(Console.NO_VERTICAL_WALL +coords);				
			}
		}
		f = (int) (r * lab.getLabyrinthDimension()
				+ lab.getLabyrinthDimension() - 1);
		c = lab.getCells().get(f);
		coords = " "+ c.getRow()+","+c.getCol() + " ";
		if (c.isWest()) {
			System.out.print(Console.VERTICAL_WALL + coords);
		} else {			
			System.out.print(Console.NO_VERTICAL_WALL +coords);
		}

		if (c.isEast()) {
			System.out.print(Console.VERTICAL_WALL);
		} else {
			System.out.print(Console.NO_VERTICAL_WALL);
		}
		System.out.println();
	}
	
	private void drawSecondThirdWithCellName(int r) {
		int f = 0;
		Cell c = null;
		for (int col = 0; col < lab.getLabyrinthDimension() - 1; col++) {

			f = (int) (r * lab.getLabyrinthDimension() + col);
			c = lab.getCells().get(f);
			if (c.isWest()) {
				System.out.print(Console.VERTICAL_WALL + "  "
						+ c.getName() + "  ");

			} else {
				System.out.print(Console.NO_VERTICAL_WALL+c.getName()
						+ Console.HORIZONTAL_SPACE);
			}

		}
		f = (int) (r * lab.getLabyrinthDimension()
				+ lab.getLabyrinthDimension() - 1);
		c = lab.getCells().get(f);
		if (c.isWest()) {
			System.out.print(Console.VERTICAL_WALL + "  "+c.getName()+"  ");
		} else {
			
			System.out.print(Console.NO_VERTICAL_WALL + "  "+c.getName()+ "  ");

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
			f = (int) (r * lab.getLabyrinthDimension() + col);
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
		}
		f = (int) (r * lab.getLabyrinthDimension()
				+ lab.getLabyrinthDimension() - 1);
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

	

	public static char adaptDirection(char c) {
		if (c == 'n' | c == 'N')
			return Cell.NORTH;
		if (c == 's' | c == 'S')
			return Cell.SOUTH;
		if (c == 'w' | c == 'W')
			return Cell.WEST;
		if (c == 'e' | c == 'E')
			return Cell.EAST;
		return 0;
	}

	public void printMoveMsg() {
		System.out.println();
		System.out.println();
		System.out.println(">   move: ");
		System.out.println();
	}
	public static void printGameStartInstructions() {
		System.out.println("    -------------------------");
		System.out.println("    Press Y to start the game");
		System.out.println("    -------------------------");
	}

	public static void printGameInstructions() {
		System.out.println("    --------------------------------------");
		System.out.println("    Press N:north, S:south, W:west, E:east");
		System.out.println("    Press Q to quit the game");
		System.out.println("    -------------------------");
	}
	public void printLevelFinishedMsg(int level) {
		System.out.println();
		System.out.println("        ------------------------ ");
		System.out.println(" WOW!!! You have finished level " + level);
		System.out.println("        ------------------------");
	}

	public void printGameTerminatedByUserMsg() {
		System.out.println("          ---------------------- ");
		System.out.println("BOOOOO!!! You quit? Too hard huh!");
		System.out.println("          ---------------------- ");

	}

	public static void printGameWonMsg() {
		System.out.println("************************************************");
		System.out.println("*     +        +            +         +        *");
		System.out.println("*                                              *");
		System.out.println("+ YEEEEEEEE!!! You have finished the Maze game +");
		System.out.println("*                                              *");
		System.out.println("*       +            +           +        +    *");
		System.out.println("************************************************");
	}

	public static void printWelcomeMsg() {
		System.out.println(" ****************************************************************************");
		System.out.println("*                             _  _   ____   ___    ___                       *");
		System.out.println("*                            | \\/ | |    |    /   |                          *");
		System.out.println("*            WELCOME TO THE  |    | |----|   /    |---           2015        *");
		System.out.println("*                            |    | |    |  /___  |___                       *");
		System.out.println("*                                                         by ahtsho & feffi  *");
		System.out.println(" ****************************************************************************");
	}

	public static void printLevel(int level) {
		System.out.println();
		System.out.println("     -------");
		System.out.println("MAZE Level " + level);
		System.out.println("     -------");
		System.out.println();
	}

	public void showCoordinates(ArrayList<Cell> labyrinthWall) {
		for (int i = 0; i < labyrinthWall.size(); i++) {
			System.out.println(labyrinthWall.get(i).getRow()+","+labyrinthWall.get(i).getCol());
		}
	}
	
	public void drawPath(ArrayList<Cell>path) {
		System.out.println();
		for (int i = 0; i < path.size(); i++) {
			Cell c = path.get(i);
			System.out.println("(" + c.getRow() + "," + c.getCol() + ")");
		}
	}
}
