package Smoke;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import base.MetodosComunes;
import commons.PropFileHelper;

public class LiverPool extends MetodosComunes{
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='row mt-lg-3 m-row-bootstrap']//a[@id='sortby']")
	WebElement btn_dropDownShortBy;
	
	@FindBy(xpath="//div[@class='row mt-lg-3 m-row-bootstrap']//div[@class='dropdown-menu show']/button[@class='dropdown-item']")
	List<WebElement> list_options;
	
	
	String url =  "https://www.liverpool.com.mx/tienda/salas/cat4340001";
	
	@BeforeTest
	public void initWebElements(@Optional("chrome") String browser) throws MalformedURLException  {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--incognito");
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.get(url);
		PageFactory.initElements(driver,  this);
	}
	
	
	@Test
	public void liverpool() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(btn_dropDownShortBy));
		btn_dropDownShortBy.click();
		String option = "2";
		switch(option) {
		case "1":
			list_options.get(0).click();
			break;
		case "2":
			list_options.get(1).click();
			break;
		case "3":
			list_options.get(2).click();
			break;
		case "4":
			list_options.get(3).click();
			break;
		case "5":
			list_options.get(4).click();
			break;
		case "6":
			list_options.get(5).click();
			break;
		}

	}
	


	

}
