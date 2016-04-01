package net.sumo.nextgen.task.quest.cooksass;

import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class GetMilk extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.COOKS_ASSISTANT && getCookStage() == CooksAssistantStage.GET_MILK) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(invContains("Bucket of milk")){
			Resources.gotFlour = true;
		}
		if (invContains("Bucket")) {
			if (playerInArea(Areas.COOK_AREA9_COW_AREA)) {
				updateState("Lets get milk");
				interactObject("Milk", 8689);
				if(invContains("Bucket of milk")){
					Resources.gotFlour = true;
				}
			}else{
				updateState("Lets go to cow area");
				webWalk(Areas.COOK_AREA9_COW_AREA);
			}
		} else {
			updateState("Lets get the bucket");
			getBucket();
		}

	}

}
