package net.sumo.nextgen.task.miner;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToBankFromMining extends Task{

	@Override
	public boolean active() {
		if(shouldMine() && !playerInArea(currentMiningAssigment().getBankArea()) && !playerHasPickaxe()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to Bank";
		webWalk(currentMiningAssigment().getBankPos());		
	}

}
