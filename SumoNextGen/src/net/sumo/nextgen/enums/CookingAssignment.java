package net.sumo.nextgen.enums;

import org.osbot.rs07.api.map.Area;

import net.sumo.nextgen.resources.Areas;

public enum CookingAssignment {
	SCHRIMP_EDGEVILLE("Raw shrimps", "stove", Areas.COOKING_AREA_EDGEVILLE, 30),
	SALMON_EDGEVILLE("Raw salmon", "stove", Areas.COOKING_AREA_EDGEVILLE, 70),
	TROUT_EDGEVILLE("Raw trout", "stove", Areas.COOKING_AREA_EDGEVILLE, 90),
	SCHRIMP_KHARID("Raw shrimps", "range", Areas.AL_KHARID_COOKING_AREA, 30),
	SALMON_KHARID("Raw salmon", "range", Areas.AL_KHARID_COOKING_AREA, 70),
	TROUT_KHARID("Raw trout", "range", Areas.AL_KHARID_COOKING_AREA, 90);
	private String _rawFoodName;
	private String _cookingObject;
	private Area _cookingArea;
	private int _xpGiven;

	private CookingAssignment(String rawFoodName, String cookingObject, Area cookingArea, int xpGiven){
		_rawFoodName = rawFoodName;
		_cookingObject = cookingObject;
		_cookingArea = cookingArea;
		_xpGiven = xpGiven;
	}
	
	public String getRawFoodName(){
		return _rawFoodName;
	}
	
	public String getCookingObject(){
		return _cookingObject;
	}
	
	public Area getCookingArea(){
		return _cookingArea;
	}
	
	public int getXpGiven(){
		return _xpGiven;
	}
}
