package net.sumo.nextgen.task.runemysteries;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.RuneMysteriesStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class ReturnNotes extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.RUNE_MYSTERIES && getRuneMystStage() == RuneMysteriesStage.RETURN_NOTES) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (playerInArea(Areas.RUNE_AREA_4_FAIL_HEAD_WIZARD_AREA) || playerInArea(Areas.RUNE_AREA_3_HEAD_WIZARD_AREA)) {
			NPC grabben = s.npcs.closest(5034);
			RS2Object door = getClosest(7122, new Area(3107,9569,3108,9571));
			if ((Areas.RUNE_AREA_4_FAIL_HEAD_WIZARD_AREA.contains(grabben) || playerInArea(Areas.RUNE_AREA_4_FAIL_HEAD_WIZARD_AREA))
					&& door != null) {
				interactObject("open", door);
			}
			updateState("Start quest");
			if (inDialogue()) {
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.RUNE_RETURN_TALISMAN_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				TalkNPC(5034);
			}
		} else {
			updateState("Lets walk to duke huracio");
			webWalk(Areas.RUNE_AREA_3_HEAD_WIZARD_AREA);

		}

	}


}
