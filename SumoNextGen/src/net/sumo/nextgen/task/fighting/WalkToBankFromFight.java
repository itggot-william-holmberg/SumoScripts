package net.sumo.nextgen.task.fighting;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToBankFromFight extends Task {

	@Override
	public boolean active() {
		if (shouldFight() && !playerInArea(getClosestBank()) && !shouldFight(currentFightingAssignment().getGear())) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to Bank";
		webWalk(getClosestBank().getRandomPosition());
	}
}
