package net.sumo.nextgen.task.quest.cooksass;

import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class GetEgg extends Task{

	@Override
	public boolean active() {
		if(getStage() == Stage.COOKS_ASSISTANT && getCookStage() == CooksAssistantStage.GET_EGG){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		updateState("Lets get egg");
		if(playerInArea(Areas.COOK_AREA8_EGG_AREA)){
			updateState("Lets pick egg");
			interactGroundItem("Take", "Egg");
			sleep(4000);
			if(invContains("Egg")){
				Resources.pickedEgg = true;
			}
		}else{
			updateState("Lets get egg");
			webWalk(Areas.COOK_AREA8_EGG_AREA);
		}
		
	}

}
