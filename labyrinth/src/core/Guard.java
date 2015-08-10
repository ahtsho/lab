package core;

public class Guard {
	String name;
	Cell position;
	private int life;
	private float damage=1;
	
	public Guard(){}
	public Guard(int lives){
		life = lives;
	}
	
	public Guard(String aname, Cell aposition, int lives){
		name=aname;
		position=aposition;
		life=lives;
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
	}
	
	public void damage(float amount){
		if(life > 0){
			if(damage>0){
				damage-=amount; 
			}else{
				life -=1;
				damage = 1;
			}
		}
	}
	public int getLife() {
		return life;
	}
	
	public float getDamage() {
		return damage;
	}

}
