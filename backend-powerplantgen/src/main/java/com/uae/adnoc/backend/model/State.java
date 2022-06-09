package com.uae.adnoc.backend.model;

import java.util.ArrayList;
import java.util.List;

public class State {

	int data_Year;
	String abbreviation;
	int code;
	int net_Generation;
	int ozone_Season_Net_Generation;
	
	PowerGenFigures powerGenFigures;
	
	List<PowerPlant> power_plant = new ArrayList<PowerPlant>();;

	public State() {
		
	}

	public int getData_Year() {
		return data_Year;
	}

	public void setData_Year(int data_Year) {
		this.data_Year = data_Year;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getNet_Generation() {
		return net_Generation;
	}

	public void setNet_Generation(int net_Generation) {
		this.net_Generation = net_Generation;
	}

	public int getOzone_Season_Net_Generation() {
		return ozone_Season_Net_Generation;
	}

	public void setOzone_Season_Net_Generation(int ozone_Season_Net_Generation) {
		this.ozone_Season_Net_Generation = ozone_Season_Net_Generation;
	}

	public PowerGenFigures getPowerGenFigures() {
		return powerGenFigures;
	}

	public void setPowerGenFigures(PowerGenFigures powerGenFigures) {
		this.powerGenFigures = powerGenFigures;
	}

	public List<PowerPlant> getPower_plant() {
		return power_plant;
	}

	public void setPower_plant(List<PowerPlant> power_plant) {
		this.power_plant = power_plant;
	}
	
	
	
}
