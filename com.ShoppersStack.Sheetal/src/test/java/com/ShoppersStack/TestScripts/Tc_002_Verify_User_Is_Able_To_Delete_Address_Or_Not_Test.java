package com.ShoppersStack.TestScripts;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import com.ShoppersStack.GenericUtility.BaseTest;
import com.ShoppersStack.POM.MyAddresses_Page;
import com.ShoppersStack.POM.MyProfile_Page;
//*[name()='svg' and @name='searchBtn']
public class Tc_002_Verify_User_Is_Able_To_Delete_Address_Or_Not_Test extends BaseTest {

	@Test
	public void deleteAddress() throws InterruptedException, IOException
	{
		homePage.getAccountSettingBtn().click();
		homePage.getMyProfileBtn().click();

		MyProfile_Page myProfilePage = new MyProfile_Page(driver);
		myProfilePage.getMyAddressesBtn().click();
		
		MyAddresses_Page myAddressPage = new MyAddresses_Page(driver);
		Thread.sleep(2000);
		myAddressPage.getDeleteBtn().click();
		Thread.sleep(2000);
		myAddressPage.getYesBtn().click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Thread.sleep(2000);
		webDriverUtility.getScreenShotOfWebPage(driver);
		Thread.sleep(10000);
	}
}
