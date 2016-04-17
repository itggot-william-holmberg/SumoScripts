package net.sumo.nextgen.task.cooking;

import java.util.Arrays;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class CookBank extends Task{

	@Override
	public boolean active() {
		if(playerInArea(WebBank.getNearest(s).getArea()) && shouldCook()  && !readyToCook()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
	    Resources.CURRENT_STATE = "Banking";
	    String item = currentCookingAssignment().getRawFoodName();
	    if (inventoryIsFull()) {
			depositAllExcept(item);
		}
	    else if (!inventoryContains(item)) {
					withdrawNeededItems(28,item);				
	    }
	}

}
