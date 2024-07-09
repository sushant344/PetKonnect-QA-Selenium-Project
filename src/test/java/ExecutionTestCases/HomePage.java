package ExecutionTestCases;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Pages.*;
import AutomationUtils.ReadFileUtils;

public class HomePage {
	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";
	
	WebDriver driver = new ChromeDriver();
	Homepage home = new Homepage(driver);
	
	
	@BeforeClass
	void getHomepage() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String homelink = ReadFileUtils.getCellData(path, "Homepage", 2, 5);
		home.gotohomepage(homelink);
		driver.manage().window().maximize();
	}
	
//	homepage navigation --
	@Test(priority=1)
	void testnavigationOfHomepage() throws IOException {
		Boolean status = driver.getTitle().trim().equalsIgnoreCase("India's Best Pets Online Shopping Store-Petkonnect – PetKonnect");
		String ActualPassed = "As expected, able to navigate homepage";
		String ActualFailed = "Not expected, unable to navigate homepage";
		ReadFileUtils.setCellData(path, "Homepage", 2, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 2);
	}
	
//	logo visibility --
	@Test(priority=2)
	void testLogo() throws IOException {
		Boolean status = home.logoVisibility();
		String ActualPassed = "As expected, Logo is visible";
		String ActualFailed = "Not expected, Logo is not visible";
		ReadFileUtils.setCellData(path, "Homepage", 3, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 3);
	}
	
//	logo navigation --
	@Test(priority=3)
	void testLogoNavigation() throws IOException {
		Boolean status = home.logoNavigation();
		String ActualPassed = "As expected, able to navigate homepage";
		String ActualFailed = "Not expected, unable to navigate homepage";
		ReadFileUtils.setCellData(path, "Homepage", 4, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 4);
	}
	
//	all images visibility --
	@Test(priority=4)
	void testImagesVisibility() throws IOException {
		Boolean status = home.imagesvisibility();
		String ActualPassed = "As expected, all images are visible";
		String ActualFailed = "Not expected, all images are not visible";
		ReadFileUtils.setCellData(path, "Homepage", 5, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 5);
	}
	
//	all links connections --
	@Test(priority=5)
	void testLinksConnectivity() throws IOException {
		Boolean status = home.brokenLinks();
		System.out.println(status ? "All links are valid" : "");
		String ActualPassed = "As expected, all links are valid";
		String ActualFailed = "Not expected, all links are not valid";
		ReadFileUtils.setCellData(path, "Homepage", 6, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 6);
	}
	
	
	@Test(priority=6)
	void testValidKeysSearchInput() throws IOException {
		Boolean status = home.validKeysSearchFunc();
		String ActualPassed = "As expected, user able to search valid products";
		String ActualFailed = "Not expected, user not able to search";
		ReadFileUtils.setCellData(path, "Homepage", 7, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 7);
	}
	
	
	@Test(priority=7)
	void testInvalidKeysSearchInput() throws IOException {
		Boolean status = home.invalidKeysSearchFunc();
		String ActualPassed = "As expected, product are showing by invalid keys";
		String ActualFailed = "Not expected, by entering invalid keys user able to see products";
		ReadFileUtils.setCellData(path, "Homepage", 8, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 8);
	}
	
	
	@Test(priority=8)
	void testBlankKeysSearchInput() throws IOException {
		Boolean status = home.blankKeysSearchFunc();
		String ActualPassed = "As expected, not able to see products";
		String ActualFailed = "Not expected, user able to see products";
		ReadFileUtils.setCellData(path, "Homepage", 9, 7, 
				status ? ActualPassed : ActualFailed);
		home.setExcelResult(status, 9);
	}
	
	
	@AfterClass
	void close() throws InterruptedException {
//		home.logoutAccount();
		driver.quit();
	}
	
	
}
