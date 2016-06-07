package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import utils.Utils;
import model.creatures.Animator;
import model.creatures.Creature;
import model.creatures.Guard;
import model.infrastructure.Cell;
import model.infrastructure.Labyrinth;
import model.tools.Bomb;
import model.tools.Box;
import model.tools.Heart;
import model.tools.Hole;
import model.tools.Medicine;
import model.tools.Plaster;
import model.tools.Tool;

/**
 * Given a Level, generate the appropriate amount of creatures and tools and
 * scatter them on the sub paths.
 * 
 * @author at
 */
public class PopulationManager {
	public static ArrayList<Animator> animators = new ArrayList<Animator>();
	private ArrayList<Tool> toolBox = new ArrayList<Tool>();
	private HashMap<Creature,Integer> creatureBox = new HashMap<Creature,Integer>();
	private Labyrinth lab = null; 
	/**
	 * Strategy 1. generate 1 tool and 1 creature per sub path
	 * 
	 * @param lab
	 * @param subPaths
	 * @param currentLevel
	 */
	public PopulationManager(Labyrinth lab, ArrayList<ArrayList> subPaths, int level) {
		if(this.lab==null)
			this.lab = lab;
		
		if(level>3){
			toolBox.add(new Plaster(.1f));
			
			if(level>4){
				toolBox.add(new Medicine(.5f));
				creatureBox.put(new Guard("G3", null, 1, 1.0f),2000);
				
				if(level>5){
					toolBox.add(new Hole(1f));
					creatureBox.put(new Guard("G5", null, 1, 1.0f),1000);
					if(level>6){
						toolBox.add(new Box(generateHeartOrBomb()));
						
						if(level>7){
							toolBox.add(new Hole(1f));
							toolBox.add(new Plaster(.1f));
							toolBox.add(new Medicine(.5f));
							
							if(level>8){
								toolBox.add(new Heart(1f));
								creatureBox.put(new Guard("G8", null, 1, 1.0f),800);
								
								if(level>9){
									toolBox.add(new Hole(1f));
									creatureBox.put(new Guard("G9", null, 1, 1.0f),500);
								} 
							}
						}
					}
				}
			}
		} 

		
		generateToolsIntoSubPaths(subPaths);
		generateCreatureIntoSubPaths(subPaths);

	}

	private CopyOnWriteArrayList<Tool> generateHeartOrBomb() {
		CopyOnWriteArrayList<Tool> obj = new CopyOnWriteArrayList<Tool>();
		switch (Utils.generateRandomNumber(2)) {
		case 0:
			obj.add(new Heart(1f));
			break;
		case 1:
			obj.add(new Bomb(1f));
		default:
			break;
		}
		return obj;
	}

	private void generateToolsIntoSubPaths(ArrayList<ArrayList> subPaths) {
		for (int t = 0; t < toolBox.size(); t++) {
			if (subPaths.size() > t) {
				CopyOnWriteArrayList<Tool> tools = new CopyOnWriteArrayList<Tool>();
				tools.add((Tool) toolBox.get(t));
				((Cell) subPaths.get(t).get(
						Utils.generateRandomNumber(subPaths.get(t).size())))
						.setTools(tools);
			} else {
				break;
			}
		}
	}

	private void generateCreatureIntoSubPaths(ArrayList<ArrayList> subPaths) {
		Iterator iterCreatureBox = creatureBox.entrySet().iterator();
		int paths = 0;
		while(iterCreatureBox.hasNext()){
			Map.Entry creature_timeMap = (Entry) iterCreatureBox.next();
			if (subPaths.size() > paths) {
				int pos = Utils.generateRandomNumber(subPaths.get(paths).size());
				ArrayList<Cell> subPath = subPaths.get(paths);
				Creature guard = (Creature) creature_timeMap.getKey();
				int time = (Integer) creature_timeMap.getValue();
				if (guard != null) {
					guard.setPosition((Cell) subPath.get(pos));
					CopyOnWriteArrayList<Creature> guards = new CopyOnWriteArrayList<Creature>();
					guards.add(guard);
					((Cell) subPath.get(pos)).setHosts(guards);
				}
				Utils.addInverse(subPath);
				Animator animator = new Animator(guard, lab, subPath, time);
				animators.add(animator);
				new Thread(animator).start();
				paths++;
			} else {
				break;
			}
		}
	}
	

}
