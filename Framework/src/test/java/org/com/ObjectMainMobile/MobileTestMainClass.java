package org.com.ObjectMainMobile;

import org.com.FrameWork.BrowserAndDriverClass;
import org.com.FrameWork.FrameworkUtilities;
import org.com.FrameWork.InitCommonMethods;
import org.com.ObjectRepo.Pages.AppPage;
import org.testng.annotations.Test;

public class MobileTestMainClass extends FrameworkUtilities implements InitCommonMethods
{
	
	AppPage app = new AppPage();
	
	@Test(priority=0)
	public void runTest1()
	{
		//callBowser();
		setDesciption("Navigate Mindtree Application And verify Blog");
		app.launchAndVerifyBlog();
		
	}
	
	/*@Test(priority=1)
	public void runTest2()
	{
		//callBowser();
		setDesciption("Navigate Mindtree Application And verify News");
		app.launchAndVerifyNews();
		
	}*/

}
