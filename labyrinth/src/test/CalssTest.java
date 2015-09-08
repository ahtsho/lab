package test;

import interfaces.Bad;

import java.util.ArrayList;

import creatures.Creature;
import creatures.Guard;
import creatures.Player;

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
