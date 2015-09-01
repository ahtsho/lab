package creatures;

import infrastructure.Cell;
import interfaces.Bad;
import interfaces.Good;

import java.util.ArrayList;

public class LifeManager {

	public void manage(Player player, Cell position) {
		ArrayList<Creature> players = position.getHosts();
		if (players.size() > 1) {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i) instanceof Bad) {
					player.damage(((Bad) players.get(i)).getCausedDamage());
					System.out.println("Player hurt! Life="+player.getLife());
				} else if (players.get(i) instanceof Good) {
					player.heal(((Good) players.get(i)).getHealAmount());
					System.out.println("Player cured! Life="+player.getLife());
				}
			}
		}
	}

}
