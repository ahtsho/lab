package test;

import java.util.ArrayList;

import model.creatures.Player;
import model.infrastructure.Cell;
import model.infrastructure.Labyrinth;
import controllers.LabyrinthGenerator;
import view.Console;

public class LabyrinthTest {

	static Labyrinth lab = null;
	public static void main(String[] args) {
		int size = 3;
		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		
		try {
			lab = labyrinthGenerator.generateLabyrinth(size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 while(labyrinthGenerator.getPath().size() < (int)(lab.getDimension()*Math.log(lab.getDimension()))){
			 lab = null;
			 try {
				lab = labyrinthGenerator.generateLabyrinth(size);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 
		 try {
			lab = labyrinthGenerator.generateDeadEndTunnels();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 Console console = new Console(lab);
		 Player p = new Player();
		 p.setName("A");
		 lab.setPlayer(p);
		 p.setPosition(lab.getEntrance());
		 console.draw();
		 
		
		
		testExitCellWall();
//		testGetLabyrinthWall(l);
	}
	
	public static void testExitCellWall(){
		System.out.println("exit = "+lab.getExitCellWall());
	}

	public static void testGetLabyrinthWall(Labyrinth l) {
		System.out.println("-BEGIN------------getLabyrinthWall Test-------------");
		ArrayList<Cell> walls = l.getLabyrinthWall();
		if (walls.size() == l.getDimension() * 4 - 4) {
			System.out.println("Correct number of cells = " + (l.getDimension() * 4 - 4));
		} else {
			System.out.println("Wrong number of cells");
		}

		System.out.println("EXPECTED: {a,b,c,d,f,g,h,i}");
		System.out.print("FOUND   : {");
		for (int i = 0; i < walls.size() - 1; i++) {
			System.out.print(walls.get(i).getName() + ",");
		}
		System.out.println(walls.get(walls.size() - 1).getName() + "}");
		System.out.println("-END------------getLabyrinthWall Test-------------");
	}
}
