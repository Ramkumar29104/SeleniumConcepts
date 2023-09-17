package seleniumWebTable;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public WebDriver driver;
	public int finalCount;
	public String url = "https://www.selenium.dev/ecosystem/";
	
	public void invokeBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void webTableElements() throws Exception {
		List<WebElement> headElements = driver.findElements(By.xpath("//div[@class=\"row justify-content-center p-5\"]//thead"));
		for(WebElement x : headElements) {
			System.out.println(x.getText());
		}
		System.out.println("=================================");
		
		
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class=\"row justify-content-center p-5\"]//tr"));
		for(int i=1;i<rows.size();i++) {
			try {
				driver.findElement(By.xpath("//div[@class='row justify-content-center p-5']//tr["+i+"]//td"));
				for(int j=1;j<=2;j++) {
					WebElement element = rows.get(i).findElement(By.xpath("//div[@class='row justify-content-center p-5']//tr["+i+"]//td["+j+"]"));		
					System.out.println(element.getText());
				}
				System.out.println("");
		
			}
			catch (Exception e) {
				
			}
		}
	}
	
	public void closeBrowser() throws Exception {
		Thread.sleep(5000);
		driver.close();
	}
	
	public static void main(String[] args) throws Exception {
		
		WebTable table = new WebTable();
		table.invokeBrowser();
		table.webTableElements();
		table.closeBrowser();
	}

}
