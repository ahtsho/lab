package test;

import view.Console;
import core.Cell;
import core.Labyrinth;
import core.LabyrinthGenerator;
import core.Player;

public class LabyrinthGeneratorTest {

	public static void main(String[] args) {
		LabyrinthGenerator labGen = new LabyrinthGenerator();
		testGenerateFull(labGen);
		testChooseEntrance(labGen);
		
		Console console = new Console(labGen.getLabyrinth());
		Player p = new Player();
		p.setName("A");
		labGen.getLabyrinth().setPlayer(p);
		p.setPosition(labGen.getLabyrinth().getEntrance());
		console.draw();
		 try {
			testdig(labGen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 console.draw();
	}

	public static void testGenerateFull(LabyrinthGenerator lg) {
		System.out.println("-BEGIN------------generateFull Test-------------");
		lg.generateFull(3);
		Labyrinth g = lg.getLabyrinth();
		System.out.println("TEST 1");
		System.out.println("EXPECTED: 9 cells");
		System.out.println("FOUND   : "+g.getCells().size()+" cells");
		System.out.println("TEST 2");
		System.out.println("EXPECTED: all walls are closed");
		
		boolean openWallfound = false;
		for (int i = 0; i < g.getCells().size(); i++) {
			if(!g.getCells().get(i).isNorth() ||
					!g.getCells().get(i).isSouth()||
					!g.getCells().get(i).isWest()||
					!g.getCells().get(i).isEast()){
				openWallfound = true;
			}
		}
		
		if(openWallfound){
			System.out.println("FOUND   : open wall found");
		} else {
			System.out.println("FOUND   : all walls are closed");
		}
		
		System.out.println("-END------------generateFull Test-------------");
	}

	public static void testChooseEntrance(LabyrinthGenerator lg) {
		System.out.println("-BEGIN------------chooseEntrance Test-------------");
		Cell c = lg.chooseEntrance();
		System.out.println("TEST 1");
		System.out.println("EXPECTED: one of {0,1,2,3,4,6,7,8} chosen as entrance");
		System.out.println("FOUND   : "+c.getName());
		System.out.println("TEST 2");
		System.out.println("EXPECTED cell equals entrance");
		System.out.println("FOUND   : "+c.equals(lg.getLabyrinth().getEntrance()));
		System.out.println("TEST 3");
		System.out.println("EXPECTED only one of the external walls is open");
		try {
			System.out.println("FOUND   : "+c.getFirstOpenWallNSWE());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-END------------chooseEntrance Test-------------");
		
	}
	
	public static void testdig(LabyrinthGenerator lg) throws Exception {
		System.out.println("-BEGIN------------dig Test-------------");
		System.out.println("TEST 1: choose new existing cell to dig up to");
		System.out.println(Constants.EXPECTED+" from cell 4 dig up, get to cell 1, breaking 4N and 1S");
		Cell next = lg.dig(lg.getLabyrinth().getCells().get(4), Labyrinth.NORTH);
		System.out.println(Constants.FOUND+" nextcell="+next.getName()+" 4N="+lg.getLabyrinth().getCells().get(4).isNorth()+", 1S="+next.isSouth());
		System.out.println("TEST 2: dig out");
		System.out.println(Constants.EXPECTED+" from cell 8 dig east, get null, breaking 8E");
		Cell next2 = lg.dig(lg.getLabyrinth().getCells().get(8), Labyrinth.EAST);
		System.out.println(Constants.FOUND+" nextcell="+next2+" 8E="+lg.getLabyrinth().getCells().get(8).isEast());
		System.out.println("TEST 3: dig an already visited cell");
		System.out.println(Constants.EXPECTED+" from cell 2 dig west, cannot go to cell 1, becaues it's in the path, so tries to go N or E going out, or S to 5 breaking 2S and 5N");
		lg.getPath().add(lg.getLabyrinth().getCells().get(1));
		Cell next3 = lg.dig(lg.getLabyrinth().getCells().get(2), Labyrinth.WEST);
		System.out.print(Constants.FOUND+" nextcell=");
		if(next3!=null){
			System.out.println(next3.getName()+" 2S="+lg.getLabyrinth().getCells().get(2).isSouth()+", 5N="+next3.isNorth());
		}else{
			System.out.println(next3+" 2N="+lg.getLabyrinth().getCells().get(2).isNorth()+", 2E="+lg.getLabyrinth().getCells().get(2).isEast());
		}
		System.out.println("-END------------dig Test-------------");
	}
}
