package tools;

import interfaces.Bad;

public class Bomb implements Tool, Bad {
	private float damageCaused;

	public Bomb(float damage){
		damageCaused = damage;
	}
	
	@Override
	public float getCausedDamage() {
		return damageCaused;
	}

	@Override
	public String getName() {
		return "*";
	}

}
