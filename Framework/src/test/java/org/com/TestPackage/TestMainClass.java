package org.com.TestPackage;

import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.com.ObjectRepo.Pages.CheckOutComplete;
import org.com.ObjectRepo.Pages.CheckOutInformationPage;
import org.com.ObjectRepo.Pages.CheckOutOverViewPage;
import org.com.ObjectRepo.Pages.HomePage;
import org.com.ObjectRepo.Pages.LoginPage;
import org.com.ObjectRepo.Pages.YourCartPage;
import org.testng.annotations.Test;

public class TestMainClass extends FrameworkUtilities implements InitCommonMethods
{
	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	YourCartPage cartPage = new YourCartPage();
	CheckOutInformationPage checkOutInfoPage = new CheckOutInformationPage();
	CheckOutOverViewPage checkOutOverView = new CheckOutOverViewPage();
	CheckOutComplete complete = new CheckOutComplete();
	
	String url = commonMethods.readExcelData("Ecommerce", "URL");
	String userName = commonMethods.readExcelData("Ecommerce", "UserName");
	String password = commonMethods.readExcelData("Ecommerce", "ValidPassword");
	String invalidPassword = commonMethods.readExcelData("Ecommerce", "InvalidPassword"); 
	
	@Test(priority=0)
	public void validateErrorMsgForInvalidPassword()
	{
		setDescription("Navigate to Ecommerce Application and Verify Invalid Password");
		loginPage.launchAndVerifyBlog(url,userName,invalidPassword);
		loginPage.ValidateErrorMsg();
	}
	
	
	@Test(priority=1)
	public void validateProductAddAndRemoveProductInCart()
	{
			setDescription("Navigate to Ecommerce Application and Validate Add Product and Remove Product");
			loginPage.launchAndVerifyBlog(url,userName,password);
			homePage.validateHomeScreen();
			homePage.addProductsToCart();
			homePage.validateTheTotalItemsInListAndClickShoppingCart();
			cartPage.removeItemAndValidate();
			cartPage.resetApplicationState();
	}
	
	@Test(priority=2)
	public void loginToApplicationValidateHomePage()
	{
		setDescription("Navigate to Ecommerce Application Add Products and complete the Transaction Successfully");
		loginPage.launchAndVerifyBlog(url,userName,password);
		homePage.validateHomeScreen();
		homePage.addProductsToCart();
		homePage.validateTheTotalItemsInListAndClickShoppingCart();
		cartPage.clickCheckOut();
		checkOutInfoPage.clickContinueButton();
		loginPage.ValidateErrorMsg();
		checkOutInfoPage.enterInformationDetails();
		checkOutInfoPage.clickContinueButton();
		checkOutOverView.validateTheTotalAmount();
		checkOutOverView.clickOnFinish();
		complete.getSuccessMsg();
	}

}
