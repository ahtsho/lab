package tools;

import interfaces.Good;

public class Medicine implements Good{

	private float healingPower; 
	
	public Medicine(float healingAmount){
		healingPower = healingAmount;
	}
	@Override
	public float getHealAmount() {
		return healingPower;
	}

}
