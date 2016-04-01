package net.sumo.nextgen.task.fishing;

import net.sumo.sumoscript.enums.PlayerTask;
import net.sumo.sumoscript.task.Task;

public class WalkToBankFromFish extends Task {

	@Override
	public boolean active() {
		if (currentTask() == PlayerTask.FISH && !playerInArea(currentFishingAssignment().getBankArea()) && !shouldFish()) {
			return true;
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		r.STATE = "Walking to Bank";
		webWalk(currentFishingAssignment().getBankPos());
	}
}
