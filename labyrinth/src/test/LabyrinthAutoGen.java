package test;

import view.Console;
import core.Labyrinth;
import core.LabyrinthGenerator;
import core.Player;

public class LabyrinthAutoGen {

	public static void main(String[] args) {
		int size = 15;
		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		 Labyrinth lab = null;
		try {
			lab = labyrinthGenerator.generateLabyrinth(size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int i = 2;
		 //(int)(i*Math.log(i)
		 while(labyrinthGenerator.getPath().size() < (int)(lab.getDimension()*Math.log(lab.getDimension()))){
//			 System.out.println("CH 1: Soluzione "+i+" Scartata");
			 i++;
			 lab = null;
			 try {
				lab = labyrinthGenerator.generateLabyrinth(size);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 try {
			lab = labyrinthGenerator.generateDeadEndTunnels();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Console console = new Console(lab);
		 Player p = new Player();
		 p.setName("A");
		 lab.setPlayer(p);
		 p.setPosition(lab.getEntrance());
		 console.draw();
		
	}
}
