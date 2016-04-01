package net.sumo.nextgen.task.quest.cooksass;

import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;


public class StartQuestCook extends Task{

	@Override
	public boolean active() {
		if(getStage() == Stage.COOKS_ASSISTANT && getCookStage() == CooksAssistantStage.START_QUEST){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(!invContains("Pot")){
			updateState("Get pot");
			getPot();
		}else if(!invContains("Bucket")){
			updateState("Get bucket");
			getBucket();
		}else {
			if(playerInArea(Areas.COOK_AREA1_CHEF_ROOM)){
				updateState("Lets start quest");
				if(inDialogue()){
					s.log("talking");
					try {
						s.dialogues.completeDialogue(Resources.LUMB_START_QUEST_DIALOGUES);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					TalkNPC(4626);
				}
			}else if(playerInArea(Areas.COOK_AREA2_LUMBRIDGE_BASEMENT)){
				updateState("Lets walk to the chef");
				if(playerInArea(Areas.COOK_AREA4_LUMBRIDGE_BASEMENT_LADDER)){
					interactObject("Climb-up", "Ladder");
				}else{
					walk(Areas.COOK_AREA4_LUMBRIDGE_BASEMENT_LADDER);
				}
			}else{
				webWalk(Areas.COOK_AREA1_CHEF_ROOM);
			}
		}
		
	}

}
