package net.sumo.nextgen.task.miner;

import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WithdrawPickaxe extends Task {

	@Override
	public boolean active() {
		if (shouldMine() && playerInArea(Areas.BANK_AREA_DRAYNOR) && !playerHasPickaxe()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		String axeName = currentPickaxe();
		Resources.CURRENT_STATE = "Banking";
		if (!inventoryContains(axeName)) {
			withdraw(axeName);
		}
	}

}
