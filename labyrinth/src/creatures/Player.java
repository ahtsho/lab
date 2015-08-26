package creatures;

import infrastructure.Cell;

public class Player {
	private String name;
	private Cell position;
	private float life;
	private float maxLife;
//	private float damage=1;
	
	public Player(){}
	public Player(float lives){
		life = lives;
	}
	
	public Player(String aName, Cell aPosition, float lives){
		name = aName;
		position = aPosition;
		aPosition.addHost(this);
		life=lives;
		maxLife = lives;
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
	
	public void damage(float amount){
		if(life > 0){
			life -=amount;
//			if(damage>0){
//				damage-=amount; 
//			}else{
//				life -=1;
//				damage = 1;
//			}
		}
	}
	public float getLife() {
		return life;
	}
	
//	public float getDamage() {
//		return damage;
//	}
	public void heal(float healAmount) {
		if(life < maxLife){
			if(life + healAmount <= maxLife){
				life += healAmount;
			} else {
				life = maxLife;
			}
		}
	}

}
