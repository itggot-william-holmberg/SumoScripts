package net.sumo.nextgen.task.fishing;

import java.util.Arrays;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class FishBank extends Task{

	@Override
	public boolean active() {
		if(playerInArea(WebBank.getNearest(s).getArea()) && shouldFish()  && !readyToFish()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
	    Resources.CURRENT_STATE = "Banking";
	    String[] fishGear = currentFishingAssignment().getFishGear();
	    Arrays.asList(fishGear).forEach(item ->{
	    	if (!inventoryContains(item)) {
				withdrawNeededItems(item);
			}
	    });
	    
	    if (inventoryIsFull()) {
			depositAllExcept(currentFishingAssignment().getFishGear());
		}
		
	}

}
