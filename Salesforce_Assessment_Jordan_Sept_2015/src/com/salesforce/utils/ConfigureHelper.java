package com.salesforce.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * ConfigureHelper class contains method to read local configuration files.
 * 
 * @author Bo Ma
 * @since 2015-09-05
 * 
 * @version 1.0
 * 
 */
public class ConfigureHelper {
	private static Logger logger = Logger.getLogger(ConfigureHelper.class);

	/**
	 * Load all properties from configuration file to memory
	 * 
	 * @param filePath
	 *            The file path under config/ directory
	 * @return Property The loaded property file
	 */
	public static Properties loadProperties(String filePath) {
		InputStream input = null;
		Properties prop = new Properties();

		try {
			input = new FileInputStream("config/" + filePath);
			prop.load(input);
			logger.info("config/" + filePath + " is loaded.");

		} catch (FileNotFoundException e) {
			logger.warn("config/" + filePath + " does NOT exist!\n");
		} catch (IOException e) {
			logger.error("Fail to open: config/" + filePath, e);
		} finally {
			try {
				if (input != null) {
					input.close();
					logger.info("config/" + filePath + " is closed.");
				}
			} catch (IOException e) {
				logger.error("Fail to close: config/" + filePath, e);
			}
		}

		return prop;
	}

}