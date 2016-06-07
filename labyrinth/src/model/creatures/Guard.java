package model.creatures;

import model.infrastructure.Cell;
import model.interfaces.Bad;


public class Guard implements Bad, Creature {

	private float damageCaused;
	private String name;
	private Cell position;
	private float life;
	private int id; 
	public Guard() {
		super();
	}

	public Guard(String aname, Cell aposition, float lives, float damage) {
		name = aname;
		damageCaused = damage;
		life = lives;
		
		if(aposition!=null){
			position = aposition;
			aposition.getHosts().add(this);
		}
	}

	@Override
	public float getCausedDamage() {
		return damageCaused;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public float getLife() {
		return life;
	}

	@Override
	public Cell getPosition() {
		return position;
	}

	@Override
	public void setPosition(Cell destination) {
		position = destination;
		destination.addHost(this);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id=id;
	}

}
