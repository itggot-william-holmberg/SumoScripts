package net.sumo.nextgen.task.quest.sheepshearer;

import org.osbot.rs07.api.map.Area;

import net.sumo.nextgen.enums.WebBank;
import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class GatherWool extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.SHEEP_SHEARER && getSheepStage() == SheepShearerStage.GATHER_WOOL) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (invContains(20, "wool")) {
			updateState("Nice! we got all wool");
			Resources.pickedWool = true;
		} else {
			if (freeSpaceInInvMinusItem("Wool") <= 20) {
				updateState("We need inventory space, lets deposit all items.");
				bankAllExcept("Shearer");
			} else {
				if (invContains("shears")) {
					if (playerInArea(Areas.SHEEP_AREA1_SHEEP_AREA)) {
						updateState("Lets get some ball of wool");
						interactNPC("Shear", Resources.SHEEP_ID);

					} else {
						updateState("Lets walk to sheep area");
						webWalk(Areas.SHEEP_AREA1_SHEEP_AREA);
					}
				} else {
					if (playerInArea(Areas.SHEEP_AREA2_FARMER_AREA)) {
						updateState("Get item:shears");
						interactGroundItem("Take", "Shears");
					} else {
						updateState("WALK_TO_FARMER");
						webWalk(Areas.SHEEP_AREA2_FARMER_AREA);
					}
				}
			}
		}

	}

	public int freeSpaceInInvMinusItem(String item) {
		int itemamount = getAmount(item);
		s.log(itemamount);
		int freespace = s.inventory.getEmptySlots();
		s.log(freespace);
		return freespace + itemamount;
	}

	public void bankAllExcept(String name) {
		Area closestBank = WebBank.getNearest(s).getArea();
		if (playerInArea(closestBank)) {
			if (s.bank.isOpen()) {
				s.bank.depositAllExcept(name);
			} else {
				try {
					s.bank.open();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			webWalk(closestBank);
		}
	}

}
