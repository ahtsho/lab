package tools;

import interfaces.Good;

public class Heart implements Good,Tool {

	private float healingPower; 
	
	public Heart(float healingAmount){
		healingPower = healingAmount;
	}
	@Override
	public float getHealAmount() {
		return healingPower;
	}
	@Override
	public String getName() {
		return "<v>";
	}


}
