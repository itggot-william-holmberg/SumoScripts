package net.sumo.nextgen.task.fighting;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class BankFight extends Task {
	public boolean active() {
		if (shouldFight() && playerInArea(getClosestBank())
				&& !shouldFight(currentFightingAssignment().getGear())) {
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
		} else if (needToDeposit()) {
			depositAllExcept(currentFightingAssignment().getInventory());
		}

	}

}
