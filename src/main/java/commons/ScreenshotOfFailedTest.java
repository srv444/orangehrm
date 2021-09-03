package commons;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenshotOfFailedTest extends Base implements ITestListener{

	
	

	/**
	 * @throws N/A
	 * @Description get the test suite Name that its failing then take an screenshot if it failed.
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter ITestResult
	 * @return N/A
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		takeScreenShot();
		Reporter.log("********* Error "+ result.getStatus()+ " test has failed **********",true);
		Reporter.log("********* Error "+ result.getTestName()+ " test has failed **********",true);
		Reporter.log("********* Error "+ result.getMethod()+ " test has failed **********",true);
		Reporter.log("********* Error "+ result.getName()+ " test has failed **********",true);
	
			
			try {
				VideoRecorder_utlity.stopRecord();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			
		if (driver != null) {
			
			getDriver().close();
			getDriver().quit();
			driver =null;
			setDriver(driver);
			Reporter.log("Driver was quited ", true);
		} 
	}//end OntestFailure
	
		
}
