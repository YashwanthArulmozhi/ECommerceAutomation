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

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.utils.FileUtil;
import com.mongodb.diagnostics.logging.Logger;

public class CommonMethods extends BrowserAndDriverClass {

	public void launchApplication(String url)
	{
		try
		{
			if(driver==null || driver.toString().equals("null"))
			{
			initDriver(getBrowserName());
			}
			else
			{
				System.out.println("Driver is already");
			}
			driver.get(url);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void clickElement(By element)
	{
		try
		{
			driver.findElement(element).click();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static String getScreenshot(String path) throws HeadlessException, AWTException, IOException
	{
		BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		ImageIO.write(image, "png", new File(path));
		return path; 
	}
	
	public void sendValues(By element,String value)
	{
		try
		{
			driver.findElement(element).sendKeys(value);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void executorClick(By element)
	{
		try
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(element));	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void scrollIntoViewByElement(By element)
	{
		try
		{
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));	
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectByVisibleText(By element,String value)
	{
		try
		{
		Select select = new Select(driver.findElement(element));
		select.selectByVisibleText(value);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void selectByIndex(By element,int indexValue)
	{
		try
		{
		Select select = new Select(driver.findElement(element));
		select.selectByIndex(indexValue);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
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
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
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
