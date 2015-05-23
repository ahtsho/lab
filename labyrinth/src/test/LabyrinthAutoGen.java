package test;

import view.Console;
import core.Labyrinth;
import core.LabyrinthGenerator;
import core.Player;

public class LabyrinthAutoGen {

	public static void main(String[] args) {
		 
		 Labyrinth lab = LabyrinthGenerator.generateFull(10);
		 LabyrinthGenerator.createTunnel(lab);
		 while(LabyrinthGenerator.path.size() < lab.getDimension()){
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
