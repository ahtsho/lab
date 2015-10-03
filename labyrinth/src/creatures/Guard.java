package creatures;

import infrastructure.Cell;
import interfaces.Bad;


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
		position = aposition;
		aposition.getHosts().add(this);
		name = aname;
		damageCaused = damage;
		life = lives;
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
