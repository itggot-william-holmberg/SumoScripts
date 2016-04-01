package net.sumo.nextgen.task.miner;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToMiningPlace extends Task{

	@Override
	public boolean active() {
		if(shouldMine() && !playerInArea(currentMiningAssigment().getMiningArea()) && shouldMineOre()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walkign to mining place";
		webWalk(currentMiningAssigment().getMiningArea().getRandomPosition());	
	}

}
