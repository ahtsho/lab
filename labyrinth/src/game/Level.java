package game;

import infrastructure.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import creatures.*;
import tools.*;
import utils.Utils;

public class Level {

	public static int MAX_LEVEL = 13;
	public static int FIRST_LEVEL = 3;
	public static boolean levelChanged;
	public static ArrayList<Animator> animators = new ArrayList<Animator>();
	private static ArrayList<Tool> tools = new ArrayList<Tool>();

	public static Labyrinth genLabyrinth(int level) throws Exception {

		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		Labyrinth lab = labyrinthGenerator.generateLabyrinth(level);

		while (labyrinthGenerator.getPath().size() < (int) (lab.getDimension() * Math
				.log(lab.getDimension()))) {
			lab = labyrinthGenerator.generateLabyrinth(level);
		}

		Labyrinth genLabyrinth = labyrinthGenerator.generateDeadEndTunnels();
		ArrayList<ArrayList> subPaths = labyrinthGenerator.getSubPaths();
		if (subPaths.size() > 1) {
			Collections.sort(subPaths, new Comparator<ArrayList>() {
				public int compare(ArrayList a1, ArrayList a2) {
					return a2.size() - a1.size(); // biggest to smallest
				}
			});
			populate(genLabyrinth, level, subPaths);
		}
		

		return genLabyrinth;
	}

	private static void populate(Labyrinth lab, int level, ArrayList<ArrayList> subPaths) {
		level -= FIRST_LEVEL-1;
		ArrayList<Cell> subPath = subPaths.get(0); // taking the bigget subpath for tools and creatures
		
		if (level == 2) {
			tools.add(new Plaster(.1f));
		}
		if (level == 3) {
			createGuard(lab, subPath, 4000, "G");
		}
		if (level == 4) {
			tools.add(new Medicine(.5f));
			createGuard(lab, subPath,4000, "G");
		}
		if (level == 5) {
			tools.add(new Plaster(.1f));
			createGuard(lab, subPath,4000,"G");
			createGuard(lab, subPath,3000, "g");
		}
		if (level == 6) {
			tools.add(new Hole(1f));
			createGuard(lab, subPath,4000,"G");
			createGuard(lab, subPath,3000, "g");
		}
		if (level == 7) {
			ArrayList<Tool> objs = new ArrayList<Tool>();
			objs.add(new Heart(1f));
			objs.add(new Bomb(1f));
			tools.add(new Box(objs));
			createGuard(lab, subPath,3000,"G");
			createGuard(lab, subPath,2000,"g");
		}
		if (level == 8) {
			tools.add(new Plaster(.1f));
			tools.add(new Medicine(.5f));
			createGuard(lab, subPath,3000,"G");
			createGuard(lab, subPath,2000,"g");
			createGuard(lab, subPath,1500, "K");
		}
		if (level == 9) {
			tools.add(new Heart(1f));
			createGuard(lab, subPath,3000,"G");
			createGuard(lab, subPath,1500,"g");
			createGuard(lab, subPath,1000, "K");
			createGuard(lab, subPath,700, "I");
		}
		if (level == 10) {
			tools.add(new Hole(1f));
			createGuard(lab, subPath,3000,"G");
			createGuard(lab, subPath,1500,"g");
			createGuard(lab, subPath,1000, "K");
			createGuard(lab, subPath,700, "I");
		}
		for (Tool t: tools){
			ArrayList<Tool> ts = new ArrayList<Tool>();
			ts.add(t);
			subPath.get(Utils.generateRandomNumber(subPath.size())).setTools(ts);
		}

	}

	private static void createGuard(Labyrinth lab, ArrayList<Cell> subPath, int time, String name) {
		Guard guard = null;
		if (subPath.size() > 2) {
			int pos = 0;
			while(!subPath.get(pos).getHosts().isEmpty()){
				pos = Utils.generateRandomNumber(subPath.size());
			}
			guard = new Guard(name, subPath.get(pos), 1, 1.0f);
			if (guard != null) {
				ArrayList<Creature> guards = new ArrayList<Creature>();
				guards.add(guard);
				subPath.get(pos).setHosts(guards);
			}
			Utils.addInverse(subPath);
			Animator animator = new Animator(guard, lab, subPath, time);
			animators.add(animator);
			new Thread(animator).start();
		}
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
