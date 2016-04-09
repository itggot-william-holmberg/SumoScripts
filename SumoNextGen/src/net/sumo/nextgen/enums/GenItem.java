package net.sumo.nextgen.enums;

import java.util.Arrays;

import net.sumo.nextgen.resources.Resources;

public enum GenItem {
	
	coins("Coins", 995),ironKiteShield("Iron kiteshield", 1191),ironPlateLegs("Iron platelegs", 1067), amuletOfStrength("Amulet of strength", 1725),
	ironScimmy("Iron scimitar", 1323), ironFullHelm("Iron full helm", 1153), leatherBody("Iron kiteshield", 1129), bronzeLongSword("Bronze longsword", 1291);

	String itemName;
	int id;
	int price;
	
	GenItem(String _itemName, int _id){
		itemName = _itemName;
		id = _id;
	}
	
	GenItem(String _itemName, int _id, int _price){
		itemName = _itemName;
		id = _id;
		price = _price;
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
