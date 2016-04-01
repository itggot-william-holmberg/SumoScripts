package net.sumo.nextgen.task.fishing;

import net.sumo.sumoscript.enums.PlayerTask;
import net.sumo.sumoscript.task.Task;

public class FishBank extends Task{

	@Override
	public boolean active() {
		if(currentTask() == PlayerTask.FISH && playerInArea(currentFishingAssignment().getBankArea()) && !shouldFish()){
			return true;
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		r.STATE = "Banking";
		if(inventoryIsFull()){
			depositAllExcept(currentFishingAssignment().getFishGear());
		}else if(!playerHasFishGear()){
			withdraw(currentFishingAssignment().getFishGear());
		}
		
	}

}
