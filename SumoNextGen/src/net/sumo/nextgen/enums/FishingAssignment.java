package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;

import net.sumo.nextgen.resources.Areas;
import net.sumo.nextgen.resources.Resources;



public enum FishingAssignment {

	SCHRIMPS_DRAYNOR(Resources.schrimpSpots, "Schrimps", "small fishing net", "net", Areas.SCHRIMP_AREA_DRAYNOR, Banks.DRAYNOR, Areas.BANK_AREA_WALKABLE_DRAYNOR, true);
	
	
	private Area _fishingArea;
	private Area _bankArea;
	private String _fishName;
	private int[] _fishingSpots;
	private Area _bankPos;
	private String _fishGear;
	private Area _depositPos;
	private boolean _depositBox;
	private Area _depositArea;
	private String _action;
	private FishingAssignment(int[] fishingSpots, String fishName,String fishGear, String action, Area fishingArea, Area bankArea, Area bankPos, boolean depositBox){
		_fishingSpots = fishingSpots;
		_fishingArea = fishingArea;
		_fishName = fishName;
		_fishGear = fishGear;
		_bankArea = bankArea;
		_bankPos = bankPos;
		_depositBox = depositBox;
		_action = action;
	}
	
	public Area getFishingArea(){
		return _fishingArea;
	}
	
	public String getFishName(){
		return _fishName;
	}
	
	public String getFishGear(){
		return _fishGear;
	}
	
	public Area getBankArea(){
		return _bankArea;
	}
	public Area getDepositArea(){
		return _depositArea;
	}
	
	public int[] getFishingSpots(){
		return _fishingSpots;
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

	public String getAction() {
		return _action;
	}
}
