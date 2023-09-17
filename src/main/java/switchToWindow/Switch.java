package switchToWindow;

import java.time.Duration; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Switch {

	public WebDriver driver;
	public String url = "https://www.hdfcbank.com/";
	
	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", "/home/ramkumarra/Documents/chromedriver_linux64/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
	}
	
	public void getPageInfo() {
		System.out.println("=====Window Info=====");
		System.out.println("The title is " + driver.getTitle());
		System.out.println("The URL of the webpage is " + driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	public void switchToWindow() throws Exception {
		
		WebElement finance = driver.findElement(By.xpath("//span[text()='HDB Financial Services']"));
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(finance));
		
		Actions action = new Actions(driver);
		action.moveToElement(finance).perform();
		finance.click();
		Thread.sleep(2000);
		
		driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		getPageInfo();
		
		WebElement persoanlLoan = driver.findElement(By.xpath("//a[text()=' Personal Loan']"));
		wait.until(ExpectedConditions.elementToBeClickable(persoanlLoan));
		
		action.moveToElement(persoanlLoan).perform();
		persoanlLoan.click();
		getPageInfo();
	}
		
	
	public void closeBrowser() throws Exception {
		Thread.sleep(5000);
		driver.close();
		driver.quit();
	}
	
	public static void main(String[] args) throws Exception {
		
		Switch switchTo = new Switch();
		switchTo.invokeBrowser();
		switchTo.getPageInfo();
		switchTo.switchToWindow();
		switchTo.closeBrowser();
		
	}

}
 