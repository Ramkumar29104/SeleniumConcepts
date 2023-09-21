package demo;

import org.openqa.selenium.By;

import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	public static void main(String[] args) {
		
		//System.setProperty("webdriver.chrome.driver", "/home/ramkumarra/Documents/eclipse-java-2022-06-R-linux-gtk-x86_64/chromedriver_linux64/chromedriver");
		WebDriver driver =new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys("9443861046");
		driver.findElement(By.id("pass")).sendKeys("sonicmaster");
		driver.findElement(By.name("login")).click();
	}
}