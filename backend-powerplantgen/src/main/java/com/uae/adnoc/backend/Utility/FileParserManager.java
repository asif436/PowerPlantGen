package com.uae.adnoc.backend.Utility;

import java.io.IOException;

import com.uae.adnoc.backend.model.Country;

public class FileParserManager {
	
	private BaseFileParser baseFileParser;
	
	
	public FileParserManager(BaseFileParser baseFileParser){
	    
		this.baseFileParser=baseFileParser;
	  
	}  
	
	public Country parseFile(String inputFileName) throws IOException{

		baseFileParser.parseFile(inputFileName);
		return baseFileParser.getCountry();
	
	} 

}
