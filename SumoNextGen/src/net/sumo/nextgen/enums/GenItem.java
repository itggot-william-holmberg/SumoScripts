package net.sumo.nextgen.enums;

import java.util.Arrays;

import net.sumo.nextgen.resources.Resources;

public enum GenItem {
	
	wizardHat("Wizard hat", 579),mindRune("Mind rune", 558), fireRune("Fire rune", 554),bronzeAxe("Bronze axe",1351),bronzePick("Bronze pickaxe",1265),coins("Coins", 995),ironKiteShield("Iron kiteshield", 1191),ironPlateLegs("Iron platelegs", 1067), 
	amuletOfStrength("Amulet of strength", 1725),ironScimmy("Iron scimitar", 1323), 
	ironFullHelm("Iron full helm", 1153), leatherBody("Leather body", 1129),
	bronzeLongSword("Bronze longsword", 1291), mithrilPick("Mithril pickaxe", 1273), addyPick("Adamant pickaxe", 1271),
	runePick("Rune pickaxe", 1275), mithrilAxe("Mithril axe", 1355),adamantAxe("Adamant axe", 1357),
	runeAxe("Rune axe", 1359), feather("Feather", 314), flyFishingRod("Fly fishing rod", 309), smallFishingNet("Small fishing net", 303),
	rawShrimp("Raw shrimps", 317), rawTrout("Raw trout", 335), rawSalmon("Raw salmon", 331), mithrilPlateLegs("Mithril platelegs", 1071),
	mithrilPlateBody("Mithril platebody", 1121), mithrilFullHelm("Mithril full helm", 1159), mithrilKiteShield("Mithril kiteshield", 1197),
	mithrilScimmy("Mithril scimitar", 1329), leatherChaps("Leather chaps", 1095), leatherVambs("Leather vambraces", 1063), coif("Leather cowl", 1167),
	bronzeArrows("Bronze arrow", 882), diamondRing("Diamond ring", 1643), amuletOfPower("Amulet of power", 1731), greenCape("Green cape", 1027),
	leatherBoots("Leather boots", 1061), shortbow("Shortbow", 841), studdedChaps("Studded chaps", 1097), mapleShortbow("Maple shortbow", 853), trout("Trout",333),
	leather("Leather", 1741), needle("Needle", 1733), thread("Thread", 1734), blueWizardRobe("Blue wizard robe", 577), shadeRobe("Shade robe", 548), airStaff("Staff of air", 1381), airRune("Air rune", 556);

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
