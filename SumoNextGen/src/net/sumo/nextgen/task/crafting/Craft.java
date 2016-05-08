package net.sumo.nextgen.task.crafting;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.enums.CraftingAssignment;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;



public class Craft extends Task {
	CraftingAssignment ass;
	@Override
	public boolean active() {
		if (shouldCraft() && playerInArea(Areas.GRAND_EXCHANGE_AREA) && readyToCraft()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute()  {
		closeBank();
		s.widgets.closeOpenInterface();
		
		ass = currentCraftingAssignment();
		Resources.CURRENT_STATE = "lets craft";
		checkContinue();
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already crafting, lets sleep");
			checkContinue();
			try {
				SkillingAntiBan();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sleep(750);
		}
		if(ass.getRawMaterial() == "Leather"){
			leatherCraft();
		}
		

	}

	public void leatherCraft() {
		int widgetID = ass.getWidgetID();
		int childID = ass.getChildID();
		if(widgetIsVisible(widgetID, childID)){
			int amount = getAmount(ass.getRawMaterial());
			int xp = ass.getXpGiven();
			int xpLeft = s.skills.experienceToLevel(Skill.CRAFTING);
			int minFoodToLevel = xpLeft/xp;
			if(ass.getFinalProduct() == "Leather gloves" || ass.getFinalProduct() == "Leather boots"  || ass.getFinalProduct() == "Leather vambraces" || ass.getFinalProduct() == "Leather chaps"){
			s.widgets.interact(widgetID, childID, "Make all pairs of " + ass.getFinalProduct());
			}else if(ass.getFinalProduct() == "Leather body"){
				s.widgets.interact(widgetID, childID, "Make all " + "Leather bodies");
				}
			else if(ass.getFinalProduct() == "Leather cowl"){
				s.widgets.interact(widgetID, childID, "Make all cowls");
				}
			if(minFoodToLevel<= amount){
			s.log("sleep for " + 1500*minFoodToLevel);
			sleep(1500*minFoodToLevel);	
			}else{
				sleep(1650*amount);
			}
		}else{
			if(inventoryContains(ass.getRawMaterial())){
			interactItemWithItem("Leather", "Needle");
			sleep(3500);
			}
		}
	}
}