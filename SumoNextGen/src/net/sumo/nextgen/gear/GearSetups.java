package net.sumo.nextgen.gear;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.api.ui.EquipmentSlot;

public enum GearSetups {

	
	STARTER_MELEE_SETUP(Gears.leatherBody, Gears.ironPlateLegs, Gears.ironFullHelm, Gears.ironKiteShield,Gears.bronzeLongSword, Gears.amuletOfStrength);
	
	List<String> _fullGear =  new ArrayList<String>();
	private String _chest;
	private String _legs;
	private String _hat;
	private String _gloves;
	private String _shield;
	private String _weapon;
	private String _cape;
	private String _feet;
	private String _amulet;
	private String _ring;
	private String _arrows;

	private GearSetups(String chest, String legs, String hat, String shield, String weapon, String amulet) {
		_chest = chest;
		_legs = legs;
		_hat = hat;
		_shield = shield;
		_weapon = weapon;
		_amulet = amulet;
		_fullGear.add(_chest);
		_fullGear.add(_legs);
		_fullGear.add(_hat);
		_fullGear.add(_shield);
		_fullGear.add(_weapon);
		_fullGear.add(_amulet);
		
	}
	
	private GearSetups(String chest, String legs, String hat, String gloves, String shield, String weapon, String cape,
			String feet, String amulet, String ring, String arrows) {
		_chest = chest;
		_legs = legs;
		_hat = hat;
		_gloves = gloves;
		_shield = shield;
		_weapon = weapon;
		_cape = cape;
		_feet = feet;
		_amulet = amulet;
		_ring = ring;
		_arrows = arrows;
		_fullGear.add(_chest);
		_fullGear.add(_legs);
		_fullGear.add(_hat);
		_fullGear.add(_gloves);
		_fullGear.add(_shield);
		_fullGear.add(_weapon);
		_fullGear.add(_cape);
		_fullGear.add(_feet);
		_fullGear.add(_amulet);
		_fullGear.add(_ring);
		_fullGear.add(_arrows);
		
	}

	public List<String> getFullGear() {
		return _fullGear;
	}
		
	public String getChest(){
		return _chest;
	}
	public String getLegs(){
		return _legs;
	}
	public String getHat(){
		return _hat;
	}
	public String getGloves(){
		return _gloves;
	}
	public String getShield(){
		return _shield;
	}
	public String getWeapon(){
		return _weapon;
	}
	public String getCape(){
		return _cape;
	}
	public String getFeet(){
		return _feet;
	}
	public String getAmulet(){
		return _amulet;
	}
	public String getRing(){
		return _ring;
	}
}