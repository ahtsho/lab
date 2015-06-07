package unitTest;

import view.Console;
import core.Labyrinth;
import core.LabyrinthGenerator;
import core.Player;

public class LabyrinthAutoGen {

	public static void main(String[] args) {
		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		 Labyrinth lab = null;
		try {
			lab = labyrinthGenerator.generateLabyrinth(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int i = 2;
		 while(labyrinthGenerator.getPath().size() < lab.getDimension()){
			 i++;
			 lab = null;
			 try {
				lab = labyrinthGenerator.generateLabyrinth(10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 Console console = new Console(lab);
		 Player p = new Player();
		 p.setName("A");
		 lab.setPlayer(p);
		 p.setPosition(lab.getEntrance());
		 console.draw();
		
	}

}
