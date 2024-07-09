package ExecutionTestCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import AutomationUtils.ReadFileUtils;
import Pages.LoginPage;

public class LogIn {
	
	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";

	WebDriver driver = new ChromeDriver();
	
	LoginPage login = new LoginPage(driver);


	@Test(priority=13)
	void testnavigationlogin() throws IOException {
		
		Boolean status = login.loginNavigation();
		String ActualPassed = "As expected, user able to navigate login page";
		String ActualFailed = "Not expected, unable to navigate login page";
		ReadFileUtils.setCellData(path, "Login", 2, 7, 
				status ? ActualPassed : ActualFailed);
		login.setExcelResult(status, 2);
	}
	
	
	@Test(priority=14)
	void testLoginwithBlankInputs() throws IOException, InterruptedException {
		Boolean status = login.loginwithBlankInvalid("", "");
		String ActualPassed = "As expected, user not able to login an account";
		String ActualFailed = "Not expected, user able to login an account";
		ReadFileUtils.setCellData(path, "Login", 3, 7, 
				status ? ActualPassed : ActualFailed);
		login.setExcelResult(status, 3);
	}
	
	
	
	@Test(priority=15)
	void testLoginwithInvalidInputs() throws IOException, InterruptedException {
		String[] data = login.getExcelDatatoArray(4, 5);
		Boolean status = login.loginwithBlankInvalid(data[0], data[1]);
		String ActualPassed = "As expected, user not able to login an account";
		String ActualFailed = "Not expected, user able to login an account";
		ReadFileUtils.setCellData(path, "Login", 4, 7, 
				status ? ActualPassed : ActualFailed);
		login.setExcelResult(status, 4);
	}
	
	
	@Test(priority=16)
	void testLoginwithValidInputs() throws IOException, InterruptedException {
		String[] data = login.getExcelDatatoArray(5, 5);
		Boolean status = login.loginwithValidInputs(data[0], data[1]);
		String ActualPassed = "As expected, user able to login an account";
		String ActualFailed = "Not expected, user not able to login an account";
		ReadFileUtils.setCellData(path, "Login", 5, 7, 
				status ? ActualPassed : ActualFailed);
		login.setExcelResult(status, 5);
	}
	

}
