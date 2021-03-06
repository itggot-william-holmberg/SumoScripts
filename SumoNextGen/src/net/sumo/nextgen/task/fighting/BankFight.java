package net.sumo.nextgen.task.fighting;

import java.util.Arrays;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class BankFight extends Task {
	public boolean active() {
		if (shouldFight() && playerInArea(getClosestBank())
				&& !shouldFight(getCurrentGear()) && Resources.BUY_LIST.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Banking";
		if (!Resources.WITHDRAW_LIST.isEmpty()) {
			s.log("lets withdraw items");
			withdrawNeededItems();
		} else if(s.inventory.isFull()){
			depositAll();
		}
		else if (currentFightingAssignment().getInventory() != null && !invContains(currentFightingAssignment().getInventory())){
			Arrays.asList(currentFightingAssignment().getInventory()).forEach(item -> {
				s.log(item);
				if(!invContains(item)){
					if(item.contains("rune")){
						withdrawNeededItems(15000, item);
					}
				}
			});		
		} 
		else if (currentFightingAssignment().getEat() == true && !inventoryContains("Trout")){
				withdrawNeededItems(20, "Trout");		
		} else if(invContains("coins")){
			depositAll("Coins");
		}
	}

}
