package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;

import net.sumo.nextgen.gear.GearSetups;
import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;



public enum FightingAssignment {
	
	SEAGULL("Seagull", Areas.SEAGULL_AREA, Areas.GRAND_EXCHANGE_AREA, Areas.GRAND_EXCHANGE_AREA, GearSetups.STARTER_MELEE_SETUP),
	SEAGULL_RANGE("Seagull", Areas.SEAGULL_AREA, Areas.GRAND_EXCHANGE_AREA, Areas.GRAND_EXCHANGE_AREA, GearSetups.STARTER_RANGE_SETUP),
	SEAGULL_MAGE("Seagull", Areas.SEAGULL_AREA, Areas.GRAND_EXCHANGE_AREA, Areas.GRAND_EXCHANGE_AREA, GearSetups.STARTER_MAGE_SETUP, Resources.COW_LOOT, Resources.WIND_STRIKE_RUNES),
	AL_KHARID_WARRIOR("Al-Kharid warrior", Areas.AL_KHARID_WARRIOR_AREA, Areas.GRAND_EXCHANGE_AREA, Areas.GRAND_EXCHANGE_AREA, GearSetups.MITHRIL_MELEE_SETUP, true),
	AL_KHARID_WARRIOR_RANGE("Al-Kharid warrior", Areas.AL_KHARID_WARRIOR_AREA, Areas.GRAND_EXCHANGE_AREA, Areas.GRAND_EXCHANGE_AREA, GearSetups.THIRTHY_RANGE_SETUP, true);
	
	private String _name;
	private Area _fightArea;
	private Area _bankArea;
	private Area _walkableBankArea;
	private GearSetups _gear;
	private String[] _loot = null;
	private String[] _inventoryItems;
	private boolean _shouldEat;
	private FightingAssignment(String name, Area fightArea, Area bankArea, Area walkableBankArea){
		_name = name;
		_fightArea = fightArea;
		_bankArea = bankArea;
		_walkableBankArea = walkableBankArea;
	}
	private FightingAssignment(String name, Area fightArea, Area bankArea, Area walkableBankArea, GearSetups gear){
		_name = name;
		_fightArea = fightArea;
		_bankArea = bankArea;
		_walkableBankArea = walkableBankArea;
		_gear = gear;
	}
	private FightingAssignment(String name, Area fightArea, Area bankArea, Area walkableBankArea, GearSetups gear, boolean eat){
		_name = name;
		_fightArea = fightArea;
		_bankArea = bankArea;
		_walkableBankArea = walkableBankArea;
		_gear = gear;
		_shouldEat = eat;
	}
	private FightingAssignment(String name, Area fightArea, Area bankArea, Area walkableBankArea, GearSetups gear,String[] loot){
		_name = name;
		_fightArea = fightArea;
		_bankArea = bankArea;
		_walkableBankArea = walkableBankArea;
		_gear = gear;
		_loot = loot;
	}
	private FightingAssignment(String name, Area fightArea, Area bankArea, Area walkableBankArea, GearSetups gear, String[] loot, String[] inventoryItems){
		_name = name;
		_fightArea = fightArea;
		_bankArea = bankArea;
		_walkableBankArea = walkableBankArea;
		_gear = gear;
		_loot = loot;
		_inventoryItems = inventoryItems;
	}
	
	
	
	public String getName(){
		return _name;
	}
	
	public Area getFightArea(){
		return _fightArea;
	}
	
	public boolean getEat(){
		return _shouldEat;
	}
	public Area getBankArea(){
		return _bankArea;
	}
	
	public Area getWalkableBankArea(){
		return _walkableBankArea;
	}
	
	public GearSetups getGear(){
		return _gear;
	}

	public Position getBankPos() {
		return _walkableBankArea.getRandomPosition();
	}

	public String[] getLoot() {
		return _loot;
	}

	public String[] getInventory() {
		return _inventoryItems;
	}
	

}
