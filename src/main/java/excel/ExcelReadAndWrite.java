package excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReadAndWrite {
	
	String file = "./data/Ebay.xlsx";
	static String file1 = "./data/Eb.xlsx";
	FileInputStream read;
	
	public void excelread() {
		
		try {
			read = new FileInputStream(file);
			XSSFWorkbook work = new XSSFWorkbook(read);
			XSSFSheet sheet = work.getSheet("Sheet1");
			XSSFRow row;
			XSSFCell cell;
			int rowCount = sheet.getLastRowNum();
			for(int i=0;i<=rowCount;i++) {
				row = sheet.getRow(i);
				int cellCount = row.getLastCellNum();

				for (int j=0;j<cellCount;j++) {
					cell=row.getCell(j);
					CellType cellType = cell.getCellType();
					
					switch(cellType) {
					case NUMERIC:
						System.out.println(cell.getNumericCellValue());
						break;
					case STRING:
						System.out.println(cell.getStringCellValue());
						break;
					default:
						System.out.println(cell.getRawValue());
						break;
					}
				}
				
			}
			work.close();
			read.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void excelwrite(String file, String sheetName,int row, int cell, String value) {
		
		InputStream read;
		XSSFWorkbook work;
		XSSFSheet sheet;
		Row iRow;
		Cell iCell;
		try {
			read = new FileInputStream(file1);
			work = new XSSFWorkbook(read);
			
			sheet = work.getSheet(sheetName);
			if(sheet==null) {
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
			
			OutputStream write = new FileOutputStream(file1);
			work.write(write);
			
			write.close();
			
			work.close();
			read.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		ExcelReadAndWrite excel = new ExcelReadAndWrite();
		excel.excelread();
		excel.excelwrite(file1, "product", 1, 1, "item");
	}

}
