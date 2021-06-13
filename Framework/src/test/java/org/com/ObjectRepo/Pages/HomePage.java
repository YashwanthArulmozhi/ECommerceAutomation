package org.com.ObjectRepo.Pages;

import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class HomePage extends FrameworkUtilities implements InitCommonMethods  {

	private final By label_ProductsTitle = By.xpath("//span[text()='Products']");
	
	private final By button_Product1ToAddToCart = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product1ToAdd")+"']//..//..//..//button[text()='Add to cart']");
	
	private final By button_Product2ToAddToCart = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product2ToAdd")+"']//..//..//..//button[text()='Add to cart']");
	
	private final By label_Product1Cost = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product1ToAdd")+"']//..//..//..//div[@class='inventory_item_price']");
	
	private final By label_Product2Cost = By.xpath("//div[text()='"+commonMethods.readExcelData("Ecommerce", "Product2ToAdd")+"']//..//..//..//div[@class='inventory_item_price']");
	
	private final By link_ShoppingCart = By.xpath("//a[@class='shopping_cart_link']");
	
	private final By label_TotalItemsInShoppingCart = By.xpath("//a[@class='shopping_cart_link']/span");
	
	
	
	
	static float product1Cost = 0;
	static float product2Cost = 0;
	
	/**
	 * Method to validate successful navigation of home screen
	 */
	public void validateHomeScreen()
	{
		try
		{
			new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_ProductsTitle));
			if(commonMethods.getSize(label_ProductsTitle)>0)
			{
				logger.log(Status.PASS, "Succesfully verified Home Screen");
			}
			else
			{
				logger.log(Status.FAIL,"Failed to validate Home screen");
			}		
		}
		catch (Exception e) 
		{
			Assert.fail(e.getMessage());
		}
	}
	
	
	/**
	 * Method to add Products to cart as per user test data from excel
	 */
	public void addProductsToCart()
	{
		try
		{
			commonMethods.waitForDynamicObjectToAppear(button_Product1ToAddToCart);
			if(commonMethods.getSize(button_Product1ToAddToCart)>0)
			{
				commonMethods.clickElement(button_Product1ToAddToCart);
				product1Cost = Float.parseFloat(commonMethods.getText(label_Product1Cost).substring(1));
				logger.log(Status.INFO, "Procduct 1 Cost : " +product1Cost);
			}
			else
			{
				Assert.fail("The Product mentioned is not available");
			}
			if(commonMethods.getSize(button_Product2ToAddToCart)>0)
			{
				commonMethods.clickElement(button_Product2ToAddToCart);
				product2Cost = Float.parseFloat(commonMethods.getText(label_Product2Cost).substring(1));
				logger.log(Status.INFO, "Procduct 2 Cost : " +product2Cost);
			}
			else
			{
				Assert.fail("The Product mentioned is not available");
			}
		}
		catch (Exception e) 
		{
			Assert.fail(e.getMessage());
		}
	}
	
	
	/**
	 * Validate the total products added to cart
	 */
	public void validateTheTotalItemsInListAndClickShoppingCart()
	{
		try
		{
			if(Integer.parseInt(commonMethods.getText(label_TotalItemsInShoppingCart))==2)
			{
				logger.log(Status.PASS, "Verified total Items added to cart" +commonMethods.getText(label_TotalItemsInShoppingCart));
			}
			else
			{
				Assert.fail("Failed to validate items added to cart" +Integer.parseInt(commonMethods.getText(label_TotalItemsInShoppingCart)));
			}
			clickShoppingCart();
		}
		catch (Exception e) 
		{
			Assert.fail(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * Method to click on Shopping cart icon
	 */
	public void clickShoppingCart()
	{
		try
		{
			new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(link_ShoppingCart));
			commonMethods.clickElement(link_ShoppingCart);
		}
		catch (Exception e) 
		{
			Assert.fail(e.getMessage());
		}
	}
}
