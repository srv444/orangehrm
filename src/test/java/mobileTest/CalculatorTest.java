package mobileTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import commons.BaseMobile;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import mobile.Calculator;

public class CalculatorTest extends BaseMobile{
	AndroidDriver<AndroidElement> driver;

	Calculator calculator;
	
	@Test
	public void calculatorRealDevice() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11");
		cap.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
		cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
		cap.setCapability("deviceName", "S20 Ultra de sergio");

		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"),cap);
		
		driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_09")).click();
		driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_mul")).click();
		driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_05")).click();
		driver.findElement(By.id("com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal")).click();
	}
	
	
	@Test
	public void calculatorEmulatorDevice() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "9");
		cap.setCapability("appActivity", "com.android.calculator2.Calculator");
		cap.setCapability("appPackage", "com.android.calculator2");

		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"),cap);
		
		driver.findElement(By.id("com.android.calculator2:id/digit_6")).click();
		driver.findElement(By.id("com.android.calculator2:id/op_mul")).click();
		driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
		driver.findElement(By.id("com.android.calculator2:id/eq")).click();
	}
	
	@Test
	public void multiplication() throws MalformedURLException {
		driver = startMobileApp();
		calculator = new Calculator(driver);
		calculator.multiplacation("6", "5");
		
	}
	
	@Test
	public void multiplicationPages() throws Exception {
		driver = startMobileApp();
		mobilepage.getMobilePage(Calculator.class).multiplacation("6", "5");
		
	}

}
