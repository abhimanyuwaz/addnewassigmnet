package com.viavi;


//Java Program to Copy file using File Stream

//Importing input output classes
import java.io.*;

//Main Class
public class CopyFiles 
{

	// Main driver method
	public static void main(String[] args)
		throws IOException
	{

		// Creating two stream
		// one input and other output
		FileInputStream fis = null;
		FileOutputStream fos = null;

		// Try block to check for exceptions
		try {

			// Initializing both the streams with
			// respective file directory on local machine

			// Custom directory path on local machine
			String path="C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\Personnel\\News\\News";
			 
			File f1 = new File(path);  
			boolean bool = f1.mkdir();  
		      if(bool)
		      {  
		         System.out.println("Folder is created successfully"); 
		         
		      }
		      else
		      {  
		         System.out.println("Error Found!");  
		      }
			fis = new FileInputStream(
				"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\EAR 6 Data\\Report.xlsx");

			// Custom directory path on local machine
			fos = new FileOutputStream(
				"C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\Personnel\\News\\N\\N\\Report.xlsx");

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
				"copied the file successfully");
		}

		// Optional finally keyword but is good practice to
		// empty the occupied space is recommended whenever
		// closing files,connections,streams
		finally {

			// Closing the streams

			if (fis != null) {

				// Closing the fileInputStream
				fis.close();
			}
			if (fos != null) {

				// Closing the fileOutputStream
				fos.close();
			}
		}
	}
	
	public void createFolder(String Name) throws IOException
	{
		// one input and other output
				FileInputStream fis = null;
				FileOutputStream fos = null;

				// Try block to check for exceptions
				try {

					// Initializing both the streams with
					// respective file directory on local machine

					// Custom directory path on local machine
					//String path="C:\\Users\\waz78153\\OneDrive - Viavi Solutions Inc\\Desktop\\Folder Testing";
					//path=path+Name;
					File f1 = new File(Name);  
					boolean bool = f1.mkdir();  
				      if(bool)
				      {  
				         System.out.println("Folder is created successfully"); 
				         
				      }
				      else
				      {  
				         System.out.println("Folder is already there!");  
				      }
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
		
	}
}
