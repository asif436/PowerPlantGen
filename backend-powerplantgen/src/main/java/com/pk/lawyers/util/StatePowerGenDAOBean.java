package com.pk.lawyers.util;

import java.io.IOException;

import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uae.adnoc.backend.Utility.ExcelFileParser;
import com.uae.adnoc.backend.Utility.FileParserManager;
import com.uae.adnoc.backend.model.Country;


@SuppressWarnings("deprecation")
@Stateless
public class StatePowerGenDAOBean implements StatePowerGenDAO {

	private static final Logger logger = LoggerFactory.getLogger(StatePowerGenDAOBean.class);


	public StatePowerGenDAOBean() {
		
	}


	public Country loadExcellFileData() {

		Country country = new Country ();
		
    	// Load Excel file records into Java HashMap
    	FileParserManager fpm = new FileParserManager(new ExcelFileParser());    	
    	
    	try {
			country = fpm.parseFile("egrid2020_data.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	
		return country;
	}


}
