package test;

import model.game.Level;
import model.infrastructure.Labyrinth;

public class LabPopulationTest {

	public static void main (String[] args) throws Exception{
		Level.currentLevel=5;
		Labyrinth l =Level.genLabyrinth();
		Level.next();
		
	}
}
