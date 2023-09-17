package flipkart;

import java.util.List;  

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FlipkartActions {
	
	public WebDriver driver;
	public int finalCount;
	public String url = "https://www.flipkart.com/";
	
	public void invokeBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	

	
	public void getAllLinks() throws Exception {
		
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		
		WebElement linkTotal = driver.findElement(By.xpath("//div[@class='_37M3Pb']"));
		List<WebElement> links = linkTotal.findElements(By.xpath(".//div[@class='eFQ30H']"));
		
		for(int i=0;i<links.size();i++) {
			String link = links.get(i).getText();
			System.out.println(link);
		}
	}
	
	public void getAllChildLinks() throws Exception {
		
		Actions action = new Actions(driver);
		
		WebElement seperateLink = driver.findElement(By.xpath("//div[@class='_37M3Pb']//div[@class='eFQ30H'][4]"));
		action.moveToElement(seperateLink).build().perform();
		
		Thread.sleep(5000);
		WebElement subLink = driver.findElement(By.xpath("//a[@class='_6WOcW9 _2-k99T']"));
		action.moveToElement(subLink).click().build().perform();
	}
	
	public void closeBrowser() throws Exception {
		Thread.sleep(5000);
		driver.close();
	}
	
	

	public static void main(String[] args) throws Exception {
		
		FlipkartActions flipkart = new FlipkartActions();
		flipkart.invokeBrowser();
		flipkart.getAllLinks();
		flipkart.getAllChildLinks();
		flipkart.closeBrowser();
		
		

	}

}
