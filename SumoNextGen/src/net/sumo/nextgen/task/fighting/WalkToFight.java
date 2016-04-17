package net.sumo.nextgen.task.fighting;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToFight extends Task {

	@Override
	public boolean active() {
		if (shouldFight() && !playerInArea(currentFightingAssignment().getFightArea())
				&& shouldFight(getCurrentGear())) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to Fighting Area";
		webWalk(currentFightingAssignment().getFightArea().getRandomPosition());
	}
}
