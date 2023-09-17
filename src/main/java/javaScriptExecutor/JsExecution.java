package javaScriptExecutor;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JsExecution {
	
	public static WebDriver driver;
	public static String url = "https://www.amazon.in/";
	
	public static void invokeBrowser() throws Exception{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	public static void normalExecution() throws Exception{
		invokeBrowser();
		
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.location='https://www.google.com/'");
		System.out.println(exe.executeScript("return document.title"));
		System.out.println(exe.executeScript("return document.URL"));
	}
	
	public static void setBorder(WebDriver driver,WebElement element) {
		
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].style.border='3px solid blue';",element);
	}
	
	public static void jsLogin() throws Exception {
		invokeBrowser();
		
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		
		exe.executeScript("window.location = 'http://demo.guru99.com/test/newtours'");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			
		WebElement userName,password,submit;
		
		userName=driver.findElement(By.xpath("//input[@name='userName']"));
		setBorder(driver,userName);
		exe.executeScript("arguments[0].value='ram';",userName);
		
		password=driver.findElement(By.xpath("//input[@name='password']"));
		setBorder(driver,password);
		exe.executeScript("arguments[0].value='ram';",password);
		
		submit = driver.findElement(By.xpath("//input[@name='submit']"));
		setBorder(driver,submit);
		//exe.executeScript("arguments[0].style.border='3px solid red';",submit); //we can create common method and shall use wrapper class for this
		exe.executeScript("arguments[0].click()",submit);
		
	}
	
	public static void scrollUptoElement() throws Exception {
		
		invokeBrowser();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		
		WebElement aboutUs = driver.findElement(By.xpath("//a[text()='About Us']"));
		
		int x = aboutUs.getRect().getX();
		int y = aboutUs.getRect().getY();
		scrollWindowByX_Y(driver, x, y);
		setBorder(driver, aboutUs);
		//exe.executeScript("arguments[0].scrollIntoView(true)",aboutUs);
	}
	
	public static void scrollWindowByX_Y(WebDriver driver, int x, int y) {
		
		JavascriptExecutor exe= (JavascriptExecutor) driver;
		
		String cmd = String.format("window.scrollTo(%d,%d)",x,y);
		exe.executeScript(cmd);
	}

	
	public static void main(String[] args) throws Exception {
		
		JsExecution jsExe = new JsExecution();
		jsExe.normalExecution();
		//jsExe.jsLogin();
		jsExe.scrollUptoElement();
	}

}
