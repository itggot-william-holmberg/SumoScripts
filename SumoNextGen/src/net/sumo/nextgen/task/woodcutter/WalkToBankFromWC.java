package net.sumo.nextgen.task.woodcutter;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToBankFromWC extends Task{

	@Override
	public boolean active() {
		if(shouldWoodcut() && !playerInArea(currentWCAssigment().getBankArea()) && !shouldCutTree()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to Bank";
		webWalk(currentWCAssigment().getBankArea());		
	}

}
