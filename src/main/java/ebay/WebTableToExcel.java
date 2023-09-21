package ebay;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebTableToExcel {
	
	public static WebDriver driver;
	public static String url = "https://www.w3schools.com/html/html_tables.asp";
	public static String file = "./data/table.xlsx";

	public static void invokeBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
		System.setProperty("webdriver.http.factory","jdk-http-client");
		
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		
		driver.manage().window().maximize();
		driver.get(url);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public static void webTableElementsInExcel() {
		
		String sheetName = "Sheet1";
		String heading,element;
		List<WebElement> headingElements = driver.findElements(By.xpath("//table[@class='ws-table-all']//tr//th"));
		List<WebElement> tableElements = driver.findElements(By.xpath("//table[@class='ws-table-all']//tr//td"));
		
		for(int i=0;i<headingElements.size();i++) {
			heading = headingElements.get(i).getText();
			System.out.println(heading);
			excelWrite(file, sheetName, 0, i, heading);
			int j=i;
			int k=1;
			while(j<tableElements.size()) {
				element = tableElements.get(j).getText();
				System.out.println(element);
				excelWrite(file, sheetName, k, i, element);
				j=j+3;
				k++;
			}
			System.out.println();
		}
	}
	
	public static void excelWrite(String file,String sheetName,int row, int cell,String value) {
	
		FileInputStream read;
		XSSFWorkbook work;
		XSSFSheet sheet = null;
		XSSFRow iRow = null;
		XSSFCell iCell = null;
		
		try {
			read = new FileInputStream(file);
			work = new XSSFWorkbook(read);
			
			sheet = work.getSheet(sheetName);
			if(sheet == null) {
				work.createSheet(sheetName);
				sheet = work.getSheet(sheetName);
			}
			
			iRow = sheet.getRow(row);
			if(iRow == null) {
				sheet.createRow(row);
				iRow = sheet.getRow(row);
			}
			
			iCell = iRow.getCell(cell);
			if(iCell == null) {
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
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		WebTableToExcel web = new WebTableToExcel();
		web.invokeBrowser();
		web.webTableElementsInExcel();

	}
}


