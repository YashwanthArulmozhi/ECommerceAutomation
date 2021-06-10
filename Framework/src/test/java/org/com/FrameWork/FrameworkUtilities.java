package org.com.FrameWork;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class FrameworkUtilities implements InitCommonMethods 
{

public static ExtentHtmlReporter htmlReporter;
	
	public static String reportName;
	
	public String MethodName;
	public String SuiteName;

	public String TestName;
	public static ExtentReports extent;
	
	public static ExtentTest logger;
	
	public static String path;
	
	String browserName;
	
	static String environmentName;
	
	public static String applicationName;
	
	Properties properties;
	
	BufferedReader reader;
	
	public String readProperty(String keyValue)
	{	
		 try {
		 reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\Configuration.properties"));
		 properties = new Properties();
		 properties.load(reader);
		 return properties.getProperty(keyValue).trim();
		 }
		 catch (Exception e) {
			 System.out.println(e.getMessage());
		}
		 return null;
	}
	
	public String getBrowserName()
	{
		try
		{
			browserName = readProperty("Browser");
			if((browserName != null) && browserName.length()>1)
			{
				return browserName;
			}
			else
			{
				browserName =null;
			}
		}
		 catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return browserName;
	}
	
	public String getEnvironmentName()
	{
		try
		{
			environmentName = readProperty("Environment");
			if(environmentName != null)
			{
				return environmentName;
			}
		}
		 catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return environmentName;
	}
	
	public String getApplicationName()
	{
		try
		{
			applicationName = readProperty("ApplicationName").trim();
			if(applicationName != null)
			{
				return applicationName;
			}
		}
		 catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return applicationName;
	}
	
	
	@BeforeSuite
	public void before(ITestContext context)
	{
		try
		{
			this.TestName = context.getName();
			path = System.getProperty("user.dir")+"\\Output\\Report"+reportName+".html";
			if(!new File(path).exists())
			{
				reportName =  new SimpleDateFormat("MM_dd_YYYY_HH_mm_ss").format(new Date());
				path = System.getProperty("user.dir")+"\\Output\\Report"+reportName+".html";
			}
			String app = getApplicationName();
			htmlReporter = new ExtentHtmlReporter(path);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setDocumentTitle(getApplicationName());
			htmlReporter.config().setReportName(app);
			htmlReporter.config().setTheme(Theme.DARK);
			//
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@BeforeMethod
	public void beforeTest()
	{
		try
		{
			System.out.println("");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setDesciption(String Description)
	{
		logger=extent.createTest(Description);
	}
	
	public String getMethodName() {
		return MethodName;
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws HeadlessException, IOException, AWTException
	{
		String screenshotPAth = System.getProperty("user.dir")+"\\Output\\Screenshots\\Image"+new SimpleDateFormat("MM_dd_YYYY_HH_mm_ss").format(new Date());
		if(result.getStatus() == ITestResult.FAILURE)
		{
			//logger.addScreenCaptureFromPath(commonMethods.getScreenshot(screenshotPAth));
			logger.fail("Test Case Failed");
		}
	}
	
	
	@AfterSuite
	public void afterSuite()
	{
		try
		{
			extent.flush();
			Desktop.getDesktop().open(new File(path));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
