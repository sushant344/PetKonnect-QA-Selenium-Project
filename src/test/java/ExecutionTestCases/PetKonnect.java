package ExecutionTestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import AutomationUtils.ReadFileUtils;
import Pages.AddtocartPage;
import Pages.Homepage;
import Pages.LoginPage;
import Pages.ProductsPage;
import Pages.SignUpPage;

public class PetKonnect {

	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";

	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	Homepage home = new Homepage(driver);
	SignUpPage sign = new SignUpPage(driver);
	LoginPage login = new LoginPage(driver);
	ProductsPage products = new ProductsPage(driver);
	AddtocartPage cart = new AddtocartPage(driver);
	
	
	@BeforeClass
	void getHomepage() throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String homelink = ReadFileUtils.getCellData(path, "Homepage", 2, 5);
		home.gotohomepage(homelink);
		driver.manage().window().maximize();
	}
	
	
//	Test executions --
	
//	Homepage --
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
	
	
//	Sign up --
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
	
	
//	Log in ---
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
	
	
	
//	Products page --
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
	
	
//	Add to cart ---
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
	
	
	
	
	
	@AfterClass
	void close() throws InterruptedException, IOException {
		Boolean status = login.logoutAccount();
		String ActualPassed = "As expected, user able remove logout accout";
		String ActualFailed = "Not expected, unable to logout";
		ReadFileUtils.setCellData(path, "Login", 6, 7, 
				status ? ActualPassed : ActualFailed);
		login.setExcelResult(status, 6);
		driver.quit();
	}
	
}
