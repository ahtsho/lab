package test;

import model.creatures.Player;
import controllers.LabyrinthGenerator;
import view.Console;

public class TunnelCreationTest {

	public static void main(String[] args) {
		LabyrinthGenerator labGen = new LabyrinthGenerator();

		try {
			testCreateTunnel(labGen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Console console = new Console(labGen.getLabyrinth());
		Player p = new Player();
		p.setName("A");
		labGen.getLabyrinth().setPlayer(p);
		p.setPosition(labGen.getLabyrinth().getEntrance());
		console.draw();
	}

	public static void testCreateTunnel(LabyrinthGenerator lg) throws Exception {
		lg.generateFull(3);
		lg.createTunnel();
		System.out.println("Entrance="+lg.getLabyrinth().getEntrance().getName());
	}

}
