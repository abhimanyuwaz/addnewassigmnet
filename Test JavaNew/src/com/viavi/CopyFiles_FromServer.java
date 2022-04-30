package com.viavi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopyFiles_FromServer 
{

	
public void readExcel(String filePath,String fileName,String sheetName) throws IOException
    
    {

    	try {
    //Create an object of File class to open xlsx file

    		File file =    new File("C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\ReportChanges.xlsx"); 
    		//OracleCon oc= new OracleCon();
    		//oc.connection();
    //Create an object of FileInputStream class to read excel file
System.out.println("Line 30");
    FileInputStream inputStream = new FileInputStream(file);

    Workbook escalationWorkbook = null;

    //Find the file extension by splitting file name in substring  and getting only extension name

    String fileExtensionName = fileName.substring(fileName.indexOf("."));


    if(fileExtensionName.equals(".xlsx"))
    {
    escalationWorkbook = new XSSFWorkbook(inputStream);
    }
  
     else if(fileExtensionName.equals(".xls"))
    {
    	 escalationWorkbook = new HSSFWorkbook(inputStream);
    }

    Sheet escalationSheet = escalationWorkbook.getSheet(sheetName);

   
    

    System.out.println(escalationSheet.getFirstRowNum());

    System.out.println(escalationSheet.getLastRowNum());
    int rowCount = escalationSheet.getLastRowNum()-escalationSheet.getFirstRowNum();
    String changename="",filenamepath="";
    

    
//for (int i = 1; i < rowCount+1; i++) 
	for (int i = 1; i < 3; i++) 
    {
    System.out.println("========================== Record  =======================================");

        Row row = escalationSheet.getRow(i);
        for (int j = 0; j < row.getLastCellNum(); j++) 
        {
        	// System.out.print(j+" "+ row.getCell(j));
    	
    	
           if ((j==1)&& (i!=0)) 
           {
        	   changename=row.getCell(j).toString();
        	 //  System.out.print(" Change Name  "+changename);
             //  System.out.println( row.getCell(j));
        
              //  System.out.println("Change Name :"+changename+" Record Count : "+i);
                //oc.getPathChanges(changename, i);
           }
           
           if ( (j==3 ) && ( i!=0) )  
           {
        	   filenamepath=row.getCell(j).toString();
        	  // System.out.print(" File Name :"+ filename);
           }
                
        }
        
        CopyFiles_FromServer cf= new CopyFiles_FromServer();
        System.out.println("File Name and Chnage Name :"+changename+" : "+filenamepath);
        cf.copyFiles(filenamepath, changename);
        System.out.println();
    
        }

//OracleCon.closeChange();

           }
           catch(Exception e)
           {
        	   System.out.println(e);
           }
                
           
        }

    //Main function is calling readExcel function to read data from excel file


public void copyFiles(String filename,String objectname)
{
	
	try 
	{
	CopyFiles cp= new CopyFiles();
	
	System.out.println("Line 119 File Name : "+filename+":!!!!!!!!!!!!");
	
	String s[]=filename.split("[\\\\/]");
	if(s.length==0)
	{
		
		System.out.println("File Name is Empty: ");
	}	
	System.out.println("Printing the File Name");
	String loc="C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\New\\";
	System.out.println("File Name :"+filename+s.length+s[0]);
	String path=filename;
	
	for (int j=0;j <s.length-1;j++)
	{
		System.out.println("creating Folder");
		loc=loc+"\\"+s[j];
		System.out.println("Sub String :"+s[0]);
		cp.createFolder(loc);
		System.out.println(loc);
	}
	
	FileInputStream	fis = new FileInputStream(
			"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\ReportChanges.xlsx");

		// Custom directory path on local machine
	FileOutputStream		fos = new FileOutputStream(
			"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\New\\"+path);

	int c;

	// Condition check
	// Reading the input file till there is input
	// present
	while ((c = fis.read()) != -1)
	{
	
		// Writing to output file of the specified
		// directory
		fos.write(c);
	
	}

	// By now writing to the file has ended, so

	// Display message on the console
	
	System.out.println(
		"copied the file successfully ");
	System.out.println("Object Name :"+objectname+" File Name "+filename);
	
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
}
    public static void main(String...strings) throws IOException
    {

    //Create an object of ReadGuru99ExcelFile class

    	CopyFiles_FromServer cpf = new CopyFiles_FromServer();
    	cpf.readExcel("  ","ReportChanges.xlsx","User Data");
    	
    //Prepare the path of excel file

    //String filePath = System.getProperty("user.dir")+"\\src\\excelExportAndFileIO";

    //Call read file method of the class to read data

    
    
    }
}
