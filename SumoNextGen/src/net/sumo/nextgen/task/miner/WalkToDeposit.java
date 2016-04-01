package net.sumo.nextgen.task.miner;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToDeposit extends Task{

	@Override
	public boolean active() {
		if(shouldMine() && !playerInArea(currentMiningAssigment().getDepositArea()) && shouldUseDeposit() && inventoryIsFull()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to deposit box";
		webWalk(currentMiningAssigment().getDepositPos());
	}

}
