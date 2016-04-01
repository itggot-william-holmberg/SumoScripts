package net.sumo.nextgen.task.miner;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;



public class MineOre extends Task{
	@Override
	public boolean active() {
		if (shouldMine() && playerInArea(currentMiningAssigment().getMiningArea()) && shouldMineOre()) {
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
		Resources.CURRENT_STATE = "lets mine some ores";
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already mining, lets sleep");
			checkContinue();
			antiBan();
			sleep(100);
		}
		mineOre();

	}

	public void mineOre() {
		int n = 0;
		checkContinue();
		RS2Object closestTarget = s.objects.closest(currentMiningAssigment().getRockID());
		if (closestTarget != null && currentMiningAssigment().getMiningArea().contains(closestTarget)) {
			closestTarget.interact("Mine");
			Resources.lastTree = closestTarget;
			s.log("Object exists, lets interact.");
			while (playerIsMoving()) {
				s.log("sleeping cuz moving");
				sleep(100);
			}
			sleep(1500, 2500);
			if (playerIsBusy()) {
				s.log("we are sucessfully interacting our target");
			} else {
				s.log("Some one got to the target faster than us. Lets find a new target");
				closestTarget = s.objects.closest(currentMiningAssigment().getRockID());
				if (closestTarget != null && shouldCutTree()) {
					mineOre();
				}
			}
		} else {

			if (Resources.lastOre != null) {
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
