package creatures;

import infrastructure.Cell;
import interfaces.Bad;
import interfaces.Good;

import java.util.ArrayList;

import tools.Box;
import tools.Tool;

public class LifeManager {

	public void manage(Player player, Cell position) {
		ArrayList<Creature> creatures = position.getHosts();
		ArrayList<Tool> tools = position.getTools();
		if (creatures.size() > 1) {
			for (int i = 0; i < creatures.size(); i++) {
				if (player.getPosition().equals(position)) {
					if (creatures.get(i) instanceof Bad) {
						player.damage(((Bad) creatures.get(i))
								.getCausedDamage());
						System.out.println("Player hurt! Life="
								+ player.getLife());
					} else if (creatures.get(i) instanceof Good) {
						player.heal(((Good) creatures.get(i)).getHealAmount());
						System.out.println("Player cured! Life="
								+ player.getLife());
					}
				}
			}
		}
		if (tools.size() > 0) {
			for (int i = 0; i < tools.size(); i++) {
				if (player.getPosition().equals(position)) {
					Tool tool = null;
					try{
						tool = ((Box)tools.get(i)).extractObject();
					} catch (Exception e){
						tool = tools.get(i);
					}
					
					if (tool instanceof Bad) {
						player.damage(((Bad) tool).getCausedDamage());
						System.out.println("Player hurt! Life="
								+ player.getLife());
					} else if (tool instanceof Good) {
						player.heal(((Good) tool).getHealAmount());
						System.out.println("Player cured! Life="
								+ player.getLife());
					}
					tools.remove(tool);
					try{
					((Box)tools.get(i)).removeObject(tool);
					}catch(Exception e){}
				}
			}
		}
		
	}

}
