package net.sumo.nextgen.task.woodcutter;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class BankWC extends Task{

	@Override
	public boolean active() {
		if(shouldWoodcut() && playerInArea(getClosestBank()) && !shouldCutTree()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		try {
			SkillingAntiBan();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String logName = currentWCAssigment().getLogName();
		String axeName = currentAxe();
		Resources.CURRENT_STATE = "Banking";
		if(inventoryContains(logName)){
			depositAllExcept(axeName);
		}else {
			if(!inventoryContains(axeName)){
				s.log("lets withdraw, from bankwc");
				withdrawNeededItems(axeName);
			}
		}	
		
	}

}
