package net.sumo.nextgen.task.cooking;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;



public class Cook extends Task {
	@Override
	public boolean active() {
		if (shouldCook() && playerInArea(currentCookingAssignment().getCookingArea()) && readyToCook()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute()  {
		Resources.CURRENT_STATE = "lets cook";
		checkContinue();
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already cooking, lets sleep");
			checkContinue();
			try {
				SkillingAntiBan();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sleep(750);
		}
		cook();

	}

	public void cook() {
		String food = currentCookingAssignment().getRawFoodName();
		if(widgetIsVisible(307,2)){
			int amount = getAmount(currentCookingAssignment().getRawFoodName());
			int xp = currentCookingAssignment().getXpGiven();
			int xpLeft = s.skills.experienceToLevel(Skill.COOKING);
			int minFoodToLevel = xpLeft/xp;
			s.widgets.interact(307, 2, "Cook all");
			if(minFoodToLevel<= amount){
			s.log("sleep for " + 1500*minFoodToLevel);
			sleep(1500*minFoodToLevel);	
			}else{
				sleep(1650*amount);
			}
		}else{
			if(inventoryContains(food)){
			interactItemWithObject(food, currentCookingAssignment().getCookingObject());
			sleep(3500);
			}
		}
	}
}