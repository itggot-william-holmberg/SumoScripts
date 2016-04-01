package net.sumo.nextgen.task.romeoandjuliet;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.RomeoAndJulietStage;
import net.sumo.nextgen.queststage.RuneMysteriesStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class SpeakToJuliet extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.ROMEO_JULIET && getRomeoStage() == RomeoAndJulietStage.SPEAK_TO_JULIET) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (playerInArea(Areas.ROMEO_AREA_2_JULIET_AREA) || playerInArea(Areas.ROMEO_AREA_3_FAIL_JULIET_AREA)) {
			NPC grabben = s.npcs.closest(6268);
			RS2Object door = getClosest(11773, new Area(new Position(3157,3427,1),new Position(3159,3426,1)));
			if ((Areas.ROMEO_AREA_3_FAIL_JULIET_AREA.contains(grabben) || playerInArea(Areas.ROMEO_AREA_3_FAIL_JULIET_AREA))
					&& door != null) {
				interactObject("open", door);
			}
			updateState("Start quest");
			if (inDialogue()) {
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.RUNE_RETURN_TALISMAN_DIALOGUES);
					sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				TalkNPC(6268);
			}
		} else {
			updateState("Lets walk to juliet");
			webWalk(Areas.ROMEO_AREA_2_JULIET_AREA);

		}

	}


}
