package ExecutionTestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import AutomationUtils.ReadFileUtils;
import Pages.ProductsPage;

public class Products {

	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";

	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	ProductsPage products = new ProductsPage(driver);
	
	
	@Test(priority=17)
	void testnavigationproducts() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.petkonnect.in/");
		driver.manage().window().maximize();
		Boolean status = products.allProductsNavigation();
		String ActualPassed = "As expected, user able to navigate all products page";
		String ActualFailed = "Not expected, unable to navigate all products page";
		ReadFileUtils.setCellData(path, "Products", 2, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 2);
	}
	
	@Test(priority=18)
	void testfilterfromPrice() throws InterruptedException, IOException {
		Boolean status = products.filterpriceinput("420", "");
		String ActualPassed = "As expected, products are filtered as per input prices";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 3, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 3);
	}
	
	
	@Test(priority=19)
	void testfiltertoPrice() throws InterruptedException, IOException {
		Boolean status = products.filterpriceinput("", "1000");
		String ActualPassed = "As expected, products are filtered as per input prices";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 4, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 4);
	}
	
	
	@Test(priority=20)
	void testfilterfromtoPrice() throws InterruptedException, IOException {
		Boolean status = products.filterpriceinput("420", "1000");
		String ActualPassed = "As expected, products are filtered as per input prices";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 5, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 5);
		driver.findElement(By.
				xpath("//div[@class='active-facets active-facets-desktop']//span[contains(text(),'Clear all')]")).click();
		Thread.sleep(2000);
		
	}
	
	
	@Test(priority=21)
	void testfilterProductType() throws InterruptedException, IOException {
//		scroll to the filter element --
		js.executeScript("arguments[0].scrollIntoView()", 
				driver.findElement(By.xpath("//span[@class='h5'][normalize-space()='Product type']")));
		Thread.sleep(2000);
		
//		get all show all button and click on specific ---
		List<WebElement> showAll = driver.findElements(By
				.xpath("//li[@class='facets__item facets__button-show-all']//show-all-facets"));
		showAll.get(0).click();
		Thread.sleep(1000);
		
		Boolean status = products.filterProductTypeinput(6, "filter.p.product_type");
		String ActualPassed = "As expected, products are filtered";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 6, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 6);
	}
	
	
	
	@Test(priority=22)
	void testfilterMoreFilters() throws InterruptedException, IOException {
//		scroll to the filter element --
		js.executeScript("arguments[0].scrollIntoView()", 
				driver.findElement(By.xpath("//span[@class='h5'][normalize-space()='More filters']")));
		Thread.sleep(2000);
		
		Boolean status = products.filterProductTypeinput(7, "filter.p.tag");
		String ActualPassed = "As expected, products are filtered";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 7, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 7);
	}
	
	
	
	@Test(priority=23)
	void testfilterColor() throws InterruptedException, IOException {
//		scroll to the filter element --
		js.executeScript("arguments[0].scrollIntoView()", 
				driver.findElement(By.
						xpath("//span[@class='h5'][normalize-space()='Color']")));
		Thread.sleep(2000);
		
//		get all show all button and click on specific ---
		List<WebElement> showAll = driver.findElements(By
				.xpath("//li[@class='facets__item facets__button-show-all']//show-all-facets"));
		showAll.get(1).click();
		Thread.sleep(1000);
		
		Boolean status = products.filterProductTypeinput(8, "filter.v.option.color");
		String ActualPassed = "As expected, products are filtered";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 8, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 8);
		
	}
	
	
	
	@Test(priority=24)
	void testfilterSize() throws InterruptedException, IOException {
//		scroll to the filter element --
		js.executeScript("arguments[0].scrollIntoView()", 
				driver.findElement(By.
						xpath("//span[@class='h5'][normalize-space()='Size']")));
		Thread.sleep(2000);
		
//		get all show all button and click on specific ---
		List<WebElement> showAll = driver.findElements(By
				.xpath("//li[@class='facets__item facets__button-show-all']//show-all-facets"));
		showAll.get(2).click();
		Thread.sleep(2000);
		
		Boolean status = products.filterProductTypeinput(9, "filter.v.option.size");
		String ActualPassed = "As expected, products are filtered";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 9, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 9);
		
	}
	
	
	
	@Test(priority=25)
	void testfilterBrand() throws InterruptedException, IOException {
//		scroll to the filter element --
		js.executeScript("arguments[0].scrollIntoView()", 
				driver.findElement(By.
						xpath("//span[@class='h5'][normalize-space()='Brand']")));
		Thread.sleep(2000);
		
//		get all show all button and click on specific ---
		List<WebElement> showAll = driver.findElements(By
				.xpath("//li[@class='facets__item facets__button-show-all']//show-all-facets"));
		showAll.get(3).click();
		Thread.sleep(2000);
		
		Boolean status = products.filterProductTypeinput(10, "filter.p.vendor");
		String ActualPassed = "As expected, products are filtered";
		String ActualFailed = "Not expected, unable to filter products";
		ReadFileUtils.setCellData(path, "Products", 10, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 10);
		
	}
	
	
	@Test(priority=26)
	void testClearAll() throws InterruptedException, IOException {
		Boolean status = products.clearAllfunc();
		String ActualPassed = "As expected, user able to clear filtered products";
		String ActualFailed = "Not expected, unable to clear products";
		ReadFileUtils.setCellData(path, "Products", 11, 7, 
				status ? ActualPassed : ActualFailed);
		products.setExcelResult(status, 11);
	}
	
	
	
	
}
