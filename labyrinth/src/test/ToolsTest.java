package test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import model.creatures.Player;
import model.infrastructure.Cell;
import model.infrastructure.Labyrinth;
import model.tools.Bomb;
import model.tools.Box;
import model.tools.Heart;
import model.tools.Medicine;
import model.tools.Plaster;
import model.tools.Tool;
import utils.Utils;
import view.Console;

public class ToolsTest {
	public static void main(String[] args) {
		positionPlasterRandomlyInPath();
	}

	private static void positionPlasterRandomlyInPath() {
		Test test = new Test();
		Labyrinth l = test.lab;

		Player p = new Player();
		p.setName("F");
		p.setPosition(l.getEntrance());
		l.setPlayer(p);

		CopyOnWriteArrayList<Cell> path = new CopyOnWriteArrayList<Cell>();
		path.add(test.a);
		path.add(test.b);
		path.add(test.c);
		
		

		Console console = new Console(l);

		while (true) {
			int pos = Utils.generateRandomNumber(path.size());
			CopyOnWriteArrayList<Tool> tools = new CopyOnWriteArrayList<Tool>();
			tools.add(new Plaster(.1f));
			path.get(pos).setTools(tools);
			console.draw();
			path.get(pos).removeTools();
			try{
				Thread.sleep(3000);
			}catch(Exception e){
				System.out.println(e);
			} 
		}
	}

}
