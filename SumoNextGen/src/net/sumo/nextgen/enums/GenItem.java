package net.sumo.nextgen.enums;

import java.util.Arrays;

import net.sumo.nextgen.resources.Resources;

public enum GenItem {
	
	ironKiteShield("Iron kiteshield", 1191),ironPlateLegs("Iron platelegs", 1067), amuletOfStrength("Amulet of strength", 1725),
	ironScimmy("Iron scimitar", 1323), ironFullHelm("Iron full helm", 1153), leatherBody("Iron kiteshield", 1129), bronzeLongSword("Bronze longsword", 1291);

	String itemName;
	int id;
	
	GenItem(String _itemName, int _id){
		itemName = _itemName;
		id = _id;
	}
	
	public int getItemID(){
		return id;
	}
	
	public String getItemName(){
		return itemName;
	}
	
	
	
	
	@Override
	public String toString(){
		return itemName;
	}
}
