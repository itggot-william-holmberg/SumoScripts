package net.sumo.nextgen.stage;

import org.osbot.rs07.api.Quests.Quest;
import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.frame.GuiChoiceHandler;
import net.sumo.nextgen.resources.Resources;

public enum Stage {

	THIEVING(StageType.SKILL, Skill.THIEVING), WOODCUTTING(StageType.SKILL, Skill.WOODCUTTING), 
	COOKS_ASSISTANT(StageType.QUEST, Quest.COOKS_ASSISTANT.toString()), SHEEP_SHEARER(StageType.QUEST, 
			Quest.SHEEP_SHEARER.toString()), THE_RESTLESS_GHOST(StageType.QUEST, Quest.THE_RESTLESS_GHOST.toString()), 
	RUNE_MYSTERIES(StageType.QUEST, Quest.RUNE_MYSTERIES.toString()), ROMEO_JULIET(StageType.QUEST, Quest.ROMEO_JULIET.toString()), 
	DEV_MODE(StageType.OTHER, Quest.RAG_AND_BONE_MAN.toString()), MINING(StageType.SKILL, Skill.WOODCUTTING),
	STRENGTH(StageType.COMBAT, Skill.STRENGTH), ATTACK(StageType.COMBAT, Skill.ATTACK), DEFENCE(StageType.COMBAT, 
	Skill.DEFENCE), BUY_ITEMS(StageType.OTHER, Quest.A_SOULS_BANE.toString()), FISHING(StageType.SKILL, Skill.FISHING), 
	COOKING(StageType.SKILL, Skill.COOKING), RANGE(StageType.COMBAT, Skill.RANGED);
	
	StageType _type;
	Skill _skill;
	String _questName;
	
	Stage(StageType type, String questName){
		_type = type;
		_questName = questName;
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
	
	public String getQuestName(){
		return _questName;
	}
}
