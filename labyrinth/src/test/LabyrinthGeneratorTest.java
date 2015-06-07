package test;

import java.util.ArrayList;

import core.Cell;
import core.Labyrinth;
import core.LabyrinthGenerator;

public class LabyrinthGeneratorTest {

	public static void main(String[] args) {
		LabyrinthGenerator labGen = new LabyrinthGenerator();
		testGenerateFull(labGen);
		testChooseEntrance(labGen);
	}

	public static void testGenerateFull(LabyrinthGenerator lg) {
		System.out.println("-BEGIN------------generateFull Test-------------");
		lg.generateFull(3);
		Labyrinth g = lg.getGenLabyrinth();
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
		System.out.print("FOUND   : (in 10 trials) ");
		for(int i = 0; i < 10 ; i++){
			System.out.print(lg.chooseEntrance().getName()+", ");
		}
		System.out.println();
		
		System.out.println("FOUND   : "+lg.getGenLabyrinth().getEntrance());
		System.out.println("-END------------chooseEntrance Test-------------");
	}
}
