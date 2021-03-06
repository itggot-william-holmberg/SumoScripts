package net.sumo.nextgen.task.fishing;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToBankFromFish extends Task {

	@Override
	public boolean active() {
		if (!playerInArea(WebBank.getNearest(s).getArea()) && shouldFish() && !readyToFish()) {
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
