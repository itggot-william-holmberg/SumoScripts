package net.sumo.nextgen.task.quest.cooksass;

import org.osbot.rs07.api.Chatbox.MessageType;

import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class MakeFlour extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.COOKS_ASSISTANT && getCookStage() == CooksAssistantStage.MAKE_FLOUR) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (getConfig(695) == 1) { //if player filled hopper with grain. the flour is now ready to be collected.
			if(playerInArea(Areas.COOK_AREA7_FLOUR_BIN_AREA)){
				updateState("fill pot with flour");
				if(invContains("pot")){
				interactObject("Empty", "Flour bin");
				sleep(4000);
				if(invContains("Pot of flour")){
					Resources.gotFlour = true;
				}
				}else{
					getPot();
				}
			}else{
				updateState("Lets walk to flour bin area");
				webWalk(Areas.COOK_AREA7_FLOUR_BIN_AREA);
			}
		} else {
			if (Resources.pickedWheat == true || invContains("Grain")) {
				if (Resources.filledHopper == true) {
					if (Resources.controlledHopper == true) {

					} else {
						if (playerInArea(Areas.COOK_AREA6_HOPPER_FLOOR_AREA)) {
							updateState("lets use the hopper controls");
							interactObject("Operate", "Hopper controls");
							sleep(4500);
							if (messageExists("You operate")) {
								Resources.controlledHopper = true;
							}
						} else {
							updateState("lets walk to hopper controls");
							webWalk(Areas.COOK_AREA6_HOPPER_FLOOR_AREA);
						}
					}
				} else {
					if (playerInArea(Areas.COOK_AREA6_HOPPER_FLOOR_AREA)) {
						updateState("lets fill hopper with grain");
						interactItemWithObject("Grain", 24961);
						sleep(6500);
						if (messageExists("You put the grain")) {
							Resources.filledHopper = true;
						} else {
							s.log("we failed");
						}
					} else {
						updateState("lets fill hopper with grain");
						webWalk(Areas.COOK_AREA6_HOPPER_FLOOR_AREA);
					}
				}
			} else {
				if (playerInArea(Areas.COOK_AREA5_WEED_AREA)) {
					updateState("lets pick grain");
					interactObject("Pick", "Wheat");
					sleep(2000);
					if (invContains("grain") || messageExists("You pick some grain")) {
						Resources.pickedWheat = true;
					}
				} else {
					updateState("Lets walk to grain area");
					webWalk(Areas.COOK_AREA5_WEED_AREA);
				}
			}
		}

	}

}
