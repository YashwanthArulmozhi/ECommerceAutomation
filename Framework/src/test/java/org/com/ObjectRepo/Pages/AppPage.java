package org.com.ObjectRepo.Pages;

import org.com.FrameWork.BrowserAndDriverClass;
import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;


public class AppPage extends FrameworkUtilities implements InitCommonMethods {
	
	
	private final By link_Blog = By.xpath("//a[text()='Blog']");
	
	private final By label_BlogText = By.xpath("//h1[contains(text(),'Blog')]");
	
	private final By link_News = By.xpath("//a[text()='News']");
	
	private final By label_News = By.xpath("//h1[contains(text(),'News')]");
	
	
	String url = commonMethods.readExcelData("TestExcel", "URL");
	
	
	public void launchAndVerifyBlog()
	{
		try
		{
			commonMethods.launchApplication(url);
			
			logger.log(Status.INFO, "Launched Mindtree application");
			if(new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(link_Blog))!=null)
			{
				commonMethods.clickElement(link_Blog);
				if(new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_BlogText))!=null)
				{
					logger.log(Status.PASS, "Succesfully verified Blog label text");
				}
			}
		}
		catch (Exception e) 
		{
			logger.log(Status.FAIL,e.getMessage());
		}
	}
	
	public void launchAndVerifyNews()
	{
		try
		{
			commonMethods.launchApplication("https://www.mindtree.com");
			logger.log(Status.INFO, "Launched Mindtree application");
			if(new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(link_News))!=null)
			{
				commonMethods.clickElement(link_News);
				if(new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_News))!=null)
				{
					logger.log(Status.PASS, "Succesfully verified News label text");
				}
			}
		}
		catch (Exception e) 
		{
			logger.log(Status.FAIL,e.getMessage());
		}
	}

}
