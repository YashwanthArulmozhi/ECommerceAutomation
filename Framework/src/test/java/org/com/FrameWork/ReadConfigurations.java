package org.com.FrameWork;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

public class ReadConfigurations {

	
	Properties prop = null;
	File file = null;
	FileInputStream fis = null;
	
	public void loadPropertiesFile()
	{
		try
		{
			file = new File(System.getProperty("user.dir")+"\\src\\test\\java\\Configuration.properties");
			if(file!=null)
			{
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
			}
		}
		catch (Exception e) {
			Assert.fail("File not Found or loaded.");
		}
	}
	
	public String getPropertyValue(String key) throws IOException
	{
		String propertyValue = null;
		try
		{
			loadPropertiesFile();
			propertyValue = prop.getProperty(key);
			return propertyValue;
		}
		catch (Exception e) {
			Assert.fail("Property Key is not available.");
		}
		finally {
			fis.close();
		}
		return propertyValue;
	}
}
