package tools;

import interfaces.Good;

public class Plaster implements Good {
	private float healingPower; 
	
	public Plaster(float healingAmount){
		healingPower = healingAmount;
	}
	@Override
	public float getHealAmount() {
		return healingPower;
	}

}
