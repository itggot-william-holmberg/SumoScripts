package net.sumo.nextgen.task.quest.sheepshearer;

import org.osbot.rs07.api.map.constants.Banks;

import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class SpinWool extends Task {

	@Override
	public boolean active() {
		if (getStage() == Stage.SHEEP_SHEARER && getSheepStage() == SheepShearerStage.SPIN_WOOL) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (invContains(20, "ball of wool")) {
			Resources.spanWool = true;
		}
		else if (playerInArea(Areas.SHEEP_AREA3_SPIN_AREA)) {
			updateState("Lets spin som wool.");
			if (widgetIsVisible(Resources.ENTER_AMOUNT_WIDGET_ROOT_ID, Resources.ENTER_AMOUNT_WIDGET_CHILD_ID)) {
				updateState("Spin!");
				int randomNumber = random.nextInt(2500) + 20;
				String randomNumberString = Integer.toString(randomNumber);
				s.keyboard.typeString(randomNumberString);
				int sleepTime = (getAmount("wool") * 1650) + 3000;
				sleep(sleepTime);
			} else {
				if (widgetIsVisible(Resources.SPINNING_WIDGET_ROOT_ID, Resources.SPINNING_WIDGET_CHILD_ID)) {
					s.widgets.interact(Resources.SPINNING_WIDGET_ROOT_ID, Resources.SPINNING_WIDGET_CHILD_ID, "make x");
				} else {
					interactObject("Spin", "Spinning wheel");
				}
			}

			s.log("good");
		} else {
			updateState("Walking to spinning room");
			webWalk(Areas.SHEEP_AREA3_SPIN_AREA);
		}

	}

}
