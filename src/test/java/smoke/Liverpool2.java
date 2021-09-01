package smoke;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Liverpool2 {
	WebDriver driver;
	
	@Test
public void liverpool2() {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--incognito");
		option.addArguments("--start-maximized");
		driver = new ChromeDriver(option);
		driver.get("https://www.liverpool.com.mx/tienda/salas/cat4340001");
		String menu = "Menor precio";
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='row mt-lg-3 m-row-bootstrap']//a[@id='sortby']"))));
		driver.findElement(By.xpath("//div[@class='row mt-lg-3 m-row-bootstrap']//a[@id='sortby']")).click();
		 driver.findElement(By.xpath("//div[@class='row mt-lg-3 m-row-bootstrap']//div[@class='dropdown-menu show']/button[@class='dropdown-item'][contains(text(),'"+menu+"')]")).click();
		
	}

}
