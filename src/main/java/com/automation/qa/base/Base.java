package com.automation.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import com.automation.qa.util.TestUtil;
import com.automation.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeSuite;

public class Base {
	
	
	public static RemoteWebDriver driver;
	public static String emailAddress;
	public static String password;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public Base() 
	{
		prop = new Properties();	
		
		try {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/automation/qa/config/config.properties");
		prop.load(fis);

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	
		
				
		
	}
		
		public void initializeDriver() throws IOException
		{
		String browserName=prop.getProperty("browser");
		emailAddress=prop.getProperty("email");
		password=prop.getProperty("password");
		
		if (browserName.equals("chrome")) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();

			URL url = new URL("http://localhost:4444/wd/hub");

			 driver = new RemoteWebDriver(url,dc);

//			WebDriverManager.chromedriver().setup();
//	        driver =new ChromeDriver();
	      
		}
		else if (browserName.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
	        driver =new FirefoxDriver();
	       
		}
		else if (browserName.equals("ie"))
		{
			WebDriverManager.iedriver().setup();
	        driver =new InternetExplorerDriver();
	       
		}
		else if (browserName.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
	        driver =new EdgeDriver();
	       
		}

		
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
//		driver = e_driver;
	    
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicit_Wait,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));

		
			
		
		}@BeforeSuite
		public void gridSetup(){
			try
			{
				// Just one line and you are done !
				// We have given a command to start cmd
				// /K : Carries out command specified by string
//           Runtime.getRuntime().exec(new String[] {"cmd", "/K", "Start && docker-compose up"});
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"docker-compose up");
				Thread.sleep(5000);
				System.out.println("Selenium Grid creation successfull - Starting test");
				Thread.sleep(5000);
				Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"docker-compose scale chrome=4");
				Thread.sleep(5000);

			}
			catch (Exception e)
			{
				System.out.println("Selenium Grid initilalization failed, Normal execution begins");
				e.printStackTrace();
			}

		}
	@AfterSuite
	public void gridClosure(){
		try
		{
			// Just one line and you are done !
			// We have given a command to start cmd
			// /K : Carries out command specified by string
//           Runtime.getRuntime().exec(new String[] {"cmd", "/K", "Start && docker-compose up"});
			Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"docker-compose down");
			Thread.sleep(5000);
			System.out.println("Selenium Grid shutdown Success");

			Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
		}
		catch (Exception e)
		{
			System.out.println("Selenium Grid shutdown failed");
			e.printStackTrace();
		}

	}
}
	

	
	
	
	
	
	
	
	
	
	
	
	
	


