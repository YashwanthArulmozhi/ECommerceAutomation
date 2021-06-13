package org.com.ObjectRepo.Pages;

import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CheckOutOverViewPage extends FrameworkUtilities implements InitCommonMethods {

	private final By label_TotalCostBeforeTax = By.xpath("//div[@class='summary_subtotal_label']");
	
	private final By label_TaxAmount = By.xpath("//div[@class='summary_tax_label']");
	
	private final By label_TotalCost = By.xpath("//div[@class='summary_total_label']");
	
	private final By button_Finish = By.id("finish");
	
	float totalCostBeforeVat = 0;
	float totalTax = 0;
	float totalCost = 0;
	
	/**
	 * Method to validate the Total Amount
	 * <br> Display the total amount in Report </br>
	 */
	public void validateTheTotalAmount()
	{	
			try
			{
				new WebDriverWait(commonMethods.driver, 25).until(ExpectedConditions.presenceOfElementLocated(label_TotalCostBeforeTax));
				totalCostBeforeVat = Float.parseFloat(commonMethods.getText(label_TotalCostBeforeTax).substring(13));
				if((HomePage.product1Cost + HomePage.product2Cost) == totalCostBeforeVat)
				{
					logger.log(Status.PASS,"The Total Price before Tax is correct with the products added in Cart. Total Amount Before Tax " +totalCostBeforeVat);
					totalTax = Float.parseFloat(commonMethods.getText(label_TaxAmount).substring(6));
					logger.log(Status.INFO,"The Total Tax for the products " +totalTax);
					totalCost = Float.parseFloat(commonMethods.getText(label_TotalCost).substring(8));
					logger.log(Status.INFO,"The Total Price of the products " +totalCost);
				}
				else
				{
					Assert.fail("The Total Price before tax and product cost from Cart is not same.");
				}
			}
			catch (TimeoutException e) 
			{
				Assert.fail("Object Not available to wait till the time period. Please check the object");
			}
	}
	
	public void clickOnFinish()
	{
		commonMethods.clickElement(button_Finish);
	}
}
