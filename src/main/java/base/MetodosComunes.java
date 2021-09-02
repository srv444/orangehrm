package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import commons.PropFileHelper;

public class MetodosComunes {
	
WebDriver driver;
String projectPath = System.getProperty("user.dir");



/**
 * @Description: Constructor for create instance without parameters
 * @Author Sergio Ramones
 * @Date 04-JUN-2021 
 * @Parameter N/A
 */
public MetodosComunes() {
	 PropFileHelper obj = new PropFileHelper();
	 obj.getSystemProp();
}


/**
 * @throws Exception
 * @Description get text from the webElement
 * @Author Sergio Ramones
 * @Date 04-JUN-2021 
 * @Parameter WebElement
 * @return String
 */
public WebDriver initBrowser(String URL, String browserName) throws MalformedURLException {
	Boolean remote =true;
	String remoteURL=System.getProperty("URL_REMOTE");
	DesiredCapabilities desCap = new DesiredCapabilities();
	ChromeOptions option = new ChromeOptions();
//	EdgeOptions edgOption = new EdgeOptions();
	FirefoxOptions fOption = new FirefoxOptions();
	switch (browserName) {

	case "chrome":

		if (remote == false) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\chromedriver\\chromedriver.exe");
			option.addArguments("--incognito");
			option.addArguments("--start-maximized");
			driver = new ChromeDriver(option);
			driver.get(URL);
		} else {

			option.addArguments("--incognito");
			option.addArguments("--start-maximized");
			desCap.setCapability("browserName", "chrome");
//			desCap .setCapability("version", "92.0");
			desCap.setCapability(ChromeOptions.CAPABILITY, option);
			driver = new RemoteWebDriver(new URL(remoteURL), desCap);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.get(URL);

		}
		break;

	case "edge":

		if (remote == false) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\msedgedriver\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(URL);

		} else {

			option.addArguments("--incognito");
			option.addArguments("--start-maximized");
			desCap.setCapability("browserName", "edge");
//		desCap .setCapability("version", "92.0");
			desCap.setCapability(ChromeOptions.CAPABILITY, option);
			driver = new RemoteWebDriver(new URL(remoteURL), desCap);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.get(URL);

		}
		break;

	case "firefox":

		if (remote == false) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\geckodriver\\geckodriver.exe");
			fOption.addArguments("--incognito");
			fOption.addArguments("--start-maximized");
			driver = new FirefoxDriver(fOption);
			driver.get(URL);

		} else {

			option.addArguments("--incognito");
			option.addArguments("--start-maximized");
			desCap.setCapability("browserName", "firefox");
//	desCap .setCapability("version", "92.0");
			desCap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, fOption);
			driver = new RemoteWebDriver(new URL(remoteURL), desCap);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.get(URL);

		}
		break;
	default:
		Reporter.log("El explorador que intentas abrir no exisite [ " + browserName + " ]", true);

	}// end switch

	return driver;

}//end initBrowser
		
		
		
		
		
		
public void scroll1(WebElement element) {
	  
	Actions actions = new Actions(driver);
	actions.moveToElement(element);
	actions.perform();
	 
  }

		
		
		
		
		
	
//	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
//	wait.pollingEvery(Duration.ofMillis(250));
//	wait.withTimeout(Duration.ofSeconds(5));
//	wait.ignoring(NoSuchElementException.class); // We need to ignore this exception.
//
//	Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
//		public WebElement apply(WebDriver arg0) {
//			System.out.println("Checking for the object!!");
//			WebElement element = arg0.findElement(By.id("dynamicText"));
//			if (element != null) {
//				System.out.println("A new dynamic object is found.");
//			}
//			return element;
//		}
//	};
//
//	
//	
//		wait.until(function);
//}
	 
}


   