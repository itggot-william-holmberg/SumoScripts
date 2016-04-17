package net.sumo.nextgen.task.fishing;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToFish extends Task {

	@Override
	public boolean active() {
		if (!playerInArea(currentFishingAssignment().getFishingArea())
				&& shouldFish()  && readyToFish()) {
			return true;
		}
		return false;
	}

	public void execute() {
		Resources.CURRENT_STATE = "Walking to Fishing area";
		webWalk(currentFishingAssignment().getFishingArea().getRandomPosition());
	}
}
