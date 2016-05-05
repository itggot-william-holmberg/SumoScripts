package net.sumo.nextgen.task.fighting;

import java.io.IOException;

import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.task.Task;


public class Fight extends Task {

	@Override
	public boolean active() {
		if (shouldFight() && playerInArea(currentFightingAssignment().getFightArea()) && shouldFight(getCurrentGear())) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		s.log("fight is called");
		checkStyle();
		if (myPlayerNeedsToEat()) {
			eat();
			try {
				fight(currentFightingAssignment().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				fight(currentFightingAssignment().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

}
