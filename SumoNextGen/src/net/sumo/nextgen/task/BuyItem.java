package net.sumo.nextgen.task;

import java.util.List;

import org.osbot.rs07.api.Bank;

import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;
import net.sumo.nextgen.stage.Stage;

public class BuyItem extends Task {
	int totalPrice;
	boolean soldItems = false;

	@Override
	public boolean active() {
		if (getStage() == Stage.BUY_ITEMS) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "we have to buy items!!!!!";
		if (playerInArea(Areas.GRAND_EXCHANGE_AREA)) {
			if (!Resources.soldItems) {
				if (!Resources.withdrawItems) {
					withdrawSellables();
					s.bank.withdrawAll(995);
				} else {
					sellSellables();
				}
			} else {     //if buylist is not empty.
				s.log("lets buy items");
				buyItems();
			}
		} else {
			Resources.CURRENT_STATE = "Lets walk to GE";
			webWalk(Areas.GRAND_EXCHANGE_AREA);
		}

	}

	/*
	 * public void getTotalPrice(List<String> string){ string.forEach(item->{
	 * ge.get }); }
	 */

}
