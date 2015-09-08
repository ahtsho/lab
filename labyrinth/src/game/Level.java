package game;

import infrastructure.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import creatures.*;
import tools.*;
import utils.Utils;

public class Level {

	public static int MAX_LEVEL = 12;
	public static int FIRST_LEVEL = 3;
	public static boolean levelChanged;
	public static int currentLevel=3;
	public static ArrayList<Animator> animators = new ArrayList<Animator>();
	private static ArrayList<Tool> tools = new ArrayList<Tool>();

	public static Labyrinth genLabyrinth() throws Exception {

		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		Labyrinth lab = labyrinthGenerator.generateLabyrinth(currentLevel);

		while (labyrinthGenerator.getPath().size() < (int) (lab.getDimension() * Math
				.log(lab.getDimension()))) {
			lab = labyrinthGenerator.generateLabyrinth(currentLevel);
		}

		Labyrinth genLabyrinth = labyrinthGenerator.generateDeadEndTunnels();
		ArrayList<ArrayList> subPaths = labyrinthGenerator.getSubPaths();
		if (subPaths.size() > 1) {
			Collections.sort(subPaths, new Comparator<ArrayList>() {
				public int compare(ArrayList a1, ArrayList a2) {
					return a2.size() - a1.size(); // biggest to smallest
				}
			});
			populate(genLabyrinth, subPaths);
		}
		

		return genLabyrinth;
	}

	private static void populate(Labyrinth lab, ArrayList<ArrayList> subPaths) {
		
		ArrayList<Cell> subPath = subPaths.get(0); // taking the bigget subpath for tools and creatures
		int pos1 =Utils.generateRandomNumber(subPath.size());
		int pos2 =Utils.generateRandomNumber(subPath.size());
		int pos3 =Utils.generateRandomNumber(subPath.size());
		int pos4 =Utils.generateRandomNumber(subPath.size());
		
		switch (currentLevel-2){
		case 2:
			ArrayList<Tool> ts1 = new ArrayList<Tool>();
			ts1.add(new Plaster(.1f));
			subPath.get(pos1).setTools(ts1);
			
//			tools.add(new Plaster(.1f));			
			break;
		case 4:
			ArrayList<Tool> ts2 = new ArrayList<Tool>();
			ts2.add(new Medicine(.5f));
			subPath.get(pos2).setTools(ts2);
			
//			tools.add(new Medicine(.5f));
			break;
		case 5:
			tools.add(new Plaster(.1f));
			break;
		case 6:
			tools.add(new Hole(1f));
			break;
		case 7:
			ArrayList<Tool> objs = new ArrayList<Tool>();
			objs.add(new Heart(1f));
			objs.add(new Bomb(1f));
			tools.add(new Box(objs));
			break;
		case 8:
			tools.add(new Plaster(.1f));
			tools.add(new Medicine(.5f));
			break;
		case 9:
			tools.add(new Heart(1f));
			break;
		case 10:
			tools.add(new Hole(1f));
			break;
		}
		
		if (currentLevel-2 >= 3) {
			createGuard(lab, subPath, 3000, "G3",pos1);
		}
		if (currentLevel-2 >= 5) {
			createGuard(lab, subPath,2000, "G5", pos2);
		}
		if (currentLevel-2 >= 8) {
			createGuard(lab, subPath,1500, "G8", pos3);
		}
		if (currentLevel-2 >= 9) {
			createGuard(lab, subPath,700, "G9", pos4);
		}
		
//		for (Tool t: tools){
//			ArrayList<Tool> ts = new ArrayList<Tool>();
//			ts.add(t);
//			subPath.get().setTools(ts);
//		}

	}

	private static void createGuard(Labyrinth lab, ArrayList<Cell> subPath, int time, String name, int pos) {
		Creature guard = null;
		if (subPath.size() > 2) {
			
//			while(!subPath.get(pos).getHosts().isEmpty()){
//				pos = Utils.generateRandomNumber(subPath.size());
//			}
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

	public static boolean isLast() {
		if (currentLevel > MAX_LEVEL)
			return true;
		return false;
	}

	public static int next() {
		return (currentLevel++);
	}

	public static int previous() {
		return (currentLevel++);
	}
	
	public static int goTo(int offset){
		currentLevel +=offset;
		return currentLevel;
	}
}

