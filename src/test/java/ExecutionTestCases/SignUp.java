package ExecutionTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import AutomationUtils.ReadFileUtils;
import Pages.SignUpPage;

public class SignUp {

	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";

	WebDriver driver = new ChromeDriver();
	
	SignUpPage sign = new SignUpPage(driver);
	
	
//	test execution cases -- 
	@Test(priority=9)
	void testNavigationSignupPage() throws IOException, InterruptedException {
		Boolean status = sign.gotoRegisterPage();
		String ActualPassed = "As expected, user able to navigate register page";
		String ActualFailed = "Not expected, unable to navigate register page";
		ReadFileUtils.setCellData(path, "Sign Up", 2, 7, 
				status ? ActualPassed : ActualFailed);
		sign.setExcelResult(status, 2);
	}
	
	
//	test by keeping inputs blank ---
	@Test(priority=10)
	void testRegisterwithBlankInputs() throws IOException, InterruptedException {
		Boolean status = sign.registerwithBlankInvalid("", "", "", "");
		String ActualPassed = "As expected, user should not be able to create an account";
		String ActualFailed = "Not expected, user able to create an account";
		ReadFileUtils.setCellData(path, "Sign Up", 3, 7, 
				status ? ActualPassed : ActualFailed);
		sign.setExcelResult(status, 3);
	}
	
	
//	test by entering invalid values --
	@Test(priority=11)
	void testRegisterwithInvalidInputs() throws IOException, InterruptedException {
		String[] data = sign.getExcelDatatoArray(4, 5);
		Boolean status = sign.registerwithBlankInvalid(data[0], data[1], data[2], data[3]);
		String ActualPassed = "As expected, user should not be able to create an account";
		String ActualFailed = "Not expected, user able to create an account";
		ReadFileUtils.setCellData(path, "Sign Up", 4, 7, 
				status ? ActualPassed : ActualFailed);
		sign.setExcelResult(status, 4);
	}
	
	
//	test by entering valid values ---
	@Test(priority=12)
	void testRegisterwithValidInputs() throws IOException, InterruptedException {
		String[] data = sign.getExcelDatatoArray(5, 5);
		Boolean status = sign.registerwithValidInputs(data[0], data[1], data[2], data[3]);
		sign.logoutAccount();
		String ActualPassed = "As expected, user able to create an account";
		String ActualFailed = "Not expected, user not able to create an account";
		ReadFileUtils.setCellData(path, "Sign Up", 5, 7, 
				status ? ActualPassed : ActualFailed);
		sign.setExcelResult(status, 5);
	}
	
	
	@AfterClass
	void close() throws InterruptedException {
//		home.logoutAccount();
		driver.quit();
	}
	
	
	
	
}
