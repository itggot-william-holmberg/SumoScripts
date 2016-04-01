package net.sumo.nextgen.task.quest.restlessghost;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class GetAmulet extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.THE_RESTLESS_GHOST && getGhostStage() == RestlessGhostStage.GET_AMULET) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(playerInArea(Areas.GHOST_AREA_2_FATHER_URNHEY)){
			updateState("Lets get the ghost amulet");
			if(inDialogue()){
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.GHOST_GET_AMULET_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				TalkNPC(923);
			}
		}else{
			updateState("lets walk to father urhney, so we can .get the ghost amulet");
			webWalk(Areas.GHOST_AREA_2_FATHER_URNHEY);
		}
		
	}

}
