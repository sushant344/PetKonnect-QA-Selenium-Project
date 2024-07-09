package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.SkipException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import AutomationUtils.ReadFileUtils;

public class Homepage {
	
	WebDriver driver;
	JavascriptExecutor js;
	String path = System.getProperty("user.dir")+"\\Test cases\\PetKonnect.xlsx";
	String logo = "//h2[@class='header__heading']//img[@alt='PetKonnect']";
	String searchInput = "//input[@id='Search-In-Modal']";
	String searchBtn = "//form[@class='search header__icons__only-space search--small']//button[@aria-label='Search']";
	String loginBtn = "//a[@class='header__button']";
	String logoutBtn = "//span[text()='Log out']";
	
	
	public Homepage(WebDriver driver){
		this.driver = driver;
		this.js = (JavascriptExecutor) driver;
	}
	
	
//	set result in column as per test pass or fail--
	public void setExcelResult(Boolean status, int rowNum) throws IOException {
		String result = status ? "PASS" : "FAIL";
		ReadFileUtils.setCellData(path, "Homepage", rowNum, 8, result);
		if(status) {
			ReadFileUtils.fillGreenColor(path, "Homepage", rowNum, 8);
		}else {
			ReadFileUtils.fillRedColor(path, "Homepage", rowNum, 8);
			Assert.fail();
		}
	}

	
//	Test functions --
	public void gotohomepage(String url) {
		driver.get(url);
	}
	
	
	public boolean logoVisibility() {
		WebElement logoElem = driver.findElement(By.xpath(logo));
		if(logoElem.isDisplayed() && 
				logoElem.getAttribute("src")!=null && 
				!logoElem.getAttribute("src").isEmpty()) {
			
			return true;
		}
		return false;
	}
	
	
	public boolean logoNavigation() {
		WebElement logoElem = driver.findElement(By.xpath(logo));
		logoElem.click();
		Boolean status = driver.getTitle().trim().equalsIgnoreCase("India's Best Pets Online Shopping Store-Petkonnect – PetKonnect")
				&& driver.getCurrentUrl().equalsIgnoreCase("https://www.petkonnect.in/");
		if(status) {
			return true;
		}
		return false;
		
	}
	
	
	public boolean imagesvisibility() throws IOException {
		List<WebElement> imgs = driver.findElements(By.tagName("img"));
		Boolean status = true;
		for(WebElement img : imgs) {
			if(!img.isDisplayed() ||
			    img.getAttribute("src") == null ||
				img.getAttribute("src").isEmpty() ||
				img.getAttribute("src").isBlank()){
					
				System.out.println("Hidden images: "+img.getAttribute("alt"));
					status = false;
					continue;
				}else {
					status = true;
				}
		}
		return status;
	}
	
	
	public boolean brokenLinks() {
		List<WebElement> alllinks = driver.findElements(By.tagName("a"));
		Boolean status = true;
		for(WebElement link: alllinks) {
			
			String hrefLink = link.getAttribute("href");
			
			if(hrefLink==null || hrefLink.isEmpty()) {
				System.out.println("href is empty");
				continue;
			}
			
			try {
				URL url = new URL(hrefLink);
				HttpURLConnection connectURL = (HttpURLConnection)url.openConnection();
				
				connectURL.connect();
				
				if(connectURL.getResponseCode() >=400) {
					System.out.println("Broken Link: "+link.getText());
					status = false;
				}else {
					status = true;
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		return status;
	}
	
	
	public boolean validKeysSearchFunc() {
		Boolean status = true;
		String[] searchKeys = {"cat food", "dog food"};
		
		for(String keys:searchKeys) {
			driver.findElement(By.xpath(searchInput)).sendKeys(keys);
			driver.findElement(By.xpath(searchBtn)).click();
			
			List<WebElement> listProducts = driver.findElements(By.xpath("//div[@id='product-grid']/div"));
			status = listProducts.size() > 0 ? true : false;
			
		}
		return status;
	}
	
	
	public boolean invalidKeysSearchFunc() {
		Boolean status = true;
		String[] searchKeys = {"invalidkeys", "samsung"};
		
		for(String keys:searchKeys) {
			driver.findElement(By.xpath(searchInput)).sendKeys(keys);
			driver.findElement(By.xpath(searchBtn)).click();
			
			List<WebElement> listProducts = driver.findElements(By.xpath("//div[@id='product-grid']/div"));
			status = listProducts.size() == 0 ? true : false;
			
		}
		return status;
	}
	
	public boolean blankKeysSearchFunc() {
		Boolean status = true;
		
		driver.findElement(By.xpath(searchInput)).sendKeys("");
		driver.findElement(By.xpath(searchBtn)).click();
			
		List<WebElement> listProducts = driver.findElements(By.xpath("//div[@id='product-grid']/div"));
		status = listProducts.size() == 0 ? true : false;
			
		return status;
	}
	
	
	
	
	
	
	
	
}
