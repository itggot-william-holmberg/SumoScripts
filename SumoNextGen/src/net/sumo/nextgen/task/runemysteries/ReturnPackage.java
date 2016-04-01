package net.sumo.nextgen.task.runemysteries;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.queststage.RuneMysteriesStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class ReturnPackage extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.RUNE_MYSTERIES && getRuneMystStage() == RuneMysteriesStage.RETURN_PACKAGE) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (playerInArea(Areas.RUNE_AREA_5_AUBURY_AREA) ||playerInArea(Areas.RUNE_AREA_6_FAIL_AUBURY_AREA)) {
			NPC grabben = s.npcs.closest(637);
			RS2Object door = getClosest(11780, new Area(3252,3399,3254,3398));
			if ((Areas.RUNE_AREA_6_FAIL_AUBURY_AREA.contains(grabben) || playerInArea(Areas.RUNE_AREA_6_FAIL_AUBURY_AREA))
					&& door != null) {
				interactObject("open", door);
			}
			updateState("Start quest");
			if (inDialogue()) {
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.RUNE_RETURN_PACKAGE_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				TalkNPC(637);
			}
		} else {
			updateState("Lets walk to varrock guy");
			webWalk(Areas.RUNE_AREA_5_AUBURY_AREA);

		}

	}


}
