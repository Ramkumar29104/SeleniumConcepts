package ebay;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveBoxingDelegate;


public class EbayProductList {
	
	public WebDriver driver;
	public String url = "https://www.ebay.com/";
	String file = "./data/Ebay.xlsx";
	public String product, category;
	
	
	public void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		
		driver = new ChromeDriver();
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		 
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
	
	public void searchProduct(String product, String category) {
		WebElement search, searchBtn, elementCategory;
		
		search = driver.findElement(By.xpath("//input[@class='gh-tb ui-autocomplete-input']"));
		search.clear();
		search.sendKeys(product);
		
		elementCategory = driver.findElement(By.xpath("//select[@aria-label='Select a category for search']"));
		Select select = new Select(elementCategory);
		select.selectByVisibleText(category);
		
		searchBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		searchBtn.click();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}
	
	public void numberOfresult() {
		
		String results = driver.findElement(By.xpath("//h1[@class='srp-controls__count-heading']")).getText();
		
		String[] split1= results.split(" ");
		String split2 = split1[0].replace("+","").replace(",","");
		
		int result = Integer.parseInt(split2);
		System.out.println("Total number of results is " + result);
	}
	
	public void searchProductNamesAndPrices(String product) {
		
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		WebElement element;
		List<WebElement> results = driver.findElements(By.xpath("//div[@class='srp-river-results clearfix']//li[@class='s-item s-item__pl-on-bottom']"));
		for(int i=0;i<results.size();i++) {
			element = results.get(i);
			
			int x = element.getRect().getX();
			int y = element.getRect().getY();
			String cmd = String.format("window.scrollTo(%d,%d)",x,y);
			exe.executeScript(cmd);
			
			String phonename = element.findElement(By.xpath(".//span[@role='heading']")).getText();
			String[] phonename1 = phonename.split(" ");
			String mobile = phonename1[0].concat(" ").concat(phonename1[1]).concat(" ").concat(phonename1[2]);
			String rate = element.findElement(By.xpath(".//span[@class='s-item__price']")).getText();
			System.out.println("The name is " + mobile + " and the rate is " + rate);	
			excelwrite(file, product, i, 0, mobile);
			excelwrite(file, product, i, 1, rate);
		}	
	}
	
	
	
	public void excelSearchProducts() {
		
		try {
			
			FileInputStream read = new FileInputStream(file);
			
			XSSFWorkbook work = new XSSFWorkbook(read);
			XSSFSheet sheet = work.getSheet("Sheet1");
			XSSFRow row=null;
			XSSFCell cell=null;
			int rowCount = sheet.getLastRowNum();
			for(int i=1;i<=rowCount;i++) {
				row = sheet.getRow(i);
				int cellCount = row.getLastCellNum();
				for (int j=0;j<cellCount;j++) {
					if(j==0) {
						cell = row.getCell(j);
						product = cell.getStringCellValue();
					}
					else if(j==1) {
						cell = row.getCell(j);
						category = cell.getStringCellValue();
					}
				}
				searchProduct(product, category);
				searchProductNamesAndPrices(product);
			}
			work.close();
			read.close();
		}
		catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	
	public void excelwrite(String file, String sheetName,int row, int cell, String value) {
		
		InputStream read;
		XSSFWorkbook work;
		XSSFSheet sheet=null;
		XSSFRow iRow=null;
		XSSFCell iCell=null;
		
		try {
			read = new FileInputStream(file);
			work = new XSSFWorkbook(read);
			
			sheet = work.getSheet(sheetName);
			if (sheet == null) {
				work.createSheet(sheetName);
				sheet = work.getSheet(sheetName);
			}
			
			iRow = sheet.getRow(row);
			if(iRow==null) {
				sheet.createRow(row);
				iRow = sheet.getRow(row);
			}
			
			iCell = iRow.getCell(cell);
			if(iCell==null) {
				iRow.createCell(cell);
				iCell = iRow.getCell(cell);
			}
			
			iCell.setCellValue(value);
			
			FileOutputStream write = new FileOutputStream(file);
			work.write(write);
			write.close();
			
			work.close();
			read.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		EbayProductList ebay = new EbayProductList();
		ebay.invokeBrowser();
		ebay.getInfo();
		ebay.excelSearchProducts();
	}

}
