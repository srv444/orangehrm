package commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public abstract class BaseMobile {
	DesiredCapabilities cap;
	PropFileHelper obj;
	AndroidDriver<AndroidElement> driver;
	public  Init mobilepage;
	public static final String USERNAME = "localhost";
	public static final String ACCESS_KEY = "4446";
	public static final String URLMOBILE ="http://localhost:4723/wd/hub";// "http://" + USERNAME + ":" + ACCESS_KEY + "/wd/hub/";
	//http://localhost:4723/wd/hub
	public static String platformName, platformVersion, appActivity, appPackage, deviceName; 
	
	/**
	 * @Description Constructor init property file and init cap variables 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public BaseMobile() {
		obj = new PropFileHelper();
		obj.getSystemProp();
		platformName = System.getProperty("PLATFORMNAME");
		platformVersion = System.getProperty("PLATFORMVERSION");
		appActivity = System.getProperty("APPACTIVITY");
		appPackage = System.getProperty("APPPACKAGE");
		deviceName = System.getProperty("DEVICENAME");
	}
	
	/**
	 * @Description Constructor init property file and init cap variables 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public BaseMobile(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;
	}
	
	/**
	 * @Description set Android driver 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidDriver<AndroidElement> 
	 * @return N/A
	 * @throws N/A 
	 */
	public void setDriver(AndroidDriver<AndroidElement> driver) {
			this.driver=driver;
	}
	
	/**
	 * @Description set init property file and init cap variables 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 * @throws N/A 
	 */
	public void setVariables() {
		PropFileHelper obj = new PropFileHelper();
		obj.getSystemProp();
		platformName = System.getProperty("PLATFORMNAME");
		platformVersion = System.getProperty("PLATFORMVERSION");
		appActivity = System.getProperty("APPACTIVITY");
		appPackage = System.getProperty("APPPACKAGE");
		deviceName = System.getProperty("DEVICENAME");
	}

	
	/**
	 * @Description AndroidDriver initialization according to desired capabilities 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter N/A
	 * @return mobileDriver
	 * @throws MalformedURLException 
	 */
	public AndroidDriver<AndroidElement> startMobileApp() throws MalformedURLException {
	
			cap = new DesiredCapabilities();
			cap.setCapability("platformName", platformName);
			cap.setCapability("platformVersion", platformVersion);
			cap.setCapability("appActivity", appActivity);
			cap.setCapability("appPackage", appPackage);
			cap.setCapability("deviceName", deviceName);
			driver = new AndroidDriver<AndroidElement>(new URL(URLMOBILE),cap);

		
		mobilepage = new Init(driver);
		
		return driver;
		
	}//startMobileApp
	
	/**
	 * @Description click on android element 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidElement
	 * @return N/A
	 * @throws InterruptedException, StaleElementReferenceException
	 * @throws N/A 
	 */
	public void click(AndroidElement element) throws InterruptedException {
		try {
			
			element.click();
			if(element.toString().contains("By.")==true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("By.")[1]+"</b> ", true);
			}else if(element.toString().contains("->")==true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("->")[1]+"</b> ", true);
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			Reporter.log("ArrayIndexOutOfBoundsException: " + element.toString(), true);

			
		} catch (ElementClickInterceptedException e) {
			Reporter.log("Web element is not possible to clicked: " + element.toString(), true);
			e.printStackTrace();
		}
	}//end click
	
	/**
	 * @Description scroll on android element 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidElement
	 * @return N/A
	 * @throws InterruptedException, StaleElementReferenceException
	 * @throws N/A 
	 */
	public void scroll(AndroidElement element) {
		TouchActions action = new TouchActions(driver);
		action.scroll(element, 10, 100);
		action.perform();
	}
	


	

	
	/**
	 * @throws Exception
	 * @Description report in the log 
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebElement
	 * @return boolean
	 *
	 **/
	public static void reporter(String text){
		 Reporter.log("Reporter Log <b> [ " + text+ " ] </b>",true);
	 
	}
	
}
