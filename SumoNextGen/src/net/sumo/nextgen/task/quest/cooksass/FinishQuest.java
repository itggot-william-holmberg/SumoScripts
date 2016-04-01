package net.sumo.nextgen.task.quest.cooksass;

import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class FinishQuest extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.COOKS_ASSISTANT && getCookStage() == CooksAssistantStage.FINISH_QUEST){
				return true;
			}
		return false;
	}

	@Override
	public void execute() {
		if(playerInArea(Areas.COOK_AREA1_CHEF_ROOM)){
			if(inDialogue()){
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.LUMB_FINISH_QUEST_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				TalkNPC(4626);
			}
		}else{
			webWalk(Areas.COOK_AREA1_CHEF_ROOM);
		}
		
	}

}
