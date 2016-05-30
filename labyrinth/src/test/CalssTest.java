package test;

import java.util.ArrayList;

import model.creatures.Creature;
import model.creatures.Guard;
import model.creatures.Player;
import model.interfaces.Bad;

public class CalssTest {

	public static void main(String[] a){
		Creature c = new Player();
		System.out.println(c instanceof Player);
		ArrayList<Creature> cs = new ArrayList<Creature>();
		cs.add(new Player());
		cs.add(new Guard());
		
		for (Creature b: cs){
			System.out.println("Player="+(b instanceof Player) + " - Guard="+(b instanceof Guard)+ " - Bad="+(b instanceof Bad));
		}
	}
}
