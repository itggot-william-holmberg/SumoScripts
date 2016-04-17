package net.sumo.nextgen.task.cooking;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToCook extends Task {

	@Override
	public boolean active() {
		if (!playerInArea(currentCookingAssignment().getCookingArea())
				&& shouldCook()  && readyToCook()) {
			return true;
		}
		return false;
	}

	public void execute() {
		Resources.CURRENT_STATE = "Walking to cooking area";
		webWalk(currentCookingAssignment().getCookingArea().getRandomPosition());
	}
}
