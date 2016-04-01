package net.sumo.nextgen.dev;

import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.task.Task;

public class Devmode extends Task{

	@Override
	public boolean active() {
		if(getStage() == Stage.DEV_MODE){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		s.log("we are ready to dev");
		s.log(s.configs.get(144));
	}

}
