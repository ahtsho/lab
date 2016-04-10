package creatures;

import infrastructure.Cell;
import interfaces.Bad;
import interfaces.Good;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import tools.Box;
import tools.Tool;

/**
 * <p>Given a player and a cell, the life manager cheks if the cell has tools or
 * creatures, and either hurs or heals the player depengin on the found objects.</p>
 * 
 * <p>Other thing the life manager does are: 
 * <ul>
 * <li>sets Player.hurt and Player.healed </li>
 * <li>remove used toools</li>
 * </ul>
 * </p>
 * @author at
 */
public class LifeManager {

	public void manage(Player player, Cell position) {
		CopyOnWriteArrayList<Creature> creatures = position.getHosts();
		CopyOnWriteArrayList<Tool> tools = position.getTools();

		if (creatures.size() > 1) {// assuming the first creature is the player
			for (int i = 0; i < creatures.size(); i++) {
				if (player.getPosition().equals(position) && !(creatures.get(i) instanceof Player)) {
					if (creatures.get(i) instanceof Bad ) {
						Player.hurt = player.damage(((Bad) creatures.get(i))
								.getCausedDamage());
						
					} else if (creatures.get(i) instanceof Good) {
						Player.healed = player.heal(((Good) creatures.get(i)).getHealAmount());
						
					}
				}
			}
		}
		if (tools.size() > 0) {
			for (int i = 0; i < tools.size(); i++) {
				if (player.getPosition().equals(position)) {
					// if the tool is a box then it will get the hidden object from here 
					Tool tool = null;
					try {
						tool = ((Box) tools.get(i)).extractObject();
					} catch (Exception e) {
						tool = tools.get(i);
					}

					if (tool instanceof Bad) {
						Player.hurt = player.damage(((Bad) tool).getCausedDamage());
						if(Player.hurt) tools.remove(tool);
					} else if (tool instanceof Good) {
						Player.healed = player.heal(((Good) tool).getHealAmount());
						if(Player.healed) tools.remove(tool);
					}
					
					try {
						((Box) tools.get(i)).removeObject(tool);
					} catch (Exception e) {}
				}
			}
		}
	}

}
