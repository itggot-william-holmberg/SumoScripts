package net.sumo.nextgen.task.woodcutter;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;



public class CutTree extends Task {

	@Override
	public boolean active() {
		if (shouldWoodcut() && playerInArea(currentWCAssigment().getTreeArea()) && shouldCutTree()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		try {
			SkillingAntiBan();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Resources.CURRENT_STATE = "lets cut some trees";
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already woodcutting, lets sleep");
			checkContinue();
			antiBan();
			sleep(100);
		}
		cutTree();

	}

	public void cutTree() {
		int n = 0;
		RS2Object closestTarget = s.objects.closest(currentWCAssigment().getName());
		if (closestTarget != null && currentWCAssigment().getTreeArea().contains(closestTarget)) {
			closestTarget.interact("Chop Down");
			Resources.lastTree = closestTarget;
			s.log("Object exists, lets interact.");
			while (playerIsMoving()) {
				s.log("sleeping cuz moving");
				sleep(100);
			}
			sleepDistance(closestTarget.getPosition().distance(s.myPlayer()));
			if (playerIsBusy()) {
				s.log("we are sucessfully interacting our target");
			} else {
				s.log("Some one got to the target faster than us. Lets find a new target");
				closestTarget = s.objects.closest(currentWCAssigment().getName());
				if (closestTarget != null && shouldCutTree()) {
					cutTree();
				}
			}
		} else {

			if (Resources.lastTree != null) {
				int x = Resources.lastTree.getX();
				int y = Resources.lastTree.getY();
				Position newPos = new Position(x - 2, y - 2, 0);
				if (newPos.distance(s.myPosition()) > 3) {
					updateMessage("Tree is unavailable, lets walk to last tree!");
					webWalk(newPos);
				} else {
					sleep(350);
				}
			}
		}
	}

}
