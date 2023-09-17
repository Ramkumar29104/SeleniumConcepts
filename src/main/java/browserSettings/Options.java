package browserSettings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Options {
	
	public WebDriver driver;
	public String url = "https://www.amazon.in/";
	
	public void invokeBrowser() throws Exception {
		
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("start-maximized","incognito"); //Thus we can change the setting of the browser
		opt.setAcceptInsecureCerts(true);
		//opt.setHeadless(true);      //As if this is in true, it wont show the driver but runs in the bg and gives the result
		
		driver = new ChromeDriver(opt);
		//driver.manage().window().maximize();
		driver.get(url);
	}
	
	
	public static void main(String[] args) throws Exception {
		
		Options option = new Options();
		option.invokeBrowser();

	}

}
