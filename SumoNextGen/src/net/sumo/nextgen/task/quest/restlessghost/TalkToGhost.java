package net.sumo.nextgen.task.quest.restlessghost;

import org.osbot.rs07.api.ui.EquipmentSlot;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class TalkToGhost extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.THE_RESTLESS_GHOST && getGhostStage() == RestlessGhostStage.TALK_TO_GHOST) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (playerInArea(Areas.GHOST_AREA_3_GHOST_HOUSE)) {
			if (playerIsWearingAmmy("Ghostspeak amulet")) {
				if (npcIsVisible(922)) {
					updateState("Lets talk to the ghost.");
					if (inDialogue()) {
						s.log("talking");
						try {
							s.dialogues.completeDialogue(Resources.GHOST_TALK_TO_GHOST_DIALOGUES);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						TalkNPC(922);
					}
				}else{
					updateState("Opening coffin.");
					interactObject("open", 2145);
				}
			} else {
				updateState("lets wield the ammy.");
				equipAmulet("Ghostspeak amulet");
				sleep(3500);
			}
		} else {
			updateState("Lets walk to the ghost.");
			webWalk(Areas.GHOST_AREA_3_GHOST_HOUSE);
		}

	}

}
