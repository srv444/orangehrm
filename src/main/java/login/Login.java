package login;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.Base;

public class Login extends Base{
	
	@FindBy(xpath="//input[@id='txtUsername']")
	WebElement txt_userName;
	
	@FindBy(id="txtPassword")
	WebElement txt_password;
	
	@FindBy(id="btnLogin")
	WebElement btn_login;
	
	@FindBy(xpath="//div[@id='branding']//*[@id='welcome']")
	WebElement link_userName;
	
	@FindBy(id="spanMessage")
	WebElement text_errorMessage;
	
	@FindBy(xpath="//a[contains(@href,'logout')]")
	WebElement link_logout;
	
	@FindBy(xpath="//div[@id='forgotPasswordLink']/a")
	WebElement link_forgotPasswordLink;

	@FindBy(id="securityAuthentication_userName")
	WebElement txt_userNameResetPassword;
	
	@FindBy(id="btnSearchValues")
	WebElement btn_resetPassword;
	
	@FindBy(id="btnCancel")
	WebElement btn_cancel;
	
	@FindBy(xpath="//div[@class='inner']/div")
	WebElement text_changeMessage;
	
	@FindBy(xpath="//div[@class='inner']/div")
	WebElement text_resetMessage;
	
	
	
	
	/**
	 * @throws Exception
	 * @Description Constructor init elements
	 * @Author Sergio Ramones
	 * @Date 02-SEP-2021 
	 * @Parameter WebDriver
	 * @return N/A
	 */
	public Login(WebDriver driver) throws IOException {
		super(driver);
		PageFactory.initElements(driver, this);;
	}
	
	/**
	 * @throws Exception
	 * @Description Enter user name and password and login in the site
	 * @Author Sergio Ramones
	 * @Date 02-SEP-2021 
	 * @Parameter String, String
	 * @return N/A
	 */
	public void validLogin(String userName, String password) throws Exception {
		setText(txt_userName, userName);
		setText(txt_password, password);
		takeScreenShot();
		click(btn_login);
		reviewElement(link_userName);
		reporter(getText(link_userName));
		takeScreenShot();
	}
	
	
	/**
	 * @throws Exception
	 * @Description Enter user name and password and login in the site
	 * @Author Sergio Ramones
	 * @Date 02-SEP-2021 
	 * @Parameter String, String, String
	 * @return N/A
	 */
	public void invalidLogin(String userName, String password, String errorMessage) throws Exception {
		setText(txt_userName, userName);
		setText(txt_password, password);
		takeScreenShot();
		click(btn_login);
		reviewElement(text_errorMessage);
		validateExpectedText(errorMessage, getText(text_errorMessage));
		takeScreenShot();
	}
	
	
	/**
	 * @throws Exception
	 * @Description Enter user name and password and login in the site
	 * @Author Sergio Ramones
	 * @Date 02-SEP-2021 
	 * @Parameter N/A
	 * @return N/A
	 */
	public void cerrarSession() throws Exception {
		click(link_userName);
		click(link_logout);
		reviewElement(btn_login);
		takeScreenShot();
	}
	
	
	/**
	 * @throws Exception
	 * @Description Enter user name and password and login in the site
	 * @Author Sergio Ramones
	 * @Date 02-SEP-2021 
	 * @Parameter String, String, String
	 * @return N/A
	 */
	public void resetPassword(String userName, String expectedMessage, String expectedResetMessage) throws Exception {
		reviewElement(btn_login);
		click(link_forgotPasswordLink);
		reviewElement(text_changeMessage);
		takeScreenShot();
		validateExpectedText(expectedMessage, getText(text_changeMessage));
		setText(txt_userNameResetPassword, userName); 
		click(btn_resetPassword);
		validateExpectedText(expectedResetMessage, getText(text_resetMessage));
		reviewElement(text_resetMessage);
		takeScreenShot();
		click(btn_cancel);
		takeScreenShot();
	}
	
	
	
	
	
	
}
