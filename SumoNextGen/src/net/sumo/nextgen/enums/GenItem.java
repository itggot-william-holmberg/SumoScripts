package net.sumo.nextgen.enums;

import java.util.Arrays;

import net.sumo.nextgen.resources.Resources;

public enum GenItem {
	
	coins("Coins", 995),ironKiteShield("Iron kiteshield", 1191),ironPlateLegs("Iron platelegs", 1067), 
	amuletOfStrength("Amulet of strength", 1725),ironScimmy("Iron scimitar", 1323), 
	ironFullHelm("Iron full helm", 1153), leatherBody("Leather body", 1129),
	bronzeLongSword("Bronze longsword", 1291), mithrilPick("Mithril pickaxe", 1273), addyPick("Adamant pickaxe", 1271),
	runePick("Rune pickaxe", 1275), mithrilAxe("Mithril axe", 1355),adamantAxe("Adamant axe", 1357),
	runeAxe("Rune axe", 1359);

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
