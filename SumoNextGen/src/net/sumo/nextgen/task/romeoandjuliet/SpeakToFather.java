package net.sumo.nextgen.task.romeoandjuliet;

import net.sumo.nextgen.queststage.RomeoAndJulietStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class SpeakToFather extends Task{

	@Override
	public boolean active() {
		if(getStage() == Stage.ROMEO_JULIET && getRomeoStage() == RomeoAndJulietStage.SPEAK_TO_FATHER) {
			return true;
		}

		return false;
	}

	@Override
	public void execute() {
		talkToNPC(5038, Resources.ROMEO_START_QUEST_DIALOGUES, Areas.ROMEO_AREA_4_FATHER_AREA);
		
	}

}
