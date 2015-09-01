package tools;

import interfaces.Good;

public class Plaster implements Tool,Good {
	private float healingPower; 
	
	public Plaster(float healingAmount){
		healingPower = healingAmount;
	}
	@Override
	public float getHealAmount() {
		return healingPower;
	}
	@Override
	public String getName() {
		return "X";
	}

}
