package net.sumo.nextgen.task.fishing;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.sumoscript.enums.PlayerTask;
import net.sumo.sumoscript.task.Task;

public class Fish extends Task {
	@Override
	public boolean active() {
		if (currentTask() == PlayerTask.FISH && playerInArea(currentFishingAssignment().getFishingArea())
				&& shouldFish()) {
			return true;
		}
		return false;
	}

	@Override
	public void run() throws InterruptedException {
		r.STATE = "lets fish";
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already fishing, lets sleep");
			checkContinue();
			antiBan();
			sleep(100);
		}
		fish();

	}

	public void fish() {
		int n = 0;
		NPC closestTarget = s.npcs.closest(currentFishingAssignment().getFishingSpots());
		if (closestTarget != null && currentFishingAssignment().getFishingArea().contains(closestTarget)) {
			closestTarget.interact(currentFishingAssignment().getAction());
			r.lastFish = closestTarget;
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
				closestTarget = s.npcs.closest(currentFishingAssignment().getFishingSpots());
				if (closestTarget != null && shouldFish()) {
					fish();
				}
			}
		} else {

			if (r.lastFish != null) {
				int x = r.lastFish.getX();
				int y = r.lastFish.getY();
				Position newPos = new Position(x - 2, y - 2, 0);
				if (newPos.distance(s.myPosition()) > 3) {
					updateMessage("fish is unavailable, lets walk to last tree!");
					webWalk(newPos);
				} else {
					sleep(350);
				}
			}
		}

	}
}