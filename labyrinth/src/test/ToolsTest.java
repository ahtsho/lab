package test;

import infrastructure.Cell;
import infrastructure.Labyrinth;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import tools.Bomb;
import tools.Box;
import tools.Heart;
import tools.Medicine;
import tools.Plaster;
import tools.Tool;
import utils.Utils;
import view.Console;
import creatures.Player;

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
