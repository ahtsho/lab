package model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;

import model.creatures.*;
import model.infrastructure.*;
import model.tools.*;
import controllers.LabyrinthGenerator;
import controllers.PopulationManager;
import utils.Utils;

public class Level {

	public static int MAX_LEVEL = 10;
	public static int FIRST_LEVEL = 3;
	public static boolean levelChanged;
	public static int currentLevel = 3;
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
				
		Labyrinth genLabyrinth = labyrinthGenerator.generateDeadEndTunnels();
		ArrayList<ArrayList> subPaths = labyrinthGenerator.getSubPaths();
		if (subPaths.size() > 1) {
			Collections.sort(subPaths, new Comparator<ArrayList>() {
				public int compare(ArrayList a1, ArrayList a2) {
					return a2.size() - a1.size(); // biggest to smallest
				}
			});
			//populate(genLabyrinth, subPaths);
			new PopulationManager(genLabyrinth, subPaths, currentLevel);
		}

		return genLabyrinth;
	}




	public static boolean isLast() {
		if (currentLevel > MAX_LEVEL)
			return true;
		return false;
	}

	public static int next() {
		if(currentLevel<=MAX_LEVEL){
			currentLevel++;
		}
		return currentLevel;
	}

	public static int previous() {
		if(currentLevel>0){
			currentLevel--;
		}
		return currentLevel;
	}

	public static int goTo(int offset) {
		if(currentLevel+offset>=0 && currentLevel+offset<=MAX_LEVEL){
			currentLevel += offset;
		}
		return currentLevel;
	}
}
