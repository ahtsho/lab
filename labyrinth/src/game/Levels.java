package game;

import infrastructure.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import creatures.*;
import tools.*;
import utils.Utils;

public class Levels {

	public static int MAX_LEVEL = 100;
	public static int FIRST_LEVEL = 3;
	public static boolean levelChanged;
	public static ArrayList<Animator> animators = new ArrayList<Animator>();
	private static ArrayList<Tool> tools = new ArrayList<Tool>();
	//private static ArrayList<Creature> creatures = new ArrayList<Creature>();
	
	public static Labyrinth genLabyrinth(int level) throws Exception {

		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		Labyrinth lab = labyrinthGenerator.generateLabyrinth(level);

		while (labyrinthGenerator.getPath().size() < (int) (lab.getDimension() * Math
				.log(lab.getDimension()))) {
			lab = labyrinthGenerator.generateLabyrinth(level);
		}

////		p.setName("A");
//		lab.setPlayer(p);
//		p.setPosition(lab.getEntrance());

		Labyrinth l = labyrinthGenerator.generateDeadEndTunnels();
		ArrayList<ArrayList> subPaths = labyrinthGenerator.getSubPaths();
		if (subPaths.size() > 1) {
			Collections.sort(subPaths, new Comparator<ArrayList>() {
				public int compare(ArrayList a1, ArrayList a2) {
					return a2.size() - a1.size(); // biggest to smallest
				}
			});
		}
		populate(l, level, subPaths);

		return l;
	}

	private static void populate(Labyrinth lab, int level,ArrayList<ArrayList> subPaths) {
		ArrayList<Cell> subPath = subPaths.get(0);
		if (level == FIRST_LEVEL+1) {
			tools.add(new Plaster(.1f));
		}
		if (level == FIRST_LEVEL+2) {
			Guard guard = createGuard(lab, subPath);
			if(guard!=null){
				ArrayList<Creature> guards = new ArrayList<Creature>();
				guards.add(guard);
				subPath.get(0).setHosts(guards);
			}
		}
		if (level == FIRST_LEVEL+3) {
			tools.add(new Medicine(.5f));
			Guard guard = createGuard(lab, subPath);
			if(guard!=null){
				ArrayList<Creature> guards = new ArrayList<Creature>();
				guards.add(guard);
				subPath.get(0).setHosts(guards);
			}
		}
		if (level == FIRST_LEVEL+4) {
			tools.add(new Plaster(.1f));
		}
		subPath.get(0).setTools(tools);
		

	}

	

	private static Guard createGuard(Labyrinth lab, ArrayList<Cell> subPath) {
		Guard guard = null;
		if(subPath.size()>2){
			guard = new Guard("G", subPath.get(0), 1, 1.0f);
			Utils.addInverse(subPath);
			Animator anima = new Animator(guard, lab, subPath, 5000);
			animators.add(anima);
			new Thread(anima).start();
		}
		return guard;
	}


	public static boolean isLast(int level) {
		if (level > MAX_LEVEL)
			return true;
		return false;
	}

	public static int next(int currentLevel) {
		return (currentLevel + 1);
	}

}
