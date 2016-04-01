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

public class SpeakToJulietScene extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.ROMEO_JULIET && getRomeoStage() == RomeoAndJulietStage.SPEAK_TO_JULIET_CUTSCENE) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		
			if (inDialogue()) {
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.RUNE_RETURN_TALISMAN_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	


}
