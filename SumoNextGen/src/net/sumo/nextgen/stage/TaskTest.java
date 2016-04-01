package net.sumo.nextgen.stage;

import org.osbot.rs07.api.ui.Skill;

public class TaskTest {
	private Stage _stage;
	private int _levelGoal;
	private Skill _skill;
	
	public TaskTest(Stage stage){
		_stage = stage;
	}
	
	public TaskTest(Stage stage, int levelGoal, Skill skill){
		_stage = stage;
		_levelGoal = levelGoal;
		_skill = skill;
	}
	
	public Stage getStage(){
		return _stage;
	}
	
	public int getLevelGoal(){
		return _levelGoal;
	}
	
	public Skill getSkill(){
		return _skill;
	}

}
