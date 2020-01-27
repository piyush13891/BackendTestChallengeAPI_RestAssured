package com.data;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;

/**
 * This class will read the properties.yaml file
 */
public class PropertyFileReader {
	
	private static final Logger LOGGER = Logger.getLogger(PropertyFileReader.class);
	
	public static PropertyFilePojo getPropertyData() {
		Yaml yaml = new Yaml();
		PropertyFilePojo property = null;
		String filePath=".\\src\\test\\resources\\properties.yaml";
		
		try {			
			File file = new File(filePath);
			property = yaml.loadAs(new FileReader(file), PropertyFilePojo.class);
		} catch (FileNotFoundException e) {
			LOGGER.info("Please validate property file path. property file not found - " +  filePath);
			Assert.fail("Please validate property file path. property file not found - " +  filePath);
		}

		return property;
	}

}
