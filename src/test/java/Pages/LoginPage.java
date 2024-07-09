package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;

import AutomationUtils.ReadFileUtils;

public class LoginPage {

	
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";
	
	WebDriver driver;
	JavascriptExecutor js;
	String loginBtn = "//a[@class='header__button']";
	String emailInput = "//input[@id='CustomerEmail']";
	String passwordInput = "//input[@id='CustomerPassword']";
	String signinBtn = "//form[@id='customer_login']//button[@class='btn btn_zoom w-full-sp']";
	String logoutBtn = "//span[text()='Log out']";
	
	
	
//	constructor --
	public LoginPage(WebDriver driver){
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
	}
	
	
//	set result in column as per test pass or fail--
	public void setExcelResult(Boolean status, int rowNum) throws IOException {
		String result = status ? "PASS" : "FAIL";
		ReadFileUtils.setCellData(path, "Login", rowNum, 8, result);
		if(status) {
			ReadFileUtils.fillGreenColor(path, "Login", rowNum, 8);
		}else {
			ReadFileUtils.fillRedColor(path, "Login", rowNum, 8);
			Assert.fail();
		}
	}
	
	
//	get excel in form of array ---
	public String[] getExcelDatatoArray(int rowNum, int cellNum) throws IOException {
		String data = ReadFileUtils.getCellData(path, "Login", rowNum, cellNum);
		String str = data.replaceAll("\n", " ");
		String[] arr = str.split(" ");
		return arr;
	}
	
	
	
//	*Test functions --
	public boolean loginNavigation() {
		driver.findElement(By.xpath(loginBtn)).click();
		
		Boolean status = false;
		if(driver.getTitle().equalsIgnoreCase("Account – PetKonnect")) {
			status = true;
		}else {
			status = false;
		}
		return status;
	}
	
	
	
//	login with valid inputs ---
	public void loginAccount(String email, String password) throws InterruptedException {
		
		driver.findElement(By.xpath(loginBtn)).click();
		js.executeScript("window.scrollBy(0, 450)", "");
		Thread.sleep(2000);
		
//		enter data in inputs and login --
		driver.findElement(By.xpath(emailInput)).sendKeys(email);
		driver.findElement(By.xpath(passwordInput)).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(By.xpath(signinBtn)).click();
		
		Thread.sleep(6000);
//		go to login page --
		driver.findElement(By.xpath(loginBtn)).click();	
		
	}
	
	
//	test with blank or invalid inputs ---
	public boolean loginwithBlankInvalid(String email, String password) throws InterruptedException {
		Boolean status = false;
		
		loginAccount(email, password);
		try {			
			driver.findElement(By.xpath(logoutBtn));
			status = false;
		}catch(NoSuchElementException e) {			
			status = true;
		}
		
		return status;
	}
	
	
//	test with valid inputs ---	
	public boolean loginwithValidInputs(String email, String password) throws InterruptedException {
		Boolean status = false;
		
		loginAccount(email, password);
		try {			
			driver.findElement(By.xpath(logoutBtn));
			driver.findElement(By.xpath(logoutBtn)).isDisplayed();
			status = true;
		}catch(NoSuchElementException e) {			
			status = false;
		}
		
		return status;
	}
	
	
//	logout --
	public boolean logoutAccount() throws InterruptedException {
		Boolean status = false;
		js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		Thread.sleep(2000);
		driver.findElement(By.xpath(loginBtn)).click();
		try {			
			WebElement logoutbutton = driver.findElement(By.xpath(logoutBtn));
			if(logoutbutton.isDisplayed()) {
				logoutbutton.click();
				status = true;
			}else {
				status = false;
			}
		}catch(Exception e) {			
			System.out.println(e.getMessage());
			status = false;
		}
		return status;
	}
	
	
	
}
