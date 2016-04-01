package net.sumo.nextgen.task.woodcutter;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToWCPlace extends Task{

	@Override
	public boolean active() {
		if(shouldWoodcut() && !playerInArea(currentWCAssigment().getTreeArea()) && shouldCutTree()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walkign to WC PLACE";
		webWalk(currentWCAssigment().getTreeArea().getRandomPosition());	
	}

}
