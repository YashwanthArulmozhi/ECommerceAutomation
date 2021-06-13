package org.com.ObjectRepo.Pages;

import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CheckOutComplete extends FrameworkUtilities implements InitCommonMethods {

	
	private final By label_ThankYouMsg = By.xpath("//h2[@class='complete-header']");
	private final By label_CompleteText = By.xpath("//div[@class='complete-text']");
	
	
	/**
	 * Method to get the Success message and print in report
	 */
	public void getSuccessMsg()
	{
		try
		{
			new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_ThankYouMsg));
			if(commonMethods.getText(label_ThankYouMsg).equalsIgnoreCase("THANK YOU FOR YOUR ORDER"))
			{
				logger.log(Status.PASS, "Order Placed. Message -> "+commonMethods.getText(label_ThankYouMsg));
				logger.log(Status.PASS,"Dispatch Message -> "+commonMethods.getText(label_CompleteText));
			}
			else
			{
				Assert.fail("Order Place message is not displayed");
			}
		}
		catch (TimeoutException e) 
		{
			Assert.fail("Object Not available to wait till the time period. Please check the object"+e);
		}
	}
}
