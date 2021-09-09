package login;

import java.io.IOException;
import java.net.SocketException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.Base;

@Listeners({commons.ScreenshotOfFailedTest.class})
public class ValidLogin  extends Base {
	String url =  System.getProperty("URL");
	static WebDriver driver;
	Login login;
	
	
	@Parameters(value= {"browser"})
	@BeforeTest
	public void iniciarExplorador(@Optional("chrome") String browser) throws IOException {
		driver = startDriver(url, browser);
		login = new Login(driver);
	
	}
	
	
	@Test(priority = 1)
	public void loginInvalido() throws Exception{
//		login.invalidLogin("Admin", "admin", "Invalid credentials");
		
		page.getInitPage(Login.class).invalidLogin("Admin", "admin", "Invalid credentials");

	}
	
	@Test(priority = 2)
	public void loginValido() throws Exception{
		login.validLogin("Admin", "admin12");
		login.cerrarSession();
	}
	
	@Test(priority = 3)
	public void changePassword() throws Exception{
		login.resetPassword("Test", "Please enter your username to identify your account to reset your password.", "Please contact HR admin in order to reset the password\n Close");
	}
	

	
	
	@AfterSuite
	public void closeSession() throws SocketException, InterruptedException {
		closeBrowser();
	}
}
