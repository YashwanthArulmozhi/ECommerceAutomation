package org.com.ObjectRepo.Pages;

import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CheckOutInformationPage extends FrameworkUtilities implements InitCommonMethods {

	private final By button_Continue = By.name("continue");
	
	private final By input_FirstName = By.id("first-name");
	
	private final By input_LastName = By.id("last-name");
	
	private final By input_PostalCode = By.id("postal-code");
	
	
	String firstName = commonMethods.readExcelData("Ecommerce", "FirstName");
	String lastName = commonMethods.readExcelData("Ecommerce", "LastName");
	String postCode = commonMethods.readExcelData("Ecommerce", "PostalCode");
	
	/**
	 * Method to create Continue button in Checkout Information page
	 */
	public void clickContinueButton()
	{
		try
		{
		new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(input_FirstName));
		commonMethods.clickElement(button_Continue);
		}
		catch (TimeoutException e) 
		{
			Assert.fail("Object Not available to wait till the time period. Please check the object");
		}
	}
	
	/**
	 * Method to enter the Check out information like first name, last name and postal code
	 */
	public void enterInformationDetails()
	{
		try
		{
			new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(input_FirstName));
			commonMethods.sendValues(input_FirstName, firstName);
			commonMethods.sendValues(input_LastName, lastName);
			commonMethods.sendValues(input_PostalCode, postCode);
		}
		catch (TimeoutException e) 
		{
			Assert.fail("Object Not available to wait till the time period. Please check the object");
		}
	}
	
}
