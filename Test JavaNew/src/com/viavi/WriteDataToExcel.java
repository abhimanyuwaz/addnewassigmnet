package com.viavi;



import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.agile.api.APIException;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WriteDataToExcel 
{

	public void excelOperationItem(Map<String, Object[]> studentData)
	{
		try {
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet
			= workbook.createSheet(" User Data ");

		// creating a row object
		XSSFRow row;

		// This data needs to be written (Object[])
		if(studentData.size()>0)
		{
			Set<String> keyid = studentData.keySet();

			int rowid = 0;

			// writing the data into the sheets...

			for (String key : keyid) {

				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = studentData.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
				//	System.out.println(obj);
					cell.setCellValue((String)obj);
				}
			}

			// .xlsx is the format for Excel Sheets...
			// writing the workbook into the file...
			FileOutputStream out = new FileOutputStream(
				new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\28March\\ReportItemsNew.xlsx"));
			
			workbook.write(out);
			out.close();
		}
		else
		{
			System.out.println("Empty Data Cell");
		}
		}
		catch(Exception e)
		{
			System.out.println(" Exception in the Excel File Operation :" +e);
		}
		
		
	}
	
	
	public void excelOperationChange(Map<String, Object[]> studentData)
	{
		try {
		XSSFWorkbook workbook = new XSSFWorkbook();

		// spreadsheet object
		XSSFSheet spreadsheet
			= workbook.createSheet(" User Data ");

		// creating a row object
		XSSFRow row;

		// This data needs to be written (Object[])
		if(studentData.size()>0)
		{
			Set<String> keyid = studentData.keySet();

			int rowid = 0;

			// writing the data into the sheets...

			for (String key : keyid) {

				row = spreadsheet.createRow(rowid++);
				Object[] objectArr = studentData.get(key);
				int cellid = 0;

				for (Object obj : objectArr) {
					Cell cell = row.createCell(cellid++);
				//	System.out.println(obj);
					cell.setCellValue((String)obj);
				}
			}

			// .xlsx is the format for Excel Sheets...
			// writing the workbook into the file...
			FileOutputStream out = new FileOutputStream(
				new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\28March\\ReportChanges.xlsx"));
			
			workbook.write(out);
			out.close();
		}
		else
		{
			System.out.println("Empty Data Cell");
		}
		}
		catch(Exception e)
		{
			System.out.println(" Exception in the Excel File Operation :" +e);
		}
		
		
	}
	
	// any exceptions need to be caught
	public static void main(String[] args) throws Exception
	{
		// workbook object
	
	}
}
