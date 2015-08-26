package tools;

import interfaces.Good;

public class Heart implements Good {

	private float healingPower; 
	
	public Heart(float healingAmount){
		healingPower = healingAmount;
	}
	@Override
	public float getHealAmount() {
		return healingPower;
	}


}
