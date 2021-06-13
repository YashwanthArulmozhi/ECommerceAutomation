package org.com.ObjectRepo.Pages;

import org.com.FrameWork.BrowserAndDriverClass;
import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;


public class LoginPage extends FrameworkUtilities implements InitCommonMethods {
	
	
	private final By input_Username = By.xpath("//input[@id='user-name']");
	
	private final By input_Password = By.xpath("//input[@id='password']");
	
	private final By button_Login = By.xpath("//input[@id='login-button']");
	
	private final By label_ErrorMsg = By.xpath("//h3[@data-test='error']");
	
	
	
	/**
	 * Method to Login an application with URL and Login Credentials
	 * @param url
	 * @param userName
	 * @param password
	 */
	public void launchAndVerifyBlog(String url,String userName,String password)
	{
		try
		{
			commonMethods.launchApplication(url);
			logger.log(Status.INFO, "Launched "+url+" application");
			if(commonMethods.waitForElement(input_Username,25)!=null)
			{
				commonMethods.sendValues(input_Username, userName);
				commonMethods.sendValues(input_Password, password);
				commonMethods.clickElement(button_Login);
			}
			else
			{
				Assert.fail("Login Failed");
			}
		}
		catch (Exception e) 
		{
			Assert.fail(e.getMessage());
		}
	}
	
	/**
	 * Method to Validate error message for the application
	 * 
	 */
	public void ValidateErrorMsg()
	{
		try
		{
			if(commonMethods.getSize(label_ErrorMsg)>0)
			{
				logger.log(Status.PASS, "Succesfully verified Error Message. Error Message -> "+commonMethods.getText(label_ErrorMsg));
			}
			else
			{
				Assert.fail("Error Message not displayed");
			}
		}
		catch (Exception e) 
		{
			Assert.fail(e.getMessage());
		}
	}
	

}
