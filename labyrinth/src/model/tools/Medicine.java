package model.tools;

import model.interfaces.Good;

public class Medicine implements Good, Tool{

	private float healingPower; 
	
	public Medicine(float healingAmount){
		healingPower = healingAmount;
	}
	@Override
	public float getHealAmount() {
		return healingPower;
	}
	@Override
	public String getName() {
		return "+";
	}

}
