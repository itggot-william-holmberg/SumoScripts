package net.sumo.nextgen.stage;

import org.osbot.rs07.api.Quests.Quest;
import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.frame.GuiChoiceHandler;
import net.sumo.nextgen.resources.Resources;

public enum Stage {

	THIEVING(StageType.SKILL, Skill.THIEVING), WOODCUTTING(StageType.SKILL, Skill.WOODCUTTING), COOKS_ASSISTANT(StageType.QUEST), SHEEP_SHEARER(StageType.QUEST), THE_RESTLESS_GHOST(StageType.QUEST), RUNE_MYSTERIES(StageType.QUEST), ROMEO_JULIET(StageType.QUEST), DEV_MODE(StageType.OTHER)
	, MINING(StageType.SKILL, Skill.WOODCUTTING), STRENGTH(StageType.COMBAT, Skill.STRENGTH), ATTACK(StageType.COMBAT, Skill.ATTACK), DEFENCE(StageType.COMBAT, Skill.DEFENCE);
	
	StageType _type;
	Skill _skill;
	
	Stage(StageType type){
		_type = type;
	}
	
	Stage(StageType type, Skill skill){
		_type = type;
		_skill = skill;
	}
	
	
	public StageType getType(){
		return _type;
	}
	
	public Skill getSkill(){
		return _skill;
	}
}
