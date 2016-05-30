package game;

import infrastructure.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import creatures.*;
import tools.*;
import utils.Utils;

public class Level {

	public static int MAX_LEVEL = 12;
	public static int FIRST_LEVEL = 3;
	public static boolean levelChanged;
	public static int currentLevel = 3;
	public static ArrayList<Animator> animators = new ArrayList<Animator>();
	private static Player player = new Player("F", 3);
	private static LabyrinthGenerator labyrinthGenerator = null;
	
	public static ArrayList<Cell> getPath(){
		return labyrinthGenerator.getPath();
	}
	
	public static Labyrinth genLabyrinth() throws Exception {
		labyrinthGenerator = new LabyrinthGenerator();
		Labyrinth lab = labyrinthGenerator.generateLabyrinth(currentLevel);

		while (labyrinthGenerator.getPath().size() < (int) (lab.getDimension() * Math.log(lab.getDimension()))) {
			lab = labyrinthGenerator.generateLabyrinth(currentLevel);
		}

		player.setId(1);
		player.setPosition(lab.getEntrance());
		lab.setPlayer(player);
		
		System.out.println("THE PLAYER "+player.getName()+" POS="+player.getPosition()+" LEVEL="+currentLevel);
		
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

		ArrayList<Cell> subPath = subPaths.get(0); // taking the bigget subpath
													// for tools and creatures
		int pos1 = Utils.generateRandomNumber(subPath.size());
		int pos2 = Utils.generateRandomNumber(subPath.size());
		int pos3 = Utils.generateRandomNumber(subPath.size());
		int pos4 = Utils.generateRandomNumber(subPath.size());
		int pos5 = Utils.generateRandomNumber(subPath.size());
		int pos6 = Utils.generateRandomNumber(subPath.size());
		int pos7 = Utils.generateRandomNumber(subPath.size());
		int pos8 = Utils.generateRandomNumber(subPath.size());
		int pos9 = Utils.generateRandomNumber(subPath.size());
		int pos10 = Utils.generateRandomNumber(subPath.size());
		int pos11 = Utils.generateRandomNumber(subPath.size());

		switch (currentLevel - 2) {
		case 2:
			CopyOnWriteArrayList<Tool> ts1 = new CopyOnWriteArrayList<Tool>();
			ts1.add(new Plaster(.1f));
			subPath.get(pos1).setTools(ts1);
			CopyOnWriteArrayList<Tool> ts11 = new CopyOnWriteArrayList<Tool>();
			ts11.add(new Hole(1f));
			subPath.get(pos11).setTools(ts11);
			break;
		case 4:
			CopyOnWriteArrayList<Tool> ts2 = new CopyOnWriteArrayList<Tool>();
			ts2.add(new Medicine(.5f));
			subPath.get(pos2).setTools(ts2);
			break;
		case 5:
			CopyOnWriteArrayList<Tool> ts3 = new CopyOnWriteArrayList<Tool>();
			ts3.add(new Plaster(.1f));
			subPath.get(pos3).setTools(ts3);
			break;
		case 6:
			CopyOnWriteArrayList<Tool> ts4 = new CopyOnWriteArrayList<Tool>();
			ts4.add(new Hole(1f));
			subPath.get(pos4).setTools(ts4);
			break;
		case 7:
			CopyOnWriteArrayList<Tool> objs = new CopyOnWriteArrayList<Tool>();
			objs.add(new Heart(1f));
			objs.add(new Bomb(1f));
			CopyOnWriteArrayList<Tool> ts5 = new CopyOnWriteArrayList<Tool>();
			ts5.add(new Box(objs));
			subPath.get(pos5).setTools(ts5);
			break;
		case 8:
			CopyOnWriteArrayList<Tool> ts6 = new CopyOnWriteArrayList<Tool>();
			ts6.add(new Hole(1f));
			subPath.get(pos6).setTools(ts6);
			CopyOnWriteArrayList<Tool> ts7 = new CopyOnWriteArrayList<Tool>();
			ts7.add(new Plaster(.1f));
			subPath.get(pos7).setTools(ts7);
			CopyOnWriteArrayList<Tool> ts8 = new CopyOnWriteArrayList<Tool>();
			ts8.add(new Medicine(.5f));
			subPath.get(pos8).setTools(ts8);
			break;
		case 9:
			CopyOnWriteArrayList<Tool> ts9 = new CopyOnWriteArrayList<Tool>();
			ts9.add(new Heart(1f));
			subPath.get(pos9).setTools(ts9);
			break;
		case 10:
			CopyOnWriteArrayList<Tool> ts10 = new CopyOnWriteArrayList<Tool>();
			ts10.add(new Hole(1f));
			subPath.get(pos10).setTools(ts10);
			break;
		}

		if (currentLevel - 2 >= 3) {
			createGuard(lab, subPath, 2000, "G3", pos1);
		}
		if (currentLevel - 2 >= 5) {
			createGuard(lab, subPath, 1000, "G5", pos2);
		}
		if (currentLevel - 2 >= 8) {
			createGuard(lab, subPath, 800, "G8", pos3);
		}
		if (currentLevel - 2 >= 9) {
			createGuard(lab, subPath, 500, "G9", pos4);
		}

	}

	private static void createGuard(Labyrinth lab, ArrayList<Cell> subPath, int time, String name, int pos) {
		Creature guard = null;
		if (subPath.size() > 2) {

			// while(!subPath.get(pos).getHosts().isEmpty()){
			// pos = Utils.generateRandomNumber(subPath.size());
			// }
			guard = new Guard(name, subPath.get(pos), 1, 1.0f);
			if (guard != null) {
				CopyOnWriteArrayList<Creature> guards = new CopyOnWriteArrayList<Creature>();
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

	public static int goTo(int offset) {
		currentLevel += offset;
		return currentLevel;
	}
}
