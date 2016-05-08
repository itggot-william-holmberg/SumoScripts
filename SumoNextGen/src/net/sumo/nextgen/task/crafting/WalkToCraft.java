package net.sumo.nextgen.task.crafting;

import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToCraft extends Task {

	@Override
	public boolean active() {
		if (!playerInArea(Areas.GRAND_EXCHANGE_AREA)
				&& shouldCraft()) {
			return true;
		}
		return false;
	}

	public void execute() {
		Resources.CURRENT_STATE = "Walking to crafting area (GE)";
		webWalk(Areas.GRAND_EXCHANGE_AREA);
	}
}
