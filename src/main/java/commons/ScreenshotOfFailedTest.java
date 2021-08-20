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
		takeScreenShot();
		if (driver != null) {
			driver.close();
			driver.quit();
			Reporter.log("Driver was quited ", true);
		} else {
			Reporter.log("Driver was not found ", true);
		}
	}//end OntestFailure
	
		
}
