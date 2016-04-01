package net.sumo.nextgen.task.miner;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class DepositMining extends Task{

	@Override
	public boolean active() {
		if(getStage() == Stage.MINING && playerInArea(currentMiningAssigment().getDepositArea()) && inventoryIsFull() && shouldUseDeposit()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		String oreName = currentMiningAssigment().getOreName();
		Resources.CURRENT_STATE = "Depositing";
		depositAllExceptInDepositBox(currentPickaxe());
	}

}
