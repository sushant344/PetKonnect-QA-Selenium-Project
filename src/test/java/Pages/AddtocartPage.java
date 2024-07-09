package Pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationUtils.ReadFileUtils;

public class AddtocartPage {

	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";
	
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait mywait;
	String searchInput = "//input[@id='Search-In-Modal']";
	String searchBtn = "//form[@class='search header__icons__only-space search--small']//button[@aria-label='Search']";
	String addtocartBtn = "//button[@class='product-form__submit btn btn_zoom w-full']";
	String checkoutBtn = "//button[@id='CartDrawer-Checkout']";
	
//	constructor --
	public AddtocartPage(WebDriver driver){
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
		this.mywait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	
//	set result in column as per test pass or fail--
	public void setExcelResult(Boolean status, int rowNum) throws IOException {
		String result = status ? "PASS" : "FAIL";
		ReadFileUtils.setCellData(path, "Add to cart", rowNum, 8, result);
		if(status) {
			ReadFileUtils.fillGreenColor(path, "Add to cart", rowNum, 8);
		}else {
			ReadFileUtils.fillRedColor(path, "Add to cart", rowNum, 8);
			Assert.fail();
		}
	}
	
	
//	*Test functions --
	public boolean selectProduct() throws InterruptedException {
		driver.findElement(By.xpath(searchInput)).sendKeys("pedigree");
		driver.findElement(By.xpath(searchBtn)).click();
		List<WebElement> productList = driver.findElements(By
				.xpath("//a[@class='product-card__heading']"));
		js.executeScript("arguments[0].scrollIntoView()", productList.get(5));
		Thread.sleep(2000);
		js.executeScript("arguments[0].click()", productList.get(5));
		
		Boolean status = false;
		if(!driver.getTitle().isBlank()) {
			status = true;
		}else {
			status = false;
		}
		return status;
	}
	
	
//	add to cart and verify --
	public boolean addproductincart() throws InterruptedException {
//		get text of selected product --
		String prodName = driver.findElement(By.tagName("h1")).getText();
		
//		set quantity --
		WebElement plusBtn = driver.findElement(By.xpath("//button[@name='plus']"));
		js.executeScript("window.scrollBy(0, 350)");
		Thread.sleep(2000);
		for(int i=1; i<3; i++) {
			plusBtn.click();
		}
		
//		add to cart --
		driver.findElement(By.xpath(addtocartBtn)).click();
		Thread.sleep(2000);
		
//		verify added cart product details --
		WebElement cartItem = driver.findElement(By
				.xpath("//div[@class='drawer__cart-item']"));
		
		String cartProdName = driver.findElement(By
				.xpath("//div[@class='drawer__cart-item__details__items body3']//a")).getText();
		Boolean status = false;
		if(driver.findElement(By
				.xpath("//drawer-inner[@aria-label='Your cart']")).isDisplayed()) {
			
			
			if(cartItem.isDisplayed() && cartProdName.equalsIgnoreCase(prodName)) {
				status = true;
			}else {
				status = false;
			}
		}else {
			js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@id='cart-icon-bubble']")).click();
			if(cartItem.isDisplayed() && cartProdName.equalsIgnoreCase(prodName)) {
				status = true;
			}else {
				status = false;
			}
		}
		
		return status;
	}
	
	
	
	public boolean checkOutProduct() throws InterruptedException {
		driver.findElement(By.xpath(checkoutBtn)).click();
		
		
//		enter details in address fields ---
		driver.findElement(By.xpath("//input[@id='email']"))
		.sendKeys("abc@gmail.com");
		WebElement selectCountry = driver.findElement(By.xpath("//select[@id='Select0']"));
		Select country = new Select(selectCountry);
		country.selectByVisibleText("India");
		driver.findElement(By.xpath("//input[@id='TextField0']")).sendKeys("Test");
		driver.findElement(By.xpath("//input[@id='TextField1']")).sendKeys("User");
		driver.findElement(By.xpath("//input[@id='TextField2']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@id='TextField4']")).sendKeys("Mumbai");
		WebElement selectState = driver.findElement(By.xpath("//select[@id='Select1']"));
		Select state = new Select(selectState);
		state.selectByVisibleText("Maharashtra");
		driver.findElement(By.xpath("//input[@id='TextField5']")).sendKeys("400016");
		driver.findElement(By.xpath("//input[@id='TextField6']")).sendKeys("1234567890");
		
//		click on check out button --
		js.executeScript("window.scrollBy(0, document.body.scollHeight)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='checkout-pay-button']")).click();
		
		
//		wait until razorpay page is visible --
		mywait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath("//div[@class='razorpay-container']")));
		System.out.println(driver.getTitle());  //Payment Page · Razorpay
		
//		verify razorpay title --
		Boolean status = false;
		if(driver.getTitle().equalsIgnoreCase("Payment Page · Razorpay")) {
			status = true;
		}else {
			status = false;
		}
		
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[@data-testid='checkout-close']")).click();
		driver.findElement(By.xpath("//button[@data-test-id='confirm-positive']")).click();
		
		return status;
		
	}
	
	
//	remove product from cart --
	public boolean removeProductcart() throws InterruptedException {
		Boolean status = false;
		driver.findElement(By.xpath("//a[@id='cart-link']")).click();
		mywait.until(ExpectedConditions.
				visibilityOfElementLocated(By.xpath("//div[@id='main-cart-items']")));
		if(driver.findElement(By.xpath("//div[@id='main-cart-items']")).isDisplayed()) {
			
			WebElement removeBtn = driver.findElement(By.xpath("//a[@class='cart-remove-button']"));
			js.executeScript("arguments[0].scrollIntoView()", removeBtn);
			Thread.sleep(2000);
			removeBtn.click();
			Thread.sleep(3000);
			
			try {
				driver.findElement(By.xpath("//tr[@class='cart-item']"));				
				status = false;
			}catch(NoSuchElementException e) {
				status = true;
			}
				
		}else {
			System.out.println("Error!!cart is empty");
		}
		
		return status;
	}
	
	
	
	
	
	
	
	
}
