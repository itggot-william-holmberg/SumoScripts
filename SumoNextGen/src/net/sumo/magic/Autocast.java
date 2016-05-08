package net.sumo.magic;

import java.awt.Point;

import org.osbot.rs07.api.ui.MagicSpell;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.input.mouse.WidgetDestination;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.ConditionalLoop;
import org.osbot.rs07.utility.ConditionalSleep;

public class Autocast {

	public static final int AUTOCAST_SPELL_CONFIG = 108;
	public static final int AUTOCAST_MODE_CONFIG = 439; // 0 = regular, 256 = defensive
	public static final Point DEFENSIVE_AUTOCAST_BUTTON_POSITION = new Point(650, 280);
	public static final Point REGULAR_AUTOCAST_BUTTON_POSITION = new Point(651, 333);
	
	private MethodProvider ctx;
	
	public Autocast(MethodProvider ctx) {
		this.ctx = ctx;
	}
	
	public boolean isAutocasting() {
		return ctx.getConfigs().get(AUTOCAST_SPELL_CONFIG) != 0;
	}
	
	public boolean isAutocasting(AutocastSpell auto) {
		return ctx.getConfigs().get(AUTOCAST_SPELL_CONFIG) == auto.getConfigValue();
	}
	
	public boolean isAutocasting(AutocastSpell auto, boolean defensive) {
		return ctx.getConfigs().get(AUTOCAST_SPELL_CONFIG) == auto.getConfigValue() && defensive == isDefensiveMode();
	}
	
	public boolean isRegularMode() {
		return ctx.getConfigs().get(AUTOCAST_MODE_CONFIG) == 0;
	}
	
	public boolean isDefensiveMode() {
		return ctx.getConfigs().get(AUTOCAST_MODE_CONFIG) == 256;
	}
	
	public MagicSpell getAutocastSpell() {
		AutocastSpell auto = AutocastSpell.forConfigValue(ctx.getConfigs().get(AUTOCAST_SPELL_CONFIG));
		if(auto == null) return null;
		return auto.getSpell();
	}
	
	public boolean isAutocastPanelOpen() {
		RS2Widget panel = ctx.getWidgets().get(201, 0);
		return panel != null && panel.isVisible();
	}
	
	@SuppressWarnings("unchecked")
	public boolean openAutocastPanel(boolean defensive) {
		new ConditionalLoop(ctx.getBot(), 10) {
			@Override
			public boolean condition() {
				return !isAutocastPanelOpen();
			};
			@Override
			public int loop() {
				if(ctx.getTabs().open(Tab.ATTACK)) {
					RS2Widget button = ctx.getWidgets().singleFilter(ctx.getWidgets().getAll(), w -> {
						return w != null && w.isVisible() && w.getMessage() != null && w.getMessage().equals("Spell") && w.getPosition().equals(defensive ? DEFENSIVE_AUTOCAST_BUTTON_POSITION : REGULAR_AUTOCAST_BUTTON_POSITION);
					});
					if(button != null) {
						WidgetDestination destination = new WidgetDestination(ctx.getBot(), button);
						if(ctx.getMouse().click(destination)) {
							new ConditionalSleep(2400, 200) {
								@Override
								public boolean condition() throws InterruptedException {
									return !button.isVisible();
								}
							}.sleep();
						}
					}
				}
				return MethodProvider.random(50, 150);
			}
		}.start();
		return isAutocastPanelOpen();
	}

	@SuppressWarnings("unchecked")
	public boolean closeAutocastPanel() {
		new ConditionalLoop(ctx.getBot(), 10) {
			@Override
			public boolean condition() {
				return isAutocastPanelOpen();
			};
			@Override
			public int loop() {
				RS2Widget cancel = ctx.getWidgets().singleFilter(ctx.getWidgets().getAll(), w -> {
					return w != null && w.isVisible() && w.getMessage() != null && w.getMessage().equals("Cancel");
				});
				if(cancel != null) {
					WidgetDestination destination = new WidgetDestination(ctx.getBot(), cancel);
					if(ctx.getMouse().click(destination)) {
						new ConditionalSleep(2400, 200) {
							@Override
							public boolean condition() throws InterruptedException {
								return !cancel.isVisible();
							}
						}.sleep();
					}
				}
				return MethodProvider.random(50, 150);
			}
		}.start();
		return !isAutocastPanelOpen();
	}
	
	public boolean autocast(AutocastSpell spell, boolean defensive) {
		new ConditionalLoop(ctx.getBot(), 10) {
			@Override
			public boolean condition() {
				return spell != null ? !isAutocasting(spell) : isAutocasting();
			};
			@SuppressWarnings("unchecked")
			@Override
			public int loop() {
				if(isAutocastPanelOpen()) {
					if(spell == null) {
						closeAutocastPanel();
					}
					else {
						if(defensive != isDefensiveMode()) {
							closeAutocastPanel();
						}
						else {
							RS2Widget button = ctx.getWidgets().singleFilter(ctx.getWidgets().getAll(), w -> {
								if(w != null && w.isVisible() && w.getInteractActions() != null) {
									String name = spell.getSpell().toString().replace("_", " ");
									for (String action : w.getInteractActions()) if(action != null && action.equalsIgnoreCase(name)) return true;
								}
								return false;
							});
							if(button != null) {
								WidgetDestination destination = new WidgetDestination(ctx.getBot(), button);
								if(ctx.getMouse().click(destination)) {
									new ConditionalSleep(2400, 200) {
										@Override
										public boolean condition() throws InterruptedException {
											return !button.isVisible();
										}
									}.sleep();
								}
							}
						}
					}
				}
				else {
					openAutocastPanel(defensive);
				}
				return MethodProvider.random(50, 150);
			}
			
		}.start();
		return spell != null ? isAutocasting(spell) : !isAutocasting();
	}
	
}