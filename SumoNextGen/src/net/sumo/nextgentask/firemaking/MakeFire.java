package net.sumo.nextgentask.firemaking;
/*package net.sumo.sumoscript.task.firemaking;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.sumoscript.abstracts.Task;
import net.sumo.sumoscript.enums.PlayerTask;

public class MakeFire extends Task{

	@Override
	public boolean active() {
		if(currentTask() == PlayerTask.FIREMAKING){
			return true;
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		r.STATE = "lets make some fire";
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already making fire, lets sleep");
			checkContinue();
			antiBan();
			sleep(100);
		}
		cutTree();

	}

	public void makeFire() {
		int n = 0;
		Item tree = s.inventory.getItem(currentFiremakingAssignment().getLogName));
		if (item != null) {
			closestTarget.interact("Chop Down");
			r.lastTree = closestTarget;
			s.log("Object exists, lets interact.");
			while (playerIsMoving()) {
				s.log("sleeping cuz moving");
				sleep(100);
			}
			sleep(2500, 3200);
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

			if (r.lastTree != null) {
				int x = r.lastTree.getX();
				int y = r.lastTree.getY();
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
*/