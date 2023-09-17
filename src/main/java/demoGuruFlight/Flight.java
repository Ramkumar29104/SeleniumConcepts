package demoGuruFlight;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.github.dockerjava.api.model.Frame;

public class Flight {
	
	public WebDriver driver;
	public int finalCount;
	public String url = "https://demo.guru99.com/test/newtours/reservation.php";
	
	public void invokeBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}
	
	public void flightBooking() throws Exception {
		
		WebElement frame1 = driver.findElement(By.xpath("//table[@bordercolor='#000000']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//a[@href='http://demo.guru99.com/test/newtours/']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Flights']")).click();
		
		WebElement frame2= driver.findElement(By.xpath("//form[@name='findflight']"));
		driver.switchTo().frame(frame2);
		
		WebElement radio,passengerDD,departingDD,arrivingDD,onMonthDD,onDayDD,returnMonthDD,returnDayDD,airlineDD;
		radio = driver.findElement(By.xpath("//font[@face='Arial, Helvetica, sans-serif']/input"));
		Select select1 = new Select(radio);
		select1.selectByVisibleText("oneway");
		
		passengerDD = driver.findElement(By.xpath("//select[@name='passCount']"));
		Select select2 = new Select(passengerDD);
		select2.selectByVisibleText("4");
		
		departingDD = driver.findElement(By.xpath("//select[@name='fromPort']"));
		Select select3 = new Select(departingDD);
		select3.selectByVisibleText("London");
		
		arrivingDD = driver.findElement(By.xpath("//select[@name='toPort']"));
		Select select4 = new Select(arrivingDD);
		select4.selectByVisibleText("Zurich");
		
		onMonthDD = driver.findElement(By.xpath("//select[@name='fromMonth']"));
		Select select5 = new Select(onMonthDD);
		select5.selectByVisibleText("April");
		
		onDayDD = driver.findElement(By.xpath("//select[@name='fromDay']"));
		Select select6 = new Select(onDayDD);
		select6.selectByVisibleText("10");
		
		returnMonthDD = driver.findElement(By.xpath("//select[@name='ToMonth']"));
		Select select7 = new Select(returnMonthDD);
		select7.selectByVisibleText("October");
		
		returnDayDD = driver.findElement(By.xpath("//select[@name='ToDay']"));
		Select select8 = new Select(returnDayDD);
		select8.selectByVisibleText("29");
		
		radio = driver.findElement(By.xpath("//font[@face='Arial, Helvetica, sans-serif']/input"));
		Select select9 = new Select(returnDayDD);
		select9.selectByVisibleText("Economy class");
		
		airlineDD = driver.findElement(By.xpath("//select[@name='airline']"));
		Select select10 = new Select(airlineDD);
		select10.selectByVisibleText("Unified Airlines");
		
	}
	
	
	

	public static void main(String[] args) throws Exception {
		Flight flight = new Flight();
		flight.invokeBrowser();
		flight.flightBooking();
	}

}
