package net.sumo.nextgen.task.quest.restlessghost;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class StartQuestGhost extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.THE_RESTLESS_GHOST && getGhostStage() == RestlessGhostStage.START_QUEST) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(playerInArea(Areas.GHOST_AREA_1_FATHER_AREA)){
			updateState("Starting quest");
			if(inDialogue()){
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.GHOST_START_QUEST_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				TalkNPC(921);
			}
		}else{
			updateState("lets walk to father, so we can start the quest");
			webWalk(Areas.GHOST_AREA_1_FATHER_AREA);
		}
		
	}

}
