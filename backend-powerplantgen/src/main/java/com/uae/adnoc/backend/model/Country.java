package com.uae.adnoc.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Country {

	int data_Year;
	String name;
	int nameplate_Capacity;
	int net_Generation;
	
	PowerGenFigures powerGenFigures;
	
	List<State> lstState = new ArrayList<State>();

	public Country() {
		
	}

	public int getData_Year() {
		return data_Year;
	}

	public void setData_Year(int data_Year) {
		this.data_Year = data_Year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNameplate_Capacity() {
		return nameplate_Capacity;
	}

	public void setNameplate_Capacity(int nameplate_Capacity) {
		this.nameplate_Capacity = nameplate_Capacity;
	}

	public int getNet_Generation() {
		return net_Generation;
	}

	public void setNet_Generation(int net_Generation) {
		this.net_Generation = net_Generation;
	}

	public List<State> getLstState() {
		return lstState;
	}

	public void setLstState(List<State> lstState) {
		this.lstState = lstState;
	}

	public PowerGenFigures getPowerGenFigures() {
		return powerGenFigures;
	}

	public void setPowerGenFigures(PowerGenFigures powerGenFigures) {
		this.powerGenFigures = powerGenFigures;
	}
}
