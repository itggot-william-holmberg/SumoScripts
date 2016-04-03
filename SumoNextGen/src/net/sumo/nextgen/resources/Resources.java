package net.sumo.nextgen.resources;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.Quests.Quest;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.enums.AttackStyle;
import net.sumo.nextgen.enums.MiningAssigment;
import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.stage.TaskTest;

public class Resources {


	public static final int QUEST = 0;
	public static List<TaskTest> TASKTEST_LIST = new ArrayList<TaskTest>();

	public static List<String> WITHDRAW_LIST = new ArrayList<String>();
	public static List<String> BUY_LIST = new ArrayList<String>();
	
	public static List<Stage> STAGE_LIST = new ArrayList<Stage>();
	public static List<Quest> QUEST_LIST = new ArrayList<Quest>();
	public static boolean SHOULD_DEV = true;
	public static long sleepGoal;
	public static Stage currentStage;
	public static boolean pickedWheat = false;
	public static boolean filledHopper = false;
	public static boolean controlledHopper = false;
	
	public static String LUMB_START_QUEST_DIALOGUES[] = {"What's wrong", "I'm always happy"};
	public static String LUMB_FINISH_QUEST_DIALOGUES[] = {"So, do I get to go", "Well, maybe one day"};
	public static String SHEEP_START_QUEST_DIALOGUES[] = {"i'm looking", "Yes okay.", "Of course", "I'm something of an expert"};
	public static String GHOST_START_QUEST_DIALOGUES[] = {"i'm looking", "ok, let me"};
	public static String GHOST_TALK_TO_GHOST_DIALOGUES[] = {"Yep, now tell me"};
	public static String GHOST_GET_AMULET_DIALOGUES[] = {"Father aereck sent", "he's got a ghost haunting"};
	public static String RUNE_START_QUEST_DIALOGUES[] = {"have you any quest", "Sure, no problem"};
	public static String RUNE_RETURN_TALISMAN_DIALOGUES[] = {"i'm looking for the", "Ok, here you are", "yes"};
	public static String RUNE_RETURN_PACKAGE_DIALOGUES[] = {"i have been sent"};
	public static String ROMEO_START_QUEST_DIALOGUES[] = {"Yes, i have", "yes, ok", "ok, thanks"};

	public static boolean gotFlour = false;
	public static boolean pickedEgg = false;
	public static boolean gotMilk = false;
	
	public static int CADAVA_BUSH_ID[] = {23625,23626};
	public static String CURRENT_STATE = "None";
	public static boolean pickedWool = false;
	public static int SHEEP_ID[] = {2801,2795,2800};
	public static CooksAssistantStage currentQuestStage;
	public static boolean spanWool = false;
	
	public static int ENTER_AMOUNT_WIDGET_ROOT_ID = 162;
	public static int ENTER_AMOUNT_WIDGET_CHILD_ID = 31;
	public static int SPINNING_WIDGET_ROOT_ID = 459;
	public static int SPINNING_WIDGET_CHILD_ID = 97;
	public static int currentSkillGoal = 1;
	
	
	
	public static Position wcPos;
	public static Position[] availableNPCs = { null, null, null, null, null, null, null, null, null, null, null, null,
			null };
	public static Position[] unavailableNPCs = { null, null, null, null, null, null, null, null, null, null, null, null,
			null };

	public static Position fightingPos;

	public static String lastLogUpdate;

	public static String[] closeNames = { null, null, null, null, null, null, null, null, null, null, null, null,
			null };

	public static int closePlayers;

	public static int firstWorld;

	public static boolean hopWorlds;

	public static int maxPlayer = 2;

	public static int totalLoot;

	public static int[] ironOreID = { 7488, 7455 };

	public static int[] goldOreID = { 7491, 7458 };

	public static int[] copperOreID = { 7484 };

	public static int[] schrimpSpots = { 1525 };

	public static int[] tinOreID = { 7485 };

	public static int lootLooted;

	public static boolean sleeping;

	public static boolean botIsReady;

	public static boolean seagull = false;

	public static boolean woodcut = false;

	public static String axeName = "Bronze axe";

	public static int totalLogs = 0;
	public static RS2Object lastTree = null;
	public static RS2Object lastOre;
	public static boolean mine;

	public static String pickaxeName = "Bronze pickaxe";

	public static MiningAssigment currentMiningAssigment = null;
	public static int wcGoal1 = 32;
	public static int wcGoal2 = 44;
	public static int miningGoal1 = 25;
	public static int miningGoal2 = 41;
	public static int fishingGoal1 = 15;
	public static int fishingGoal2 = 35;
	public static int fmGoal1 = 25;
	public static boolean needsTrade = false;
	public static String itemNeeded = "none";
	public static String tradeName = "Wavh";
	public static boolean shouldConnect = false;
	public static String[] COW_LOOT = { "Cowhide" };
	public static NPC lastFish;
	public static Skill attackStyle;
	
	public static int SELLABLE_ITEMS[] = {1511,438,436,439,437};
	public static boolean soldItems = false;
	public static boolean withdrawItems = false;
	public static List<TaskTest>taskTest = new ArrayList<TaskTest>();
	public static Skill currentSkill;
	
}
