package com.viavi;

import com.agile.api.AgileSessionFactory;
import com.agile.api.IAgileSession;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.IUser;
import com.agile.api.UserConstants;



	import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile 
{

    public void readExcelItem(String filePath,String fileName,String sheetName) throws IOException
    
    {

    	try {
    //Create an object of File class to open xlsx file

    		//File file =    new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\ItemandParts.xlsx");
    		System.out.println("Line 40");
    		File file =    new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\28March\\ItemData.xlsx");
    		
    		System.out.println("Line 41");
//    	   File file =    new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\Escalation\\FinalEscalationDataCode.xlsx");
    		//
 
    		OracleCon oc= new OracleCon();
 
    		oc.connection();
    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook escalationWorkbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));

    //Check condition if the file is xlsx file

    if(fileExtensionName.equals(".xlsx"))
    {
    escalationWorkbook = new XSSFWorkbook(inputStream);
    System.out.println(" Escalation Workbook Name : "+escalationWorkbook);
    }
     else if(fileExtensionName.equals(".xls"))
    {
    	 escalationWorkbook = new HSSFWorkbook(inputStream);
    }

    Sheet escalationSheet = escalationWorkbook.getSheet(sheetName);
    
        
   System.out.println("Escalaton Sheet "+escalationSheet);
    System.out.println(" Printing Size "+escalationSheet.getLastRowNum());
    System.out.println(escalationSheet.getLastRowNum());
    int rowCount = escalationSheet.getLastRowNum()-escalationSheet.getFirstRowNum();

    String itenname="",filename="";
    System.out.println("Line 78");
    System.out.println("Row Count "+rowCount);
for (int i = 0; i < rowCount+1; i++) 
   //for (int i = 0; i < 15; i++) 
    {
System.out.println("Line 82");
        Row row = escalationSheet.getRow(i);
        System.out.println("Line 83");
        for (int j = 0; j < row.getLastCellNum(); j++) 
        {
    	
           if ((j==0 )&& (i!=0)) 
           {
        	   System.out.println("Line 90");
        	   itenname=row.getCell(j).toString();
        	   
        	   String s=row.getCell(j).toString();
        	   if(s.contains("E")||((s.contains(".0"))))
        	   {
        		   
        		   Double doug= Double.parseDouble(row.getCell(j).toString());
            	   System.out.println(doug+" Row :"+row.getCell(j));
            	   Cell nextCell=row.getCell(j);
            	 
            	   System.out.println( " bhai bhai "+row.getCell(j).getNumericCellValue());
            	   
            	   double data=row.getCell(j).getNumericCellValue();
            	   BigDecimal bd = new BigDecimal(data);
            	   
            	   long val=bd.longValue();
            	   
            	   System.out.println("Value in i "+i+" Integer Value:  "+val);
                  
            	    System.out.println("========================== Record  =======================================");
                    System.out.println("Item Name :"+itenname+" Record Count : "+i);      
                    oc.getPathItems(String.valueOf(val)  ,i);
        		   
        	   }
        	   else
        	   {
        		   
        		   System.out.println("Value in i "+i+" Valuee:  "+s);
                   
        		   System.out.println("========================== Record  =======================================");
                   System.out.println("Item Name :"+itenname+" Record Count : "+i);      
                   oc.getPathItems(s,i);
        		   
        	   }
        	   
        	   
           }
           
           if ( (j==3 ) && ( i!=0) )  
           {
        	    //System.out.print(row.getCell(j).getStringCellValue()+" ");
        	   // filename=row.getCell(j).getStringCellValue();
        	
        	    
        	    
           
           }
                
        }
        
        System.out.println();
    
        }

OracleCon.closeItem();

           }
           catch(Exception e)
           {
        	   e.printStackTrace();
           }
                
           
        }

  
   public void readExcelChange(String filePath,String fileName,String sheetName) throws IOException
    
    {

    	try 
    	{
    		//Create an object of File class to open xlsx file

    	//	File file =    new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\ChangesEAR.xls"); 
    		
    		File file =    new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\28March\\ChangesData.xlsx");
    		
    		OracleCon oc= new OracleCon();
    		oc.connection();
    		
    //Create an object of FileInputStream class to read excel file

    FileInputStream inputStream = new FileInputStream(file);

    Workbook escalationWorkbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));


    if(fileExtensionName.equals(".xlsx"))
    {
    escalationWorkbook = new XSSFWorkbook(inputStream);
    System.out.println(" Escalation Workbook Name : "+escalationWorkbook);
    }
     else if(fileExtensionName.equals(".xls"))
    {
    	 escalationWorkbook = new HSSFWorkbook(inputStream);
    }

    Sheet escalationSheet = escalationWorkbook.getSheet(sheetName);
    int rowCount = escalationSheet.getLastRowNum()-escalationSheet.getFirstRowNum();

    String changename="",filename="";
for (int i = 0; i < rowCount+1; i++) 
    {

        Row row = escalationSheet.getRow(i);
        for (int j = 0; j < row.getLastCellNum(); j++) 
        {
    	
           if ((j==0 )&& (i!=0)) 
           {
        	   changename=row.getCell(j).toString();
               System.out.println( row.getCell(j));
        	    System.out.println("========================== Record  =======================================");
                System.out.println("Change Name :"+changename+" Record Count : "+i);
                oc.getPathChanges(changename, i);
           }
           
           if ( (j==3 ) && ( i!=0) )  
           {
        	      
    
           }
                
        }
        
        System.out.println();
    
        }

OracleCon.closeChange();

           }
           catch(Exception e)
           {
        	  e.printStackTrace();
           }
                
           
        }

    //Main function is calling readExcel function to read data from excel file

    public static void main(String...strings) throws IOException
    {

    //Create an object of ReadGuru99ExcelFile class

    ReadExcelFile objExcelFile = new ReadExcelFile();
    System.out.println("Line 200 :");
    
    objExcelFile.readExcelItem("  ","ItemData.xlsx","Sheet1");
  
   objExcelFile.readExcelChange("  ","ChangesData.xlsx","Sheet0");
    //Prepare the path of excel file
    
    System.out.println("Like 204");
    //String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";

    //Call read file method of the class to read data

    
    
    }

}


