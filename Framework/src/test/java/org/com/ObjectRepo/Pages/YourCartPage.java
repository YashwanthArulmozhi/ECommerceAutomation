package org.com.ObjectRepo.Pages;

import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class YourCartPage extends FrameworkUtilities implements InitCommonMethods {

	private final By label_Product1InCart = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product1ToAdd")+"']");
	
	private final By label_Product2InCart = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product2ToAdd")+"']");
	
	private final By button_ProductToRemove = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product2ToAdd")+"']//..//../div/button[text()='Remove']");
	
	private final By button_ThreeDotIcon = By.xpath("//div[@class='bm-burger-button']/button");
	
	private final By link_ResetState = By.xpath("//a[@id='reset_sidebar_link']");
	
	
	private final By button_Checkout = By.id("checkout");
	
	
	
	public void clickCheckOut()
	{
		try
		{
			new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(button_Checkout));
				commonMethods.clickElement(button_Checkout);
		}
		catch (TimeoutException e) 
		{
			Assert.fail("Object Not available to wait till the time period. Please check the object");
		}
	}
	
	public void validateCheckOutItems()
	{
		try
		{
			new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_Product1InCart));
			if(commonMethods.getSize(label_Product1InCart)>0)
			{
				logger.log(Status.PASS, "Product 1 is available in Cart :" +commonMethods.getText(label_Product1InCart));
			}
			else
			{
				Assert.fail("Product 1 is not available in Cart ");
			}
			if(commonMethods.getSize(label_Product2InCart)>0)
			{
				logger.log(Status.PASS, "Product 2 is available in Cart :" +commonMethods.getText(label_Product1InCart));
			}
			else
			{
				Assert.fail("Product 2 is not available in Cart ");
			}
		}
		catch (TimeoutException e) 
		{
			Assert.fail("Object Not available to wait till the time period. Please check the object" +e);
		}
	}
		
		public void removeItemAndValidate()
		{
			try
			{
				new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_Product2InCart));
				commonMethods.clickElement(button_ProductToRemove);
				if(commonMethods.getSize(label_Product2InCart)==0)
				{
					logger.log(Status.PASS, "Product 2 is removed from Cart ");
				}
				else
				{
					Assert.fail("Product 2 is still available in Cart");
				}
			}
			catch (TimeoutException e) 
			{
				Assert.fail("Object Not available to wait till the time period. Please check the object"+e);
			}
		}
		
		public void resetApplicationState()
		{
			try
			{
				new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(button_ThreeDotIcon));
				commonMethods.clickElement(button_ThreeDotIcon);
				new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(link_ResetState));
				commonMethods.clickElement(link_ResetState);
			}
			catch (TimeoutException e) 
			{
				Assert.fail("Object Not available to wait till the time period. Please check the object"+e);
			}
		}
}
