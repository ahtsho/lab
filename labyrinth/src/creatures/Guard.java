package creatures;

import infrastructure.Cell;
import interfaces.Bad;


public class Guard extends Player implements Bad {

	private float damageCaused;
	public Guard() {
		super();
	}

	public Guard(int lives) {
		super(lives);
	}

	public Guard(String aname, Cell aposition, int lives, float damage) {
		super(aname, aposition, lives);
		damageCaused = damage;
	}

	@Override
	public float getCausedDamage() {
		return damageCaused;
	}

}
