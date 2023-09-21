package appcompat;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Daily {
	
	public int n;
	WebDriver driver;
	public String url1 = "https://www.ebay.com/";
	public String url = "https://testtracker.googleplex.com/efforts/testplans/1597079"; 
	
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
		driver.get(url);
	}
	
	public void search() {
		Actions action = new Actions(driver);
		WebElement share = driver.findElement(By.xpath("//i[@class='material-icons prefix'][3]"));
		action.moveToElement(share).pause(Duration.ofSeconds(2)).click().build().perform();
		
	}
	
	public void getBugDetails() {
		try {
			WebElement bugElement; 
			List<WebElement> bugList = driver.findElements(By.xpath("//table[@id='bug_table']"));
				for(int i=1;i<=bugList.size();i++) {
					bugElement = bugList.get(i); 
					String bugId = bugElement.findElement(By.xpath("//table[@id='bug_table']//tr["+(i+1)+"]//td[2][@class='di-hover']")).getText();
					String bugPriority = bugElement.findElement(By.xpath("//table[@id='bug_table']//tr["+(i+1)+"]//td[1]")).getText();
					String bugDescription = bugElement.findElement(By.xpath("//table[@id='bug_table']//tr["+(i+1)+"]//td[4]")).getText();
					String bug = "b/" + bugId + " | " + bugPriority + " | " + bugDescription;
					System.out.println(bug);
				}
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void clickLink() {
		try {
			WebElement link;
			link = driver.findElement(By.xpath("(//td[@class='tooltipped'])[1]"));
			link.click();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		Daily daily = new Daily();
		daily.invokeBrowser();
		daily.navigateToUrl();
		//daily.getBugDetails();
		daily.clickLink();
		//daily.search();
	}

}
