package net.sumo.nextgen.task.crafting;

import java.util.Arrays;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;

public class CraftBank extends Task {

	@Override
	public boolean active() {
		if (playerInArea(Areas.GRAND_EXCHANGE_AREA) && shouldCraft() && !readyToCraft()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "Banking";
		if (currentCraftingAssignment().getRawMaterial() == "Leather") {
			if (s.inventory.contains("leather") && s.inventory.getItem("leather").isNote()) {
					depositAll();	
			} else {
				if (inventoryIsFull()) {
					depositAllExcept("Needle", "thread");
				} else {
					if (!inventoryContains("needle")) {
						withdrawNeededItems("Needle");
					} else if (!inventoryContains("thread")) {
						withdrawNeededItems("Thread");
					}
					if (!inventoryContains("leather")) {
						withdrawNeededItems("Leather");
					}
				}
			}

		}

	}

}
