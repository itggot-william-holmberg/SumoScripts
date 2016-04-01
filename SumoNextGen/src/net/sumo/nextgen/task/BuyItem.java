package net.sumo.nextgen.task;

import java.util.List;

import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;

public class BuyItem extends Task {
	int totalPrice;
	boolean soldItems = false;
	@Override
	public boolean active() {
		if(!Resources.BUY_LIST.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		Resources.CURRENT_STATE = "we have to buy items!!!!!";
		if(playerInArea(Areas.GRAND_EXCHANGE_AREA)){
			if(!Resources.soldItems){
				if(!Resources.withdrawItems){
					withdrawSellables();
				}else{
					sellSellables();
				}
			}
		}else{
			Resources.CURRENT_STATE = "Lets walk to GE";
			webWalk(Areas.GRAND_EXCHANGE_AREA);
		}
		
	}
	
	/*public void getTotalPrice(List<String> string){
		string.forEach(item->{
			ge.get
		});
	}*/

}
