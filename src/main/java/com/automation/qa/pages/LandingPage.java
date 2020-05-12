package com.automation.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.qa.base.Base;

public class LandingPage<WebElements> extends Base{
	
//	WebDriver ldriver;
	
//	public LandingPage(WebDriver rdriver)
//	//here l for local and r for remote
//	{
//		ldriver=rdriver;
//		PageFactory.initElements(rdriver, this);
//
//	}

	public LandingPage(){
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@class='login']")
	@CacheLookup
	WebElement loginButton;
	
	@FindBy(xpath="//img[@class='logo img-responsive']")
	@CacheLookup
	WebElement siteLogo;
	
	

	
	public boolean checkLogo()
	{
		 return siteLogo.isDisplayed();
	}
	
	
	public String ValidateLandingPageTitle()
	{
		return driver.getTitle();
	}
	
	
	public LoginPage clickLoginButton()
	{
		loginButton.click();
		return new LoginPage();
	}
	
	
}
