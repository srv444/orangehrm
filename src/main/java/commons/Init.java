package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class Init {
	public WebDriver driver;

	/**
	 * @throws N/A
	 * @Description constructor for initialize the driver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebDriver
	 * @return N/A
	 */
	public Init(RemoteWebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @throws N/A
	 * @Description constructor for initialize the driver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebDriver
	 * @return N/A
	 */
	public Init(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @throws N/a
	 * @Description Create and return a New Page
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Class
	 * @return initialized class
	 */
	public <TPage extends Base> TPage getInitPage(Class<TPage> pageClass) {
		try {
			
			Reporter.log("Page Object initializated ---> <b>" + pageClass.getName() +"</b>", true);
			// Initialize the Page with its elements and return it.
			return PageFactory.initElements(driver, pageClass);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}// End getInstance method
	


}// end class
