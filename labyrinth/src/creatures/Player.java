package creatures;

import java.math.BigDecimal;
import java.math.RoundingMode;

import infrastructure.Cell;

public class Player implements Creature {
	private String name;
	private Cell position;
	private static float life;
	private float maxLife;
	public static boolean hurt; 
	public static boolean healed;
	
	public Player(){}
	public Player(String aName, float lives){
		name = aName;
		life=lives;
		maxLife = lives;
		hurt = false;
		healed = false;
	}
	
	public Player(String aName, Cell aPosition, float lives){
		name = aName;
		position = aPosition;
		aPosition.addHost(this);
		life=lives;
		maxLife = lives;
		hurt = false;
		healed = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cell getPosition() {
		return position;
	}
	public void setPosition(Cell position) {
		this.position = position;
		position.addHost(this);
	}
	
	public synchronized void damage(float amount){
		if(life > 0){
			System.out.print("damage caused = "+life+" - ");
			life = life - amount;
			System.out.println("amount = "+amount+" = "+life);
			if(life < 0) life = 0;
		}
	}
	
	public void heal(float healAmount) {
		if(life < maxLife){
			if(life + healAmount <= maxLife){
				life += healAmount;
			} else {
				life = maxLife;
			}
		}
	}
	@Override
	public synchronized float getLife() {
		BigDecimal bd = new BigDecimal(life);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		life = bd.floatValue();
		return life;
	}

}
