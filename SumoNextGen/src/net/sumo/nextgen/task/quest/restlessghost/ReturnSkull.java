package net.sumo.nextgen.task.quest.restlessghost;

import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class ReturnSkull extends Task{

	@Override
	public boolean active() {
		if (getStage() == Stage.THE_RESTLESS_GHOST && getGhostStage() == RestlessGhostStage.RETURN_SKULL) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(playerInArea(Areas.GHOST_AREA_3_GHOST_HOUSE)){
			updateState("Lets return the ghost's skull");
			if(!objectIsVisible(2145, Areas.GHOST_AREA_3_GHOST_HOUSE)){
				if(invContains("Ghost's skull")){
					interactItemWithObject("Ghost's skull", 15061);
				}
			}else{
				interactObject("open",2145);
			}
			
		}else{
			updateState("lets walk to the ghost");
			webWalk(Areas.GHOST_AREA_3_GHOST_HOUSE);
		}
		
	}

}
