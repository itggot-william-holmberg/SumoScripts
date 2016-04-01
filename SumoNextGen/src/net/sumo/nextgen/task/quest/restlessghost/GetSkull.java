package net.sumo.nextgen.task.quest.restlessghost;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class GetSkull extends Task{
	@Override
	public boolean active() {
		if (getStage() == Stage.THE_RESTLESS_GHOST && getGhostStage() == RestlessGhostStage.GET_SKULL) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(playerInArea(Areas.GHOST_AREA_4_SKULL_AREA)){
			updateState("Lets get the ghost amulet");
			if(!invContains("Ghost's skull")){
			
				interactObject("Search", 2146);
			}else{
				s.log("ruuun");
			}
		}else{
			updateState("we have to find the skull!");
			webWalk(Areas.GHOST_AREA_4_SKULL_AREA);
		}
		
	}

}
