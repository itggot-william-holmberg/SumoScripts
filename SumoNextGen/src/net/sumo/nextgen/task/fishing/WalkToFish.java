package net.sumo.nextgen.task.fishing;

import net.sumo.sumoscript.enums.PlayerTask;
import net.sumo.sumoscript.task.Task;

public class WalkToFish extends Task {

	@Override
	public boolean active() {
		if (currentTask() == PlayerTask.FISH && !playerInArea(currentFishingAssignment().getFishingArea())
				&& shouldFish()) {
			return true;
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		r.STATE = "Walking to Fishing area";
		webWalk(currentFishingAssignment().getFishingArea().getRandomPosition());
	}
}
