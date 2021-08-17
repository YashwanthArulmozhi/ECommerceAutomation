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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import junit.framework.Assert;

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
	
	String browserName = null;
	
	static String environmentName = null;
	
	public static String applicationName = null;
	
	Properties properties;
	
	BufferedReader reader;
	
	public String getBrowserName()
	{
		try
		{
			browserName = new ReadConfigurations().getPropertyValue("Browser");
			if((browserName != null) && !browserName.trim().isEmpty())
			{
				return browserName;
			}
			else
			{
				Assert.fail("Browser value is not provided");
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
			environmentName = new ReadConfigurations().getPropertyValue("Environment");
			if(environmentName != null  && !environmentName.trim().isEmpty())
			{
				return environmentName;
			}
			else
			{
				Assert.fail("Environment is not provided");
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
			applicationName = new ReadConfigurations().getPropertyValue("ApplicationName").trim();
			if(applicationName != null  && !applicationName.trim().isEmpty())
			{
				return applicationName;
			}
			else
			{
				Assert.fail("Application name is not provided");
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
			String applicationName = getApplicationName();
			htmlReporter = new ExtentHtmlReporter(path);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			htmlReporter.config().setDocumentTitle(getApplicationName());
			htmlReporter.config().setReportName(applicationName);
			htmlReporter.config().setTheme(Theme.DARK);
			//
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void setDescription(String Description)
	{
		logger=extent.createTest(Description);
	}
	
	public String getMethodName() {
		return MethodName;
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws HeadlessException, IOException, AWTException
	{
		String screenshotPAth = System.getProperty("user.dir")+"\\Output\\Screenshots\\Image"+new SimpleDateFormat("MM_dd_YYYY_HH_mm_ss").format(new Date())+".png";
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.addScreenCaptureFromPath(commonMethods.getScreenshotSelenium(screenshotPAth));
			logger.fail(result.getThrowable().getMessage());
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
