package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;

import net.sumo.nextgen.resources.Areas;

public enum CraftingAssignment {

	
	LEATHER_GLOVES("Leather gloves", "Leather", 14, 154, 92),
	LEATHER_BOOTS("Leather boots", "Leather", 16, 154, 95),
	LEATHER_BODY("Leather body", "Leather", 25, 154, 89),
	LEATHER_VAMBRACES("Leather vambraces", "Leather", 22, 154, 98),
	LEATHER_CHAPS("Leather chaps", "Leather", 25, 154, 101),
	LEATHER_COWL("Leather cowl", "Leather", 19, 154, 107);
	private String _finalProductName;
	private String _rawMaterial;
	private int _xpGiven;
	private int _widgetID, _childID;

	private CraftingAssignment(String finalProductName, String rawMaterial, int xpGiven, int widgetID, int childID){
		_finalProductName = finalProductName;
		_rawMaterial = rawMaterial;
		_widgetID = widgetID;
		_childID = childID;
		_xpGiven = xpGiven;
	}
	
	public String getFinalProduct(){
		return _finalProductName;
	}
	
	public int getWidgetID() {
		return _widgetID;
	}
	public int getChildID(){
		return _childID;
	}
	
	public String getRawMaterial(){
		return _rawMaterial;
	}
	
	public int getXpGiven(){
		return _xpGiven;
	}
}
