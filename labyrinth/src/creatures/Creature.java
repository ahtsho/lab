package creatures;

import infrastructure.Cell;

public interface Creature {

	public String getName();

	public float getLife();

	public Cell getPosition();

	public void setPosition(Cell destination);

}
