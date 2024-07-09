package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationUtils.ReadFileUtils;

public class ProductsPage {

	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait mywait;
	String shopAll = "//ul[@class='unstyle-ul tiles-menu']//li[1]";
	String fromPriceInput = "//input[@id='Filter-Price-GTE']";
	String toPriceInput = "//input[@id='Filter-Price-LTE']";
	String clearAllBtn = "//div[@class='active-facets active-facets-desktop']//span[contains(text(),'Clear all')]";
	
	
//	constructor --
	public ProductsPage(WebDriver driver){
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
		this.mywait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	
//	set result in column as per test pass or fail--
	public void setExcelResult(Boolean status, int rowNum) throws IOException {
		String result = status ? "PASS" : "FAIL";
		ReadFileUtils.setCellData(path, "Products", rowNum, 8, result);
		if(status) {
			ReadFileUtils.fillGreenColor(path, "Products", rowNum, 8);
		}else {
			ReadFileUtils.fillRedColor(path, "Products", rowNum, 8);
			Assert.fail();
		}
	}
	
	
//	get total products text data --
	public String getTotalNumofProducts() {
		String data = driver.findElement(By.id("ProductCountDesktop")).getText();
		return data.split(" ")[0];
	}
	
	
//	get excel in form of array ---
	public String[] getExcelDatatoArray(int rowNum, int cellNum) throws IOException {
		String data = ReadFileUtils.getCellData(path, "Products", rowNum, cellNum);
		String str = data.replaceAll("\n", "@");
		String[] arr = str.split("@");
		return arr;
	}
	
	
	
//	*Test functions --
	public boolean allProductsNavigation() {
		driver.findElement(By.xpath(shopAll)).click();
		
		Boolean status = false;
		if(driver.getTitle().equalsIgnoreCase("Shop All – PetKonnect")) {
			status = true;
		}else {
			status = false;
		}
		return status;
	}
	
	
//	test filter prices --
	public boolean filterpriceinput(String fromPrice, String toPrice) throws InterruptedException {
		String totalProducts = getTotalNumofProducts();
		
		driver.findElement(By.xpath(fromPriceInput)).clear();
		driver.findElement(By.xpath(toPriceInput)).clear();

		driver.findElement(By.xpath(fromPriceInput)).sendKeys(fromPrice);
		driver.findElement(By.xpath(toPriceInput)).sendKeys(toPrice);
		Thread.sleep(3000);
		
		String filterProducts = getTotalNumofProducts();
		
//		after applying filters if total products is changed then result is passed -
		Boolean status = true;
		if(totalProducts.equalsIgnoreCase(filterProducts)) {
			status = false;
		}else {
			status = true;
		}
		return status;
	}
	
	
//	test filter product types --
	public boolean filterProductTypeinput(int rowNum, String filterName) throws InterruptedException, IOException {
		String totalProducts = getTotalNumofProducts();
		
//		get list of all filter inputs and click on chosen --
		List<WebElement> ptypes = driver.
				findElements(By.xpath("//ul[@class='unstyle-ul']//input[@name='"+filterName+"']/following-sibling::span[1]"));
		
//		get want to select filters from excel and click --
		String chooseOptions[] = getExcelDatatoArray(rowNum, 5);
//		System.out.println(Arrays.toString(chooseOptions));
		for(int i=0; i<ptypes.size(); i++) {
			for(int j=0; j<chooseOptions.length; j++) {
				if(ptypes.get(i).getText().equalsIgnoreCase(chooseOptions[j])) {
					if(!ptypes.get(i).isSelected()) {						
						ptypes.get(i).click();
					}
				}
			}
		}
//		Thread.sleep(10000);
		
		js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		Thread.sleep(3000);
		WebElement clearallbutton = mywait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath(clearAllBtn)));
		
		String filterProducts = getTotalNumofProducts();
		
//		go to top of page and click clear all --
		clearallbutton.click();
		Thread.sleep(3000);
		
//		after applying filters if total products is changed then result is passed -
		Boolean status = true;
		if(totalProducts.equalsIgnoreCase(filterProducts)) {
			status = false;
		}else {
			status = true;
		}
		System.out.println(status);
		return status;
	}
	
	
	
	public boolean clearAllfunc() throws InterruptedException {
		String totalProducts = getTotalNumofProducts();
		
		driver.findElement(By.xpath(fromPriceInput)).clear();
		driver.findElement(By.xpath(toPriceInput)).clear();

		driver.findElement(By.xpath(fromPriceInput)).sendKeys("375");
		driver.findElement(By.xpath(toPriceInput)).sendKeys("1000");
		Thread.sleep(3000);
		
		js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		WebElement clearallbutton = mywait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath(clearAllBtn)));
		clearallbutton.click();
		
		String clearProducts = getTotalNumofProducts();
		
		Boolean status = true;
		if(totalProducts.equalsIgnoreCase(clearProducts)) {
			status = true;
		}else {
			status = false;
		}
		return status;
	}
	
	
	
	
	
	
}
