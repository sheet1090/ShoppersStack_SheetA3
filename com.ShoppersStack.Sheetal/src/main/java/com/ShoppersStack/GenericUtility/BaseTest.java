package com.ShoppersStack.GenericUtility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.ShoppersStack.POM.Home_Page;
import com.ShoppersStack.POM.Login_Page;
import com.ShoppersStack.POM.Welcome_Page;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	public WebDriver driver;
	static WebDriver sDriver;
	public FileUtility fileUtility =new FileUtility();
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public Welcome_Page welcomePage;
	public Login_Page loginPage;
	public WebDriverWait wait;
	public Home_Page homePage;
	public JavaUtility javaUtility=new JavaUtility();
	public WebDriverUtility webDriverUtility=new WebDriverUtility();
	
	@BeforeSuite
	public void beforeSuit()
	{
		System.out.println("@BeforeSuit");
	}
	
	@BeforeTest
	public void beforeTest()
	{
		System.out.println("@BeforeTest");
		
		spark=new ExtentSparkReporter(FrameWorkConstants.extentReportsPath+javaUtility.getLocalDateTime()+".html");
		reports=new ExtentReports();
		reports.attachReporter(spark);
		test=reports.createTest("Demo");
		
	}
	
	@BeforeClass
	public void beforeClass() throws IOException
	{
		
		System.out.println("@BeforeClass");
		
		String browserName=fileUtility.readDataFromePropertyFile("browserName");
		String url=fileUtility.readDataFromePropertyFile("url");
		
		
		//String url=System.getProperty("url");//to give parameters from cmd
		//String browserName=System.getProperty("browser");//if you want to execute from md and oass para from cmd then write this 2 lines or comment these
		
		if(browserName.contains("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browserName.contains("edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browserName.contains("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("Please enter valid browser name...");
		}
	sDriver=driver;
	
	wait=new WebDriverWait(driver,Duration.ofSeconds(15));
	homePage=new Home_Page(driver);
	

	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
	driver.get(url);
	}
	
	@BeforeMethod
	public void beforeMethod() throws InterruptedException, IOException
	{
		System.out.println("@BeforeMethod");
		
		welcomePage=new Welcome_Page(driver);
		wait.until(ExpectedConditions.elementToBeClickable(welcomePage.getLoginBtn()));
		Thread.sleep(3000);
		
		welcomePage.getLoginBtn().click();
		
		loginPage=new Login_Page(driver);
		loginPage.getEmailTextField().sendKeys(fileUtility.readDataFromePropertyFile("userName"));
		loginPage.getPasswordTextField().sendKeys(fileUtility.readDataFromePropertyFile("password"));
		loginPage.getLoginBtn().click();
		
		
		
		
	}
	
	@AfterMethod
	public void afterMethod() throws InterruptedException
	{
		System.out.println("@AfterMethod");
		
		homePage.getAccountSettingBtn().click();
		Thread.sleep(2000);
		homePage.getLogOutBtn().click();
		
	}
	@AfterClass
	public void aftetrClass() throws InterruptedException
	{
		System.out.println("@AfterClass");
		Thread.sleep(3000);
		driver.quit();
	}
	
	@AfterTest
	public void afterTest()
	{
System.out.println("@AfterTest");
reports.flush();

	}
	
	@AfterSuite
	public void afterSuit()
	{
		System.out.println("@AfterSuit");
		
	}
}
