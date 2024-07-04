package AutomationUtils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.petkonnect.in/account/login");
		
		driver.findElement(By.xpath("//input[@id='CustomerEmail']")).sendKeys("abc@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='CustomerPassword']")).sendKeys("test@123");
		
		js.executeScript("window.scrollBy(0, 200)");
		Thread.sleep(2000);
		
		WebElement loginBtn = driver.findElement(By.xpath("//button[@class='btn btn_zoom w-full-sp']//span[text()='Sign in']"));
		js.executeScript("arguments[0].click();", loginBtn);
		
		WebElement logoutBtn = driver.findElement(By.xpath("//span[normalize-space()='Log out']"));
		if(logoutBtn.isDisplayed()){
			System.out.println("Login successfull");
			Thread.sleep(2000);
			logoutBtn.click();
			
			if(driver.getCurrentUrl().equalsIgnoreCase("https://www.petkonnect.in/")) {
				System.out.println("Logout successful");
			}else {
				System.out.println("Logout failed");
			}
			
		}else {
			System.out.println("Login failed");
		}
		
		Thread.sleep(5000);
		driver.quit();
		
		
		String path = System.getProperty("user.dir")+"\\testdata\\data.xlsx";
		int rc = ReadFileUtils.getRowCount(path, "Sheet1");
		System.out.println(rc);
		int cc = ReadFileUtils.getCellCount(path, "Sheet1", 0);
		System.out.println(cc);
		String cd = ReadFileUtils.getCellData(path, "Sheet1", 1, 0);
		System.out.println(cd);
		
		ReadFileUtils.setCellData(path, "Sheet1", 4, 4, "FAIL");
		ReadFileUtils.fillRedColor(path, "Sheet1", 4, 4);
		
		
	}

}
