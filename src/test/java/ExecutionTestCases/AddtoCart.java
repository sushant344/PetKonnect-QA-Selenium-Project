package ExecutionTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import AutomationUtils.ReadFileUtils;
import Pages.AddtocartPage;

public class AddtoCart {

	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";

	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	AddtocartPage cart = new AddtocartPage(driver);
	
	
	@Test(priority=27)
	void testSelectProduct() throws InterruptedException, IOException {

		Boolean status = cart.selectProduct();
		String ActualPassed = "As expected, user able to navigate product page";
		String ActualFailed = "Not expected, unable to navigate product page";
		ReadFileUtils.setCellData(path, "Add to cart", 2, 7, 
				status ? ActualPassed : ActualFailed);
		cart.setExcelResult(status, 2);
	}
	
	
	@Test(priority=28)
	void testAddproductinCart() throws InterruptedException, IOException {
		Boolean status = cart.addproductincart();
		String ActualPassed = "As expected, user able to add product in cart";
		String ActualFailed = "Not expected, unable to add product in cart";
		ReadFileUtils.setCellData(path, "Add to cart", 3, 7, 
				status ? ActualPassed : ActualFailed);
		cart.setExcelResult(status, 3);
	}
	
	
	@Test(priority=29, dependsOnMethods= {"testAddproductinCart"})
	void testCheckoutProduct() throws InterruptedException, IOException {
		Boolean status = cart.checkOutProduct();
		String ActualPassed = "As expected, user able to checkout the product";
		String ActualFailed = "Not expected, unable to checkout the product";
		ReadFileUtils.setCellData(path, "Add to cart", 4, 7, 
				status ? ActualPassed : ActualFailed);
		cart.setExcelResult(status, 4);
	}
	
	
	
	@Test(priority=30, dependsOnMethods= {"testCheckoutProduct"})
	void testRemoveProduct() throws InterruptedException, IOException {
		Boolean status = cart.removeProductcart();
		String ActualPassed = "As expected, user able remove product";
		String ActualFailed = "Not expected, unable to remove product";
		ReadFileUtils.setCellData(path, "Add to cart", 5, 7, 
				status ? ActualPassed : ActualFailed);
		cart.setExcelResult(status, 5);
	}
	
	
	
}
