package net.sumo.nextgen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.util.ExperienceTracker;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

import net.sumo.nextgen.dev.Devmode;
import net.sumo.nextgen.dev.Log;
import net.sumo.nextgen.frame.GuiTest;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;
import net.sumo.nextgen.stage.StageType;
import net.sumo.nextgen.stage.TaskTest;
import net.sumo.nextgen.task.BuyItem;
import net.sumo.nextgen.task.Task;
import net.sumo.nextgen.task.fighting.BankFight;
import net.sumo.nextgen.task.fighting.Fight;
import net.sumo.nextgen.task.fighting.WalkToBankFromFight;
import net.sumo.nextgen.task.fighting.WalkToFight;
import net.sumo.nextgen.task.miner.DepositMining;
import net.sumo.nextgen.task.miner.MineOre;
import net.sumo.nextgen.task.miner.WalkToBankFromMining;
import net.sumo.nextgen.task.miner.WalkToDeposit;
import net.sumo.nextgen.task.miner.WalkToMiningPlace;
import net.sumo.nextgen.task.miner.WithdrawPickaxe;
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
import net.sumo.nextgen.task.woodcutter.BankWC;
import net.sumo.nextgen.task.woodcutter.CutTree;
import net.sumo.nextgen.task.woodcutter.WalkToBankFromWC;
import net.sumo.nextgen.task.woodcutter.WalkToWCPlace;

@ScriptManifest(author = "Sumo", info = "NextGen", logo = "", name = "Nextgen", version = 0)
public class Nextgen extends Script {
	public static List<Task> tasks = new ArrayList<Task>();
	private long timeRan;
	private long timeBegan;
	private long timeSpentOnTask;
	private Stage lastStage;
	private long taskBegan;
	Log log;
	ExperienceTracker xp;

	@Override
	public void onStart() {
		
		tasks.add(new Devmode().init(this));
		addTeskTasks();
		xp = this.getExperienceTracker();
		timeBegan = System.currentTimeMillis();
		taskBegan = System.currentTimeMillis();
		xp.startAll();
		addGui();

	}
	
	private void addTeskTasks(){
	
	
		Resources.taskTest.add(new TaskTest(Stage.WOODCUTTING, 17, Skill.WOODCUTTING));
		Resources.taskTest.add(new TaskTest(Stage.MINING, 11, Skill.MINING));
		Resources.taskTest.add(new TaskTest(Stage.WOODCUTTING, 23, Skill.WOODCUTTING));
		Resources.taskTest.add(new TaskTest(Stage.MINING, 15, Skill.MINING));
		Resources.taskTest.add(new TaskTest(Stage.STRENGTH, 6, Skill.STRENGTH));
		Resources.taskTest.add(new TaskTest(Stage.ATTACK, 4, Skill.ATTACK));
		Resources.taskTest.add(new TaskTest(Stage.DEFENCE, 7, Skill.DEFENCE));
		Resources.taskTest.add(new TaskTest(Stage.STRENGTH, 14, Skill.STRENGTH));
		Resources.taskTest.add(new TaskTest(Stage.ATTACK, 10, Skill.ATTACK));
		Resources.taskTest.add(new TaskTest(Stage.DEFENCE, 13, Skill.DEFENCE));
		Resources.taskTest.add(new TaskTest(Stage.ATTACK, 12, Skill.ATTACK));
		Resources.taskTest.add(new TaskTest(Stage.MINING, 19, Skill.MINING));
		Resources.taskTest.add(new TaskTest(Stage.WOODCUTTING, 30, Skill.WOODCUTTING));
		Resources.taskTest.add(new TaskTest(Stage.MINING, 25, Skill.MINING));
		Resources.taskTest.add(new TaskTest(Stage.STRENGTH, 16, Skill.STRENGTH));
		Resources.taskTest.add(new TaskTest(Stage.ATTACK, 17, Skill.ATTACK));
		Resources.taskTest.add(new TaskTest(Stage.DEFENCE, 16, Skill.DEFENCE));
		Resources.taskTest.add(new TaskTest(Stage.STRENGTH, 19, Skill.STRENGTH));
		Resources.taskTest.add(new TaskTest(Stage.ATTACK, 20, Skill.ATTACK));
		Resources.taskTest.add(new TaskTest(Stage.DEFENCE, 20, Skill.DEFENCE));
		Resources.taskTest.add(new TaskTest(Stage.MINING, 28, Skill.MINING));
		log("added");
	}

	private void addGui() {
		GuiTest dual = new GuiTest();

	}

	@Override
	public int onLoop() throws InterruptedException {
		boolean isActive = false;

		updateTask();

		for (Task t : this.tasks) {

			if (t.active()) {
				t.execute();
				isActive = true;
			}

		}

		if (!isActive) {
			log("cant find any state");

			sleep(1000);
		}
		return 500;
	}

	@Override
	public void onPaint(Graphics2D g) {
		timeRan = System.currentTimeMillis() - timeBegan;
		timeSpentOnTask = System.currentTimeMillis() - taskBegan;
		g.setColor(Color.white);
		g.drawString("Total time ran: " + ft(timeRan), 65, 120);
		g.drawString("Total time spent on this task: " + ft(timeSpentOnTask), 65, 135);
		g.drawString("Current task: " + Resources.currentStage, 65, 150);
		g.drawString("Current state: " + Resources.CURRENT_STATE, 65, 165);
		long sleepTime = TimeUnit.MILLISECONDS.toSeconds(Resources.sleepGoal - System.currentTimeMillis());
		if (Resources.currentStage.getSkill() != null) {
			g.drawString("XP gained: " + xp.getGainedXP(Resources.currentStage.getSkill()) + " ("
					+ xp.getGainedXPPerHour(Resources.currentStage.getSkill()) + ")", 65, 180);
			g.drawString("Goal: " + Resources.currentSkillGoal + " (" + getSkills().getDynamic(Resources.currentSkill) + ")", 65, 195);
		}

		if (sleepTime > 0) {
			g.setColor(Color.red);
			g.drawString("Sleeping for another: " + sleepTime + " seconds.", 65, 230);
		}
	}

	public void updateTask() {
		if (lastStage == null) {
			addTask();
			taskBegan = System.currentTimeMillis();
			lastStage = Resources.currentStage;
			xp.startAll();
		} else if (lastStage != Resources.currentStage) {
			tasks.forEach(task -> {
				log("lets remove");
				task.remove();
			});
			addTask();
			taskBegan = System.currentTimeMillis();
			lastStage = Resources.currentStage;
		}

	}

	public void addTask() {
		if (Resources.currentStage == null) {
			log("null");
		} else {
			log("not null");
			tasks.add(new BuyItem().init(this));
			if (Resources.currentStage == Stage.COOKS_ASSISTANT) {
				// lumb
				log("cook ass added");
				tasks.add(new MakeFlour().init(this));
				tasks.add(new GetEgg().init(this));
				tasks.add(new GetMilk().init(this));
				tasks.add(new FinishQuest().init(this));
				tasks.add(new StartQuestCook().init(this));
			} else if (Resources.currentStage == Stage.SHEEP_SHEARER) {
				// sheep
				log("sheep added");
				tasks.add(new GatherWool().init(this));
				tasks.add(new SpinWool().init(this));
				tasks.add(new StartQuestSheep().init(this));
			} else if (Resources.currentStage == Stage.THE_RESTLESS_GHOST) {
				// ghost
				log("ghost added");
				tasks.add(new StartQuestGhost().init(this));
				tasks.add(new GetAmulet().init(this));
				tasks.add(new TalkToGhost().init(this));
				tasks.add(new GetSkull().init(this));
				tasks.add(new ReturnSkull().init(this));
			} else if (Resources.currentStage == Stage.RUNE_MYSTERIES) {
				// rune mysteries
				log("mysteries added");
				tasks.add(new StartQuestRuneMyst().init(this));
				tasks.add(new ReturnTalisman().init(this));
				tasks.add(new ReturnPackage().init(this));
				tasks.add(new GetNotes().init(this));
				tasks.add(new ReturnNotes().init(this));
			} else if (Resources.currentStage == Stage.ROMEO_JULIET) {
				// romeo and juliet
				log("romeo and juliet added");
				tasks.add(new StartQuestRomeo().init(this));
				tasks.add(new SpeakToJuliet().init(this));
				tasks.add(new GiveMessageToRomeo().init(this));
				tasks.add(new SpeakToFather().init(this));
				tasks.add(new SpeakToApothecary().init(this));
				tasks.add(new SpeakToJulietScene().init(this));
			} else if (Resources.currentStage == Stage.WOODCUTTING) {
				// woodcutting
				log("woodcutting added");
				tasks.add(new BankWC().init(this));
				tasks.add(new CutTree().init(this));
				tasks.add(new WalkToBankFromWC().init(this));
				tasks.add(new WalkToWCPlace().init(this));
			} else if (Resources.currentStage == Stage.MINING) {
				// mining
				log("Mining added");
				tasks.add(new DepositMining().init(this));
				tasks.add(new MineOre().init(this));
				tasks.add(new WalkToBankFromMining().init(this));
				tasks.add(new WalkToMiningPlace().init(this));
				tasks.add(new WithdrawPickaxe().init(this));
				tasks.add(new WalkToDeposit().init(this));
			} else if (Resources.currentStage.getType() == StageType.COMBAT) {
				// mining
				log("Combat added");
				tasks.add(new BankFight().init(this));
				tasks.add(new Fight().init(this));
				tasks.add(new WalkToBankFromFight().init(this));
				tasks.add(new WalkToFight().init(this));
			}
		}
		// tasks.add(new Devmode().init(this));
	}

	private String ft(long duration) {

		String res = "";
		long days = TimeUnit.MILLISECONDS.toDays(duration);
		long hours = TimeUnit.MILLISECONDS.toHours(duration)
				- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration)
				- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration)
				- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		if (days == 0) {
			res = (hours + ":" + minutes + ":" + seconds);
		} else {
			res = (days + ":" + hours + ":" + minutes + ":" + seconds);
		}
		return res;
	}

}
