package game;

import infrastructure.Cell;
import infrastructure.Labyrinth;
import infrastructure.LabyrinthGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import creatures.Animator;
import creatures.Guard;
import creatures.Player;
import utils.Utils;

public class Levels {

	public static int MAX_LEVEL = 100;
	public static boolean levelChanged;
	public static ArrayList<Animator> animators = new  ArrayList<Animator>();
	
	
	public static Labyrinth genLabyrinth(int level, Player p) throws Exception {

		LabyrinthGenerator labyrinthGenerator = new LabyrinthGenerator();
		Labyrinth lab = labyrinthGenerator.generateLabyrinth(level);

		while (labyrinthGenerator.getPath().size() < (int) (lab.getDimension() * Math
				.log(lab.getDimension()))) {
			lab = labyrinthGenerator.generateLabyrinth(level);
		}
		p.setName("A");
		lab.setPlayer(p);
		p.setPosition(lab.getEntrance());

		Labyrinth l = labyrinthGenerator.generateDeadEndTunnels();
		ArrayList<ArrayList> subPaths = labyrinthGenerator.getSubPaths();
		
		if (subPaths.size() > 1) {
			Collections.sort(subPaths, new Comparator<ArrayList>() {
				public int compare(ArrayList a1, ArrayList a2) {
			        return a2.size() - a1.size(); //  biggest to smallest
			    }	
			});
			
			if(subPaths.get(0).size()>2){
			ArrayList<Cell> subPath = subPaths.get(0);
			Guard guard = new Guard("G", subPath.get(0), 1,1.0f);
			ArrayList<Player> guards = new ArrayList<Player>();
			guards.add(guard);
			
			Utils.addInverse(subPath);
			
			Animator anima = new Animator(guard, l, subPath, 3000);
			animators.add(anima);
			new Thread(anima).start();
			
//			Guard guard2 = new Guard("R", g, 1);
//			guards.add(guard2);
//			l.setGuards(guards);
//			ArrayList<Cell> path2 = new ArrayList<Cell>();
						
//			Animator anima2 = new Animator(guard2, l, path2, 1500);
//			new Thread(anima2).start();
			}
		}
		return l;
	}

	public static Labyrinth getLabyrinth(int level, Player p) {
		if (level == 1) {
			Cell first = new Cell(true, true, false, false, "A");
			Cell c2 = new Cell(true, false, false, true, "B");
			Cell c3 = new Cell(true, false, true, true, "C");
			Cell last = new Cell(false, false, true, true, "D");

			p.setPosition(first);

			ArrayList<Cell> labs = new ArrayList<Cell>();
			labs.add(first);
			labs.add(c2);
			labs.add(c3);
			labs.add(last);

			return new Labyrinth(labs, first, last, p);
		}
		if (level == 2) {
			Cell c1 = new Cell(true, false, true, false, "B");
			Cell c2 = new Cell(true, true, false, false, "C");
			Cell c3 = new Cell(true, false, false, true, "C");
			Cell entrance = new Cell(false, false, false, true, "A");
			Cell c5 = new Cell(true, true, true, false, "C");
			Cell c6 = new Cell(false, false, false, true, "C");
			Cell c7 = new Cell(false, true, true, false, "C");
			Cell c8 = new Cell(true, true, false, true, "C");
			Cell exit = new Cell(false, true, true, false, "D");

			p.setPosition(entrance); 

			ArrayList<Cell> labs = new ArrayList<Cell>();
			labs.add(c1);
			labs.add(c2);
			labs.add(c3);
			labs.add(entrance);
			labs.add(c5);
			labs.add(c6);
			labs.add(c7);
			labs.add(c8);
			labs.add(exit);

			return new Labyrinth(labs, entrance, exit, p);
		}

		return null;
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
