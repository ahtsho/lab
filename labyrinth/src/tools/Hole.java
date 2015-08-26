package tools;

import interfaces.Bad;

public class Hole implements Bad {

	private float damageCaused;

	public Hole(float damage){
		damageCaused = damage;
	}
	@Override
	public float getCausedDamage() {
		return damageCaused;
	}

}
