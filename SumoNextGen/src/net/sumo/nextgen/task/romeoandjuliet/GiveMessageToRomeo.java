package net.sumo.nextgen.task.romeoandjuliet;

import org.osbot.rs07.api.model.NPC;

import net.sumo.nextgen.queststage.RomeoAndJulietStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class GiveMessageToRomeo extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.ROMEO_JULIET && getRomeoStage() == RomeoAndJulietStage.GIVE_MESSAGE_TO_ROMEO) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (playerInArea(Areas.ROMEO_AREA_1_ROMEO_AREA)) {
			NPC grabben = s.npcs.closest(5037);
			
			updateState("Start quest");
			if (inDialogue()) {
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.ROMEO_START_QUEST_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				TalkNPC(5037);
			}
		} else {
			updateState("Lets walk to romeo");
			webWalk(Areas.ROMEO_AREA_1_ROMEO_AREA);

		}

	}

}
