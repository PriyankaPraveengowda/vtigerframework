package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to perform actions on properties file
 * @author 91897
 *
 */
public class PropertiesUtility {
	
	private Properties property;
	
	/**
	 * This method is used to initialize properties file
	 * @param filePath
	 */
	public void propertiesInit(String filePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		property = new Properties();
		try {
			property.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * This method is used to fetch data from the properties file using key
	 * @return 
	 */
	public String fetchDataFromProperties(String key) {
		return property.getProperty(key);
	}
	
	/** 
	 * This method is used to write data into properties file
	 * @param key
	 * @param value
	 * @param filepath
	 * @param comments
	 */
	public void writeDataToProperties(String key, String value, String filepath, String comments) {
		property.put(key, value);
		FileOutputStream fos = null;
		
		 try {
			 fos = new FileOutputStream(filepath);
		 }catch(FileNotFoundException e) {
			 e.printStackTrace();
		 }
		 
		 try {
			 property.store(fos, comments);
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
	}
}
