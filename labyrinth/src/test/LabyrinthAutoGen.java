package test;

import view.Console;
import core.Labyrinth;
import core.LabyrinthGenerator;
import core.Player;

public class LabyrinthAutoGen {

	public static void main(String[] args) {
		 System.out.println("START");
		 Labyrinth lab = LabyrinthGenerator.generateFull(10);
		 System.out.println("Create tunnel 1");
		 LabyrinthGenerator.createTunnel(lab);
		 int i = 2;
		 while(LabyrinthGenerator.path.size() < lab.getDimension()){
			 System.out.println("Path too short. Create tunnel "+i);
			 i++;
			 lab = LabyrinthGenerator.generateFull(10);
			 LabyrinthGenerator.createTunnel(lab);
		 }
		 
		 Console console = new Console(lab);
		 Player p = new Player();
		 p.setName("A");
		 lab.setPlayer(p);
		 p.setPosition(lab.getEntrance());
		 console.draw();
		
	}

}
