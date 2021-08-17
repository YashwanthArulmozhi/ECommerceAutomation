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

import org.com.TestPackage.TestMainClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.diagnostics.logging.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

public class BrowserAndDriverClass extends FrameworkUtilities {
	
	
	public WebDriver driver;
	


	public WebDriver initDriver(String browserName)
	{
		try
		{
			switch (browserName.toLowerCase()) {
			
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				return driver;
			
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				return driver;
				
			default:
				
				if(browserName==null || browserName.contains("") || !(browserName.length()>1))
				{
					logger.fail("Please provide Valid Browser");
				}
				break;
			}
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		return driver;
	}

}
