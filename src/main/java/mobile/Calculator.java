package mobile;

import org.openqa.selenium.support.PageFactory;

import commons.BaseMobile;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Calculator extends BaseMobile {

	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_edt_formula")
	private AndroidElement txt_numbers;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_01")
	private AndroidElement btn_digit_0;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_01")
	private AndroidElement btn_digit_1;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_02")
	private AndroidElement btn_digit_2;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_03")
	private AndroidElement btn_digit_3;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_04")
	private AndroidElement btn_digit_4;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_05")
	private AndroidElement btn_digit_5;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_06")
	private AndroidElement btn_digit_6;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_07")
	private AndroidElement btn_digit_7;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_08")
	private AndroidElement btn_digit_8;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_09")
	private AndroidElement btn_digit_9;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_equal")
	private AndroidElement btn_equal;
	
	@AndroidFindBy(id = "com.sec.android.app.popupcalculator:id/calc_keypad_btn_mul")
	private AndroidElement btn_mul;
	
	public Calculator(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	public void multiplacation(String number, String number2) throws InterruptedException {
		txt_numbers.sendKeys(number);
		click(btn_mul);
		txt_numbers.sendKeys(number2);
		btn_equal.click();
		
	}

}
