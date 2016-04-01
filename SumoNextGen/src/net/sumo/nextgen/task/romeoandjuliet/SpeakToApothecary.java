package net.sumo.nextgen.task.romeoandjuliet;

import net.sumo.nextgen.queststage.RomeoAndJulietStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class SpeakToApothecary extends Task{

	@Override
	public boolean active() {
		if(getStage() == Stage.ROMEO_JULIET && getRomeoStage() == RomeoAndJulietStage.SPEAK_TO_APOTHECARY) {
			return true;
		}

		return false;
	}

	@Override
	public void execute() {
		if(invContainsAtleast(2,"cadava berries")){
		talkToNPC(5036, Resources.ROMEO_START_QUEST_DIALOGUES, Areas.ROMEO_AREA_5_APOTHECARY_AREA);
		}else{
			getItemFromObject("pick-from", Areas.ROMEO_AREA_6_CADAVA_AREA, Resources.CADAVA_BUSH_ID);
		}
	}

}
