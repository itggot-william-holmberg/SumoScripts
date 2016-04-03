package net.sumo.nextgen.task.miner;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class WalkToBankFromMining extends Task{

	@Override
	public boolean active() {
		if(shouldMine() && !playerInArea(getClosestBank()) && !playerHasPickaxe()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Walking to Bank ";
		webWalk(getClosestBank());		
	}

}
