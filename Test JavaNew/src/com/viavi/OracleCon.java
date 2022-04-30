package com.viavi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.TreeMap;

import com.agile.api.UserConstants;

class OracleCon{

	static Connection con;	
	static Map<String, Object[]> Data;
	static Map<String, Object[]> ChangeData;
	static int count=2;
	CopyFiles cp= new CopyFiles();
	String loc,path="";
	FileInputStream fis = null;
	FileOutputStream fos = null;
public  static void  connection() 
{
	try
	{
		 Data
		= new TreeMap<String, Object[]>();
		Data.put(
				"1",
			new Object[] { "Count NO","Item Name ","Location","Path" });
		

		ChangeData
		= new TreeMap<String, Object[]>();
		Data.put(
				"1",
			new Object[] { "Count NO","Change Name ","Location","Path" });
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");

		 //con=DriverManager.getConnection("jdbc:oracle:thin:@dendboradev02.ds.jdsu.net:7114:ORAAGLU","agile","Agiletst#936");
		 
		con=DriverManager.getConnection("jdbc:oracle:thin:@ca2db12ora01.ds.jdsu.net:7101:ORAPAGL","Dpadm","Ag1L34Pr0D1");
		 
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
}

public  static void  closeItem() 
{
	try
	{
		WriteDataToExcel wd=new WriteDataToExcel();
		
		wd.excelOperationItem(Data);
	

		con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
}

public  static void  closeChange() 
{
	try
	{
		WriteDataToExcel wd=new WriteDataToExcel();
		wd.excelOperationChange(ChangeData);

		con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
}



public void getPathItems(String itemname, int i) throws IOException
{
	try{
		
		loc="";
		//i=i+1;
		//System.out.println(con);
		//System.out.println(filename);

		//PreparedStatement  stmt=con.prepareStatement("select * from AGILE.file_info where file_id in (select id from agile.files where filename =?)");
		
		//PreparedStatement  stmt=con.prepareStatement("select * from file_info where file_id in (select id from files where id in( select file_id from version_file_map where version_id in(select latest_vsn from attachment_map where parent_id in (select item from Rev where rev.item in (select id from item where item.item_number=?)))))");
		PreparedStatement  stmt=con.prepareStatement("select * from agile.file_info where file_id in (select id from agile.files where id in( select file_id from agile.version_file_map where version_id in(select latest_vsn from agile.attachment_map where parent_id in (select item from agile.Rev where rev.item in (select id from agile.item where item.item_number=?)))))");
		//Item and File version, attachment MAP
		stmt.setString(1,itemname);
		
		//System.out.println(stmt);

		ResultSet rs=stmt.executeQuery();
		ResultSet rs1;
		rs1=rs;
		//System.out.println(rs1.next());

		//if(rs1.next())
		{
			while(rs.next())
			{
			//System.out.println(rs.getInt(1)+"  ");
				//System.out.print(itemname+"  "+filename+" "+rs.getString(5));
			
			//System.out.println(rs.getString(7));
			//System.out.println("result set"+rs.getString(7));
			//System.out.println(rs);;
			Data.put(Integer.toString(count)  , new Object[] {Integer.toString(count),itemname,rs.getString(5),rs.getString(7) });
			System.out.println(count);			
			System.out.println("Count No "+Integer.toString(i)+" Item Name "+itemname+" Location Name "+rs.getString(5)+" File Name "+rs.getString(7));
			
			String filename =rs.getString(7);
			System.out.println("Line 94");
			String s[]=filename.split("[\\\\/]");
			if(s.length==0)
			{
				//String s1[]=filename.split("\");
				//s=s1;
			}
			
//			System.out.println("Printing the File Name");
//			loc="C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data";
//			System.out.println("File Name :"+filename);
//			path=filename;
//			for (int j=0;j <s.length-1;j++)
//			{
//				System.out.println("creating Folder");
//				loc=loc+"\\"+s[j];
//				System.out.println("Sub String :"+s[j]);
//				cp.createFolder(loc);
//				System.out.println(loc);
//			}
//			fis = new FileInputStream(
//					"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\Report.xlsx");
//
//				// Custom directory path on local machine
//				fos = new FileOutputStream(
//					"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\"+path);
//
//			int c;
//
//			// Condition check
//			// Reading the input file till there is input
//			// present
//			while ((c = fis.read()) != -1)
//			{
//
//				// Writing to output file of the specified
//				// directory
//				fos.write(c);
//			}
//
//			// By now writing to the file has ended, so
//
//			// Display message on the consolecount;
			count++;
			
		}
		}

		// Optional finally keyword but is good practice to
		// empty the occupied space is recommended whenever
		// closing files,connections,streams
		
			
			//System.out.println("Data");
		
		//else
		{
		
			//Data.put(Integer.toString(count)  , new Object[] {Integer.toString(count),itemname, " Blank ","Blank " });
//			System.out.println(count);	
//			System.out.println("Count No "+Integer.toString(count)+" Item Name "+itemname+" Location Name "+"Blank"+" File Name "+"Blank");
//			count++;
		
		}
			
	}
	catch(Exception e)
		{ 

			System.out.println("Exception Occureed : ");
			e.printStackTrace();
			
		}
	finally 
	{

		// Closing the streams

//		if (fis != null) 
//		{
//
//			// Closing the fileInputStream
//			fis.close();
//		}
//		
//		if (fos != null) 
//		{
//
//			// Closing the fileOutputStream
//			fos.close();
//		}
	}
		
	//	count++;
		
		}
public void getPathChanges(String changenumber, int i) throws IOException
{
	try{
		
		loc="";
		//i=i+1;
		//System.out.println(con);
		//System.out.println(filename);

		//PreparedStatement  stmt=con.prepareStatement("select * from AGILE.file_info where file_id in (select id from agile.files where filename =?)");
		
		//PreparedStatement  stmt=con.prepareStatement("select * from file_info where file_id in ( select id from files where id in( select file_id from version_file_map where version_id in(select latest_vsn from attachment_map where parent_id in (select id from change where change_number=?))))");
		PreparedStatement  stmt=con.prepareStatement("select * from agile.file_info where file_id in ( select id from agile.files where id in( select file_id from agile.version_file_map where version_id in(select latest_vsn from agile.attachment_map where parent_id in (select id from agile.change where change_number=?))))");
		
		//Item and File version, attachment MAP
		stmt.setString(1,changenumber);
		
		//System.out.println(stmt);

		ResultSet rs=stmt.executeQuery();
		ResultSet rs1;
		rs1=rs;
		//System.out.println(rs1.next());

		//if(rs1.next())
		{
			while(rs.next())
			{
				
				
			//System.out.println(rs.getInt(1)+"  ");
				//System.out.print(itemname+"  "+filename+" "+rs.getString(5));
			
			//System.out.println(rs.getString(7));
			//System.out.println("result set"+rs.getString(7));
			//System.out.println(rs);;
			
				
			ChangeData.put(Integer.toString(count)  , new Object[] {Integer.toString(count),changenumber,rs.getString(5),rs.getString(7) });
			System.out.println(count);			
			System.out.println("Count No "+Integer.toString(i)+" Change Name "+changenumber+" Location Name "+rs.getString(5)+" File Name "+rs.getString(7));
			
			String filename =rs.getString(7);
//			System.out.println("Line 94");
//			
//			String s[]=filename.split("[\\\\/]");
//			if(s.length==0)
//			{
//				//String s1[]=filename.split("\");
//				//s=s1;
//			}
//			
//			System.out.println("Printing the File Name");
//			loc="C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data";
//			System.out.println("File Name :"+filename);
//			path=filename;
//			for (int j=0;j <s.length-1;j++)
//			{
//				System.out.println("creating Folder");
//				loc=loc+"\\"+s[j];
//				System.out.println("Sub String :"+s[j]);
//				cp.createFolder(loc);
//				System.out.println(loc);
//			}
//			fis = new FileInputStream(
//					"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\ReportChanges.xlsx");
//
//				// Custom directory path on local machine
//				fos = new FileOutputStream(
//					"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\"+path);
//
//			int c;
//
//			// Condition check
//			// Reading the input file till there is input
//			// present
//			while ((c = fis.read()) != -1)
//			{
//
//				// Writing to output file of the specified
//				// directory
//				fos.write(c);
//			}
//
//			// By now writing to the file has ended, so
//
//			// Display message on the console
//			System.out.println(
//				"copied the file successfully");
			count++;
		}
		}

		// Optional finally keyword but is good practice to
		// empty the occupied space is recommended whenever
		// closing files,connections,streams
		
			
			//System.out.println("Data");
		
		//else
		{
		
			//Data.put(Integer.toString(count)  , new Object[] {Integer.toString(count),itemname, " Blank ","Blank " });
//			System.out.println(count);	
//			System.out.println("Count No "+Integer.toString(count)+" Item Name "+itemname+" Location Name "+"Blank"+" File Name "+"Blank");
			
		
		}
			
	}
	catch(Exception e)
		{ 

			System.out.println("Exception Occureed : ");
			e.printStackTrace();
			
		}
	finally 
	{

		// Closing the streams

//		if (fis != null) 
//		{
//
//			// Closing the fileInputStream
//			fis.close();
//		}
//		
//		if (fos != null) 
//		{
//
//			// Closing the fileOutputStream
//			fos.close();
//		}
	}
		
		//count++;
		
		}



public static void main(String args [])
{
	//OracleCon oc= new OracleCon();
//	oc.connection();
	//oc.getPath("10000008917", "6133_D0_3515AR_MaintenanceManual.pdf"
//, 1);
//	oc.close();
	//String filename="001\\653\\514\\agile165351442.pdf";
	String filename="001/653/514/agile165351442.pdf";

	
	System.out.println(filename);
	String sp[]=filename.split("[\\\\/]");

//System.out.println(sp.length);


for (int i=0;i<sp.length;i++)
{
	System.out.println(sp[i]+i);
}


}
}