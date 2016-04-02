package net.sumo.nextgen.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.osbot.rs07.api.Quests;
import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.Bank.BankMode;
import org.osbot.rs07.api.Chatbox.MessageType;
import org.osbot.rs07.api.Quests.Quest;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.EquipmentSlot;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;

import net.sumo.grandexchange.GrandExchangeAPI;
import net.sumo.grandexchange.GrandExchangePriceAPI;
import net.sumo.nextgen.Nextgen;
import net.sumo.nextgen.enums.FightingAssignment;
import net.sumo.nextgen.enums.FishingAssignment;
import net.sumo.nextgen.enums.MiningAssigment;
import net.sumo.nextgen.enums.WCAssignment;
import net.sumo.nextgen.gear.GearSetups;
import net.sumo.nextgen.queststage.CooksAssistantStage;
import net.sumo.nextgen.queststage.RestlessGhostStage;
import net.sumo.nextgen.queststage.RomeoAndJulietStage;
import net.sumo.nextgen.queststage.RuneMysteriesStage;
import net.sumo.nextgen.queststage.SheepShearerStage;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.stage.StageType;
import net.sumo.nextgen.stage.TaskTest;
import net.sumo.nextgen.task.enums.AttackStyle;
import net.sumo.nextgen.task.quest.cooksass.FinishQuest;
import net.sumo.nextgen.task.quest.cooksass.GetEgg;
import net.sumo.nextgen.task.quest.cooksass.GetMilk;
import net.sumo.nextgen.task.quest.cooksass.MakeFlour;
import net.sumo.nextgen.task.quest.cooksass.StartQuestCook;
import net.sumo.nextgen.task.quest.restlessghost.GetAmulet;
import net.sumo.nextgen.task.quest.restlessghost.GetSkull;
import net.sumo.nextgen.task.quest.restlessghost.ReturnSkull;
import net.sumo.nextgen.task.quest.restlessghost.StartQuestGhost;
import net.sumo.nextgen.task.quest.restlessghost.TalkToGhost;
import net.sumo.nextgen.task.quest.sheepshearer.GatherWool;
import net.sumo.nextgen.task.quest.sheepshearer.SpinWool;
import net.sumo.nextgen.task.quest.sheepshearer.StartQuestSheep;
import net.sumo.nextgen.task.romeoandjuliet.GiveMessageToRomeo;
import net.sumo.nextgen.task.romeoandjuliet.SpeakToApothecary;
import net.sumo.nextgen.task.romeoandjuliet.SpeakToFather;
import net.sumo.nextgen.task.romeoandjuliet.SpeakToJuliet;
import net.sumo.nextgen.task.romeoandjuliet.SpeakToJulietScene;
import net.sumo.nextgen.task.romeoandjuliet.StartQuestRomeo;
import net.sumo.nextgen.task.runemysteries.GetNotes;
import net.sumo.nextgen.task.runemysteries.ReturnNotes;
import net.sumo.nextgen.task.runemysteries.ReturnPackage;
import net.sumo.nextgen.task.runemysteries.ReturnTalisman;
import net.sumo.nextgen.task.runemysteries.StartQuestRuneMyst;

public abstract class Task {
	protected Script s;
	protected Random random;
	private Stage lastStage = null;
	RS2Object door;
	NPC grabben;
	String npcName;
	private String itemName;
	protected GrandExchangePriceAPI gePrice;
	protected GrandExchangeAPI ge;

	public Task init(Script script) {
		this.s = script;
		this.random = new Random();
		gePrice = new GrandExchangePriceAPI();
		ge = new GrandExchangeAPI(s);
		return this;
	}

	public void remove() {
		Nextgen.tasks.remove(this);
	}

	public abstract boolean active();

	public abstract void execute();

	public boolean shouldDoQuest(Quest quest) {
		if (!isQuestCompleted(quest)) {
			return true;
		}
		return false;
	}

	public Stage getStage() {
		/*
		 * for (int i = 0; i < Resources.STAGE_LIST.size();) {
		 * 
		 * Stage stage = Resources.STAGE_LIST.get(i); if (stage.getType() ==
		 * StageType.QUEST) { if(Quest.valueOf(stage.toString()) != null){ if
		 * (shouldDoQuest(Quest.valueOf(stage.toString()))) { return stage; } }
		 * } i++; } return null;
		 */
		/*
		 * for (int i = 0; i < Resources.TASKTEST_LIST.size();) { Stage stage =
		 * Resources.TASKTEST_LIST.get(i).getStage(); TaskTest t =
		 * Resources.TASKTEST_LIST.get(i); if (stage.getType() ==
		 * StageType.QUEST) { s.log(stage); if (Quest.valueOf(stage.toString())
		 * != null) { s.log("yo0"); if
		 * (shouldDoQuest(Quest.valueOf(stage.toString()))) { s.log("yo");
		 * Resources.currentStage = stage; return stage; } } }else{
		 * if(getLevel(t.getSkill()) < t.getLevelGoal()){ s.log("new task: " +
		 * t.getLevelGoal() + stage); Resources.currentStage = stage;
		 * Resources.currentSkillGoal = t.getLevelGoal(); return stage; } } i++;
		 * } return null; for (int i = 0; i <
		 * Arrays.asList(Stage.values()).size();) { Stage stage =
		 * Arrays.asList(Stage.values()).get(i); if (stage.getType() ==
		 * StageType.QUEST) { s.log(stage); if (Quest.valueOf(stage.toString())
		 * != null) { s.log("yo0"); if
		 * (shouldDoQuest(Quest.valueOf(stage.toString()))) { s.log("yo");
		 * Resources.currentStage = stage; return stage; } } }else{
		 * Resources.currentStage = stage; return stage; } i++; } return null;
		 */

		if (shouldDoQuest(Quest.COOKS_ASSISTANT)) {
			Resources.currentStage = Stage.COOKS_ASSISTANT;
			return Stage.COOKS_ASSISTANT;
		} else if (shouldDoQuest(Quest.SHEEP_SHEARER)) {
			Resources.currentStage = Stage.SHEEP_SHEARER;
			return Stage.SHEEP_SHEARER;
		} else if (shouldDoQuest(Quest.THE_RESTLESS_GHOST)) {
			Resources.currentStage = Stage.THE_RESTLESS_GHOST;
			return Stage.THE_RESTLESS_GHOST;
		} else if (shouldDoQuest(Quest.RUNE_MYSTERIES)) {
			Resources.currentStage = Stage.RUNE_MYSTERIES;
			return Stage.RUNE_MYSTERIES;
		} else if (shouldDoQuest(Quest.ROMEO_JULIET)) {
			Resources.currentStage = Stage.ROMEO_JULIET;
			return Stage.ROMEO_JULIET;
		} else if (getWCLevel() < 17) {
			Resources.currentStage = Stage.WOODCUTTING;
			return Stage.WOODCUTTING;
		} else if (getMiningLevel() < 11) {
			Resources.currentStage = Stage.MINING;
			return Stage.MINING;
		} else if (getWCLevel() < 23) {
			Resources.currentStage = Stage.WOODCUTTING;
			return Stage.WOODCUTTING;
		} else if (getMiningLevel() < 15) {
			Resources.currentStage = Stage.MINING;
			return Stage.MINING;
		} else if (getStrLevel() < 6) {
			Resources.currentStage = Stage.STRENGTH;
			Resources.attackStyle = AttackStyle.STRENGTH;
			return Stage.STRENGTH;
		} else if (getAttLevel() < 4) {
			Resources.currentStage = Stage.ATTACK;
			Resources.attackStyle = AttackStyle.ATTACK;
			return Stage.ATTACK;
		}
		return Stage.MINING;
	}

	public boolean shouldWoodcut() {
		return getStage() == Stage.WOODCUTTING;
	}

	public WCAssignment currentWCAssignment() {
		if (getWCLevel() < 21) {
			return WCAssignment.NORMAL_TREE_LUMBRIDGE;
		}
		return WCAssignment.NORMAL_TREE_LUMBRIDGE;
	}

	/*
	 * public WCAssignment getRandomAssignment(int level){
	 * 
	 * boolean set = false; List<WCAssignment> newlist = new
	 * ArrayList<WCAssignment>();
	 * Arrays.asList(WCAssignment.values()).forEach(value->{
	 * if(value.getMaxLevel() > level && value.getMinLevel() < level){
	 * newlist.add(value); set = true; } }); return
	 * newlist.get(Script.random(newlist.size())); }
	 */

	public CooksAssistantStage getCookStage() {
		int stageVarbit = getConfig(29);
		if (stageVarbit == 0) {
			return CooksAssistantStage.START_QUEST;
		} else if (stageVarbit == 1) {
			if (!invContains("Pot of flour") && !Resources.gotFlour) {
				return CooksAssistantStage.MAKE_FLOUR;
			} else if (!invContains("Egg") && !Resources.pickedEgg) {
				return CooksAssistantStage.GET_EGG;
			} else if (!invContains("Bucket of milk") && !Resources.gotMilk) {
				return CooksAssistantStage.GET_MILK;
			} else {
				return CooksAssistantStage.FINISH_QUEST;
			}
		}
		return null;
	}

	public SheepShearerStage getSheepStage() {
		int stageVarbit = getConfig(29);
		if (!Resources.pickedWool) {
			return SheepShearerStage.GATHER_WOOL;
		} else if (!Resources.spanWool) {
			return SheepShearerStage.SPIN_WOOL;
		} else if (!isQuestCompleted(Quest.SHEEP_SHEARER)) {
			return SheepShearerStage.START_QUEST;
		}
		return null;
	}

	public RestlessGhostStage getGhostStage() {
		int stageVarbit = getConfig(107);
		if (stageVarbit == 0) {
			return RestlessGhostStage.START_QUEST;
		} else if (stageVarbit == 1) {
			return RestlessGhostStage.GET_AMULET;
		} else if (stageVarbit == 2) {
			return RestlessGhostStage.TALK_TO_GHOST;
		} else if (stageVarbit == 3) {
			return RestlessGhostStage.GET_SKULL;
		} else if (stageVarbit == 4) {
			return RestlessGhostStage.RETURN_SKULL;
		}
		return null;
	}

	public RuneMysteriesStage getRuneMystStage() {
		int stageVarbit = getConfig(63);
		if (stageVarbit == 0) {
			return RuneMysteriesStage.START_QUEST;
		} else if (stageVarbit == 1 || stageVarbit == 2) {
			return RuneMysteriesStage.RETURN_TALISMAN;
		} else if (stageVarbit == 3) {
			return RuneMysteriesStage.RETURN_PACKAGE;
		} else if (stageVarbit == 4) {
			return RuneMysteriesStage.GET_NOTES;
		} else if (stageVarbit == 5) {
			return RuneMysteriesStage.RETURN_NOTES;
		}
		return null;
	}

	public RomeoAndJulietStage getRomeoStage() {
		int stageVarbit = getConfig(144);
		if (stageVarbit < 10) {
			return RomeoAndJulietStage.START_QUEST;
		} else if (stageVarbit == 10) {
			return RomeoAndJulietStage.SPEAK_TO_JULIET;
		} else if (stageVarbit == 20) {
			return RomeoAndJulietStage.GIVE_MESSAGE_TO_ROMEO;
		} else if (stageVarbit == 30) {
			return RomeoAndJulietStage.SPEAK_TO_FATHER;
		} else if ((stageVarbit == 40 || stageVarbit == 50) && !invContains("Cadava potion")) {
			return RomeoAndJulietStage.SPEAK_TO_APOTHECARY;
		} else if (invContains("Cadava potion") && s.getConfigs().get(1021) != 192) {
			return RomeoAndJulietStage.SPEAK_TO_JULIET;
		} else if (s.getConfigs().get(1021) == 192) {
			return RomeoAndJulietStage.SPEAK_TO_JULIET_CUTSCENE;
		} else if (stageVarbit == 60) {
			return RomeoAndJulietStage.START_QUEST;
		}
		return null;
	}

	public void SkillingAntiBan() throws InterruptedException {
		switch (Script.random(1, 500)) {
		case 1:
			s.camera.movePitch(50);
			break;
		case 2:
			s.camera.moveYaw(100 + (Script.random(1, 70)));
			break;
		case 3:
			s.tabs.open(Tab.SKILLS);
			break;
		case 4:
			s.tabs.open(Tab.SKILLS);
			break;
		case 5:
			s.tabs.open(Tab.QUEST);
			break;
		case 8:
			s.tabs.open(Tab.CLANCHAT);
			break;
		case 9:
			s.tabs.open(Tab.FRIENDS);
			break;
		case 10:
			s.log("sleep 10000");
			s.mouse.moveOutsideScreen();
			sleep(Script.random(5000, 10000));
			break;
		case 11:
			s.log("sleep60000");
			s.mouse.moveOutsideScreen();
			sleep(Script.random(60000, 100000));
			break;
		}
		sleep(Script.random(700, 1800));
		s.tabs.open(Tab.INVENTORY); // RETURNS TO THE INVENTORY TAB AFTER EVERY
									// ANTIBAN INSTANCE
	}

	public boolean objectIsVisible(int i, Area a) {
		RS2Object obj = s.objects.closest(i);
		if (obj != null) {
			if (a.contains(obj)) {
				return true;
			}
		}
		return false;
	}

	public boolean npcIsVisible(int i, Area a) {
		NPC obj = s.npcs.closest(i);
		if (obj != null) {
			if (a.contains(obj)) {
				return true;
			}
		}
		return false;
	}

	public boolean npcIsVisible(int i) {
		NPC obj = s.npcs.closest(i);
		if (obj != null) {
			return true;
		}
		return false;
	}

	public boolean playerIsWearingAmmy(String item) {
		return s.getEquipment().isWearingItem(EquipmentSlot.AMULET, item);
	}

	public void equipAmulet(String item) {
		if (invContains(item)) {
			s.equipment.equip(EquipmentSlot.AMULET, item);
		} else {
			s.log("inventory doesnt contain amulet");
		}

	}

	public boolean objectIsVisible(String i) {
		RS2Object obj = s.objects.closest(i);
		if (obj != null) {
			return true;
		}
		return false;
	}

	public int getConfig(int i) {
		return s.configs.get(i);
	}

	public int getLevel(Skill skill) {
		return s.getSkills().getStatic(skill);
	}

	public int getWCLevel() {
		return getLevel(Skill.WOODCUTTING);
	}

	public int getThievLevel() {
		return getLevel(Skill.THIEVING);
	}

	public boolean isQuestCompleted(Quest quest) {
		return s.quests.isComplete(quest);
	}

	public boolean widgetIsVisible(int i) {
		return s.widgets.isVisible(i);
	}

	public boolean invContains(String name) {
		return s.inventory.contains(name);
	}

	public boolean invContains(int amount, String name) {
		int itemamount = (int) s.inventory.getAmount(name);
		if (invContains(name) && itemamount == amount) {
			return true;
		}
		return false;
	}

	public boolean invContainsAtleast(int amount, String name) {
		int itemamount = (int) s.inventory.getAmount(name);
		if (invContains(name) && itemamount >= amount) {
			return true;
		}
		return false;
	}

	public int getAmount(String name) {
		return (int) s.inventory.getAmount(name);
	}

	public boolean invContains(int name) {
		return s.inventory.contains(name);
	}

	public boolean playerInArea(Area area) {
		return area.contains(s.myPlayer());
	}

	public void sleepDistance(int distance) {
		if (distance > 15) {
			sleep(20000);
		} else {
			sleep(distance * 1000 + 2000);
		}
	}

	public int interactGroundItem(String action, String item) {
		GroundItem groundItem = s.groundItems.closest(item);
		if (groundItem != null) {
			groundItem.interact(action);
			sleepDistance(getDistance(groundItem));
		}
		return 300;
	}

	public int interactGroundItem(String action, int item) {
		GroundItem groundItem = s.groundItems.closest(item);
		if (groundItem != null) {
			groundItem.interact(action);
			sleepDistance(getDistance(groundItem));
		}
		return 300;
	}

	public int interactNPC(String action, String npc) {
		NPC realnpc = s.npcs.closest(npc);
		if (realnpc != null) {
			realnpc.interact(action);
			sleepDistance(getDistance(realnpc));
		} else {
			s.log("finns inte");
		}
		return 300;
	}

	public int interactNPC(String action, int npc[]) {
		NPC realnpc = s.npcs.closest(npc);
		if (realnpc != null) {
			realnpc.interact(action);
			sleepDistance(getDistance(realnpc));
		} else {
			s.log("finns inte");
		}
		return 300;
	}

	public int interactNPC(String action, int npc) {
		NPC realnpc = s.npcs.closest(npc);
		if (realnpc != null) {
			realnpc.interact(action);
			sleepDistance(getDistance(realnpc));
		} else {
			s.log("finns inte");
		}
		return 300;
	}

	public int TalkNPC(String npc) {
		NPC realnpc = s.npcs.closest(npc);
		if (realnpc != null) {
			realnpc.interact("Talk");
			sleepDistance(getDistance(realnpc));
		} else {
			s.log("finns inte");
		}
		return 300;
	}

	public int TalkNPC(int npc) {
		NPC realnpc = s.npcs.closest(npc);
		if (realnpc != null) {
			realnpc.interact("Talk-to");
			sleepDistance(getDistance(realnpc));
		} else {
			s.log("finns inte");
		}
		return 300;
	}

	public boolean isTalkingWithCook() {
		if (widgetIsVisible(162, 546)) {
			return true;
		}
		return widgetIsVisible(162, 546);
	}

	public boolean isTalkingWithNPC(int npc) {
		NPC interacting = (NPC) s.myPlayer().getInteracting();
		if (interacting != null) {
			if (interacting.getId() == npc) {
				return true;
			}
		}
		return false;

	}

	public boolean inDialogue() {
		return s.dialogues.inDialogue();
	}

	public boolean widgetIsVisible(int root, int child) {
		return s.widgets.isVisible(root, child);
	}

	public void updateState(String string) {
		Resources.CURRENT_STATE = string;
	}

	public void interactItemWithObject(String item, String object) {
		if (!s.inventory.isItemSelected()) {
			s.getInventory().getItem(item).interact("Use");
			sleep(1000, 1250);
		} else {
			s.getObjects().closest(object).interact("Use");
			sleep(750, 1375);
		}
	}

	public void getPot() {
		if (playerInArea(Areas.COOK_AREA1_CHEF_ROOM)) {
			interactGroundItem("Take", "Pot");
		} else {
			webWalk(Areas.COOK_AREA1_CHEF_ROOM);
		}
	}

	public void getBucket() {
		if (playerInArea(Areas.COOK_AREA2_LUMBRIDGE_BASEMENT)) {
			if (playerInArea(Areas.COOK_AREA3_LUMBRIDGE_BASEMENT_BUCKET)) {
				interactGroundItem("Take", "Bucket");
			} else {
				walk(Areas.COOK_AREA3_LUMBRIDGE_BASEMENT_BUCKET);
			}
		} else {
			if (playerInArea(Areas.COOK_AREA1_CHEF_ROOM)) {
				interactObject("Climb-down", "Trapdoor");
			} else {
				webWalk(Areas.COOK_AREA1_CHEF_ROOM);
			}
		}
	}

	public boolean messageExists(String message) {
		return s.chatbox.contains(MessageType.ALL, message);
	}

	public void interactItemWithObject(String item, int object) {
		RS2Object objectz = s.objects.closest(object);
		if (s.inventory.getItem(item) != null && objectz != null) {
			if (!s.inventory.isItemSelected()) {
				s.getInventory().getItem(item).interact("Use");
				sleep(1000, 1250);
			} else {
				objectz.interact("Use");
				sleep(750, 1375);
			}
		} else {
			s.log("somethign is wrong");
		}
	}

	public int interactObject(RS2Object object, String action) {

		if (object != null) {
			object.interact(action);
			sleep(getDistance(object) * 1000 + 2000);
		}
		return 300;
	}

	public int interactObject(String action, String item) {
		RS2Object object = s.objects.closest(item);
		if (object != null) {
			object.interact(action);
			sleep(getDistance(object) * 1000 + 2000);
		}
		return 300;
	}

	public int interactObject(String action, int item) {
		RS2Object object = s.objects.closest(item);
		if (object != null) {
			object.interact(action);
			sleep(getDistance(object) * 1000 + 2000);
		}
		return 300;
	}

	public int interactObject(String action, int item[]) {
		RS2Object object = s.objects.closest(item);
		if (object != null) {
			object.interact(action);
			sleep(getDistance(object) * 1000 + 2000);
		}
		return 300;
	}

	public int interactObject(String action, int item, Area a) {
		RS2Object object = s.objects.closest(item);
		if (object != null) {
			object.interact(action);
			sleep(getDistance(object) * 1000 + 2000);
		}
		return 300;
	}

	public int interactObject(String action, RS2Object object) {
		if (object != null) {
			object.interact(action);
			sleep(getDistance(object) * 1000 + 2000);
		}
		return 300;
	}

	public RS2Object getClosest(int i, Area a) {
		RS2Object object = s.objects.closest(i);
		if (object != null) {
			if (a.contains(object)) {
				return object;
			}
		}
		return null;
	}

	public int getDistance(Entity item) {
		return item.getPosition().distance(s.myPlayer());
	}

	public void webWalk(Area area) {
		s.walking.webWalk(area.getRandomPosition());
	}

	public void webWalk(Position pos) {
		s.walking.webWalk(pos);
	}

	public void walk(Area area) {
		s.walking.walk(area);
	}

	public void sleep(int millisec) {
		try {
			Resources.sleepGoal = System.currentTimeMillis() + millisec;
			Script.sleep(millisec + Script.random(1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sleep(int millisec, int millisec2) {
		try {
			int sleepTime = Script.random(millisec, millisec2);
			Resources.sleepGoal = System.currentTimeMillis() + sleepTime;
			Script.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void talkToNPC(int npc, String[] dialogue, Area npcArea) {
		grabben = s.npcs.closest(npc);
		if (grabben != null)
			npcName = grabben.getName();
		if (playerInArea(npcArea)) {
			updateState("Talking to " + npcName);
			questDialogue(npc, dialogue);
		} else {
			updateState("Walking to " + npcName);
			webWalk(npcArea);

		}

	}

	public void talkToNPC(int npc, String[] dialogue, Area npcArea, Area failArea, int doorID, Area doorArea) {
		door = getClosest(doorID, doorArea);
		grabben = s.npcs.closest(npc);
		if (grabben != null)
			npcName = grabben.getName();

		if (playerInArea(npcArea) || playerInArea(failArea)) {
			updateState("Talking to " + npcName);
			if ((failArea.contains(grabben) || playerInArea(failArea)) && door != null) {
				interactObject("open", door);
			}
			questDialogue(npc, dialogue);
		} else {
			updateState("Walking to " + npcName);
			webWalk(npcArea);

		}

	}

	public void getGroundItem(String action, int itemID, Area area) {
		GroundItem groundItem = s.groundItems.closest(itemID);
		if (groundItem != null)
			itemName = groundItem.getName();
		if (playerInArea(area)) {
			updateState("Lets get the " + groundItem);
			interactGroundItem(action, itemID);
		} else {
			updateState("we have to find the " + itemName + "!");
			webWalk(area);
		}
	}

	public void getItemFromObject(String action, Area area, int[] cADAVA_BUSH_ID) {
		if (playerInArea(area)) {
			updateState("Lets get the item");
			interactObject(action, cADAVA_BUSH_ID);
		} else {
			updateState("we have to find the item");
			webWalk(area);
		}
	}

	public void questDialogue(int npc, String[] dialogue) {
		if (inDialogue()) {
			try {
				s.dialogues.completeDialogue(dialogue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			TalkNPC(npc);
		}
	}

	public boolean shouldFish() {
		return inventoryContains(currentFishingAssignment().getFishGear()) && !inventoryIsFull();
	}

	public boolean shouldFight() {
		return getStage().getType() == StageType.COMBAT && Resources.BUY_LIST.isEmpty();
	}

	public boolean playerHasFishGear() {
		return inventoryContains(currentFishingAssignment().getFishGear());
	}

	public boolean playerIsMoving() {
		if (s.myPlayer().isMoving()) {
			return true;
		}
		return false;
	}

	public String currentAxe() {
		if (getWoodCuttingLevel() < 31) {
			return "Bronze axe";
		} else if (getWoodCuttingLevel() < 41) {
			return "Adamant axe";
		} else {
			return "Rune axe";
		}
	}

	public String currentPickaxe() {
		if (getMiningLevel() < 31) {
			return "Bronze pickaxe";
		} /*
			 * else if(getMiningLevel() < 31){ return "Mithril pickaxe"; }
			 */else if (getMiningLevel() < 41) {
			return "Adamant pickaxe";
		}
		return "Rune pickaxe";
	}

	public boolean playerIsBusy() {
		if (s.myPlayer().getInteracting() != null || s.myPlayer().getAnimation() != -1) {
			return true;
		}
		return false;
	}

	public boolean inventoryContains(String strings) {
		return s.inventory.contains(strings);
	}

	public boolean inventoryContains(String[] strings) {
		return s.inventory.contains(strings);
	}

	public boolean inventoryIsFull() {
		return s.inventory.isFull();
	}

	public boolean shouldUseDeposit() {
		if (currentMiningAssigment() != null) {
			return currentMiningAssigment().getDepositBox();
		}
		return false;
	}

	public boolean continueMessageIsVisible() {
		if (s.widgets.getWidgetContainingText("Click here to continue") != null) {
			return true;
		}
		return false;
	}

	public void clickContinue() throws InterruptedException {
		s.getDialogues().clickContinue();
		sleep(1000);
	}

	public void checkContinue() {
		if (continueMessageIsVisible()) {
			try {
				clickContinue();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

	public void depositAll() {
		if (s.bank.isOpen()) {
			s.bank.depositAll();
		} else {
			NPC banker = s.npcs.closest("Banker");
			banker.interact("Bank");
			sleep(1250, 1765);
		}
	}

	public void depositAllExceptInDepositBox(String name) {
		if (s.depositBox.isOpen()) {
			s.depositBox.depositAllExcept(name);
			sleep(1000, 1500);
		} else {
			RS2Object depositBox = s.objects.closest(26254);
			if (depositBox != null) {
				depositBox.interact("Deposit");
				sleep(1000, 1500);
			}
		}
	}

	/*
	 * public void withdraw(String name){ if(s.bank.isOpen()){
	 * if(inventoryIsFull()){ depositAll(); }else{ if(s.bank.contains(name)){
	 * s.bank.withdraw(name, 1); }else{ updateMessage("bank doesnt contain" +
	 * name); s.stop(); s.stop(); } } }else{ NPC banker =
	 * s.npcs.closest("Banker"); banker.interact("Bank"); sleep(1250,1765); } }
	 */

	public NPC getClosestFreeNPC(String name) {
		return s.getNpcs()
				.closest(npc -> !npc.isUnderAttack() && npc.getHealthPercent() > 0 && npc.getInteracting() == null
						&& npc.getName().equals(name) && npc.exists() && npc.hasAction("Attack"));
		/*
		 * return s.getNpcs().getAll().stream() .filter(npc -> (npc.exists() &&
		 * npc.getName().equalsIgnoreCase(name)) && npc.getInteracting() == null
		 * && npc.isAttackable() && !npc.isUnderAttack() && npc.getHealth() > 0)
		 * .collect(Collectors.toList());
		 */
	}

	@SuppressWarnings("unchecked")
	public RS2Object getClosestFreeObject(String name, String action) {
		return s.getObjects().closest(object -> object.exists() && object.hasAction(action));
		/*
		 * return s.getNpcs().getAll().stream() .filter(npc -> (npc.exists() &&
		 * npc.getName().equalsIgnoreCase(name)) && npc.getInteracting() == null
		 * && npc.isAttackable() && !npc.isUnderAttack() && npc.getHealth() > 0)
		 * .collect(Collectors.toList());
		 */
	}

	public List<NPC> getAllClosestFreeNPC(String name) {
		return s.getNpcs().getAll().stream()
				.filter(npc -> (!npc.isUnderAttack() && npc.getHealthPercent() > 0 && npc.getInteracting() == null
						&& npc.getName().equals(name) && npc.exists() && npc.hasAction("Attack")))
				.collect(Collectors.toList());
	}

	public List<NPC> getAllClosestUnavaiableNPC(String name) {
		return s.getNpcs().getAll().stream()
				.filter(npc -> (npc.isUnderAttack() && npc.getHealthPercent() > 0 && npc.getInteracting() != null
						&& npc.getName().equals(name) && npc.exists() && npc.hasAction("Attack")))
				.collect(Collectors.toList());
	}

	public void updateAvailableNPCs(String name) {
		NPC n = getClosestFreeNPC(name);
		if (n != null) {
			if (Resources.wcPos != n.getPosition()) {
				Resources.wcPos = n.getPosition();
				for (int i = 0; i < getAllClosestFreeNPC(name).size(); i++) {
					Resources.availableNPCs[i] = getAllClosestFreeNPC(name).get(i).getPosition();
				}
				sleep(550, 750);
			}
		} else {
			Resources.wcPos = null;
		}
	}

	public void updateAvailableTrees(String name) {
		RS2Object n = getClosestFreeObject(name, "Chop down");
		if (n != null) {
			if (Resources.wcPos != n.getPosition()) {
				Resources.wcPos = n.getPosition();
				for (int i = 0; i < getAllClosestFreeNPC(name).size(); i++) {
					Resources.availableNPCs[i] = getAllClosestFreeNPC(name).get(i).getPosition();
				}
				sleep(550, 750);
			}
		} else {
			Resources.wcPos = null;
		}
	}

	public void updateUnAvailableNPCs(String name) {
		// updateMessage("Close players: " + r.closePlayers);
		List<NPC> n = getAllClosestUnavaiableNPC(name);
		if (n != null) {

			for (int i = 0; i < getAllClosestUnavaiableNPC(name).size(); i++) {
				String playerName = getAllClosestUnavaiableNPC(name).get(i).getInteracting().getName();
				if (playerName != null && playerName != s.myPlayer().getName()) {
					if (!Arrays.asList(Resources.closeNames).contains(playerName)) {
						Resources.closePlayers++;
						Resources.closeNames[i] = playerName;
					}
				}
			}
			sleep(550, 750);

		} else {

		}
	}

	public void updateFightingNPC() {
		NPC fighting = (NPC) s.myPlayer().getInteracting();
		if (fighting != null) {
			Resources.fightingPos = fighting.getPosition();
		} else {
			Resources.fightingPos = null;
		}
	}

	public void antiBan() {
		Random r = new Random();
		int randomInt = r.nextInt(100);
		switch (randomInt) {
		case 10: {
			s.log("lets move screen");
			break;
		}
		}
	}

	public void updateMessage(String logUpdate) {
		if (logUpdate != Resources.lastLogUpdate) {
			s.log(logUpdate);
			Resources.lastLogUpdate = logUpdate;
		}
		return;
	}

	public void hopWorlds() {
		s.worlds.hopToP2PWorld();
	}

	public void hopF2PWorlds() {
		s.worlds.hopToF2PWorld();
	}

	public void hopWorld(int world) {
		s.worlds.hop(world);
	}

	public boolean playerInPos(Position pos) {
		if (s.myPosition().distance(pos) == 0) {
			return true;
		}
		return false;
	}

	public boolean shouldHopBecausePlayers(int max) {
		List<Player> players = s.getPlayers().getAll();
		int i = players.size();
		if (i > Resources.maxPlayer) {
			return true;
		}
		return false;
	}

	public boolean shouldHopBecauseMessage() {
		checkContinue();
		List<String> messages = s.chatbox.getMessages(MessageType.PLAYER);
		if (!messages.isEmpty()) {
			s.log(messages.toString());
			s.log("hop message");
			return true;
		}
		return false;
	}

	public void attack(String name) throws InterruptedException {
		int n = 0;
		NPC closestTarget = getClosestFreeNPC(name);
		if (closestTarget != null && !playerIsBusy()) {
			closestTarget.interact("Attack");
			updateMessage("NPC exists, lets attack.");
			while (closestTarget.getInteracting() == null && closestTarget != null && n < 50) {
				n++;
				sleep(100);
			}
			n = 0;
			if (closestTarget.getInteracting() == s.myPlayer()) {
				updateMessage("we are sucessfully fighting our target");
			} else {
				updateMessage("Some one got to the target faster than us. Lets find a new target");
				closestTarget = getClosestFreeNPC(name);
				if (closestTarget != null) {
					attack(name);
				}
			}
		} else {
			updateMessage("NPC is unavailable or player is already fighting");
		}
	}

	public void prepareForFight(String name) throws InterruptedException {
		checkContinue();
		updateAvailableNPCs("Seagull");
		updateFightingNPC();
		updateUnAvailableNPCs("Seagull");
		while ((playerIsBusy() && s.myPlayer().isUnderAttack()) || playerIsMoving()) {
			updateMessage("We are already fighting, lets sleep");
			checkContinue();
			updateAvailableNPCs("Seagull");
			updateFightingNPC();
			antiBan();
			sleep(100);
		}
	}

	public void fight(String name) throws InterruptedException {
		prepareForFight(name);
		if (shouldLoot()) {
			loot();
		} else {
			attack(name);
		}

	}

	public void loot() {
		GroundItem loot = s.groundItems.closest(currentFightingAssignment().getLoot());
		if (loot != null && currentFightingAssignment().getFightArea().contains(loot)) {
			if (loot.isOnScreen()) {
				loot.interact("Take");
				sleep(1250, 2200);
			} else {
				s.log("walking to item");
				s.walking.walk(loot);
			}
		} else {
			s.log("loot is not available");
			return;
		}

	}

	public boolean lootIsAvailable() {
		GroundItem loot = s.groundItems.closest(currentFightingAssignment().getLoot());
		if (loot != null) {
			if (currentFightingAssignment().getFightArea().contains(loot)) {
				return true;
			}
		}
		return false;
	}

	public boolean shouldLoot() {
		if (currentFightingAssignment().getLoot() != null) {
			if (lootIsAvailable()) {
				return true;
			}
		}
		return false;
	}

	public FightingAssignment currentFightingAssignment() {
		return FightingAssignment.SEAGULL;
	}

	public FishingAssignment currentFishingAssignment() {
		return FishingAssignment.SCHRIMPS_DRAYNOR;
	}

	public WCAssignment currentWCAssigment() {
		if (getWoodCuttingLevel() < 25) {
			return WCAssignment.NORMAL_TREE_LUMBRIDGE;
		}
		return WCAssignment.NORMAL_TREE_LUMBRIDGE;
	}

	public MiningAssigment currentMiningAssigment() {
		if (getMiningLevel() < 6) {
			return MiningAssigment.TIN_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 9) {
			return MiningAssigment.COPPER_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 12) {
			return MiningAssigment.TIN_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 14) {
			return MiningAssigment.COPPER_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 17) {
			return MiningAssigment.TIN_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 21) {
			return MiningAssigment.COPPER_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 24) {
			return MiningAssigment.TIN_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 27) {
			return MiningAssigment.COPPER_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 29) {
			return MiningAssigment.TIN_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 31) {
			return MiningAssigment.COPPER_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 50) {
			return MiningAssigment.IRON_ORE_RIMMINGTON;
		} else if (getMiningLevel() < 60) {
			return MiningAssigment.GOLD_ORE_RIMMINGTON;
		}
		return MiningAssigment.IRON_ORE_RIMMINGTON;
	}

	public void checkStyle() {
		checkContinue();

		if (s.widgets.isVisible(593)) {

			if (Resources.attackStyle == AttackStyle.STRENGTH && (attackStyle() != 1)) {
				s.log("lets click train str1");
				s.mouse.click(689, 270, false);// click "train strength"
			} else if (Resources.attackStyle == AttackStyle.ATTACK && (attackStyle() != 0)) {
				s.log("lets click train att1");
				s.mouse.click(601, 269, false); // click "train attack"
			} else if (Resources.attackStyle == AttackStyle.DEFENCE && (attackStyle() != 0)) {
				s.log("lets click train def1");
				s.mouse.click(701, 335, false); // click "train def"
			}
			/*
			 * if (getStrLevel() < Resources.strGoal1 && (attackStyle() != 1)) {
			 * s.log("lets click train str1"); s.mouse.click(689, 270, false);//
			 * click "train strength" } else if (getAttLevel() <
			 * Resources.attGoal1 && (attackStyle() != 0) && getStrLevel() ==
			 * Resources.strGoal1) { s.log("lets click train att1");
			 * s.mouse.click(601, 269, false); // click "train attack" } else if
			 * (getDefLevel() < Resources.defGoal1 && (attackStyle() != 3) &&
			 * getAttLevel() == Resources.attGoal1) { s.log(
			 * "lets click train def1"); s.mouse.click(701, 335, false); //
			 * click "train def" } else if (getStrLevel() < Resources.strGoal2
			 * && getAttLevel() >= Resources.attGoal1 && (attackStyle() != 1)) {
			 * s.log("lets click train str2"); s.mouse.click(689, 270, false);//
			 * click "train strength" } else if (getAttLevel() <
			 * Resources.attGoal2 && (attackStyle() != 0) && getStrLevel() ==
			 * Resources.strGoal2) { s.log("lets click train att2");
			 * s.mouse.click(601, 269, false); // click "train attack" } else if
			 * (getDefLevel() < Resources.defGoal2 && getDefLevel() >=
			 * Resources.defGoal1 && (attackStyle() != 3) && getAttLevel() ==
			 * Resources.attGoal2) { s.log("lets click train def2");
			 * s.mouse.click(701, 335, false); // click "train def" }
			 */

		} else {

			s.mouse.click(545, 190, false);
			s.log("200");
			checkStyle();

		}

	}

	public int attackStyle() {

		return s.configs.get(43);

	}

	public boolean playerHasAxe() {
		return (inventoryContains(currentAxe()) || s.equipment.isWieldingWeapon(currentAxe()));
	}

	public boolean playerHasAxe(String name) {
		return (inventoryContains(name) || s.equipment.isWieldingWeapon(name));
	}

	public boolean playerHasPickaxe() {
		return (inventoryContains(currentPickaxe()) || s.equipment.isWieldingWeapon(currentPickaxe()));
	}

	public boolean shouldCutTree() {
		return playerHasAxe() && !inventoryIsFull();
	}

	public boolean shouldFight(GearSetups gear) {
		return !needToWithdrawGear(gear) || needToDeposit();
	}

	public boolean needToDeposit() {
		if (inventoryIsFull() && currentFightingAssignment().getLoot() != null) {
			return true;
		}
		return false;
	}

	public boolean shouldMineOre() {
		return playerHasPickaxe() && !inventoryIsFull();
	}

	public boolean shouldMine() {
		return getStage() == Stage.MINING;
	}

	public void depositAll(String item) {
		NPC banker = s.npcs.closest("Banker");
		if (bankIsOpen()) {
			if (inventoryContains(item)) {
				s.bank.depositAll(item);
				sleep(1000, 2100);
			} else {
				return;
			}
		} else {
			openBank();
			sleep(1000, 2100);
		}
	}

	public void depositAllExcept(String[] strings) {
		NPC banker = s.npcs.closest("Banker");
		if (bankIsOpen()) {
			s.bank.depositAllExcept(strings);
			sleep(1000, 2100);

		} else {
			openBank();
			sleep(1000, 2100);
		}
	}

	public void depositAllExcept(String strings) {
		NPC banker = s.npcs.closest("Banker");
		if (bankIsOpen()) {
			s.bank.depositAllExcept(strings);
			sleep(1000, 2100);

		} else {
			openBank();
			;
			sleep(1000, 2100);
		}
	}

	public boolean shouldDepositBecauseMessage() throws InterruptedException {
		checkContinue();
		List<String> messages = s.chatbox.getMessages(MessageType.GAME);
		if (messages != null) {
			s.log(messages);
			for (int i = 0; i < messages.size(); i++) {
				s.log(messages.get(i));
				if (messages.get(i).contains("You don't have enough")) {
					s.log(messages.get(i));
					s.log("lets deposit all");
					return true;
				}
			}
		}
		return false;

	}

	public void withdraw(int amount, String item) throws InterruptedException {
		if (shouldDepositBecauseMessage()) {
			if (s.bank.isOpen()) {
				s.bank.depositAll();
				sleep(1000);
			}
		}
		NPC banker = s.npcs.closest("Banker");
		if (bankIsOpen()) {
			if (s.bank.contains(item)) {
				if (getAmount(item) != amount) {
					if (getAmount(item) > amount) {
						s.bank.deposit(item, (getAmount(item)) - amount);
					} else if (getAmount(item) < amount) {
						s.bank.withdraw(item, (amount - getAmount(item)));
					}
				} else {
					return;
				}
				sleep(500, 1250);
			} else {
				s.bank.close();
				// buy item == true
			}
		} else {
			openBank();
		}

	}

	public void withdraw(String item) {
		NPC banker = s.npcs.closest("Banker");
		if (bankIsOpen()) {
			if (getAmount(item) > 1) {
				if (getAmount(item) > 1) {
					s.bank.deposit(item, (getAmount(item)) - 1);
				} else if (getAmount(item) < 1) {
					s.bank.withdraw(item, (1 - getAmount(item)));
				}
			} else {
				s.log("lets check if bank contains");
				if (s.bank.contains(item)) {
					s.bank.withdraw(item, 1);
				} else {
					s.log("we have to trade, from withdraw");
					s.bank.close();
					// buyitem == true
				}

			}
			sleep(1000, 2300);
		} else {
			openBank();
			sleep(1000, 2100);
		}

	}
	
	public void buyItems(){
		if (!s.getGrandExchange().isOpen()) { //Checks if ge is open
			ge.openGE(); //open ge randomly using booth or npc
		} else {
			Resources.BUY_LIST.forEach(item -> {
				int coins = (int) s.inventory.getAmount(995);
				while(!s.inventory.contains(item)){
					ge.collectItems(false);
					ge.createBuyOffer(item, coins, 1);
				}
				if(s.inventory.contains(item)){
					Resources.BUY_LIST.remove(item);
				}
			});
		}
	}

	public void sellSellables(){
		if (!s.getGrandExchange().isOpen()) { //Checks if ge is open
			ge.openGE(); //open ge randomly using booth or npc
		} else {
			if(s.inventory.contains(Resources.SELLABLE_ITEMS)){
				for(int i = 0; i<Resources.SELLABLE_ITEMS.length;){
				s.log(i);
				int id = Resources.SELLABLE_ITEMS[i];
				s.log(id);
				while(s.inventory.contains(id)){
					s.log("inventory contains "+ id);
					Item item = s.inventory.getItem(id);
					String itemName = item.getName();
					int amount = item.getAmount();
					ge.collectItems(false); //collect items (boolean true -> to bank, false -> inventory)
					ge.createSellOffer(itemName, 10, amount); //Sells all of the specified items in inventory at specified price ( 0 = all, int = specified amount)
					sleep(1000);
				}
				i++;
				}
			}else{
				ge.collectItems(false);
				Resources.soldItems = true;
			}
			
			
		}
	}

	public void withdrawSellables() {
		NPC banker = s.npcs.closest("Banker");
		if (bankIsOpen()) {
			while (s.bank.contains(Resources.SELLABLE_ITEMS)) {
				if (s.bank.getWithdrawMode() != BankMode.WITHDRAW_NOTE) {
					s.getBank().enableMode(BankMode.WITHDRAW_NOTE);
				} else {
					for (int i = 0; i < Resources.SELLABLE_ITEMS.length;) {
						if (s.bank.contains(Resources.SELLABLE_ITEMS[i])) {
							s.log("bank contains " + Resources.SELLABLE_ITEMS[i]);
							s.bank.withdrawAll(Resources.SELLABLE_ITEMS[i]);
							i++;
							sleep(1000);
						}
					}
				}
				sleep(1000);
			}
			sleep(1000, 2300);
			if (!s.bank.contains(Resources.SELLABLE_ITEMS)) {
				Resources.withdrawItems = true;
			}
		} else {
			openBank();
			sleep(1000, 2100);
		}

	}

	private void openBank() {
		if (playerInArea(Banks.LUMBRIDGE_UPPER)) {
			RS2Object booth = s.objects.closest("Bank booth");
			if (booth != null) {
				booth.interact("Bank");
				sleep(4000, 6500);
			}
		} else {
			try {
				s.bank.open();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean bankIsOpen() {
		return s.bank.isOpen();
	}

	public void checkBank() {
		if (bankIsOpen()) {
			closeBank();
		}
		return;
	}

	public void closeBank() {
		s.bank.close();
		sleep(950);
	}

	int getStrLevel() {
		return getLevel(Skill.STRENGTH);
	}

	int getAttLevel() {
		return getLevel(Skill.ATTACK);
	}

	int getDefLevel() {
		return getLevel(Skill.DEFENCE);
	}

	int getMiningLevel() {
		return getLevel(Skill.MINING);
	}

	int getFishingLevel() {
		return getLevel(Skill.FISHING);
	}

	int getWoodCuttingLevel() {
		return getLevel(Skill.WOODCUTTING);
	}

	int getFireMakingLevel() {
		return getLevel(Skill.FIREMAKING);
	}
	// fm
	// fishing
	// cooking
	// kill cows and tan hide
	// else if(get AttLevel() < r.attGoal2 || getStrLevel() < r.strGoal2 ||
	// getDefLevel() < r.defGoal2)) {
	// kill cows and tan hide
	// return PlayerTask.FIGHT
	// }
	// kör lite range med crossbow , bury all bones o picka upp arrows.
	// smithing
	// crafting
	// gör runecrafting questet o träna runecrafting
	/*
	 * else if(getFireMakingLevel() < r.fmGoal1){ return PlayerTask.FIREMAKING;
	 * } else if(getFishingLevel() < r.fishingGoal1){ return PlayerTask.FISH; }
	 */

	/*
	 * * else if (getFishingLevel() < r.fishingGoal1) { return PlayerTask.FISH;
	 * } else if (getFireMakingLevel() < r.fmGoal1) { return
	 * PlayerTask.FIREMAKING; } else if ((getAttLevel() < r.attGoal2 ||
	 * getStrLevel() < r.strGoal2 || getDefLevel() < r.defGoal2) &&
	 * r.trainCombat) { return PlayerTask.MINITOURS; } else if
	 * (getWoodCuttingLevel() < r.wcGoal2) { return PlayerTask.WOODCUT; } else
	 * if (getFishingLevel() < r.fishingGoal2) {
	 * 
	 * }
	 * 
	 * return PlayerTask.WOODCUT; }
	 */

	public boolean inventoryContainsFullGear(GearSetups gear) {
		int check = 0;
		for (int i = 0; i < gear.getFullGear().size(); i++) {
			if (inventoryContains(gear.getFullGear().get(i).toString())) {
				check++;
			} else {
				return false;
			}
		}
		if (check == gear.getFullGear().size()) {
			s.log("inventory contains full gear");
			return true;
		}
		return false;
	}

	public boolean needToWithdrawGear(GearSetups gear) {

		int test = 0;
		for (int i = 0; i < gear.getFullGear().size(); i++) {
			if (s.getEquipment().contains(gear.getFullGear().get(i).toString())) {
				test++;
			} else {
				if(s.inventory.contains(gear.getFullGear().get(i).toString())){
					Item item = s.inventory.getItem(gear.getFullGear().get(i).toString());
					if (item.hasAction("Wield")) {
						s.log("lets wield");
						item.interact("wield");
					} else if (item.hasAction("Wear")) {
						s.log("lets wear");
						item.interact("wear");
					}
					sleep(3000);
					if (s.getEquipment().contains(gear.getFullGear().get(i).toString())) {
						test++;
					}
				}else{
				if (!Resources.WITHDRAW_LIST.contains(gear.getFullGear().get(i))) {
					s.log("we need" + gear.getFullGear().get(i));
					Resources.WITHDRAW_LIST.add(gear.getFullGear().get(i));
				}
				}
			}
		}
		if (test != gear.getFullGear().size()) {
			return true;

		}
		return false;

	}

	public void withdrawNeededItems() {
		if (bankIsOpen()) {
			Resources.WITHDRAW_LIST.forEach(item -> {
				if (s.bank.contains(item)) {
					s.bank.withdraw(item, 1);
					sleep(1350, 1530);
				} else {
					if (!Resources.BUY_LIST.contains(item)) {
						s.log("adding " + item + " to buy list");
						Resources.BUY_LIST.add(item);
					}
				}
			});
		} else {
			openBank();
		}
	}

	public void withdrawEach(String[] items) {
		ArrayList list = (ArrayList) Arrays.asList(items);
		list.forEach(item -> {
			String ourItem = (String) item;
			if (!inventoryContains(ourItem)) {
				withdraw(ourItem);
			}
		});
	}

	public boolean withdrawGear(GearSetups gear) {

		int test = 0;
		for (int i = 0; i < gear.getFullGear().size(); i++) {
			if (s.getEquipment().contains(gear.getFullGear().get(i).toString())) {
				test++;
			} else {
				if (inventoryContains(gear.getFullGear().get(i).toString())) {
					Item item = s.inventory.getItem(gear.getFullGear().get(i).toString());
					s.log("item exists");
					s.log("lets interact");
					if (s.bank.isOpen()) {
						s.bank.close();
					}
					if (item.hasAction("Wield")) {
						s.log("lets wield");
						item.interact("wield");
					} else if (item.hasAction("Wear")) {
						s.log("lets wear");
						item.interact("wear");
					}
					try {
						s.sleep(s.random(100, 500) + 500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					s.log("we have to withdraw");
					withdraw(gear.getFullGear().get(i).toString());
					break;
				}
			}
		}
		if (test != gear.getFullGear().size()) {
			return true;
		}
		return false;
	}

}
