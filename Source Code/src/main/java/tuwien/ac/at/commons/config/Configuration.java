package tuwien.ac.at.commons.config;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Vanja Bisanovic
 *
 */
public class Configuration {

	private static Configuration INSTANCE;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);


	private static Properties properties;
	
	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		Configuration.properties = properties;
	}

	private Configuration() {
		properties = new Properties();
		try {
			properties.load(Configuration.class.getClassLoader().getResourceAsStream("aic15.properties"));
		} catch (IOException e) {
			LOGGER.error("Unable to load configuration file", e);
		}
	}

	public static Configuration getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new Configuration();
		}
		return INSTANCE;

	}
	
	public String getValue(String key) {		
		if (getProperties().containsKey(key)) {
			return getProperties().getProperty(key);
		}
		return null;
	}
}
