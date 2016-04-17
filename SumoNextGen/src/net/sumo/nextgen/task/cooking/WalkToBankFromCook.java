package net.sumo.nextgen.task.cooking;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToBankFromCook extends Task {

	@Override
	public boolean active() {
		if (!playerInArea(WebBank.getNearest(s).getArea()) && shouldCook() && !readyToCook()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to Bank";
		webWalk(currentFishingAssignment().getBankPos());
	}
}
