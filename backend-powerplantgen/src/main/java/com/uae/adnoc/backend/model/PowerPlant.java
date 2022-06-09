package com.uae.adnoc.backend.model;

public class PowerPlant {

	int data_Year;
	String state_Abbrevation;
	String name;
	int code;
	String owner_Name;
	String owner_Id;
	String utility_Name;
	int utility_Id;
	String county_Name;
	double latitude;
	double longitued;
	int total_Units;
	int total_Generators;
	String primary_Fuel;
	String primary_Fuel_Category;
	double capacity_Factor;
	double nameplate_Capacity;
	double annual_Net_Generation;
	
	PowerGenFigures powerGenFigures;
	
	public PowerPlant() {
		
	}
	
	public int getData_Year() {
		return data_Year;
	}
	public void setData_Year(int data_Year) {
		this.data_Year = data_Year;
	}
	public String getState_Abbrevation() {
		return state_Abbrevation;
	}
	public void setState_Abbrevation(String state_Abbrevation) {
		this.state_Abbrevation = state_Abbrevation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getOwner_Name() {
		return owner_Name;
	}
	public void setOwner_Name(String owner_Name) {
		this.owner_Name = owner_Name;
	}
	public String getOwner_Id() {
		return owner_Id;
	}
	public void setOwner_Id(String owner_Id) {
		this.owner_Id = owner_Id;
	}
	public String getUtility_Name() {
		return utility_Name;
	}
	public void setUtility_Name(String utility_Name) {
		this.utility_Name = utility_Name;
	}
	public int getUtility_Id() {
		return utility_Id;
	}
	public void setUtility_Id(int utility_Id) {
		this.utility_Id = utility_Id;
	}
	public String getCounty_Name() {
		return county_Name;
	}
	public void setCounty_Name(String county_Name) {
		this.county_Name = county_Name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitued() {
		return longitued;
	}
	public void setLongitued(double longitued) {
		this.longitued = longitued;
	}
	public int getTotal_Units() {
		return total_Units;
	}
	public void setTotal_Units(int total_Units) {
		this.total_Units = total_Units;
	}
	public int getTotal_Generators() {
		return total_Generators;
	}
	public void setTotal_Generators(int total_Generators) {
		this.total_Generators = total_Generators;
	}
	public String getPrimary_Fuel() {
		return primary_Fuel;
	}
	public void setPrimary_Fuel(String primary_Fuel) {
		this.primary_Fuel = primary_Fuel;
	}
	public String getPrimary_Fuel_Category() {
		return primary_Fuel_Category;
	}
	public void setPrimary_Fuel_Category(String primary_Fuel_Category) {
		this.primary_Fuel_Category = primary_Fuel_Category;
	}
	public double getCapacity_Factor() {
		return capacity_Factor;
	}
	public void setCapacity_Factor(double capacity_Factor) {
		this.capacity_Factor = capacity_Factor;
	}
	public double getNameplate_Capacity() {
		return nameplate_Capacity;
	}
	public void setNameplate_Capacity(double nameplate_Capacity) {
		this.nameplate_Capacity = nameplate_Capacity;
	}
	public double getAnnual_Net_Generation() {
		return annual_Net_Generation;
	}
	public void setAnnual_Net_Generation(double annual_Net_Generation) {
		this.annual_Net_Generation = annual_Net_Generation;
	}

	public PowerGenFigures getPowerGenFigures() {
		return powerGenFigures;
	}

	public void setPowerGenFigures(PowerGenFigures powerGenFigures) {
		this.powerGenFigures = powerGenFigures;
	}
	
	
}
