package com.pk.rpklawyers.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceFile {

	public ResourceFile(){
		
	}
	
	public Properties getResourceProperties(String fileName){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = getClass().getClassLoader().getResourceAsStream(fileName);

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	return prop;
	}

}
