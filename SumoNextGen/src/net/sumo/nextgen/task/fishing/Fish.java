package net.sumo.nextgen.task.fishing;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;



public class Fish extends Task {
	@Override
	public boolean active() {
		if (shouldFish() && playerInArea(currentFishingAssignment().getFishingArea()) && readyToFish()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute()  {
		Resources.CURRENT_STATE = "lets fish";
		while ((playerIsBusy()) || playerIsMoving()) {
			updateMessage("We are already fishing, lets sleep");
			checkContinue();
			FishingAntiBan();
			sleep(10000);
		}
		fish();

	}

	public void fish() {
		int n = 0;
		NPC closestTarget = s.npcs.closest(currentFishingAssignment().getFishingSpots());
		if (closestTarget != null && currentFishingAssignment().getFishingArea().contains(closestTarget)) {
			closestTarget.interact(currentFishingAssignment().getAction());
			Resources.lastFish = closestTarget;
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
				if (closestTarget != null && shouldFish() && !inventoryIsFull()) {
					fish();
				}
			}
		} else {

			if (Resources.lastFish != null) {
				int x = Resources.lastFish.getX();
				int y = Resources.lastFish.getY();
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