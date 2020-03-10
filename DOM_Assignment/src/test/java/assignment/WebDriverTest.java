package assignment;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverTest {
	
	WebDriver driver;

	/**
	 * Test to navigate Yahoo page
	 * 
	 * Starting point will be the yahoo.com
	 * 
	 * *** You may want to do a manual test at first to gather the needed
	 * element locators needed for the test ****** Run Thru The Debugger
	 * 
	 * https://www.yahoo.com
	 * 
	 * Follow Steps below
	 * 
	 */
		
	@Test
	public void funWithYahooPage() {
		
		driver.get("https://www.yahoo.com/");
		
		// Step 1. Assert that we are on the correct page by checking that title = 'Yahoo' 
		
		
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contentEquals("Yahoo"));
		/*
		 * Step 2. Display the count of options on the left side panel ('Mail', 'News', 'Sports',......)
		 * including 'More Yahoo Sites' option
	 	 */
		List<WebElement>leftpanelOptionsList= driver.findElements(By.xpath("//div[@id='header-nav-bar-wrapper']//a"));
		
		System.out.println("the count of options on the left side panel is: "+leftpanelOptionsList.size());
		System.out.println("==============================================================================");
		// Step 3: Enter 'Nutrition' on the search bar on the top
		driver.findElement(By.id("header-search-input")).sendKeys("Nutrition");
		// Step 4: Click Search button
		driver.findElement(By.id("header-desktop-search-button")).click();
		// Step 5: Display count of Images on the page
		List<WebElement>countImages= driver.findElements(By.tagName("img"));
		System.out.println("count of Images on the page is: "+countImages.size());
		System.out.println("==============================================================================");
		// Step 6. Click 'Sign In' button on the top left side
		driver.findElement(By.xpath("//a[text()=' Sign in ']")).click();
		// Step 7. Display the boolean state of the checkbox next to 'Keep me signed in'
		
		Boolean bool=driver.findElement(By.xpath("//label[contains(text(),'Stay signed in')]")).isDisplayed();
		System.out.println("the boolean state of the checkbox: "+bool);
		System.out.println("==============================================================================");
		// Step 8. Click 'Sign In' button 
		
		driver.findElement(By.id("login-signin")).click();
		
		/*
		 * Step 9. Error will be displayed. 
		 * Assert true when the error message contains the expectedErrorStr defined below
		 */
		String expectedErrorStr = "Sorry, we don't recognize this email.";
		
		String errormessage=driver.findElement(By.id("username-error")).getText();
		
		Assert.assertTrue(errormessage.contains(expectedErrorStr));
	}
	
	/**
	 * Test to simulate an attempted Sign in to Paypal and validate error
	 * message returned
	 * 
	 * Starting point will be the PayPal
	 * 
	 * *** You may want to do a manual test at first to gather the needed
	 * element locators needed for the test ****** Run Thru The Debugger
	 * 
	 * https://www.paypal.com
	 * 
	 * Follow Steps below
	 * 
	 */
		
	@Test
	public void funWithPayPalSignUpPage() {
		
		driver.get("https://www.paypal.com");
		
		/*
		 * Step 1. Assert that we are on the correct page by checking that title = 'Send Money, Pay Online or Set Up
		 * a Merchant Account - PayPal'  
		 */ 
		String actualTitle=driver.getTitle();
		Assert.assertTrue(actualTitle.contains("Send Money, Pay Online or Set Up a Merchant Account - PayPal"));
		// Step 2. Click 'Sign Up for Free' button
		
		driver.findElement(By.xpath("//div[contains(@class,'span8 center-block')]//a[contains(@class,'btn-white')][contains(text(),'Sign Up for Free')]")).click();
		// Step 3: Enter email address test@google.com 
		driver.findElement(By.id("cta-btn")).click();
		driver.findElement(By.id("paypalAccountData_email")).sendKeys("test@google.com");
		
		// Step 4: Enter password test123 
		driver.findElement(By.id("paypalAccountData_password")).sendKeys("test123");
		// Step 5: Enter confirm password test123
		driver.findElement(By.id("paypalAccountData_confirmPassword")).sendKeys("test123");
		// Step 6: Click 'Continue' button  
		driver.findElement(By.xpath("//button[@id='/appData/action']")).click();
		/*
		 * Step 7. Error will be displayed		
		 * Assert True that error message contains the expectedErrorStr defined below
 		 */
		String expectedErrorStr = "It looks like you already signed up.";
		String errormessage=driver.findElement(By.xpath("//span[@id='paypalAccountData_email_helptext']//p")).getText();
		
		Assert.assertTrue(errormessage.contains(expectedErrorStr));
		
		// Step 8. Print out the boolean state of the 'confirmPassword' input field displayed
		Boolean bool=driver.findElement(By.id("paypalAccountData_confirmPassword")).isDisplayed();
		
		System.out.println("boolean state of the 'confirmPassword' input field is: "+bool);
		System.out.println("==============================================================================");
		// Step 9. Display the count of Images on the Sign In page
		List<WebElement>countImages= driver.findElements(By.tagName("img"));
		System.out.println("count of Images on the page is: "+countImages.size());
		// Step 10. Display the country flag shown on the bottom right side
		String contryflag= driver.findElement(By.xpath("//button[@class='country US']")).getAttribute("class");
		System.out.println("the country flag shown is: "+contryflag);
		System.out.println("==============================================================================");
	}

	@Before
	public void setUp() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() {
		driver.quit();
	}	
}
