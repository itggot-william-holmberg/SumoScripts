package net.sumo.nextgen.task.fighting;

import net.sumo.nextgen.task.Task;

public class Fight extends Task {

	@Override
	public boolean active() {
		if (shouldFight() && playerInArea(currentFightingAssignment().getFightArea()) && shouldFight(getCurrentGear())) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		s.log("fight is called");
		checkStyle();
		try {
			fight(currentFightingAssignment().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
