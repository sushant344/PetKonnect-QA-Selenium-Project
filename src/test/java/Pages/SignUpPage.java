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

public class SignUpPage {

	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";
	
	WebDriver driver;
	JavascriptExecutor js;
	String loginBtn = "//a[@class='header__button']";
	String registerBtn = "//a[normalize-space()='Create account']";
	String fnameInput = "//input[@id='RegisterForm-FirstName']";
	String lnameInput = "//input[@id='RegisterForm-LastName']";
	String emailInput = "//input[@id='RegisterForm-email']";
	String passwordInput = "//input[@id='RegisterForm-password']";
	String createBtn = "//button[@class='btn btn_zoom w-full-sp']";
	String logoutBtn = "//span[text()='Log out']";
	
//	constructor --
	public SignUpPage(WebDriver driver){
		this.driver = driver;
		this.js = (JavascriptExecutor)driver;
	}
	
	
//	set result in column as per test pass or fail--
	public void setExcelResult(Boolean status, int rowNum) throws IOException {
		String result = status ? "PASS" : "FAIL";
		ReadFileUtils.setCellData(path, "Sign Up", rowNum, 8, result);
		if(status) {
			ReadFileUtils.fillGreenColor(path, "Sign Up", rowNum, 8);
		}else {
			ReadFileUtils.fillRedColor(path, "Sign Up", rowNum, 8);
			Assert.fail();
		}
	}
	
	
//	get excel in form of array ---
	public String[] getExcelDatatoArray(int rowNum, int cellNum) throws IOException {
		String data = ReadFileUtils.getCellData(path, "Sign Up", rowNum, cellNum);
		String str = data.replaceAll("\n", " ");
		String[] arr = str.split(" ");
		return arr;
	}
	
//	Test functions ---
	public boolean gotoRegisterPage() throws InterruptedException {
		driver.findElement(By.xpath(loginBtn)).click();
		js.executeScript("window.scrollBy(0, 650)", "");
		Thread.sleep(2000);
		js.executeScript("arguments[0].click", driver.findElement(By.xpath(registerBtn)));
		driver.findElement(By.xpath(registerBtn)).click();
		
		Boolean status = false;
		if(driver.getTitle().equalsIgnoreCase("Create Account – PetKonnect")) {
			status = true;
		}
		return status;
	}
	
	
	
	public void registerAccount(String fname, String lname, String email, String password) throws InterruptedException {
		
		driver.findElement(By.xpath(loginBtn)).click();
		js.executeScript("window.scrollBy(0, 650)", "");
		Thread.sleep(2000);
		js.executeScript("arguments[0].click", driver.findElement(By.xpath(registerBtn)));
		driver.findElement(By.xpath(registerBtn)).click();
		
		js.executeScript("window.scrollBy(0, 450)", "");
		Thread.sleep(2000);
		
//		enter data in inputs and create --
		driver.findElement(By.xpath(fnameInput)).sendKeys(fname);
		driver.findElement(By.xpath(lnameInput)).sendKeys(lname);
		driver.findElement(By.xpath(emailInput)).sendKeys(email);
		driver.findElement(By.xpath(passwordInput)).sendKeys(password);
		Thread.sleep(3000);
		driver.findElement(By.xpath(createBtn)).click();
		
		Thread.sleep(6000);
//		go to login page --
		driver.findElement(By.xpath(loginBtn)).click();	
		
	}
	
	
	public boolean registerwithBlankInvalid(String fname, String lname, String email, String password) throws InterruptedException {
		Boolean status = false;
		
		registerAccount(fname, lname, email, password);
		try {			
			driver.findElement(By.xpath(logoutBtn));
			status = false;
		}catch(NoSuchElementException e) {			
			status = true;
		}
		
		return status;
	}
	
	
	public boolean registerwithValidInputs(String fname, String lname, String email, String password) throws InterruptedException {
		Boolean status = false;
		
		registerAccount(fname, lname, email, password);
		try {			
			driver.findElement(By.xpath(logoutBtn));
			driver.findElement(By.xpath(logoutBtn)).isDisplayed();
			status = true;
		}catch(NoSuchElementException e) {			
			status = false;
		}
		
		return status;
	}
	
	
	public void logoutAccount() {
		try {			
			WebElement logoutbutton = driver.findElement(By.xpath(logoutBtn));
			if(logoutbutton.isDisplayed()) {
				logoutbutton.click();
			}else {
				throw new SkipException("Error!! logout button not found");
			}
		}catch(Exception e) {			
			
		}
	}
	
	
	
}
