package org.com.FrameWork;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class CommonMethods extends BrowserAndDriverClass {

	public void launchApplication(String url)
	{
		try
		{
			if(driver==null || driver.toString().equals("null"))
			{
			initDriver(getBrowserName());
			driver.get(url);
			}
			else
			{
				driver.get(url);
			}
			
		}
		catch (Exception e) {
			Assert.fail("Driver Initialization wrong");
		}
	}
	
	/**
	 * Method to Click an Element
	 * @param element
	 */
	public void clickElement(By element)
	{
		try
		{
		driver.findElement(element).click();
		}
		catch (Exception e) {
			Assert.fail(element+ " is not correct or not available in the page");
		}
	}
	
	/**
	 * Method to get text from the application
	 * @param element
	 * @return textValue
	 */
	public String getText(By element)
	{
		String textValue ="";
		try
		{
			textValue = driver.findElement(element).getText();
			return textValue;
		}
		catch (NoSuchElementException e) {
			Assert.fail(element+" not correct");
		}
		return textValue;
	}
	
	/**
	 * Close the browser
	 */
	public void closeBrowser()
	{
		try
		{
			driver.close();
		}
		catch (Exception e) {
			Assert.fail("Driver Initialization wrong");
		}
	}
	
	/**
	 * Get the total size of the element
	 * @param element
	 * @return size
	 */
	public int getSize(By element)
	{
		int size =0;
		try
		{
			size = driver.findElements(element).size();
			return size;
		}
		catch (NoSuchElementException e) {
			Assert.fail(element+" not correct");
		}
		return size;
	}
	
/*	public static String getScreenshot(String path) throws HeadlessException, AWTException, IOException
	{
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(path));
		return path; 
	}*/
	
	/**
	 * Method to take screenshot of the entire page
	 * @param path
	 * @return path
	 * @throws IOException
	 */
	public String getScreenshotSelenium(String path) throws IOException
	{
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(path));
		return path;
	}
	
	public boolean waitForDynamicObjectToAppear(By Element) throws InterruptedException
	{
		try
		{
			int i=1;
			do
			{
				if(driver.findElements(Element).size()==1)
				{
					return true;
				}
				else
				{
					Thread.sleep(2000);
					i++;
				}
			}while(i<=3);
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}
	
	/**
	 * Method to send value to an input field
	 * @param element
	 * @param value
	 */
	public void sendValues(By element,String value)
	{
		try
		{
			driver.findElement(element).sendKeys(value);
		}
		catch (NoSuchElementException e) {
			Assert.fail(element+" not correct");
		}
	}
	
	public WebElement waitForElement(By element,int timeToWait)
	{
		return new WebDriverWait(commonMethods.driver, timeToWait).until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	/**
	 * Method for Executor Click an element
	 * @param element
	 */
	public void executorClick(By element)
	{
		try
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(element));	
		}
		catch (Exception e) {
			Assert.fail(element+" not correct");
		}
	}
	
	/**
	 * Method to scroll to an object in a page
	 * @param element
	 */
	public void scrollIntoViewByElement(By element)
	{
		try
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));	
		}
		catch (Exception e) {
			Assert.fail(element+" not correct");
		}
	}
	
	/**
	 * Method to select the value in a dropdown by value
	 * @param element
	 * @param value
	 */
	public void selectByVisibleText(By element,String value)
	{
		try
		{
		Select select = new Select(driver.findElement(element));
		select.selectByVisibleText(value);
		}
		catch (NoSuchElementException e) {
			Assert.fail(element+" not correct");
		}
	}
	
	/**
	 * Method to select the value in a dropdown by index
	 * @param element
	 * @param indexValue
	 */
	public void selectByIndex(By element,int indexValue)
	{
		try
		{
		Select select = new Select(driver.findElement(element));
		select.selectByIndex(indexValue);
		}
		catch (Exception e) {
			Assert.fail(element+" not correct");
		}
	}
	
	/**
	 * Method to switch Window
	 * @exception NoSuchWindowException
	 */
	public void windowSwitch()
	{
		try
		{
			String windowBefore = driver.getWindowHandle();
			Set<String> totalWindows = driver.getWindowHandles();
			for(String window : totalWindows)
			{
				driver.switchTo().window(window);
			}
			driver.manage().window().maximize();
		}
		catch (NoSuchWindowException e) {
			logger.log(Status.FAIL,"No Such Window");
		}
	}
	
	/**
	 * Method to get value from the excel with column name as KEY
	 * @param excelName
	 * @param columnName
	 * @return
	 */
	public String readExcelData(String excelName,String columnName)
	{
		String excelValue = null;
		try
		{
			
			FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\testData\\"+excelName+".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(readProperty("Environment"));
			XSSFRow row = sheet.getRow(0);
			int cell =-1;
			int cellCount = row.getPhysicalNumberOfCells();
			for(int i=0;i<=cellCount-1;i++)
			{
				if(row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(columnName))
				{
					cell=i;
					break;
				}
			}
			row = sheet.getRow(1);
			excelValue = row.getCell(cell).getStringCellValue().trim();
			return excelValue;
		}
		catch (Exception e) {
			Reporter.log(e.getMessage());
		}
		return excelValue;
	}
	
	/**
	 * Method to create database connection with set of parameters and get data for column in a list
	 * @param driverName
	 * @param server
	 * @param userName
	 * @param Password
	 * @param query
	 * @param columnName
	 * @return getColumnData
	 * @throws SQLException
	 */
	public List<String> getDbDataForColumn(String driverName,String server,String userName,String Password,String query,String columnName) throws SQLException
	{
		List<String> getColumnData = null;
		Connection conn = null;
		Statement ps = null;
		ResultSet rs = null;
		
		try
		{
			try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(server, userName, Password);
			}catch (ClassNotFoundException ex) {
				System.out.println(ex.getMessage());
			}
			ps = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery(query);
			while(rs.next())
			{
				getColumnData.add(rs.getString(columnName));
			}
			return getColumnData;
		}
		finally {
			ps.close();
			conn.close();
			
		}
	}
}
