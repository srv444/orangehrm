package commons;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Base extends TestListenerAdapter {
	
	public static Init page;
	protected static WebDriver driver;
	WebDriverWait wait;
	String filePath;
	private static String chromeDriver, geckoDriver, msedgeDriver;
	public static String osName = System.getProperty("os.name");
	public static final String USERNAME = "DreamJobCentral";
	public static final String ACCESS_KEY = "3b0223bc-a26a-422e-a4e5-9d138efefb66";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	
	/**
	 * @throws IOException 
	 * @Description: Constructor that will initialize driver paths and also driver
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebDriver
	 */
	public Base(RemoteWebDriver driver) throws IOException {
		Base.driver = driver;


	}// end constructor

	/**
	 * @Description: Constructor for create instance without parameters
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter N/A
	 */
	public Base() {
		 PropFileHelper obj = new PropFileHelper();
		 obj.getSystemProp();
	}

	/**
	 * @Description return driver already initialized
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter N/A
	 */
	public static WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * @Description get Operation System Name
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter N/A
	 * @return String
	 */
	public static String getOSname() {
		if (osName.contains("Mac")) {
			osName = "Mac";
		} else if (osName.contains("Windows")) {
			osName = "Windows";
		} else if (osName.contains("Linux")) {
			osName = "Linux";
		}
		return osName;
	}
	
	
	/**
	 * @Description initialization driver paths according to the Operating System
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter N/A
	 */
	public static void setDriverPaths() {
		osName = getOSname();
		String path = System.getProperty("user.dir");
		
		switch (osName) {
		case "Mac":
		case "Linux":
			chromeDriver = path+"/chromedriver/chromedriver";
			geckoDriver = path+"/geckodriver/geckodriver";
			msedgeDriver = path+"/msedgedriver/msedgedriver";
			break;
		case "Windows":
			chromeDriver = path+"\\chromedriver\\chromedriver.exe";
			geckoDriver = path+"\\geckodriver\\geckodriver.exe";
			msedgeDriver = path+"\\msedgedriver\\msedgedriver.exe";
			break;

		}
	}

	/**
	 * @throws Exception
	 * @Description WebDriver initialization selecting browser
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter String
	 * @return WebDriver
	 */
	public static WebDriver startDriver(String url) {
		try {
			// set the path according to the Operating System that we are using
			setDriverPaths();
			boolean remote = Boolean.parseBoolean(System.getProperty("REMOTE"));
			MutableCapabilities sauceOptions = new MutableCapabilities();
			// case to initialize driver with the specific browser that we have selected.
			String browser = System.getProperty("BROWSER");
			switch (browser.trim()) {

			case "chrome":
				System.setProperty("webdriver.chrome.driver", chromeDriver);
				ChromeOptions option = new ChromeOptions();
				
				if (remote == true) {
					option.setExperimentalOption("w3c", true);
					option.setCapability("platformName", System.getProperty("OPERATING_SYSTEM"));
					option.setCapability("browserVersion", System.getProperty("BROWSERVERSION"));
					option.setCapability("sauce:options", sauceOptions);
					driver = new RemoteWebDriver(new URL(URL), option);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					sleep(3000);
					driver.get(url);
					Reporter.log("Chrome browser opened with URL ---> <b>" +url+"</b>", true);
				}else {
					option.addArguments("--incognito");
					option.addArguments("--start-maximized");
					option.addArguments("--whitelisted-ips");
					driver = new ChromeDriver(option);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					sleep(3000);
					driver.get(url);
					Reporter.log("Chrome browser opened with URL ---> <b>" +url+"</b>", true);
				}
				
				
 				break;
			case "firefox":
				FirefoxOptions option2 = new FirefoxOptions();
				System.setProperty("webdriver.gecko.driver", geckoDriver);
				
				if (remote == true) {
					
					option2.setCapability("platformName", System.getProperty("OPERATING_SYSTEM"));
					option2.setCapability("browserVersion", System.getProperty("BROWSERVERSION"));
					option2.setCapability("sauce:options", sauceOptions);
					driver = new RemoteWebDriver(new URL(URL), option2);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					sleep(3000);
					driver.get(url);
					Reporter.log("Firefox browser opened with URL ---> <b>" +url+"</b>", true);
				}else {
					option2.addArguments("--incognito");
					option2.addArguments("--start-maximized");
					option2.addArguments("--whitelisted-ips");
					driver = new FirefoxDriver(option2);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					sleep(3000);
					driver.get(url);
					Reporter.log("Firefox browser opened with URL ---> <b>" +url+"</b>", true);
				}
				break;
			case "edge":
				EdgeOptions option3 = new EdgeOptions();
				System.setProperty("webdriver.edge.driver", msedgeDriver);
				if (remote == true) {

					option3.setCapability("platformName", System.getProperty("OPERATING_SYSTEM"));
					option3.setCapability("browserVersion", System.getProperty("BROWSERVERSION"));
					option3.setCapability("sauce:options", sauceOptions);
					driver = new RemoteWebDriver(new URL(URL), option3);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					sleep(3000);
					driver.get(url);
					Reporter.log("Edge browser opened with URL ---> <b>" +url+"</b>", true);
				} else {
//					option3.addArguments("--incognito");
//					option3.addArguments("--start-maximized");
//					option3.addArguments("--whitelisted-ips");
					driver = new EdgeDriver(option3);
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
					sleep(3000);
					driver.get(url);
					Reporter.log("Edge browser opened with URL ---> <b>" +url+"</b>", true);
				}
				break;
			default:
				Reporter.log(
						"Driver can't be initialited, ensure that you have selected the proper browser: " + System.getProperty("BROWSER"),
						true);

			}// end switch
			
			page = new Init(driver);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("WebDriver can't be initialize");
			
		}

		return driver;
	}// end startDrivermethod
	

	
	/**
	 * @throws Exception
	 * @Description wait for web element is visible in the page timeout //Duration.ofSeconds(60)
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void reviewElement(WebElement element) throws Exception {
		try {
			// set timeout
			wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			highlighElement(element);
			Reporter.log("Web Element is visible by locatior ---> <b>" + element.toString().split("->")[1]+"</b>", true);
		} catch (Exception e) {
			Assert.fail("Web Element is not visible: " + element.toString());
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description scroll in to view webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void scroll(WebElement element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			Reporter.log("Its not posible to Scroll to the Web element by locatior ---> <b>" + element.toString().split("By.")[1]+"</b>", true);
		}
	}// end scroll

	/**
	 * @throws Exception
	 * @Description set text in the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter WebElement, String
	 * @return N/A
	 */
	public void setText(WebElement element, String text) throws Exception {
		try {
			reviewElement(element);
			scroll(element);
			element.clear();
			element.sendKeys(text);
			Reporter.log("Text was entered: <b><br>[ "+ text+" ]</br></b>", true);
		} catch (Exception e) {
			Assert.fail("It's not possible to insert the text: <b><br>[ "+ text+" ]</br></b>");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @throws Exception
	 * @Description set text in the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebElement, String
	 * @return N/A
	 */
	public void keywordEnter(WebElement element) throws Exception {
		try {
			element.sendKeys(Keys.ENTER);
			Reporter.log("Enter was sent: " ,true);
		} catch (Exception e) {
			Assert.fail("It's not possible to send Enter");
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description click in the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter WebElement
	 * @return N/A
	 */
	public void click(WebElement element)  {
		try {
			reviewElement(element);
			scroll(element);
			highlighElement(element);
			element.click();
			if(element.toString().contains("By.")==true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("By.")[1]+"</b> ", true);
			}if(element.toString().contains("->")==true) {
				Reporter.log("Web element was clicked by locatior ---> <b> " + element.toString().split("->")[1]+"</b> ", true);
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			Reporter.log("ArrayIndexOutOfBoundsException: " + element.toString(), true);
			e.printStackTrace();
			
		} catch (Exception e) {
			Reporter.log("Web element is not possible to clicked: " + element.toString(), true);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description click in the webElement
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter List WebElement, text
	 * @return N/A
	 */
	public void selectElementByValue(List<WebElement> element, String text) throws Exception {
		try {
			reviewElement(element.get(0));
			for (int i = 0; i <= element.size(); i++) {

				if (i >= element.size()) {
					Assert.fail("The Text is not in the list: " + text);
					break;
				}

				if (element.get(i).getText().contains(text)) {
					scroll(element.get(i));
					highlighElement(element.get(i));
					click(element.get(i));
					
					break;
				}

			} // end for
			Reporter.log("The Element in the list was selected: " + text);

		} catch (Exception e) {
			Reporter.log("The Element is not the list: " + text);
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description generate random name flag true unique name, flag false name in the list
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter Boolean
	 * @return String
	 */
	public String getRandomName(Boolean flag) {
		Date date = new Date();
		String[] people = new String[] { "Sergio", "Ivan", "John", "Marcus", "Henry", "Ismael", "Nishant", "Rakesh",
				"Carlos", "Felix", "Miriam", "Diana", "Adriana", "Alejandro", "Gaby", "Caro", "Melisa", "Aimee",
				"Nataly", "Fernando", "Thomas", "Fidel", "Javier", "Ricardo", "Monica", "Nidia", "Eddy", "Evert", "Ben",
				"Anu", "Rosa","Azucena" };
		List<String> names = Arrays.asList(people);
		Collections.shuffle(names);
		int index = new Random().nextInt(names.size());
		String randomName = names.get(index);
		if (flag == true) {
			DateFormat hourdateFormat = new SimpleDateFormat("HHmmssddMMyyyyssss");
			randomName = randomName + hourdateFormat.format(date);
		}
		sleep(1000);
		return randomName;

	}
	
	/**
	 * @throws Exception
	 * @Description generate random last name flag true unique name, flag false name in the list
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021
	 * @Parameter Boolean
	 * @return String
	 */
	public String getRandomLastName(Boolean flag) {
		Date date = new Date();
		String[] people = new String[] { "Ramones", "Velez", "Flores", "Williams", "Hetfield", "Abbot", "Anderson", "Avalos",
				"Ortiz", "Serrato", "Hernandez", "Pushkarna", "Kim", "Reddy", "Paramasivam", "Molina", "Soria", "Heredia",
				"Torres", "Melchor", "Alladi", "Velazquez", "Kumar", "Montesano", "Marcelino","Cruz" };
		List<String> lastNames = Arrays.asList(people);
		Collections.shuffle(lastNames);
		int index = new Random().nextInt(lastNames.size());
		String randomName = lastNames.get(index);
		if (flag == true) {
			DateFormat hourdateFormat = new SimpleDateFormat("HHmmssddMMyyyy");
			randomName = randomName + hourdateFormat.format(date);
		}

		return randomName;

	}
	
	/**
	 * @throws Exception
	 * @Description generate random number length according to the parameter
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter int
	 * @return String
	 */
	public String getRandomNumber(int length) {
		Random random = new Random();
		int randomNumber = 0;
		Boolean flag = true;
		while (flag) {
			randomNumber = random.nextInt();
			if (Integer.toString(randomNumber).length() == length && !Integer.toString(randomNumber).startsWith("-")) {
				flag = false;
			}
		}
		return String.valueOf(randomNumber);
	}
	
	/**
	 * @throws Exception
	 * @Description Read JSON file
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter String, String
	 * @return JsonNode
	 * @implNote nodeTree.path("fieldName").asText()
	 */
	 public static JsonNode readJsonFileByNode(String jsonpath, String nodeName) {
		 JsonNode nodeTree = null;
		 try {
		 ObjectMapper mapper = new ObjectMapper();
		 JsonNode root = mapper.readTree(new File(jsonpath));
		 // Get Name
		 nodeTree = root.path(nodeName);
		 } catch (JsonGenerationException e) {
	            e.printStackTrace();
	        } catch (JsonMappingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 return nodeTree;
	 }
	 
	 /**
		 * @throws Exception
		 * @Description Read JSON file
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter String, String
		 * @return JsonNode
		 * @implNote nodeTree.path("fieldName").asText()
		 */
		 public static JsonNode readJsonFile(String jsonpath) {
			 JsonNode root = null;
			 try {
			 ObjectMapper mapper = new ObjectMapper();
			  root = mapper.readTree(new File(jsonpath));
			 // Get Name
			 } catch (JsonGenerationException e) {
		            e.printStackTrace();
		        } catch (JsonMappingException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			 return root;
		 }
	 
		/**
		 * @throws N/A
		 * @Description This method is take today date plus the amount of days that you are give by parameter and returned
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter int 
		 * @return String
		 * @implNote N/A
		 */
		public String getDate(int amountDays){
			 
			 	Date myDate = new Date();
				DateFormat df=new SimpleDateFormat("MM/dd/YYYY");//("YYYY-MM-dd");
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(myDate);
				cal.add(Calendar.DATE, amountDays); 
				
				myDate = cal.getTime();
				
				String date=df.format(myDate);
							
				return date;
			 
		 }
	
		/**
		 * @throws N/A
		 * @Description take and screen shoot of specific part during the execution.
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter N/A 
		 * @return N/A
		 */
		public void takeScreenShot() {
			switch (osName) {
			case "Mac":
			case "Linux":
				filePath = "/execution_results/" + driver.getTitle() + "/screenshots/";
				break;
			case "Windows":
				filePath = ".\\execution_results\\screenshots\\";
				break;
			
			}
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//the below method will save the screen shot in the path that we are passing 
			try {	
					String fullpath =  filePath + driver.getTitle()+"_"+formater.format(calendar.getTime())+".png";
					
					FileUtils.copyFile(srcFile, new File(fullpath));
					fullpath = "."+fullpath;
					Reporter.log("******Placed screen shot in: "+ fullpath+" ******",true);
					Reporter.log("<br> <img src='"+ fullpath+"' height='400' with='400'/></b>",true);
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		

		
		/**
		 * @throws N/A
		 * @Description Click webElement with JScript.
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement 
		 * @return N/A
		 */
		public void clickJScript(WebElement button) {

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click();", button);
		}
		
		
		
		/**
		 * @throws N/A
		 * @Description navigate to URL
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter String
		 * @return N/A
		 * @throws InterruptedException 
		 */
		public void navigateToURL(String url) throws InterruptedException {
			driver.navigate().to(url);
			Reporter.log("The URL was properly open [ "+url+" ] ", true);
			waitLoadPage();
		}
		
		/**
		 * @throws N/A
		 * @Description navigate to URL
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter N/A
		 * @return N/A
		 * @throws InterruptedException 
		 */
		public void waitLoadPage() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"));
			 Thread.sleep(5000);
		}
		
		
		/**
		 * @throws Exception
		 * @Description click in the webElement
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter List WebElement, text
		 * @return N/A
		 */
		public void verifyElementByValue(List<WebElement> element, String text) throws Exception {
			try {
				waitLoadPage();
				
				boolean flag = false;
				
				for(WebElement el : element) {
					if(el.getText().equals(text)) {
						scroll(el);
						flag=true;
						Reporter.log("The Element in the list: " + text, true);
					}
					
				}
				
				if(flag==false) {
					Assert.fail("The text is not in the list: " + text);
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		/**
		 * @throws Exception
		 * @Description get text from the webElement
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement, String
		 * @return N/A
		 */
		public String getText(WebElement element) throws Exception {
			String text = null;
			try {
				
				scroll(element);
				text = element.getText();
				Reporter.log("Text got form application is: " + text, true);
			} catch (Exception e) {
				Assert.fail("It's not possible to get the text: " + text);
				e.printStackTrace();
			}
			return text;
		}
		
		/**
		 * @throws Exception
		 * @Description change to the tab with the index
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter index
		 * @return N/A
		 */
		public void switchToTab(int index) {
			 ArrayList<String> tab = new ArrayList<>(driver.getWindowHandles());
		     driver.switchTo().window(tab.get(index));
		}
		
		/**
		 * @throws Exception
		 * @Description Open new tab 
		 * @Author Sergio Ramones
		 * @Date 04-AUG-2021
		 * @Parameter index
		 * @return N/A
		 */
		public void openNewTab() {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.open()");
		}
		/**
		 * @Description refresh the page and wait until load
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021
		 * @Parameter N/A
		 * @return N/A
		 * @throws InterruptedException 
		 */
		public void refreshPage() throws InterruptedException {
			driver.navigate().refresh();
			waitLoadPage();
		}
		
		/**
		 * @Description close browser
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter N/A
		 * @return N/A
		 * @throws InterruptedException
		 */
		public void closeBrowser() throws SocketException, InterruptedException  {
			if (driver != null) {
				waitLoadPage();
				driver.close();
				driver.quit();
				Reporter.log("Driver was quited ", true);
			} else {
				Reporter.log("Driver was not found ", true);
			}
			
			
		}
		
		/**
		 * @Description wait till page load
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 10/09/2020
		 * @Parameter N/A
		 * @return N/A
		 * @throws N/A 
		 */
		public void waitForPreloaderToDisappear() {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading_div")));
		}
		
		/**
		 * @Description sleep in milliseconds the executions
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter N/A
		 * @return N/A
		 * @throws N/A 
		 */
		public static void sleep(long milliseconds) {
			try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				Reporter.log("Cannot wait the millisenconds: " + milliseconds, true);
			}
		}
		
		/**
		 * @Description selects the drop down values by value
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement, String
		 * @return N/A
		 * @throws N/A
		 */
		public void selectDropDownByValue(WebElement element, String selectValue) {

			Select action = new Select(element);
			int attempts = 0;

			while (attempts < 5) {
				try {
					action.selectByValue(selectValue);
					Reporter.log("Element was selected: " + selectValue, true);
					break;
				} catch (StaleElementReferenceException e) {
						Reporter.log("Cannot select element by value: " + selectValue, true);
				}
				 catch (NoSuchElementException e) {
						Reporter.log("Cannot select element by value: " + selectValue, true);
					}
				attempts++;
			} // end while

		}// end method
		
		/**
		 * @Description selects the drop down values by index
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement, Int
		 * @return N/A
		 * @throws N/A
		 */
		public void selectDropDownByIndex(WebElement element, int index) {

			Select action = new Select(element);

			int attempts = 0;

			while (attempts < 2) {
				try {

					action.selectByIndex(index);
					break;

				} catch (StaleElementReferenceException e) {
					Reporter.log("Cannot select element: " + element.toString(), true);
					Assert.fail("Cannot select element: "+ element.toString());
				}
				 catch (NoSuchElementException e) {
						Reporter.log("Cannot select element: " + element.toString(), true);
						Assert.fail("Cannot select element: "+ element.toString());
					}
				attempts++;
			}

		}
		
		
		
		/**
		 * @Description switch to a frame 
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement
		 * @return N/A
		 * @throws InterruptedException 
		 * @throws N/A
		 */
		public void switchToFrame(WebElement frame) throws InterruptedException {
			waitLoadPage();
			driver.switchTo().frame(frame);
		}
		
		/**
		 * @Description switch to a frame 
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement
		 * @return N/A
		 * @throws InterruptedException 
		 * @throws N/A
		 */
		public void switchToDefaultFrame() throws InterruptedException {
			waitLoadPage();
			driver.switchTo().defaultContent();
		}
		
		/**
		 * @Description validate values in dropdown
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement, List <String.>
		 * @return N/A
		 * @throws N/A
		 */
		public void validateDropDownValues(WebElement element, List<String> values) {
			try {
			Select action = new Select(element);
	
			List<String> actualValuesString = new ArrayList<>();

			
					List<WebElement> actualValues = action.getOptions();
					
					for(WebElement value : actualValues) {
						actualValuesString.add(value.getText());
					
					}
						
				        Collections.sort(values);
				        Collections.sort(actualValuesString);
				         
				      boolean isEqual = actualValuesString.equals(values);    
				      
				      if(isEqual==true) {
				    	  Reporter.log("Actual Elements  : " + actualValuesString, true);
				    	  Reporter.log("Expected Elements: " + values, true);
				        Assert.assertTrue(true, "Values are in the dropdown");
				        
				      }else {
				  
				    	  Reporter.log("Actual Elements  : " + actualValuesString, true);
				    	  Reporter.log("Expected Elements: " + values, true);
				    	  Assert.fail("Values are not in the dropdown");
				    	 
				      }

					
				} catch (StaleElementReferenceException e) {
					Reporter.log("Cannot select dropdown", true);
				}
				 catch (NoSuchElementException e) {
						Reporter.log("Cannot select element in dropdown: ", true);
					}
				
		

		}// end method
	
		
		/**
		 * @throws Exception
		 * @Description Verify element exist
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement
		 * @return boolean
		 *
		 **/
		public boolean verifyElementExist(WebElement element){
			try{
				element.getSize();
	            return true;
	        }
	        catch(NoSuchElementException e){
	            return false;
	        }
		}
		
		// Reporter.log("Expected Elements: " + values, true);
		
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
			 Reporter.log("Reporter Log [ " + text+ " ]",true);
		 
		}
		
		/**
		 * @throws Exception
		 * @Description validate texts match with expected
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter String, String
		 * @return N/A
		 *
		 **/
		public void validateExpectedText(String actual, String expected) {
			try {
			Assert.assertEquals(actual, expected);
			reporter(actual+" is equal to " + expected);
			}catch(AssertionError e) {
				reporter(e.toString());
			}
		}
		
		/**
		 * @throws InterrupedException
		 * @Description validate texts match with expected
		 * @Author Sergio Ramones
		 * @Date 12-JUN-2021
		 * @Parameter WebElement
		 * @return N/A
		 *
		 **/
		public static void highlighElement(WebElement element) throws InterruptedException {
			if (Boolean.parseBoolean(System.getProperty("HIGHLIGH")) == true) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border:  solid red');",element);
				js.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white');", element);
			} else {
				reporter("[ Element won't be highlighed ]");
			}

		}
		
		/**
		 * @throws Exception
		 * @Description Verify element exist
		 * @Author Sergio Ramones
		 * @Date 04-JUN-2021 
		 * @Parameter WebElement
		 * @return boolean
		 *
		 **/
		public boolean verifyElementExist(List<WebElement> element) {
			try {
				if (element.size() > 0) {
					return true;
				} else {
					return false;
				}
			} catch (NoSuchElementException e) {
				return false;
			}
		}
	
	
}//end class
