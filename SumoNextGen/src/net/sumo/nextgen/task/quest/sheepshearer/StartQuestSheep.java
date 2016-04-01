package net.sumo.nextgen.task.quest.sheepshearer;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class StartQuestSheep extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.SHEEP_SHEARER && getSheepStage() == SheepShearerStage.START_QUEST) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if(playerInArea(Areas.SHEEP_AREA2_FARMER_AREA)){
			NPC grabben = s.npcs.closest(732);
			RS2Object door = getClosest(13001,new Area(3188,3273,3187,3271));
			if((Areas.SHEEP_AREA4_FARMER_FAIL_AREA.contains(grabben) || playerInArea(Areas.SHEEP_AREA4_FARMER_FAIL_AREA))&& door != null){
				interactObject("open", 13001, new Area(3188,3273,3187,3271));
			}
			updateState("Start quest");
			if(inDialogue()){
				s.log("talking");
				try {
					s.dialogues.completeDialogue(Resources.SHEEP_START_QUEST_DIALOGUES);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				TalkNPC(732);
			}
		}else{
		if(playerInArea(Areas.SHEEP_AREA3_SPIN_AREA) && objectIsVisible(7143, new Area(new Position(3206,3212,1), new Position(3208,3216,1)))){
			updateState("Open door");
			interactObject("Open", 7143);
		}else{
			updateState("Lets walk to farmer");
			webWalk(Areas.SHEEP_AREA2_FARMER_AREA);
		}
		}
		
	}

}
