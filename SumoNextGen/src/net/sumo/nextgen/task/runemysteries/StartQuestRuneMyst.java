package net.sumo.nextgen.task.runemysteries;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.queststage.RuneMysteriesStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class StartQuestRuneMyst extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.RUNE_MYSTERIES && getRuneMystStage() == RuneMysteriesStage.START_QUEST) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (playerInArea(Areas.RUNE_AREA_1_DUKE_AREA) || playerInArea(Areas.RUNE_AREA_2_FAIL_DUKE_AREA)) {
			NPC grabben = s.npcs.closest(815);
			RS2Object door = getClosest(7143, new Area(new Position(3207, 3221, 1), new Position(3208, 3223, 1)));
			if ((Areas.RUNE_AREA_2_FAIL_DUKE_AREA.contains(grabben) || playerInArea(Areas.RUNE_AREA_2_FAIL_DUKE_AREA))
					&& door != null) {
				interactObject("open", door);
			}
			updateState("Start quest");
			if (inDialogue()) {
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.RUNE_START_QUEST_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				TalkNPC(815);
			}
		} else {
			updateState("Lets walk to duke huracio");
			webWalk(Areas.RUNE_AREA_1_DUKE_AREA);

		}

	}

}
