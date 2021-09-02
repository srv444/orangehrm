package smoke;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.MetodosComunes;
import commons.PropFileHelper;
@Listeners({commons.ScreenshotOfFailedTest.class})
public class TestClass extends MetodosComunes {
	WebDriver driver;
	String url =  System.getProperty("URL");
	
	
	
	@FindBy(xpath="//input[@id='txtUsername']")
	WebElement txt_userName;
	
	@FindBy(id="txtPassword")
	WebElement txt_password;
	
	@FindBy(id="btnLogin")
	WebElement btn_login;
	
	
	@FindBy(id="btnLogin")
	List<WebElement> sbtn_login;
	
	
	
	
	
	@Parameters(value= {"browser"})
	@BeforeTest
	public void initWebElements(@Optional("chrome") String browser) throws MalformedURLException {
		 PropFileHelper obj = new PropFileHelper();
		 obj.getSystemProp();
		driver = initBrowser(url,browser);
		PageFactory.initElements(driver,  this);
	}
	@Test
	public void probarGrid() throws MalformedURLException {
		txt_userName.sendKeys("Admin");
		txt_password.sendKeys("admin123");
		btn_login.click();
		btn_login.isDisplayed();
		Assert.fail("The text is not in the list: " + "");
		System.out.println();
		
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}// end class
