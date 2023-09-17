package ebay;

import java.util.List;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Ebay {
	
	public int n;
	WebDriver driver;

	public void invokeBrowser() throws Exception {
		System.out.println("Choose the Browser");
		System.out.println("1.Chrome");
		System.out.println("2.Firefox");
		int browserType = 1;
		
		switch (browserType) {
		case 1:
			System.out.println("User Input is " + 1 + ". So invoking Chrome Browser");
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			driver = new ChromeDriver();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			break;

		
		case 2:
			System.out.println("User Input is " + 2 + ". So invoking Firefox Browser");
			driver =  new FirefoxDriver();
			break;
		}
		driver.manage().window().maximize();
	}
	
	public void navigateToUrl() {
		driver.get("https://testtracker.googleplex.com/efforts/testplans/1593422");
	}
	
	public void getPageInfo() {
		String url = driver.getCurrentUrl();
		String title = driver.getTitle();
		System.out.println("The Current URL of the page is " + url);
		System.out.println("The title of the page is " + title);
	}
	
	public void searchProduct() {
		WebElement searchBox,search;
		searchBox = driver.findElement(By.xpath("//input[@class='gh-tb ui-autocomplete-input']"));
		searchBox.sendKeys("Iphone");
		search = driver.findElement(By.xpath("//input[@class='btn btn-prim gh-spr']"));
		search.click();	
	}
	
	public void demoGuruIndia() throws Exception{
		driver.get("https://amolujagare.com/sample");
		driver.manage().window().maximize();
		
		WebElement dropDown;
		dropDown=driver.findElement(By.xpath("//select[@id='seltext']"));
		Select select = new Select(dropDown);
		select.selectByVisibleText("text 4");
		List<WebElement> options = select.getOptions();
		for(WebElement x : options) {
			System.out.println(x.getText());
		}
		
		for(int i=0;i<options.size();i++) {
			String a = options.get(i).getText();
			if(a.equals("text 4")) {
				System.out.println("The index of text4 is " + i);
			}
		}
	}
	
	
	public void closeBrowser() {
		driver.close();
	}

	public static void main(String[] args) throws Exception {
		Ebay ebay = new Ebay();
		ebay.invokeBrowser();
		ebay.navigateToUrl();
		/*
		 * ebay.getPageInfo(); ebay.searchProduct(); ebay.demoGuruIndia();
		 * ebay.closeBrowser();
		 */
	}

}
