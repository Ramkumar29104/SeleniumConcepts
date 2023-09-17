package frame;

import java.time.Duration; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleniumFrame {

	public WebDriver driver;
	public String url = "https://www.selenium.dev/selenium/docs/api/java/index.html?overview.summary.html";
	
	public void invokeBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void getInfo() {
		System.out.println(driver.getCurrentUrl());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	public void frameMethod() {
		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@name='packageListFrame']"));
		driver.switchTo().frame(frame1);
		
		WebElement element1 = driver.findElement(By.xpath("//a[text()='org.openqa.selenium']"));
		element1.click();
		
		driver.switchTo().parentFrame();
		
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@name='packageFrame']"));
		driver.switchTo().frame(frame2);
		
		WebElement element2 = driver.findElement(By.xpath("//span[text()='Alert']"));
		element2.click();
		
		driver.switchTo().parentFrame();
	}
	
	public void closeBrowser() throws Exception {
		Thread.sleep(5000);
		driver.close();
	}
	
	
	
	public static void main(String[] args) throws Exception {
		
		seleniumFrame frameAction = new seleniumFrame();
		frameAction.invokeBrowser();
		frameAction.frameMethod();
		frameAction.closeBrowser();

	}

}
