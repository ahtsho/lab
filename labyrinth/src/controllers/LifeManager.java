package controllers;

import java.util.concurrent.CopyOnWriteArrayList;

import model.creatures.Creature;
import model.creatures.Player;
import model.infrastructure.Cell;
import model.interfaces.Bad;
import model.interfaces.Good;
import model.tools.Box;
import model.tools.Tool;

/**
 * <p>Given a player and a cell, the life manager checks if the cell has tools or
 * creatures, and either hurts or heals the player depending on the found objects.</p>
 * 
 * <p>Other thing the life manager does are: 
 * <ul>
 * <li>sets Player.hurt and Player.healed </li>
 * <li>remove used tools</li>
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
