package net.sumo.nextgen.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;

import net.sumo.nextgen.resources.Areas;




public enum WCAssignment {
	
	NORMAL_TREE_LUMBRIDGE("Tree", "Logs", Areas.NORMAL_TREE_LUMBRIDGE, Banks.LUMBRIDGE_UPPER), 
	OAK_TREE_LUMBRIDGE("Oak", "Oak logs", Areas.OAK_TREE_LUMBRIDGE_AREA, Banks.LUMBRIDGE_UPPER);

	private int _minLevel, _maxLevel;
	private Area _area;
	private Area _bankArea;
	private String _logName;
	private String _treeName;
	private Area _bankPos;
	private WCAssignment(String treeName, String logName, Area area, Area bankArea){
		_treeName = treeName;
		_area = area;
		_logName = logName;
		_bankArea = bankArea;
	}
	
	public Area getTreeArea(){
		return _area;
	}
	
	public String getLogName(){
		return _logName;
	}
	
	public Area getBankArea(){
		return _bankArea;
	}
	
	public String getName(){
		return _treeName;
	}
	

}
