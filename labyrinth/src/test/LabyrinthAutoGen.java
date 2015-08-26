package test;

import infrastructure.Labyrinth;
import infrastructure.LabyrinthGenerator;
import view.Console;
import creatures.Player;

public class LabyrinthAutoGen {

	public static void main(String[] args) {
		int size = 15;
		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		 Labyrinth lab = null;
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
		
	}
}
