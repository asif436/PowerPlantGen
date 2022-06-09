package com.uae.adnoc.backend.Utility;

import java.io.IOException;

import com.uae.adnoc.backend.model.Country;

public abstract class BaseFileParser {
	
	protected Country country = new Country();
	
	public abstract void parseFile(String inputFileName) throws IOException;

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country inCountry) {
		this.country = inCountry;
	}


}
