package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;


public enum MiningAssigment {
	TIN_ORE_RIMMINGTON(Resources.tinOreID, "Tin ore", Areas.TIN_AREA_RIMMINGTON, Areas.DEPOSIT_BOX_PORT_SARIM, Areas.BANK_AREA_DRAYNOR, Areas.DEPOSIT_BOX_PORT_SARIM_WALKABLE, Areas.BANK_AREA_WALKABLE_DRAYNOR, true),
	COPPER_ORE_RIMMINGTON(Resources.copperOreID, "Copper ore", Areas.COPPER_AREA_RIMMINGTON, Areas.DEPOSIT_BOX_PORT_SARIM, Areas.BANK_AREA_DRAYNOR, Areas.DEPOSIT_BOX_PORT_SARIM_WALKABLE, Areas.BANK_AREA_WALKABLE_DRAYNOR, true),
	IRON_ORE_RIMMINGTON(Resources.ironOreID, "Iron ore", Areas.IRON_AREA_RIMMINGTON, Areas.DEPOSIT_BOX_PORT_SARIM, Areas.BANK_AREA_DRAYNOR, Areas.DEPOSIT_BOX_PORT_SARIM_WALKABLE, Areas.BANK_AREA_WALKABLE_DRAYNOR, true),
	GOLD_ORE_RIMMINGTON(Resources.goldOreID, "Gold ore", Areas.GOLD_AREA_RIMMINGTON, Areas.DEPOSIT_BOX_PORT_SARIM, Areas.BANK_AREA_DRAYNOR, Areas.DEPOSIT_BOX_PORT_SARIM_WALKABLE, Areas.BANK_AREA_WALKABLE_DRAYNOR, true);
	
	private Area _area;
	private Area _bankArea;
	private String _oreName;
	private int[] _rockID;
	private Area _bankPos;
	private Area _depositPos;
	private boolean _depositBox;
	private Area _depositArea;
	private MiningAssigment(int[] rockID, String oreName, Area area, Area depositArea, Area bankArea, Area depositPos, Area bankPos, boolean depositBox){
		_rockID = rockID;
		_area = area;
		_oreName = oreName;
		_bankArea = bankArea;
		_depositArea = depositArea;
		_bankPos = bankPos;
		_depositPos = depositPos;
		_depositBox = depositBox;
	}
	
	public Area getMiningArea(){
		return _area;
	}
	
	public String getOreName(){
		return _oreName;
	}
	
	public Area getBankArea(){
		return _bankArea;
	}
	public Area getDepositArea(){
		return _depositArea;
	}
	
	public int[] getRockID(){
		return _rockID;
	}
	
	public Position getBankPos(){
		return _bankPos.getRandomPosition();
	}
	public Position getDepositPos(){
		return _depositPos.getRandomPosition();
	}
	
	public boolean getDepositBox(){
		return _depositBox;
	}
}
