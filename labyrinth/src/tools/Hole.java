package tools;

import creatures.Player;
import game.Level;
import interfaces.Bad;

public class Hole implements Bad,Tool {

	private float damageCaused;

	public Hole(float damage){
		damageCaused = damage;
	}
	@Override
	public float getCausedDamage() {
		Level.goTo(-2);
		Level.levelChanged=true;
		Player.fell=true;
		return damageCaused;
	}
	@Override
	public String getName() {
		return "O";
	}

}
