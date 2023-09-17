package amazon;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class AmazonProducts {

	public WebDriver driver;
	public int finalCount;
	public String url = "https://www.amazon.in/";
	
	public void invokeBrowser() {
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
		driver.get(url);
	}
	
	public void searchProduct(String product) throws Exception {
		

		WebElement search, searchButton, category;

		search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		search.sendKeys(product);

		searchButton = driver.findElement(By.xpath("//input[@type='submit']"));
		searchButton.click();

		category = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		Select select = new Select(category);
		select.selectByVisibleText("Electronics");

		search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		search.clear();
	}

	public String searchResultWhole() throws Exception {

		WebElement wholeResult;
		wholeResult = driver
				.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[1]"));
		String result = wholeResult.getText();
		return result;
	}

	public void resultCount() throws Exception {
		String result1 = searchResultWhole();
		String[] split1 = result1.split("of");
		String[] split2 = split1[0].split("-");
		String split3 = split2[1].replace(" ", "");
		finalCount = Integer.parseInt(split3);
		System.out.println("Total number of products displayed as per your search: " + finalCount);
	}

	public void amazonSearchResultList() throws Exception {

		WebElement phoneElement, rateElement;
		List<WebElement> phoneList = driver
				.findElements(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div"));
		for (int i = 1; i <= phoneList.size(); i++) {
			try {
				phoneElement = phoneList.get(i);
				rateElement = phoneList.get(i);
				String phoneName = phoneElement
						.findElement(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div["
								+ i + "]//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-2']"))
						.getText();
				String rate = rateElement
						.findElement(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']/div["
								+ i + "]//span[@class='a-price']"))
						.getText();
				System.out.println("The rate of " + phoneName + " is " + rate);

			} catch (Exception E) {

			}
		}

	}

	public void amazonResult() throws Exception {
		if (finalCount > 0) {
			System.out.println("The List of Products and prices of your search are:");
			amazonSearchResultList();
		} else {
			System.out.println("There are no products for your search");
		}
	}

	public void closeBrowser() throws Exception {
		Thread.sleep(5000);
		driver.close();
	}

	public static void main(String[] args) throws Exception {

		AmazonProducts amazon = new AmazonProducts();
		amazon.invokeBrowser();
		amazon.searchProduct("iphone");
		//amazon.searchResultWhole();
		amazon.resultCount();
		//amazon.amazonSearchResultList();
		amazon.amazonResult();
		amazon.closeBrowser();
	}
}
