package commons;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseMobile {
	DesiredCapabilities cap;
	PropFileHelper obj;
	AndroidDriver<AndroidElement> mobileDriver;
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
	public BaseMobile(AndroidDriver<AndroidElement> mobileDriver) {
		this.mobileDriver=mobileDriver;
	}
	
	/**
	 * @Description set Android driver 
	 * @Author Sergio Ramones
	 * @Date 06-SEP-2021 
	 * @Parameter AndroidDriver<AndroidElement> 
	 * @return N/A
	 * @throws N/A 
	 */
	public void setDriver(AndroidDriver<AndroidElement> mobileDriver) {
			this.mobileDriver=mobileDriver;
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
			mobileDriver = new AndroidDriver<AndroidElement>(new URL(URLMOBILE),cap);

		
		mobilepage = new Init(mobileDriver);
		
		return mobileDriver;
		
	}//startMobileApp
}
