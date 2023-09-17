package ebay;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class EbayProductList {
	
	public WebDriver driver;
	public String url = "https://www.ebay.com/";
	String product = "Iphone";
	String category = "Cell Phones & Accessories";
	
	public void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		driver = new ChromeDriver();
		
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--remote-allow-origins=*");
		 */
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void getInfo() {
		String title = driver.getTitle();
		String url = driver.getCurrentUrl();
		
		String details[]= new String[5];
		details[0]=url;
		details[1]=title;
		
		for(int i=0;i<details.length;i++) {
			if(details[i]!=null) {
				System.out.println(details[i]);
			}
			else
				continue;
		}
	}
	
	public void searchProduct() {
		WebElement search, searchBtn, elementCategory;
		
		search = driver.findElement(By.xpath("//input[@class='gh-tb ui-autocomplete-input']"));
		search.sendKeys(product);
		
		searchBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		searchBtn.click();
		
		elementCategory = driver.findElement(By.xpath("//select[@aria-label='Select a category for search']"));
		Select select = new Select(elementCategory);
		select.selectByVisibleText(category);
		
		search = driver.findElement(By.xpath("//input[@class='gh-tb ui-autocomplete-input']"));
		search.clear();
	}
	
	public void numberOfresult() {
		
		String results = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']")).getText();
		
		String[] split1= results.split(" ");
		String split2 = split1[0].replace("+","").replace(",","");
		
		int result = Integer.parseInt(split2);
		System.out.println("Total number of results is " + result);
	}
	
	public void searchProductNamesAndPrices() {
		WebElement element;
		List<WebElement> results = driver.findElements(By.xpath("//div[@class='srp-river-results clearfix']//li[@class='s-item s-item__pl-on-bottom']"));
		
		
		
		for(int i=1;i<results.size();i++) {
			element = results.get(i);
			
			int x = element.getRect().getX();
			int y = element.getRect().getY();
			JavascriptExecutor exe = (JavascriptExecutor)driver;
			String cmd = String.format("window.scrollTo(%d,%d)",x,y);
			exe.executeScript(cmd);
			
			String phonename = element.findElement(By.xpath(".//span[@role='heading']")).getText();
			String[] phonename1 = phonename.split(" ");
			String mobile = phonename1[0].concat(" ").concat(phonename1[1]).concat(" ").concat(phonename1[2]);
			String rate = element.findElement(By.xpath(".//span[@class='s-item__price']")).getText();
			System.out.println("The name is " + mobile + " and the rate is " + rate);			
		}
		
	}

	public static void main(String[] args) {
		
		EbayProductList ebay = new EbayProductList();
		ebay.invokeBrowser();
		ebay.getInfo();
		ebay.searchProduct();
		ebay.numberOfresult();
		ebay.searchProductNamesAndPrices();
	}

}
