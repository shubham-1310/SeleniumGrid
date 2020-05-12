package com.automation.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.automation.qa.base.Base;
import com.automation.qa.pages.LandingPage;
import com.automation.qa.pages.LoginPage;

public class LoginPageNegativeTest extends Base {
	
	LandingPage landingPage;
	LoginPage loginPage;
	
	public LoginPageNegativeTest()
	{
		super();
	}
	
	@BeforeClass
	public void setup() throws IOException
	{
		initializeDriver();
		landingPage = new LandingPage();
		loginPage= landingPage.clickLoginButton();
		
	}
	
	@Test(priority=1)
	public void validateTitle()
	{
	String title=loginPage.ValidateLoginPageTitle();
	Assert.assertEquals(title, "Login - My Store");	
	}
	
	
	
	@Test(priority=2)
	public void enterEmailid()
	{
		loginPage.enterEmailId("test");
		
	}
	
	@Test  (priority=3)
	public void enterPassword()
	{
		loginPage.enterpwd(password);
		
	}
	@Test  (priority=4)
	public void clickSignButton()
	{
		loginPage.clickLoginButton();
		
	}
	
	@Test (priority=5)
	public void inValidEmailTextDispalyed() {
		Assert.assertTrue(loginPage.invalidEmailText());
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
	
	
	
	
	

}
