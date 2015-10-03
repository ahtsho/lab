package creatures;

import infrastructure.Cell;

/**
 * A creature should have
 * <ul>
 * <li>Name</li>
 * <li>Life</li>
 * <li>Position</li>
 * </ul>
 * 
 * @author at
 * 
 */
public interface Creature {

	public String getName();

	public int getId();

	public float getLife();

	public Cell getPosition();

	public void setPosition(Cell destination);

	void setId(int id);

}
